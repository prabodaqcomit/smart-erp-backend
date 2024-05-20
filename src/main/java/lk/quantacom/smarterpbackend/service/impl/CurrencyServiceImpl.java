package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.CurrencyRequest;
import lk.quantacom.smarterpbackend.dto.request.CurrencyUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.CurrencyResponse;
import lk.quantacom.smarterpbackend.entity.Currency;
import lk.quantacom.smarterpbackend.repository.CurrencyRepository;
import lk.quantacom.smarterpbackend.service.CurrencyService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.utils.Settings;
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
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Override
    @Transactional
    public CurrencyResponse save(CurrencyRequest request) {

        Currency currency = new Currency();
        currency.setCurrency(request.getCurrency());
        currency.setRate(request.getRate());
        currency.setCurrencySymbol(request.getCurrencySymbol());
        currency.setCurrencyDescription(request.getCurrencyDescription());
        currency.setLocalCurrency(Settings.readSettings("DEFAULT_CURRENCY"));
        currency.setIsDeleted(Deleted.NO);
        Currency save = currencyRepository.save(currency);

        saveLog("Currency", "Data Saved - " + save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public CurrencyResponse update(CurrencyUpdateRequest request) {

        Currency currency = currencyRepository.findById(request.getId()).orElse(null);
        if (currency == null) {
            return null;
        }

        currency.setId(request.getId());
        currency.setCurrency(request.getCurrency());
        currency.setRate(request.getRate());
        currency.setCurrencySymbol(request.getCurrencySymbol());
        currency.setCurrencyDescription(request.getCurrencyDescription());
        currency.setLocalCurrency(Settings.readSettings("DEFAULT_CURRENCY"));
        currency.setIsDeleted(request.getIsDeleted());
        Currency updated = currencyRepository.save(currency);

        saveLog("Currency", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public CurrencyResponse getById(Long id) {

        return currencyRepository.findById(id).map(CurrencyServiceImpl::convert).orElse(null);
    }

    @Override
    public List<CurrencyResponse> getAll() {

        return currencyRepository.findAll()
                .stream().map(CurrencyServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<CurrencyResponse> getAllActive() {

        return currencyRepository.findByIsDeleted(Deleted.NO)
                .stream().map(CurrencyServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        Currency got = currencyRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        currencyRepository.save(got);

        saveLog("Currency", "Data Deleted - " + id);

        return 1;
    }

    public static CurrencyResponse convert(Currency currency) {

        CurrencyResponse typeResponse = new CurrencyResponse();
        typeResponse.setCurrency(currency.getCurrency());

        typeResponse.setRate(currency.getRate());

        typeResponse.setCurrencySymbol(currency.getCurrencySymbol());

        typeResponse.setCurrencyDescription(currency.getCurrencyDescription());

        typeResponse.setLocalCurrency(currency.getLocalCurrency());

        typeResponse.setId(currency.getId());
        typeResponse.setCreatedBy(currency.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(currency.getCreatedDateTime()));
        typeResponse.setModifiedBy(currency.getModifiedBy());
        typeResponse.setIsDeleted(currency.getIsDeleted());

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