package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.ExpenceHistoryRequest;
import lk.quantacom.smarterpbackend.dto.request.ExpenceHistoryUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ExpenceHistoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenceHistoryService {

    ExpenceHistoryResponse save(ExpenceHistoryRequest request);

    ExpenceHistoryResponse update(ExpenceHistoryUpdateRequest request);

    ExpenceHistoryResponse getById(Long id);

    List<ExpenceHistoryResponse> getAll();


    Integer delete(Long id);
}