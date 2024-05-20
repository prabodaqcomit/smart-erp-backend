package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.GeneralJournalDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BranchNetworkResponse;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalDetailResponse;
import lk.quantacom.smarterpbackend.dto.response.LadgerAccountResponse;
import lk.quantacom.smarterpbackend.entity.GeneralJournalDetail;
import lk.quantacom.smarterpbackend.entity.GeneralJournalHeader;
import lk.quantacom.smarterpbackend.entity.LadgerAccount;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.repository.GeneralJournalDetailRepository;
import lk.quantacom.smarterpbackend.repository.LadgerAccountRepository;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.service.GeneralJournalDetailService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GeneralJournalDetailServiceImpl implements GeneralJournalDetailService {

    @Autowired
    private GeneralJournalDetailRepository generalJournalDetailRepository;

    //@Autowired
    //private LadgerAccountRepository ladgerAccountRepository;


//    @Override
//    public GeneralJournalDetail save(GeneralJournalDetailRequest request) {
//
//        GeneralJournalDetail generalJournalDetail = new GeneralJournalDetail();
//        GeneralJournalHeader header = new GeneralJournalHeader();
//        header.setId(request.getGeneralJournalId());
//        generalJournalDetail.setGeneralJournalHeader(header);
//
//        generalJournalDetail.setJournalNumber(request.getJournalNumber());
//        generalJournalDetail.setLineNumber(request.getLineNumber());
//
//        //LadgerAccount ladgerAccount = new LadgerAccount();
//        //ladgerAccount.setId(request.getLadgerAccountId());
//        //generalJournalDetail.setLadgerAccount(ladgerAccount);
//        LadgerAccount ladgerAccount = ladgerAccountRepository.getById(request.getAccountId());
//        generalJournalDetail.setLadgerAccount(ladgerAccount);
//
//        generalJournalDetail.setDebit(request.getDebit());
//        generalJournalDetail.setCredit(request.getCredit());
//        generalJournalDetail.setIsDeleted(Deleted.NO);
//        GeneralJournalDetail save = generalJournalDetailRepository.save(generalJournalDetail);
//
//        saveLog("GeneralJournalDetail", "Save GeneralJournalDetail - " + save.getId());
//
//        //return convert(save);
//        return save;
//    }

//    @Override
//    public GeneralJournalDetail update(GeneralJournalDetailUpdateRequest request) {
//
//        GeneralJournalDetail generalJournalDetail = generalJournalDetailRepository.findById(request.getId()).orElse(null);
//        if (generalJournalDetail == null) {
//            return null;
//        }
//
//        generalJournalDetail.setId(request.getId());
//        generalJournalDetail.setLineNumber(request.getLineNumber());
//
//        GeneralJournalHeader header = new GeneralJournalHeader();
//        header.setId(request.getGeneralJournalId());
//        generalJournalDetail.setGeneralJournalHeader(header);
//        generalJournalDetail.setJournalNumber(request.getJournalNumber());
//
//        LadgerAccount ladgerAccount = ladgerAccountRepository.getById( request.getAccountId() );
//        generalJournalDetail.setLadgerAccount(ladgerAccount);
//
//        generalJournalDetail.setDebit(request.getDebit());
//        generalJournalDetail.setCredit(request.getCredit());
//
//        GeneralJournalDetail updated = generalJournalDetailRepository.save(generalJournalDetail);
//
//        saveLog("GeneralJournalDetail", "Update GeneralJournalDetail - " + updated.getId());
//
//        //return (convert(updated));
//        return updated;
//    }
//    @Override
//    public List<GeneralJournalDetailResponse> getByGeneralJournalHeaderId(Long id){
//        return generalJournalDetailRepository.findByGeneralJournalHeaderIdAndIsDeleted(id, Deleted.NO)
//                .stream().map(GeneralJournalDetailServiceImpl::convert).collect(Collectors.toList());
//    }

    @Override
    public GeneralJournalDetailResponse getById(Long id) {

        return generalJournalDetailRepository.findById(id).map(GeneralJournalDetailServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GeneralJournalDetailResponse> getAll() {

        return generalJournalDetailRepository.findByIsDeleted(Deleted.NO)
                .stream().map(GeneralJournalDetailServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        GeneralJournalDetail got = generalJournalDetailRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        generalJournalDetailRepository.save(got);

        return 1;
    }

    public static GeneralJournalDetailResponse convert(GeneralJournalDetail generalJournalDetail) {

        GeneralJournalDetailResponse typeResponse = new GeneralJournalDetailResponse();

        //typeResponse.setGeneralJournalHeader(generalJournalDetail.getGeneralJournalHeader());
        typeResponse.setLineNumber(generalJournalDetail.getLineNumber());

        LadgerAccountResponse ladgerAccountResponse =  LadgerAccountServiceImpl.convert(generalJournalDetail.getLadgerAccount());
        typeResponse.setLedgerAccount(ladgerAccountResponse);

        typeResponse.setDebit(generalJournalDetail.getDebit());
        typeResponse.setCredit(generalJournalDetail.getCredit());
        typeResponse.setId(generalJournalDetail.getId());
        typeResponse.setCreatedBy(generalJournalDetail.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(generalJournalDetail.getCreatedDateTime()));
        typeResponse.setModifiedBy(generalJournalDetail.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(generalJournalDetail.getModifiedDateTime()));
        typeResponse.setIsDeleted(generalJournalDetail.getIsDeleted());

        return typeResponse;
    }
}