package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.converters.DateFormatConverter;
import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.repository.GeneralJournalDetailRepository;
import lk.quantacom.smarterpbackend.repository.GeneralJournalHeaderRepository;
import lk.quantacom.smarterpbackend.repository.GeneralJournalReverseRepository;
import lk.quantacom.smarterpbackend.repository.LadgerAccountRepository;
import lk.quantacom.smarterpbackend.service.GeneralJournalHeaderService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.utils.JDBC;
import lk.quantacom.smarterpbackend.utils.RunningNumberFormatUtils;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GeneralJournalHeaderServiceImpl implements GeneralJournalHeaderService {

    protected final String JournalNumberFormat = "@GJ@SEQ(1,6)"; //See the lk.quantacom.smarterpbackend.utils.RunningNumberFormatUtils

    @Autowired
    private GeneralJournalHeaderRepository generalJournalHeaderRepository;

    @Autowired
    private GeneralJournalDetailRepository generalJournalDetailRepository;

    @Autowired
    private GeneralJournalReverseServiceImpl generalJournalReverseService;

    @Autowired
    private LadgerAccountRepository ladgerAccountRepository;


    @Override
    public GeneralJournalHeaderDocumentNumberResponse getNextDocumentNumber() {
        //String _journalNumberFormat = this.JournalNumberFormat;
        String _maxJournalNumber = generalJournalHeaderRepository.getMaximumJournalNumber();
        String _journalNumber = RunningNumberFormatUtils.FormatRunningNumber(_maxJournalNumber, this.JournalNumberFormat);

        GeneralJournalHeaderDocumentNumberResponse documentNumberResponse = new GeneralJournalHeaderDocumentNumberResponse();
        documentNumberResponse.setGeneratedDate(new Date());
        documentNumberResponse.setDocumentNumber(_journalNumber);
        documentNumberResponse.setIsDisplayOnly(true);

        return documentNumberResponse;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public GeneralJournalHeaderResponse save(GeneralJournalHeaderRequest headerRequest) {

        //String _journalNumberFormat = this.JournalNumberFormat;
        //String _maxJournalNumber = generalJournalHeaderRepository.getMaximumJournalNumber();
        //String _journalNumber = RunningNumberFormatUtils.FormatRunningNumber(_maxJournalNumber, this.JournalNumberFormat);

        String _journalNumber = this.getNextDocumentNumber().getDocumentNumber();

        GeneralJournalHeader generalJournalHeader = new GeneralJournalHeader();

        BranchNetwork branch = new BranchNetwork();
        branch.setId(headerRequest.getBranchId());
        generalJournalHeader.setBranch(branch);

        generalJournalHeader.setJournalNumber(_journalNumber);
        generalJournalHeader.setTransactionDate(headerRequest.getTransactionDate() == null ? null : ConvertUtils.convertStrToDate(headerRequest.getTransactionDate()));
        generalJournalHeader.setRemark(headerRequest.getRemark());
        generalJournalHeader.setTransactionDetail(headerRequest.getTransactionDetail());
        generalJournalHeader.setIsDeleted(Deleted.NO);
        generalJournalHeader.setTotalDebit(_CalculateTotalDebit(headerRequest.getJournalDetails()));
        generalJournalHeader.setTotalCredit(_CalculateTotalCredit(headerRequest.getJournalDetails()));

        GeneralJournalHeader header = generalJournalHeaderRepository.save(generalJournalHeader);

        //Setting details to saved header make infinite loop
        generalJournalHeader = new GeneralJournalHeader();
        generalJournalHeader.setBranch(header.getBranch());
        generalJournalHeader.setJournalNumber(header.getJournalNumber());
        generalJournalHeader.setTransactionDate(header.getTransactionDate());
        generalJournalHeader.setRemark(header.getRemark());
        generalJournalHeader.setTransactionDetail(header.getTransactionDetail());
        generalJournalHeader.setTotalDebit(header.getTotalDebit());
        generalJournalHeader.setTotalCredit(header.getTotalCredit());
        generalJournalHeader.setId(header.getId());
        generalJournalHeader.setCreatedBy(header.getCreatedBy());
        generalJournalHeader.setCreatedDateTime(header.getCreatedDateTime());
        generalJournalHeader.setModifiedBy(header.getModifiedBy());
        generalJournalHeader.setModifiedDateTime(header.getModifiedDateTime());
        generalJournalHeader.setIsDeleted(header.getIsDeleted());
        generalJournalHeader.setGeneralJournalDetails(new ArrayList<>());


        List<GeneralJournalDetail> details = new ArrayList<>();
        for (GeneralJournalDetailRequest detailRequest : headerRequest.getJournalDetails()) {
            GeneralJournalDetail generalJournalDetail = new GeneralJournalDetail();
            generalJournalDetail.setGeneralJournalHeader(header);
            generalJournalDetail.setJournalNumber(header.getJournalNumber());

            generalJournalDetail.setLineNumber(detailRequest.getLineNumber());

            LadgerAccount ladgerAccount = ladgerAccountRepository.getById(detailRequest.getAccountId());
            generalJournalDetail.setLadgerAccount(ladgerAccount);

            generalJournalDetail.setDebit(detailRequest.getDebit());
            generalJournalDetail.setCredit(detailRequest.getCredit());
            generalJournalDetail.setIsDeleted(Deleted.NO);
            GeneralJournalDetail detail = generalJournalDetailRepository.save(generalJournalDetail);
            details.add(detail);
        }
        generalJournalHeader.setGeneralJournalDetails(details);

        return convert(generalJournalHeader);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public GeneralJournalHeaderResponse update(GeneralJournalHeaderUpdateRequest headerRequest) {

        GeneralJournalHeader generalJournalHeader = generalJournalHeaderRepository.findById(headerRequest.getId()).orElse(null);
        if (generalJournalHeader == null) {
            return null;
        }

        BranchNetwork branch = new BranchNetwork();
        branch.setId(headerRequest.getBranchId());
        generalJournalHeader.setBranch(branch);

        //generalJournalHeader.setJournalNumber(headerRequest.getJournalNumber());
        generalJournalHeader.setTransactionDate(headerRequest.getTransactionDate() == null ? null : ConvertUtils.convertStrToDate(headerRequest.getTransactionDate()));
        generalJournalHeader.setRemark(headerRequest.getRemark());
        generalJournalHeader.setTransactionDetail(headerRequest.getTransactionDetail());
        generalJournalHeader.setIsDeleted(Deleted.NO);
        generalJournalHeader.setTotalDebit(_CalculateTotalDebit_Update(headerRequest.getJournalDetails()));
        generalJournalHeader.setTotalCredit(_CalculateTotalCredit_Update(headerRequest.getJournalDetails()));

        GeneralJournalHeader header = generalJournalHeaderRepository.save(generalJournalHeader);

        //Setting details to saved header make infinite loop
        generalJournalHeader = new GeneralJournalHeader();
        generalJournalHeader.setBranch(header.getBranch());
        generalJournalHeader.setJournalNumber(header.getJournalNumber());
        generalJournalHeader.setTransactionDate(header.getTransactionDate());
        generalJournalHeader.setRemark(header.getRemark());
        generalJournalHeader.setTransactionDetail(header.getTransactionDetail());
        generalJournalHeader.setTotalDebit(header.getTotalDebit());
        generalJournalHeader.setTotalCredit(header.getTotalCredit());
        generalJournalHeader.setId(header.getId());
        generalJournalHeader.setCreatedBy(header.getCreatedBy());
        generalJournalHeader.setCreatedDateTime(header.getCreatedDateTime());
        generalJournalHeader.setModifiedBy(header.getModifiedBy());
        generalJournalHeader.setModifiedDateTime(header.getModifiedDateTime());
        generalJournalHeader.setIsDeleted(header.getIsDeleted());
        generalJournalHeader.setGeneralJournalDetails(new ArrayList<>());


        List<GeneralJournalDetail> details = new ArrayList<>();
        for (GeneralJournalDetailUpdateRequest detailRequest : headerRequest.getJournalDetails()) {
            GeneralJournalDetail generalJournalDetail = new GeneralJournalDetail();
            generalJournalDetail.setGeneralJournalHeader(header);
            generalJournalDetail.setJournalNumber(header.getJournalNumber());

            generalJournalDetail.setLineNumber(detailRequest.getLineNumber());

            LadgerAccount ladgerAccount = ladgerAccountRepository.getById(detailRequest.getAccountId());
            generalJournalDetail.setLadgerAccount(ladgerAccount);

            generalJournalDetail.setDebit(detailRequest.getDebit());
            generalJournalDetail.setCredit(detailRequest.getCredit());
            generalJournalDetail.setIsDeleted(Deleted.NO);
            GeneralJournalDetail detail = generalJournalDetailRepository.save(generalJournalDetail);
            details.add(detail);
        }
        generalJournalHeader.setGeneralJournalDetails(details);

        return convert(generalJournalHeader);
    }

    @Override
    public GeneralJournalHeaderResponse getById(Long id) {

//        Optional<GeneralJournalHeader> generalJournalHeader = generalJournalHeaderRepository.findById(id);
//        if(generalJournalHeader == null){
//            return null;
//        }

        //List<GeneralJournalDetailResponse> details = generalJournalDetailRepository.findByGeneralJournalHeaderIdAndIsDeleted(id, Deleted.NO);


        return generalJournalHeaderRepository.findById(id).map(GeneralJournalHeaderServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GeneralJournalHeaderResponse> getAll() {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        return generalJournalHeaderRepository.findByIsDeleted(Deleted.NO, sort)
                .stream().map(GeneralJournalHeaderServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public Page<GeneralJournalHeaderResponse> getPaginatedAll(int pageNumber, int countPerPage) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage, sort);
        Page<GeneralJournalHeader> _pgResult = generalJournalHeaderRepository.findByIsDeleted(Deleted.NO, pgRequest);

        List<GeneralJournalHeaderResponse> _lstResult = _pgResult
                .stream().map(GeneralJournalHeaderServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_lstResult, pgRequest, _pgResult.getTotalElements());
    }

    @Override
    public Page<GeneralJournalHeaderResponse> getPaginatedAll(Long branchId, int pageNumber, int countPerPage) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage, sort);
        Page<GeneralJournalHeader> _pgResult = generalJournalHeaderRepository.findByIsDeletedAndBranchId(Deleted.NO, branchId, pgRequest);

        List<GeneralJournalHeaderResponse> _lstResult = _pgResult
                .stream().map(GeneralJournalHeaderServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_lstResult, pgRequest, _pgResult.getTotalElements());
    }

    @Override
    public Page<GeneralJournalHeaderResponse> getSearchPaginated(int pageNumber, int countPerPage, GeneralJournalHeaderSearchRequest searchRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable _pgRequest = PageRequest.of(pageNumber, countPerPage, sort);

        List<GeneralJournalHeader> _unPagedHeaders = generalJournalHeaderRepository.findByIsDeletedAndTransactionDateBetween(
                Deleted.NO,
                ConvertUtils.convertStrToDate(searchRequest.getTransactionFromDate()),
                ConvertUtils.convertStrToDate(searchRequest.getTransactionToDate()),
                sort
        );

//        Page<GeneralJournalHeader> _pgResult =
//                generalJournalHeaderRepositoryPg.findByIsDeletedAndTransactionDateBetween(
//                        Deleted.NO,
//                        ConvertUtils.convertStrToDate(searchRequest.getTransactionFromDate()),
//                        ConvertUtils.convertStrToDate(searchRequest.getTransactionToDate()),
//                        _pgRequest
//                );

        int _fromIndex = pageNumber * countPerPage;
        int _toIndex = ((pageNumber == 0 ? countPerPage : (pageNumber * countPerPage)) - 1);
        _toIndex = Math.min(_toIndex, _unPagedHeaders.size());
        List<GeneralJournalHeader> _pagedHeaders = _unPagedHeaders.subList(
                _fromIndex,
                _toIndex
        );

        List<GeneralJournalHeaderResponse> _pagedResponses = _pagedHeaders
                .stream().map(GeneralJournalHeaderServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_pagedResponses, _pgRequest, _unPagedHeaders.size());
    }

    @Override
    public Page<GeneralJournalHeaderResponse> getSearchPaginated(Long branchId, int pageNumber, int countPerPage, GeneralJournalHeaderSearchRequest searchRequest) {

//        Sort sort = Sort.by("id");
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable _pgRequest = PageRequest.of(pageNumber, countPerPage, sort);

//        Page<GeneralJournalHeader> _pgResult =
//                generalJournalHeaderRepositoryPg.findByIsDeletedAndBranchIdAndTransactionDateBetween(
//                        Deleted.NO,
//                        branchId,
//                        ConvertUtils.convertStrToDate(searchRequest.getTransactionFromDate()),
//                        ConvertUtils.convertStrToDate(searchRequest.getTransactionToDate()),
//                        _pgRequest
//                );

        List<GeneralJournalHeader> _unPagedHeaders = generalJournalHeaderRepository.findByIsDeletedAndBranchIdAndTransactionDateBetween(
                Deleted.NO,
                branchId,
                ConvertUtils.convertStrToDate(searchRequest.getTransactionFromDate()),
                ConvertUtils.convertStrToDate(searchRequest.getTransactionToDate()),
                sort
        );

        int _fromIndex = pageNumber * countPerPage;
        int _toIndex = ((pageNumber == 0 ? countPerPage : (pageNumber * countPerPage)) - 1);
        _toIndex = Math.min(_toIndex, _unPagedHeaders.size());
        List<GeneralJournalHeader> _pagedHeaders = _unPagedHeaders.subList(
                _fromIndex,
                _toIndex
        );

        List<GeneralJournalHeaderResponse> _pagedResponses = _pagedHeaders
                .stream().map(GeneralJournalHeaderServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_pagedResponses, _pgRequest, _unPagedHeaders.size());
    }

    @Override
    public File printSingle(Long id) {
        File out = null;
        Connection co = null;

        try {
            File file = new File("JRXML/report/leader_reports/rpt_GeneralJournal.jrxml");
            Map<String, Object> map = new HashMap<>();
            map.put("prm_HeaderId", id);
            co = JDBC.con();
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());

            JasperPrint print = JasperFillManager.fillReport(jasperReport, map, co);

            File tmpDir = new File("TMP/");
            if (!tmpDir.exists()) {
                tmpDir.mkdirs();
            }

//            String filepath = filepath = "TMP/" + System.currentTimeMillis() + ".pdf";
//            String filepath = "TMP/" + System.currentTimeMillis() + ".pdf";
            String filepath = "TMP/" + System.currentTimeMillis() + ".pdf";
            JasperExportManager.exportReportToPdfFile(print, filepath);
            out = new File(filepath);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (co != null) {
                try {
                    co.close();
                } catch (Exception ignored) {
                }
            }
        }
        return out;
    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        GeneralJournalHeader got = generalJournalHeaderRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }

        //Is Already reversed
        GeneralJournalReverseResponse hasReversed = generalJournalReverseService.getIfAlreadyReversed(id);
        if (hasReversed != null) {
            //Already reversed
            return 3;
        }

        //got.setIsDeleted(Deleted.YES);
        //generalJournalHeaderRepository.save(got);

        if (got.getGeneralJournalDetails() == null || got.getGeneralJournalDetails().isEmpty()) {
            return 0;
        }

        //Generate a identical but debit credit reversed journal
        GeneralJournalHeaderRequest reverseJournal = new GeneralJournalHeaderRequest();
        reverseJournal.setRemark("REVERSED: " + got.getRemark());
        reverseJournal.setTransactionDetail("REVERSED: " + got.getTransactionDetail());
        reverseJournal.setTransactionDate(new DateFormatConverter().patternDate(new Date()));
        reverseJournal.setBranchId(got.getBranch().getId());
        List<GeneralJournalDetailRequest> detailRequests = new ArrayList<>();
        for (GeneralJournalDetail detail : got.getGeneralJournalDetails()) {
            GeneralJournalDetailRequest reverseDetail = new GeneralJournalDetailRequest();
            reverseDetail.setLineNumber(detail.getLineNumber());
            reverseDetail.setAccountId(detail.getLadgerAccount().getId());
            //Set Debit to Credit and Credit to Debit
            reverseDetail.setDebit(detail.getCredit());
            reverseDetail.setCredit(detail.getDebit());
            detailRequests.add(reverseDetail);
        }
        reverseJournal.setJournalDetails(detailRequests);
        GeneralJournalHeaderResponse savedReverseJournal = this.save(reverseJournal);

        if (savedReverseJournal == null) {
            return 0;
        }

        //Generate a reversed entry
        GeneralJournalReverseRequest reverseRequest = new GeneralJournalReverseRequest();
        reverseRequest.setReverseForGeneralJournalId(got.getId());
        reverseRequest.setReverseAtGeneralJournalId(savedReverseJournal.getId());

        GeneralJournalReverseResponse response = generalJournalReverseService.save(reverseRequest);
        if (response == null) {
            return 0;
        }


        return 1;
    }

    private double _CalculateTotalDebit(List<GeneralJournalDetailRequest> details) {
        double _total = 0.00;

        for (GeneralJournalDetailRequest detail : details) {
            _total += detail.getDebit();
        }

        return _total;
    }

    private double _CalculateTotalDebit_Update(List<GeneralJournalDetailUpdateRequest> details) {
        double _total = 0.00;

        for (GeneralJournalDetailUpdateRequest detail : details) {
            _total += detail.getDebit();
        }

        return _total;
    }

    private double _CalculateTotalCredit(List<GeneralJournalDetailRequest> details) {
        double _total = 0.00;

        for (GeneralJournalDetailRequest detail : details) {
            _total += detail.getCredit();
        }

        return _total;
    }

    private double _CalculateTotalCredit_Update(List<GeneralJournalDetailUpdateRequest> details) {
        double _total = 0.00;

        for (GeneralJournalDetailUpdateRequest detail : details) {
            _total += detail.getCredit();
        }

        return _total;
    }

    private static GeneralJournalHeaderResponse convert(GeneralJournalHeader generalJournalHeader) {

        GeneralJournalHeaderResponse typeResponse = new GeneralJournalHeaderResponse();
        typeResponse.setId(generalJournalHeader.getId());

        BranchNetworkResponse branchNetworkResponse = BranchNetworkServiceImpl.convert(generalJournalHeader.getBranch());
        typeResponse.setBranch(branchNetworkResponse);

        typeResponse.setJournalNumber(generalJournalHeader.getJournalNumber());
        typeResponse.setTransactionDate(generalJournalHeader.getTransactionDate());
        typeResponse.setRemark(generalJournalHeader.getRemark());
        typeResponse.setTransactionDetail(generalJournalHeader.getTransactionDetail());
        typeResponse.setTotalDebit(generalJournalHeader.getTotalDebit());
        typeResponse.setTotalCredit(generalJournalHeader.getTotalCredit());
        typeResponse.setCreatedBy(generalJournalHeader.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(generalJournalHeader.getCreatedDateTime()));
        typeResponse.setModifiedBy(generalJournalHeader.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(generalJournalHeader.getModifiedDateTime()));
        typeResponse.setIsDeleted(generalJournalHeader.getIsDeleted());

        List<GeneralJournalDetailResponse> details = new ArrayList<>();
        for (GeneralJournalDetail detail : generalJournalHeader.getGeneralJournalDetails()) {
            details.add(GeneralJournalDetailServiceImpl.convert(detail));
        }
        typeResponse.setGeneralJournalDetails(details);

        return typeResponse;
    }
}