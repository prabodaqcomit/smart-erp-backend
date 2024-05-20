package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.TaxRequest;
import lk.quantacom.smarterpbackend.dto.request.TaxUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TaxResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaxService {

    TaxResponse save(TaxRequest request);

    TaxResponse update(TaxUpdateRequest request);

    TaxResponse getById(Long id);

    List<TaxResponse> getAll();

    TaxResponse getByName(String name);

    Integer delete(Long id);

}