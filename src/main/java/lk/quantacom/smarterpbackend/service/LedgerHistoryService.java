package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.LedgerHistoryRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerHistoryUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerHistoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LedgerHistoryService {

    LedgerHistoryResponse save(LedgerHistoryRequest request);

    LedgerHistoryResponse update(LedgerHistoryUpdateRequest request);

    LedgerHistoryResponse getById(Long id);

    List<LedgerHistoryResponse> getAll();


    Integer delete(Long id);
}