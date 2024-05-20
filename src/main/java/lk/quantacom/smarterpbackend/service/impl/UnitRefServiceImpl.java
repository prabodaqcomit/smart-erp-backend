package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.UnitRefRequest;
import lk.quantacom.smarterpbackend.dto.request.UnitRefUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.UnitRefResponse;
import lk.quantacom.smarterpbackend.entity.UnitRef;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.repository.UnitRefRepository;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.service.UnitRefService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitRefServiceImpl implements UnitRefService {

    @Autowired
    private UnitRefRepository unitRefRepository;

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
    public UnitRefResponse save(UnitRefRequest request) {

        UnitRef unitRef = new UnitRef();
        unitRef.setUnitShort(request.getUnitShort());
        unitRef.setUnitLong(request.getUnitLong());
        unitRef.setIsDeleted(Deleted.NO);
        UnitRef save = unitRefRepository.save(unitRef);

        saveLog("UnitRef","Data Saved - "+save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public UnitRefResponse update(UnitRefUpdateRequest request) {

        UnitRef unitRef = unitRefRepository.findById(request.getId()).orElse(null);
        if (unitRef == null) {
            return null;
        }

        unitRef.setId(request.getId());
        unitRef.setUnitShort(request.getUnitShort());
        unitRef.setUnitLong(request.getUnitLong());
        unitRef.setIsDeleted(request.getIsDeleted());
        UnitRef updated = unitRefRepository.save(unitRef);

        saveLog("UnitRef","Data Saved - "+updated.getId());

        return (convert(updated));
    }

    @Override
    public UnitRefResponse getById(Long id) {

        return unitRefRepository.findById(id).map(UnitRefServiceImpl::convert).orElse(null);
    }

    @Override
    public List<UnitRefResponse> getAll() {

        return unitRefRepository.findAll()
                .stream().map(UnitRefServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        UnitRef got = unitRefRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        unitRefRepository.save(got);

        saveLog("UnitRef","Data Deleted - "+id);

        return 1;
    }

    private static UnitRefResponse convert(UnitRef unitRef) {

        UnitRefResponse typeResponse = new UnitRefResponse();
        typeResponse.setUnitShort(unitRef.getUnitShort());

        typeResponse.setUnitLong(unitRef.getUnitLong());

        typeResponse.setId(unitRef.getId());
        typeResponse.setCreatedBy(unitRef.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(unitRef.getCreatedDateTime()));
        typeResponse.setModifiedBy(unitRef.getModifiedBy());
        typeResponse.setIsDeleted(unitRef.getIsDeleted());

        return typeResponse;
    }
}