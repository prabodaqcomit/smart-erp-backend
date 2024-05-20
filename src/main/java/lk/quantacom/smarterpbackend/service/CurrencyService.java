package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.CurrencyRequest;
import lk.quantacom.smarterpbackend.dto.request.CurrencyUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.CurrencyResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CurrencyService {

    CurrencyResponse save(CurrencyRequest request);

    CurrencyResponse update(CurrencyUpdateRequest request);

    CurrencyResponse getById(Long id);

    List<CurrencyResponse> getAll();

    List<CurrencyResponse> getAllActive();

    Integer delete(Long id);
}