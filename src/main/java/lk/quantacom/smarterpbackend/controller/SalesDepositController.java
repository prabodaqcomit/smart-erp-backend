package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.SalesDepositRequest;
import lk.quantacom.smarterpbackend.dto.request.SalesDepositUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ReceivedChequesAndCustomerInterfaceResponse;
import lk.quantacom.smarterpbackend.dto.response.SalesDepositResponse;
import lk.quantacom.smarterpbackend.dto.response.getBySalesReceiptAndSalesDepositResponse;
import lk.quantacom.smarterpbackend.service.SalesDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("SalesDeposit")
@RestController
@CrossOrigin
public class SalesDepositController {

    @Autowired
    private SalesDepositService salesDepositService;


    @PostMapping
    public ResponseEntity<SalesDepositResponse> save(@Valid @RequestBody SalesDepositRequest request){
        SalesDepositResponse save = salesDepositService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<SalesDepositResponse> update(@Valid @RequestBody SalesDepositUpdateRequest request){
        SalesDepositResponse updated = salesDepositService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<SalesDepositResponse> getById(@PathVariable("id") @NotBlank Long id){
        SalesDepositResponse get = salesDepositService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<SalesDepositResponse>> getAll(){
        List<SalesDepositResponse> getall = salesDepositService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = salesDepositService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("customerId/{customerId}/branchId/{branchId}")
    public ResponseEntity<List<SalesDepositResponse>> getByCustomerIdAndBranchId(@PathVariable("customerId") @NotBlank Integer customerId,@PathVariable("branchId") @NotBlank String branchId){
        List<SalesDepositResponse> getall = salesDepositService.getByCustomerIdAndBranchId(customerId,branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByCustomerName/customerName/{customerName}/startDate/{startDate}/endDate/{endDate}/branchId/{branchId}")
    public ResponseEntity<List<getBySalesReceiptAndSalesDepositResponse>> getSalesDepositAndReceiptByCustomerName(@PathVariable("customerName") @NotBlank String customerName,
                                                                                                             @PathVariable("startDate") @NotBlank String startDate,
                                                                                                             @PathVariable("endDate") @NotBlank String endDate,
                                                                                                             @PathVariable("branchId") @NotBlank Integer branchId){
        List<getBySalesReceiptAndSalesDepositResponse> getall = salesDepositService.getSalesDepositAndReceiptByCustomerName(customerName,startDate,endDate,branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getSalesDepositAndReceiptByBankName/bankName/{bankName}/startDate/{startDate}/endDate/{endDate}/branchId/{branchId}")
    public ResponseEntity<List<getBySalesReceiptAndSalesDepositResponse>> getSalesDepositAndReceiptByBankName(@PathVariable("bankName") @NotBlank String bankName,
                                                                                                                  @PathVariable("startDate") @NotBlank String startDate,
                                                                                                                  @PathVariable("endDate") @NotBlank String endDate,
                                                                                                                  @PathVariable("branchId") @NotBlank Integer branchId){
        List<getBySalesReceiptAndSalesDepositResponse> getall = salesDepositService.getSalesDepositAndReceiptByBankName(bankName,startDate,endDate,branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getSalesDepositAndReceiptByBranchName/branchName/{branchName}/startDate/{startDate}/endDate/{endDate}/branchId/{branchId}")
    public ResponseEntity<List<getBySalesReceiptAndSalesDepositResponse>> getSalesDepositAndReceiptByBranchName(@PathVariable("branchName") @NotBlank String branchName,
                                                                                                                  @PathVariable("startDate") @NotBlank String startDate,
                                                                                                                  @PathVariable("endDate") @NotBlank String endDate,
                                                                                                                  @PathVariable("branchId") @NotBlank Integer branchId){
        List<getBySalesReceiptAndSalesDepositResponse> getall = salesDepositService.getSalesDepositAndReceiptByBranchName(branchName,startDate,endDate,branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getSalesDepositAndReceiptByAccNo/accNo/{accNo}/startDate/{startDate}/endDate/{endDate}/branchId/{branchId}")
    public ResponseEntity<List<getBySalesReceiptAndSalesDepositResponse>> getSalesDepositAndReceiptByAccNo(@PathVariable("accNo") @NotBlank String accNo,
                                                                                                                  @PathVariable("startDate") @NotBlank String startDate,
                                                                                                                  @PathVariable("endDate") @NotBlank String endDate,
                                                                                                                  @PathVariable("branchId") @NotBlank Integer branchId){
        List<getBySalesReceiptAndSalesDepositResponse> getall = salesDepositService.getSalesDepositAndReceiptByAccNo(accNo,startDate,endDate,branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getSalesDepositAndReceiptByAccName/accName/{accName}/startDate/{startDate}/endDate/{endDate}/branchId/{branchId}")
    public ResponseEntity<List<getBySalesReceiptAndSalesDepositResponse>> getSalesDepositAndReceiptByAccName(@PathVariable("accName") @NotBlank String accName,
                                                                                                                  @PathVariable("startDate") @NotBlank String startDate,
                                                                                                                  @PathVariable("endDate") @NotBlank String endDate,
                                                                                                                  @PathVariable("branchId") @NotBlank Integer branchId){
        List<getBySalesReceiptAndSalesDepositResponse> getall = salesDepositService.getSalesDepositAndReceiptByAccName(accName,startDate,endDate,branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getSalesDepositAndReceiptByDateRange/startDate/{startDate}/endDate/{endDate}/branchId/{branchId}")
    public ResponseEntity<List<getBySalesReceiptAndSalesDepositResponse>> getSalesDepositAndReceiptByDateRange(@PathVariable("startDate") @NotBlank String startDate,
                                                                                                                  @PathVariable("endDate") @NotBlank String endDate,
                                                                                                                  @PathVariable("branchId") @NotBlank Integer branchId){
        List<getBySalesReceiptAndSalesDepositResponse> getall = salesDepositService.getSalesDepositAndReceiptByDateRange( startDate,endDate,branchId);
        return ResponseEntity.ok(getall);
    }
}