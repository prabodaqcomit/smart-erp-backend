package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.LedgerHistoryRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerHistoryUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.CommonDebitAccountWithInvestmentResponse;
//import lk.quantacom.smarterpbackend.dto.response.GetGrnHeaderAndGrnPaymentResponse;
import lk.quantacom.smarterpbackend.dto.response.LedgerCommonSelectionsResponse;
import lk.quantacom.smarterpbackend.dto.response.LedgerHistoryResponse;
import lk.quantacom.smarterpbackend.service.LedgerCommonSelectionsService;
import lk.quantacom.smarterpbackend.service.LedgerHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("LedgerCommonSelections")
@RestController
@CrossOrigin
public class LedgerCommonSelectionsController {

    @Autowired
    private LedgerCommonSelectionsService ledgerCommonSelectionsService;


    @GetMapping("{debitAccNum}")
    public ResponseEntity<List<LedgerCommonSelectionsResponse>> getByDebitAccNum(@PathVariable("debitAccNum") @NotBlank String debitAccNum){
        List<LedgerCommonSelectionsResponse> getall = ledgerCommonSelectionsService.getByDebitAccNum(debitAccNum);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("CommonDebitAccountWithInvestment/{debitAccId}")
    public ResponseEntity<List<CommonDebitAccountWithInvestmentResponse>> getByCommonDebitAccountWithInvestment(@PathVariable("debitAccId") @NotBlank Integer debitAccId){
        List<CommonDebitAccountWithInvestmentResponse> getall = ledgerCommonSelectionsService.getByCommonDebitAccountWithInvestment(debitAccId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getBySearchChequeDepositCommons")
    public ResponseEntity<List<LedgerCommonSelectionsResponse>> getBySearchChequeDepositCommons(){
        List<LedgerCommonSelectionsResponse> getall = ledgerCommonSelectionsService.getBySearchChequeDepositCommons();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getAllByFrameIdAndPayModeAndDescription")
    public ResponseEntity<List<LedgerCommonSelectionsResponse>> getAllByFrameIdAndPayModeAndDescription(){
        List<LedgerCommonSelectionsResponse> getall = ledgerCommonSelectionsService.getAllByFrameIdAndPayModeAndDescription();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getAllByIssueMoneyForBorrower")
    public ResponseEntity<List<LedgerCommonSelectionsResponse>> getAllByIssueMoneyForBorrower(){
        List<LedgerCommonSelectionsResponse> getall = ledgerCommonSelectionsService.getAllByIssueMoneyForBorrower();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getAllByReceivedMoneyFromBorrower")
    public ResponseEntity<List<LedgerCommonSelectionsResponse>> getAllByReceivedMoneyFromBorrower(){
        List<LedgerCommonSelectionsResponse> getall = ledgerCommonSelectionsService.getAllByReceivedMoneyFromBorrower();
        return ResponseEntity.ok(getall);
    }
}