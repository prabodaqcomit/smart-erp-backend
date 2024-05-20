package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalTemplateDetailResponse;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalTemplateHeaderResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.repository.GeneralJournalTemplateDetailRepository;
import lk.quantacom.smarterpbackend.repository.GeneralJournalTemplateHeaderRepository;
import lk.quantacom.smarterpbackend.service.GeneralJournalTemplateHeaderService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneralJournalTemplateHeaderServiceImpl implements GeneralJournalTemplateHeaderService {

    @Autowired
    private GeneralJournalTemplateHeaderRepository generalJournalTemplateHeaderRepository;

    @Autowired
    private GeneralJournalTemplateDetailRepository generalJournalTemplateDetailRepository;

    @Override
    @Transactional
    public GeneralJournalTemplateHeaderResponse save(GeneralJournalTemplateHeaderRequest request) {

        GeneralJournalTemplateHeader generalJournalTemplateHeader = new GeneralJournalTemplateHeader();

        BranchNetwork branch = new BranchNetwork();
        branch.setId(request.getBranchId());
        generalJournalTemplateHeader.setBranch(branch);

        generalJournalTemplateHeader.setTemplateName(request.getTemplateName());
        generalJournalTemplateHeader.setRemark(request.getRemark());
        generalJournalTemplateHeader.setLastUsedDateTime(request.getLastUsedDateTime() == null ? null : ConvertUtils.convertStrToDate(request.getLastUsedDateTime()));
        generalJournalTemplateHeader.setIsDeleted(Deleted.NO);
        GeneralJournalTemplateHeader save = generalJournalTemplateHeaderRepository.save(generalJournalTemplateHeader);

        //List<GeneralJournalTemplateDetail> details = new ArrayList<>();
        for(GeneralJournalTemplateDetailRequest detailRequest : request.getTemplateDetails()){
            GeneralJournalTemplateDetail generalJournalTemplateDetail = new GeneralJournalTemplateDetail();

            generalJournalTemplateDetail.setGeneralJournalTemplateHeader(save);

            generalJournalTemplateDetail.setLineNumber(detailRequest.getLineNumber());

            LadgerAccount ladgerAccount = new LadgerAccount();
            ladgerAccount.setId(detailRequest.getAccountId());
            generalJournalTemplateDetail.setLadgerAccount(ladgerAccount);

            generalJournalTemplateDetail.setDebit(detailRequest.getDebit());
            generalJournalTemplateDetail.setCredit(detailRequest.getCredit());
            generalJournalTemplateDetail.setIsDeleted(Deleted.NO);

            GeneralJournalTemplateDetail detail = generalJournalTemplateDetailRepository.save(generalJournalTemplateDetail);

        }
        //save.setGeneralJournalTemplateDetails(details);

        return convert(save);
    }

    @Override
    @Transactional
    public GeneralJournalTemplateHeaderResponse update(GeneralJournalTemplateHeaderUpdateRequest request) {

        GeneralJournalTemplateHeader generalJournalTemplateHeader = generalJournalTemplateHeaderRepository.findById(request.getId()).orElse(null);
        if (generalJournalTemplateHeader == null) {
            return null;
        }

        generalJournalTemplateHeader.setId(request.getId());

        BranchNetwork branch = new BranchNetwork();
        branch.setId(request.getBranchId());
        generalJournalTemplateHeader.setBranch(branch);

        generalJournalTemplateHeader.setTemplateName(request.getTemplateName());
        generalJournalTemplateHeader.setRemark(request.getRemark());
        generalJournalTemplateHeader.setLastUsedDateTime(request.getLastUsedDateTime() == null ? null : ConvertUtils.convertStrToDate(request.getLastUsedDateTime()));
        GeneralJournalTemplateHeader updated = generalJournalTemplateHeaderRepository.save(generalJournalTemplateHeader);

        //List<GeneralJournalTemplateDetail> details = new ArrayList<>();
        for(GeneralJournalTemplateDetailUpdateRequest detail : request.getTemplateDetails()){

            GeneralJournalTemplateDetail generalJournalTemplateDetail = generalJournalTemplateDetailRepository.findById(request.getId()).orElse(null);
            if (generalJournalTemplateDetail == null) {
               continue; //TO DO  Save new entry
            }

            generalJournalTemplateDetail.setId(generalJournalTemplateDetail.getId());

            generalJournalTemplateDetail.setGeneralJournalTemplateHeader(updated);

            generalJournalTemplateDetail.setLineNumber(generalJournalTemplateDetail.getLineNumber());

            LadgerAccount ladgerAccount = new LadgerAccount();
            ladgerAccount.setId(detail.getAccountId());
            generalJournalTemplateDetail.setLadgerAccount(ladgerAccount);

            generalJournalTemplateDetail.setDebit(detail.getDebit());
            generalJournalTemplateDetail.setCredit(detail.getCredit());
            generalJournalTemplateDetailRepository.save(generalJournalTemplateDetail);
        }
        //updated.setGeneralJournalTemplateDetails(details);

        return convert(updated);
    }

    @Override
    public GeneralJournalTemplateHeaderResponse getById(Long id) {

        return generalJournalTemplateHeaderRepository.findById(id).map(GeneralJournalTemplateHeaderServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GeneralJournalTemplateHeaderResponse> getAll() {

        return generalJournalTemplateHeaderRepository.findByIsDeleted(Deleted.NO)
                .stream().map(GeneralJournalTemplateHeaderServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public Page<GeneralJournalTemplateHeaderResponse> getPaginatedAll(int pageNumber, int countPerPage){
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage);
        Page<GeneralJournalTemplateHeader> _pgResult = generalJournalTemplateHeaderRepository.findByIsDeleted(Deleted.NO, pgRequest);
        List<GeneralJournalTemplateHeaderResponse> _lstResult = _pgResult
                .stream().map(GeneralJournalTemplateHeaderServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_lstResult, pgRequest, _pgResult.getTotalElements());
    }

    @Override
    public Page<GeneralJournalTemplateHeaderResponse> getPaginatedAll(Long branchId, int pageNumber, int countPerPage){
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage);
        Page<GeneralJournalTemplateHeader> _pgResult = generalJournalTemplateHeaderRepository.findByIsDeletedAndBranchId(Deleted.NO, branchId, pgRequest);
        List<GeneralJournalTemplateHeaderResponse> _lstResult = _pgResult
                .stream().map(GeneralJournalTemplateHeaderServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_lstResult, pgRequest, _pgResult.getTotalElements());
    }

    @Override
    public Page<GeneralJournalTemplateHeaderResponse> getSearchPaginated(int pageNumber, int countPerPage, GeneralJournalTemplateHeaderSearchRequest searchRequest){
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage);
        Page<GeneralJournalTemplateHeader> _pgResult =
                generalJournalTemplateHeaderRepository.findByIsDeletedAndTemplateNameContaining(
                        Deleted.NO,
                        searchRequest.getTemplateName(),
                        pgRequest
                );
        List<GeneralJournalTemplateHeaderResponse> _lstResult = _pgResult
                .stream().map(GeneralJournalTemplateHeaderServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_lstResult, pgRequest, _pgResult.getTotalElements());
    }

    @Override
    public Page<GeneralJournalTemplateHeaderResponse> getSearchPaginated(Long branchId, int pageNumber, int countPerPage, GeneralJournalTemplateHeaderSearchRequest searchRequest){
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage);
        Page<GeneralJournalTemplateHeader> _pgResult =
                generalJournalTemplateHeaderRepository.findByIsDeletedAndBranchIdAndTemplateNameContaining(
                        Deleted.NO,
                        branchId,
                        searchRequest.getTemplateName(),
                        pgRequest
                );
        List<GeneralJournalTemplateHeaderResponse> _lstResult = _pgResult
                .stream().map(GeneralJournalTemplateHeaderServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_lstResult, pgRequest, _pgResult.getTotalElements());
    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        GeneralJournalTemplateHeader got = generalJournalTemplateHeaderRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        generalJournalTemplateHeaderRepository.save(got);

        return 1;
    }

    private static GeneralJournalTemplateHeaderResponse convert(GeneralJournalTemplateHeader generalJournalTemplateHeader) {

        GeneralJournalTemplateHeaderResponse typeResponse = new GeneralJournalTemplateHeaderResponse();
        typeResponse.setBranch( BranchNetworkServiceImpl.convert(generalJournalTemplateHeader.getBranch()) );
        typeResponse.setTemplateName(generalJournalTemplateHeader.getTemplateName());
        typeResponse.setRemark(generalJournalTemplateHeader.getRemark());
        typeResponse.setLastUsedDateTime(generalJournalTemplateHeader.getLastUsedDateTime());
        typeResponse.setId(generalJournalTemplateHeader.getId());
        typeResponse.setCreatedBy(generalJournalTemplateHeader.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(generalJournalTemplateHeader.getCreatedDateTime()));
        typeResponse.setModifiedBy(generalJournalTemplateHeader.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(generalJournalTemplateHeader.getModifiedDateTime()));
        typeResponse.setIsDeleted(generalJournalTemplateHeader.getIsDeleted());
        List<GeneralJournalTemplateDetailResponse> details = new ArrayList<>();
        for(GeneralJournalTemplateDetail detail: generalJournalTemplateHeader.getGeneralJournalTemplateDetails()){
            details.add(GeneralJournalTemplateDetailServiceImpl.convert(detail));
        }
        typeResponse.setTemplateDetails(details);

        return typeResponse;
    }

}