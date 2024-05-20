package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.LedgerTypesRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerTypesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerTypesResponse;
import lk.quantacom.smarterpbackend.entity.LedgerTypes;
import lk.quantacom.smarterpbackend.repository.LedgerTypesRepository;
import lk.quantacom.smarterpbackend.service.LedgerTypesService;
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
public class LedgerTypesServiceImpl implements LedgerTypesService {

    @Autowired
    private LedgerTypesRepository ledgerTypesRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Override
    @Transactional
    public LedgerTypesResponse save(LedgerTypesRequest request) {

        LedgerTypes ledgerTypes = new LedgerTypes();
        ledgerTypes.setAccCategory(request.getAccCategory());
        ledgerTypes.setMainAccType(request.getMainAccType());
        ledgerTypes.setMainAccTypeCode(request.getMainAccTypeCode());
        ledgerTypes.setSubAccType(request.getSubAccType());
        ledgerTypes.setSubAccTypeCode(request.getSubAccTypeCode());
        ledgerTypes.setSubAccSinhala(request.getSubAccSinhala());
        ledgerTypes.setMainAccTypeSinhala(request.getMainAccTypeSinhala());
        ledgerTypes.setIsDeleted(Deleted.NO);
        LedgerTypes save = ledgerTypesRepository.save(ledgerTypes);

        saveLog("LedgerTypes", "Data Saved - " + save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public LedgerTypesResponse update(LedgerTypesUpdateRequest request) {

        LedgerTypes ledgerTypes = ledgerTypesRepository.findById(request.getId()).orElse(null);
        if (ledgerTypes == null) {
            return null;
        }

        ledgerTypes.setId(request.getId());
        ledgerTypes.setAccCategory(request.getAccCategory());
        ledgerTypes.setMainAccType(request.getMainAccType());
        ledgerTypes.setMainAccTypeCode(request.getMainAccTypeCode());
        ledgerTypes.setSubAccType(request.getSubAccType());
        ledgerTypes.setSubAccTypeCode(request.getSubAccTypeCode());
        ledgerTypes.setSubAccSinhala(request.getSubAccSinhala());
        ledgerTypes.setMainAccTypeSinhala(request.getMainAccTypeSinhala());
        LedgerTypes updated = ledgerTypesRepository.save(ledgerTypes);

        saveLog("LedgerTypes", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public LedgerTypesResponse getById(Long id) {

        return ledgerTypesRepository.findById(id).map(LedgerTypesServiceImpl::convert).orElse(null);
    }

    @Override
    public List<LedgerTypesResponse> getAll() {

        return ledgerTypesRepository.findByIsDeleted(Deleted.NO)
                .stream().map(LedgerTypesServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<LedgerTypesResponse> getByCategory(String category) {
        return ledgerTypesRepository.getByCategory(category)
                .stream().map(LedgerTypesServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<LedgerTypesResponse> getByMain(String category, String main) {
        return ledgerTypesRepository.getByMain(category,main)
                .stream().map(LedgerTypesServiceImpl::convert).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        LedgerTypes got = ledgerTypesRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        ledgerTypesRepository.save(got);

        saveLog("LedgerTypes", "Data Deleted - " + id);

        return 1;
    }

    private static LedgerTypesResponse convert(LedgerTypes ledgerTypes) {

        LedgerTypesResponse typeResponse = new LedgerTypesResponse();
        typeResponse.setAccCategory(ledgerTypes.getAccCategory());

        typeResponse.setMainAccType(ledgerTypes.getMainAccType());

        typeResponse.setMainAccTypeCode(ledgerTypes.getMainAccTypeCode());

        typeResponse.setSubAccType(ledgerTypes.getSubAccType());

        typeResponse.setSubAccTypeCode(ledgerTypes.getSubAccTypeCode());

        typeResponse.setSubAccSinhala(ledgerTypes.getSubAccSinhala());

        typeResponse.setMainAccTypeSinhala(ledgerTypes.getMainAccTypeSinhala());

        typeResponse.setId(ledgerTypes.getId());
        typeResponse.setCreatedBy(ledgerTypes.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(ledgerTypes.getCreatedDateTime()));
        typeResponse.setModifiedBy(ledgerTypes.getModifiedBy());
        typeResponse.setIsDeleted(ledgerTypes.getIsDeleted());

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