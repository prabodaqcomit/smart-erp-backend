package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.LedgerCommonSelectionsRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerCommonSelectionsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.CommonDebitAccountWithInvestmentResponse;
import lk.quantacom.smarterpbackend.dto.response.LedgerCommonSelectionsResponse;
import lk.quantacom.smarterpbackend.entity.LedgerCommonSelections;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LedgerCommonSelectionsService {

    LedgerCommonSelectionsResponse save(LedgerCommonSelectionsRequest request);

    LedgerCommonSelectionsResponse update(LedgerCommonSelectionsUpdateRequest request);

    LedgerCommonSelectionsResponse getById(Integer id);

    List<LedgerCommonSelectionsResponse> getAll();

    List<LedgerCommonSelectionsResponse> getAllByFrameIdAndPayModeAndDescription();

    List<LedgerCommonSelectionsResponse> getByDebitAccNum(String debitAccNum);

    List<CommonDebitAccountWithInvestmentResponse> getByCommonDebitAccountWithInvestment(Integer debitAccId);

    List<LedgerCommonSelectionsResponse> getBySearchChequeDepositCommons();

    List<LedgerCommonSelectionsResponse> getAllByIssueMoneyForBorrower();

    List<LedgerCommonSelectionsResponse> getAllByReceivedMoneyFromBorrower();

//    List<LedgerCommonSelectionsResponse> getAllActive();
//
//    Integer delete(Long id);


}