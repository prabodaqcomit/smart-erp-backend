package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.LedgerBankAccountDebitRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerBankAccountDebitUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerBankAccountDebitResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LedgerBankAccountDebitService {

    LedgerBankAccountDebitResponse save(LedgerBankAccountDebitRequest request);

    LedgerBankAccountDebitResponse update(LedgerBankAccountDebitUpdateRequest request);

    LedgerBankAccountDebitResponse getById(Long id);

    List<LedgerBankAccountDebitResponse> getAll();


    Integer delete(Long id);
}