package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.ChequeIssueNoteRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeIssueNoteUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ChequeIssueNoteResponse;
import lk.quantacom.smarterpbackend.service.ChequeIssueNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ChequeIssueNote")
@RestController
@CrossOrigin
public class ChequeIssueNoteController {

    @Autowired
    private ChequeIssueNoteService chequeIssueNoteService;


    @PostMapping
    public ResponseEntity<ChequeIssueNoteResponse> save(@Valid @RequestBody ChequeIssueNoteRequest request){
        ChequeIssueNoteResponse save = chequeIssueNoteService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ChequeIssueNoteResponse> update(@Valid @RequestBody ChequeIssueNoteUpdateRequest request){
        ChequeIssueNoteResponse updated = chequeIssueNoteService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ChequeIssueNoteResponse> getById(@PathVariable("id") @NotBlank Long id){
        ChequeIssueNoteResponse get = chequeIssueNoteService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ChequeIssueNoteResponse>> getAll(){
        List<ChequeIssueNoteResponse> getall = chequeIssueNoteService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = chequeIssueNoteService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("getByUpdateWindow/{updateWindow}")
    public ResponseEntity<List<ChequeIssueNoteResponse>> getByUpdateWindow(@PathVariable("updateWindow") @NotBlank String updateWindow){
        List<ChequeIssueNoteResponse> getall = chequeIssueNoteService.getByUpdateWindow(updateWindow);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByPayeeName/{payeeName}")
    public ResponseEntity<List<ChequeIssueNoteResponse>> getByPayeeName(@PathVariable("payeeName") @NotBlank String payeeName){
        List<ChequeIssueNoteResponse> getall = chequeIssueNoteService.getByPayeeName(payeeName);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByAccName/{accName}")
    public ResponseEntity<List<ChequeIssueNoteResponse>> getByAccName(@PathVariable("accName") @NotBlank String accName){
        List<ChequeIssueNoteResponse> getall = chequeIssueNoteService.getByAccName(accName);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByBankCode/{bankCode}")
    public ResponseEntity<List<ChequeIssueNoteResponse>> getByBankCode(@PathVariable("bankCode") @NotBlank String bankCode){
        List<ChequeIssueNoteResponse> getall = chequeIssueNoteService.getByBankCode(bankCode);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("findByBranchCode/{branchCode}")
    public ResponseEntity<List<ChequeIssueNoteResponse>> findByBranchCode(@PathVariable("branchCode") @NotBlank String branchCode){
        List<ChequeIssueNoteResponse> getall = chequeIssueNoteService.findByBranchCode(branchCode);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("findByIsDeletedAndChequeNo/{chequeNo}")
    public ResponseEntity<List<ChequeIssueNoteResponse>> findByIsDeletedAndChequeNo(@PathVariable("chequeNo") @NotBlank String chequeNo){
        List<ChequeIssueNoteResponse> getall = chequeIssueNoteService.findByIsDeletedAndChequeNo(chequeNo);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByAccNo/{chequeNo}")
    public ResponseEntity<List<String>> getByAccNo(@PathVariable("chequeNo") @NotBlank String chequeNo){
        List<String> getall = chequeIssueNoteService.getByAccNo(chequeNo);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByPayeeName/{payeeName}/{Branch}/{from}/{to}")
    public ResponseEntity<List<ChequeIssueNoteResponse>> findByPayeeNameAndBranchCodeAndIssueDateBetween(@PathVariable("payeeName") @NotBlank String payeeName,
                                                                                        @PathVariable("Branch") @NotBlank String Branch,
                                                                                        @PathVariable("from") @NotBlank String from,
                                                                                        @PathVariable("to") @NotBlank String to){
        List<ChequeIssueNoteResponse> getall = chequeIssueNoteService.findByPayeeNameAndBranchCodeAndIssueDateBetween(payeeName,Branch,from,to);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByUpdateWindow/{UpdateWindow}/{Branch}/{from}/{to}")
    public ResponseEntity<List<ChequeIssueNoteResponse>> findByUpdateWindowAndBranchCodeAndIssueDateBetween(@PathVariable("UpdateWindow") @NotBlank String UpdateWindow,
                                                                                                         @PathVariable("Branch") @NotBlank String Branch,
                                                                                                         @PathVariable("from") @NotBlank String from,
                                                                                                         @PathVariable("to") @NotBlank String to){
        List<ChequeIssueNoteResponse> getall = chequeIssueNoteService.findByUpdateWindowAndBranchCodeAndIssueDateBetween(UpdateWindow,Branch,from,to);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByBankCode/{BankCode}/{Branch}/{from}/{to}")
    public ResponseEntity<List<ChequeIssueNoteResponse>> findByBankCodeAndBranchCodeAndIssueDateBetween(@PathVariable("BankCode") @NotBlank String BankCode,
                                                                                                         @PathVariable("Branch") @NotBlank String Branch,
                                                                                                         @PathVariable("from") @NotBlank String from,
                                                                                                         @PathVariable("to") @NotBlank String to){
        List<ChequeIssueNoteResponse> getall = chequeIssueNoteService.findByBankCodeAndBranchCodeAndIssueDateBetween(BankCode,Branch,from,to);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByChequeNo/{ChequeNo}/{Branch}/{from}/{to}")
    public ResponseEntity<List<ChequeIssueNoteResponse>> findByChequeNoAndBranchCodeAndIssueDateBetween(@PathVariable("ChequeNo") @NotBlank String ChequeNo,
                                                                                                         @PathVariable("Branch") @NotBlank String Branch,
                                                                                                         @PathVariable("from") @NotBlank String from,
                                                                                                         @PathVariable("to") @NotBlank String to){
        List<ChequeIssueNoteResponse> getall = chequeIssueNoteService.findByChequeNoAndBranchCodeAndIssueDateBetween(ChequeNo,Branch,from,to);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByAccName/{accName}/{Branch}/{from}/{to}")
    public ResponseEntity<List<ChequeIssueNoteResponse>> findByAccNameAndBranchCodeAndIssueDateBetween(@PathVariable("accName") @NotBlank String accName,
                                                                                                        @PathVariable("Branch") @NotBlank String Branch,
                                                                                                        @PathVariable("from") @NotBlank String from,
                                                                                                        @PathVariable("to") @NotBlank String to){
        List<ChequeIssueNoteResponse> getall = chequeIssueNoteService.findByAccNameAndBranchCodeAndIssueDateBetween(accName,Branch,from,to);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByDateRangeIssueDate/{Branch}/{from}/{to}")
    public ResponseEntity<List<ChequeIssueNoteResponse>> findByBranchCodeAndIssueDateBetween( @PathVariable("Branch") @NotBlank String Branch,
                                                                                                         @PathVariable("from") @NotBlank String from,
                                                                                                         @PathVariable("to") @NotBlank String to){
        List<ChequeIssueNoteResponse> getall = chequeIssueNoteService.findByAccNameAndBranchCodeAndIssueDateBetween(Branch,from,to);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByDateRangeChequeDate/{Branch}/{from}/{to}")
    public ResponseEntity<List<ChequeIssueNoteResponse>> findByBranchCodeAndChequeDateBetween(  @PathVariable("Branch") @NotBlank String Branch,
                                                                                                         @PathVariable("from") @NotBlank String from,
                                                                                                         @PathVariable("to") @NotBlank String to){
        List<ChequeIssueNoteResponse> getall = chequeIssueNoteService.findByAccNameAndBranchCodeAndChequeDateBetween(Branch,from,to);
        return ResponseEntity.ok(getall);
    }


}