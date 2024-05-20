package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.LedgerBankHistoryRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerBankHistoryUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerBankHistoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LedgerBankHistoryService {

    LedgerBankHistoryResponse save(LedgerBankHistoryRequest request);

    LedgerBankHistoryResponse update(LedgerBankHistoryUpdateRequest request);

    LedgerBankHistoryResponse getById(Long id);

    List<LedgerBankHistoryResponse> getAll();


    Integer delete(Long id);
}