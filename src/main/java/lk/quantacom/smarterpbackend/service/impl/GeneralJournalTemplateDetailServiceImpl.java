package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.GeneralJournalTemplateDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalTemplateDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalTemplateDetailResponse;
import lk.quantacom.smarterpbackend.entity.GeneralJournalTemplateDetail;
import lk.quantacom.smarterpbackend.entity.GeneralJournalTemplateHeader;
import lk.quantacom.smarterpbackend.entity.LadgerAccount;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.repository.GeneralJournalTemplateDetailRepository;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.service.GeneralJournalTemplateDetailService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneralJournalTemplateDetailServiceImpl implements GeneralJournalTemplateDetailService {

    @Autowired
    private GeneralJournalTemplateDetailRepository generalJournalTemplateDetailRepository;

//    @Override
//    public GeneralJournalTemplateDetail save(GeneralJournalTemplateDetailRequest request) {
//
//        GeneralJournalTemplateDetail generalJournalTemplateDetail = new GeneralJournalTemplateDetail();
//
//        GeneralJournalTemplateHeader header = new GeneralJournalTemplateHeader();
//        header.setId(request.getTemplateId());
//        generalJournalTemplateDetail.setGeneralJournalTemplateHeader(header);
//
//        generalJournalTemplateDetail.setLineNumber(request.getLineNumber());
//
//        LadgerAccount ladgerAccount = new LadgerAccount();
//        ladgerAccount.setId(request.getAccountId());
//        generalJournalTemplateDetail.setLadgerAccount(ladgerAccount);
//
//        generalJournalTemplateDetail.setDebit(request.getDebit());
//        generalJournalTemplateDetail.setCredit(request.getCredit());
//        generalJournalTemplateDetail.setIsDeleted(Deleted.NO);
//
//        GeneralJournalTemplateDetail save = generalJournalTemplateDetailRepository.save(generalJournalTemplateDetail);
//
//        return save;
//    }

//    @Override
//    public GeneralJournalTemplateDetail update(GeneralJournalTemplateDetailUpdateRequest request) {
//
//        GeneralJournalTemplateDetail generalJournalTemplateDetail = generalJournalTemplateDetailRepository.findById(request.getId()).orElse(null);
//        if (generalJournalTemplateDetail == null) {
//            return null;
//        }
//
//        generalJournalTemplateDetail.setId(request.getId());
//
//        GeneralJournalTemplateHeader header = new GeneralJournalTemplateHeader();
//        header.setId(request.getTemplateId());
//        generalJournalTemplateDetail.setGeneralJournalTemplateHeader(header);
//
//        generalJournalTemplateDetail.setLineNumber(request.getLineNumber());
//
//        LadgerAccount ladgerAccount = new LadgerAccount();
//        ladgerAccount.setId(request.getAccountId());
//        generalJournalTemplateDetail.setLadgerAccount(ladgerAccount);
//
//        generalJournalTemplateDetail.setDebit(request.getDebit());
//        generalJournalTemplateDetail.setCredit(request.getCredit());
//        GeneralJournalTemplateDetail updated = generalJournalTemplateDetailRepository.save(generalJournalTemplateDetail);
//
//        return updated;
//    }

    @Override
    public GeneralJournalTemplateDetailResponse getById(Long id) {

        return generalJournalTemplateDetailRepository.findById(id).map(GeneralJournalTemplateDetailServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GeneralJournalTemplateDetailResponse> getAll() {

        return generalJournalTemplateDetailRepository.findByIsDeleted(Deleted.NO)
                .stream().map(GeneralJournalTemplateDetailServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        GeneralJournalTemplateDetail got = generalJournalTemplateDetailRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        generalJournalTemplateDetailRepository.save(got);

        return 1;
    }

    public static GeneralJournalTemplateDetailResponse convert(GeneralJournalTemplateDetail generalJournalTemplateDetail) {

        GeneralJournalTemplateDetailResponse typeResponse = new GeneralJournalTemplateDetailResponse();

        typeResponse.setJournalTemplateHeader(generalJournalTemplateDetail.getGeneralJournalTemplateHeader());
        typeResponse.setLineNumber(generalJournalTemplateDetail.getLineNumber());
        typeResponse.setLadgerAccount(generalJournalTemplateDetail.getLadgerAccount());
        typeResponse.setDebit(generalJournalTemplateDetail.getDebit());
        typeResponse.setCredit(generalJournalTemplateDetail.getCredit());
        typeResponse.setId(generalJournalTemplateDetail.getId());
        typeResponse.setCreatedBy(generalJournalTemplateDetail.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(generalJournalTemplateDetail.getCreatedDateTime()));
        typeResponse.setModifiedBy(generalJournalTemplateDetail.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(generalJournalTemplateDetail.getModifiedDateTime()));
        typeResponse.setIsDeleted(generalJournalTemplateDetail.getIsDeleted());

        return typeResponse;
    }

}