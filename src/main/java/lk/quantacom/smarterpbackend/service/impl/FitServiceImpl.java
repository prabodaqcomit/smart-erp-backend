package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.FitRequest;
import lk.quantacom.smarterpbackend.dto.request.FitUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.FitResponse;
import lk.quantacom.smarterpbackend.entity.Fit;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.repository.FitRepository;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.service.FitService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FitServiceImpl implements FitService {

    @Autowired
    private FitRepository fitRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    private void saveLog(String form,String action){
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }

    @Override
    @Transactional
    public FitResponse save(FitRequest request) {

        Fit fit = new Fit();
        fit.setFitDesc(request.getFitDesc());
        fit.setIsDeleted(Deleted.NO);
        Fit save = fitRepository.save(fit);

        saveLog("Fit","Data Saved - "+save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public FitResponse update(FitUpdateRequest request) {

        Fit fit = fitRepository.findById(request.getId()).orElse(null);
        if (fit == null) {
            return null;
        }

        fit.setId(request.getId());
        fit.setFitDesc(request.getFitDesc());
        Fit updated = fitRepository.save(fit);

        saveLog("Category","Data Updated - "+updated.getId());

        return (convert(updated));
    }

    @Override
    public FitResponse getById(Long id) {

        return fitRepository.findById(id).map(FitServiceImpl::convert).orElse(null);
    }

    @Override
    public List<FitResponse> getAll() {

        return fitRepository.findAll()
                .stream().map(FitServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        Fit got = fitRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        fitRepository.save(got);

        saveLog("Category","Data Deleted - "+id);

        return 1;
    }

    private static FitResponse convert(Fit fit) {

        FitResponse typeResponse = new FitResponse();
        typeResponse.setFitDesc(fit.getFitDesc());

        typeResponse.setId(fit.getId());
        typeResponse.setCreatedBy(fit.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(fit.getCreatedDateTime()));
        typeResponse.setModifiedBy(fit.getModifiedBy());
        typeResponse.setIsDeleted(fit.getIsDeleted());

        return typeResponse;
    }
}