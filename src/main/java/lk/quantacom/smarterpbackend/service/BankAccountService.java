package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.BankAccountRequest;
import lk.quantacom.smarterpbackend.dto.request.BankAccountUpdateRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerAccountNameRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerAccountNameUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BankAccountResponse;
import lk.quantacom.smarterpbackend.entity.BankAccount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankAccountService {

    BankAccountResponse save(BankAccountRequest request);

    BankAccountResponse update(BankAccountUpdateRequest request);

    BankAccountResponse getById(Long id);

    List<BankAccountResponse> getAll();

    Integer delete(Long id);

    List<BankAccountResponse> getByBankNameAndAccNo(String bankName,String accNo);

    List<BankAccountResponse> getByAccNo(String accNo);

    List<BankAccountResponse> saveWithLedgerAccountName(List<LedgerAccountNameRequest> requests);

    BankAccountResponse getByAccNum(String accNo);

    BankAccountResponse updateBankLedgerAccount(LedgerAccountNameUpdateRequest request);

    List<BankAccountResponse> getByBankName(String bankName);

    List<BankAccountResponse> getByBranchName(String branchName);

    List<BankAccountResponse> getByAccName(String accName);



}