package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.LedgerBankAccountCreditRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerBankAccountCreditUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerBankAccountCreditResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LedgerBankAccountCreditService {

    LedgerBankAccountCreditResponse save(LedgerBankAccountCreditRequest request);

    LedgerBankAccountCreditResponse update(LedgerBankAccountCreditUpdateRequest request);

    LedgerBankAccountCreditResponse getById(Long id);

    List<LedgerBankAccountCreditResponse> getAll();

    Integer delete(Long id);

}