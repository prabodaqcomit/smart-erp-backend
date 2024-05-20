package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.LedgerCashbookCreditRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerCashbookCreditUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerCashbookCreditResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LedgerCashbookCreditService {

    LedgerCashbookCreditResponse save(LedgerCashbookCreditRequest request);

    LedgerCashbookCreditResponse update(LedgerCashbookCreditUpdateRequest request);

    LedgerCashbookCreditResponse getById(Long id);

    List<LedgerCashbookCreditResponse> getAll();


    Integer delete(Long id);
}