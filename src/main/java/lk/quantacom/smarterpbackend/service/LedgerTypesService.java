package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.LedgerTypesRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerTypesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerTypesResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LedgerTypesService {

    LedgerTypesResponse save(LedgerTypesRequest request);

    LedgerTypesResponse update(LedgerTypesUpdateRequest request);

    LedgerTypesResponse getById(Long id);

    List<LedgerTypesResponse> getAll();

    List<LedgerTypesResponse> getByCategory(String category);

    List<LedgerTypesResponse> getByMain(String category,String main);

    Integer delete(Long id);
}