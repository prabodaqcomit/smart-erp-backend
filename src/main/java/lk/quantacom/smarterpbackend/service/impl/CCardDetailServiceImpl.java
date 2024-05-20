package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.CCardDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.CCardDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.CCardDetailResponse;
import lk.quantacom.smarterpbackend.entity.CCardDetail;
import lk.quantacom.smarterpbackend.repository.CCardDetailRepository;
import lk.quantacom.smarterpbackend.service.CCardDetailService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CCardDetailServiceImpl implements CCardDetailService {

    @Autowired
    private CCardDetailRepository cCardDetailRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Override
    @Transactional
    public CCardDetailResponse save(CCardDetailRequest request) {

        CCardDetail cCardDetail = new CCardDetail();
        cCardDetail.setCCardDetCode(request.getCCardDetCode());
        cCardDetail.setCCardHedCode(request.getCCardHedCode());
        cCardDetail.setCCardFormat(request.getCCardFormat());
        cCardDetail.setCCardStartingStr(request.getCCardStartingStr());
        //cCardDetail.setIsDeleted(Deleted.NO);
        CCardDetail save = cCardDetailRepository.save(cCardDetail);

        saveLog("CCardDetail", "Data Saved - " + save.getCCardDetCode());

        return convert(save);
    }

    @Override
    @Transactional
    public CCardDetailResponse update(CCardDetailUpdateRequest request) {

        CCardDetail cCardDetail = cCardDetailRepository.findById(request.getCCardDetCode()).orElse(null);
        if (cCardDetail == null) {
            return null;
        }

        //cCardDetail.setId(request.getId());
        cCardDetail.setCCardDetCode(request.getCCardDetCode());
        cCardDetail.setCCardHedCode(request.getCCardHedCode());
        cCardDetail.setCCardFormat(request.getCCardFormat());
        cCardDetail.setCCardStartingStr(request.getCCardStartingStr());
        CCardDetail updated = cCardDetailRepository.save(cCardDetail);

        saveLog("CCardDetail", "Data Updated - " + updated.getCCardDetCode());

        return (convert(updated));
    }

    @Override
    public CCardDetailResponse getById(String id) {

        return cCardDetailRepository.findById(id).map(CCardDetailServiceImpl::convert).orElse(null);
    }

    @Override
    public List<CCardDetailResponse> getAll() {

        return cCardDetailRepository.findAll()
                .stream().map(CCardDetailServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(String id) {

        CCardDetail got = cCardDetailRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        //got.setIsDeleted(Deleted.YES);
        cCardDetailRepository.delete(got);

        saveLog("CCardDetail", "Data Deleted - " + id);

        return 1;
    }

    private static CCardDetailResponse convert(CCardDetail cCardDetail) {

        CCardDetailResponse typeResponse = new CCardDetailResponse();
        typeResponse.setCCardDetCode(cCardDetail.getCCardDetCode());

        typeResponse.setCCardHedCode(cCardDetail.getCCardHedCode());

        typeResponse.setCCardFormat(cCardDetail.getCCardFormat());

        typeResponse.setCCardStartingStr(cCardDetail.getCCardStartingStr());

//        typeResponse.setId(cCardDetail.getId());
//        typeResponse.setCreatedBy(cCardDetail.getCreatedBy());
//        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(cCardDetail.getCreatedDateTime()));
//        typeResponse.setModifiedBy(cCardDetail.getModifiedBy());
//        typeResponse.setIsDeleted(cCardDetail.getIsDeleted());

        return typeResponse;
    }

    private void saveLog(String form, String action) {
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }
}