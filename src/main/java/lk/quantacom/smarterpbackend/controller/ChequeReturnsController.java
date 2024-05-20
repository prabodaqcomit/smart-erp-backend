package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.ChequeReturnsRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeReturnsSaveListRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeReturnsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.service.ChequeReturnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ChequeReturns")
@RestController
@CrossOrigin
public class ChequeReturnsController {

    @Autowired
    private ChequeReturnsService chequeReturnsService;


//    @PostMapping
//    public ResponseEntity<ChequeReturnsResponse> save(@Valid @RequestBody ChequeReturnsRequest request){
//        ChequeReturnsResponse save = chequeReturnsService.save(request);
//        return ResponseEntity.ok(save);
//    }
//
//    @PutMapping
//    public ResponseEntity<ChequeReturnsResponse> update(@Valid @RequestBody ChequeReturnsUpdateRequest request){
//        ChequeReturnsResponse updated = chequeReturnsService.update(request);
//        if(updated==null){
//            return  ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updated);
//    }


    @GetMapping("{id}")
    public ResponseEntity<ChequeReturnsResponse> getById(@PathVariable("id") @NotBlank Long id){
        ChequeReturnsResponse get = chequeReturnsService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ChequeReturnsResponse>> getAll(){
        List<ChequeReturnsResponse> getall = chequeReturnsService.getAll();
        return ResponseEntity.ok(getall);
    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
//        int deleted = chequeReturnsService.delete(id);
//        if(deleted==0){
//            return  ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(deleted);
//    }

    @GetMapping("getBy/customerId/{customerId}/branchId/{branchId}")
    public ResponseEntity<List<ChequeReturnsResponse>> getByCustomerIdAndBranchId(@PathVariable("customerId") @NotBlank Integer customerId,@PathVariable("branchId") @NotBlank String branchId){
        List<ChequeReturnsResponse> getall = chequeReturnsService.getByCustomerIdAndBranchId(customerId,branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getBy/bankCode/{bankCode}")
    public ResponseEntity<List<ChequeReturnsResponse>> getByBankCode(@PathVariable("bankCode") @NotBlank String bankCode){
        List<ChequeReturnsResponse> getall = chequeReturnsService.getByBankCode(bankCode);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getBy/branchCode/{branchCode}")
    public ResponseEntity<List<ChequeReturnsResponse>> getByBranchCode(@PathVariable("branchCode") @NotBlank String branchCode){
        List<ChequeReturnsResponse> getall = chequeReturnsService.getByBranchCode(branchCode);
        return ResponseEntity.ok(getall);
    }

    @PostMapping("SaveCustomerChequeReturn")
    public ResponseEntity<List<ChequeReturnsResponse>> saveCustomerChequeReturn(@Valid @RequestBody ChequeReturnsSaveListRequest request){
        List<ChequeReturnsResponse> save = chequeReturnsService.saveCustomerChequeReturn(request);
        return ResponseEntity.ok(save);
    }

    @GetMapping("SearchForCustomerChRtn")
    public ResponseEntity<List<CustomerChequeReturnSearch>> getAllForCusChRTN(){
        List<CustomerChequeReturnSearch> getall = chequeReturnsService.getForCusRtn();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("FromReceivedCheques/{chequeNo}")
    public ResponseEntity<List<GetReceivedCheckWiithCustomer>> getFromReceivedCheques(@PathVariable("chequeNo") @NotBlank String chequeNo) {
        List<GetReceivedCheckWiithCustomer> getall = chequeReturnsService.getFromReceivedCheques(chequeNo);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("InvoicesOfCheque/{chequeNo}")
    public ResponseEntity<List<GetInvOfCheque>> getInvoicesOfCheque(@PathVariable("chequeNo") @NotBlank String chequeNo){
        return ResponseEntity.ok(chequeReturnsService.getInvoicesOfCheque(chequeNo));
    };

    @GetMapping("CreditAcc/{chequeNo}")
    public ResponseEntity<LadgerAccountResponse> getCreditAcc(@PathVariable("chequeNo") @NotBlank String  chequeNo) {
        LadgerAccountResponse get = chequeReturnsService.getCreditAcc(chequeNo);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }

}