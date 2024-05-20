package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.ReceivedChequesRequest;
import lk.quantacom.smarterpbackend.dto.request.ReceivedChequesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ReceivedChequesAndCustomerInterfaceResponse;
import lk.quantacom.smarterpbackend.dto.response.ReceivedChequesInterfaceResponse;
import lk.quantacom.smarterpbackend.dto.response.ReceivedChequesResponse;
import lk.quantacom.smarterpbackend.service.ReceivedChequesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ReceivedCheques")
@RestController
@CrossOrigin
public class ReceivedChequesController {

    @Autowired
    private ReceivedChequesService receivedChequesService;


    @PostMapping
    public ResponseEntity<ReceivedChequesResponse> save(@Valid @RequestBody ReceivedChequesRequest request){
        ReceivedChequesResponse save = receivedChequesService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ReceivedChequesResponse> update(@Valid @RequestBody ReceivedChequesUpdateRequest request){
        ReceivedChequesResponse updated = receivedChequesService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ReceivedChequesResponse> getById(@PathVariable("id") @NotBlank Long id){
        ReceivedChequesResponse get = receivedChequesService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ReceivedChequesResponse>> getAll(){
        List<ReceivedChequesResponse> getall = receivedChequesService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = receivedChequesService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("customerId/{customerId}")
    public ResponseEntity<List<ReceivedChequesResponse>> getAllByCustomerId(@PathVariable("customerId") @NotBlank Integer customerId){
        List<ReceivedChequesResponse> getall = receivedChequesService.getAllByCustomerId(customerId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("chequeNo/{chequeNo}")
    public ResponseEntity<List<ReceivedChequesResponse>> getAllByChequeNoGroupByChequeNo(@PathVariable("chequeNo") @NotBlank String chequeNo){
        List<ReceivedChequesResponse> getall = receivedChequesService.getAllByChequeNoGroupByChequeNo(chequeNo);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("bankCode/{bankCode}")
    public ResponseEntity<List<ReceivedChequesResponse>> getAllByBankCodeGroupByBankCode(@PathVariable("bankCode") @NotBlank String bankCode){
        List<ReceivedChequesResponse> getall = receivedChequesService.getAllByBankCodeGroupByBankCode(bankCode);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("branchCode/{branchCode}")
    public ResponseEntity<List<ReceivedChequesResponse>> getAllByBranchCodeGroupByBranchCode(@PathVariable("branchCode") @NotBlank String branchCode){
        List<ReceivedChequesResponse> getall = receivedChequesService.getAllByBranchCodeGroupByBranchCode(branchCode);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("customerId/{customerId}/startDate/{startDate}/endDate/{endDate}/branchId/{branchId}")
    public ResponseEntity<List<ReceivedChequesInterfaceResponse>> getByCustomerIdAndDateAndBranchId(@PathVariable("customerId") @NotBlank Integer customerId,
                                                                                                    @PathVariable("startDate") @NotBlank String startDate,
                                                                                                    @PathVariable("endDate") @NotBlank String endDate,
                                                                                                    @PathVariable("branchId") @NotBlank Integer branchId){
        List<ReceivedChequesInterfaceResponse> getall = receivedChequesService.getByCustomerIdAndDateAndBranchId(customerId,startDate,endDate,branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("chequeNo/{chequeNo}/startDate/{startDate}/endDate/{endDate}/branchId/{branchId}")
    public ResponseEntity<List<ReceivedChequesInterfaceResponse>> getByChequeNoAndDateAndBranchId(@PathVariable("chequeNo") @NotBlank String chequeNo,
                                                                                                    @PathVariable("startDate") @NotBlank String startDate,
                                                                                                    @PathVariable("endDate") @NotBlank String endDate,
                                                                                                    @PathVariable("branchId") @NotBlank Integer branchId){
        List<ReceivedChequesInterfaceResponse> getall = receivedChequesService.getByChequeNoAndDateAndBranchId(chequeNo,startDate,endDate,branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("bankCode/{bankCode}/startDate/{startDate}/endDate/{endDate}/branchId/{branchId}")
    public ResponseEntity<List<ReceivedChequesInterfaceResponse>> getByBankCodeAndDateAndBranchId(@PathVariable("bankCode") @NotBlank String bankCode,
                                                                                                    @PathVariable("startDate") @NotBlank String startDate,
                                                                                                    @PathVariable("endDate") @NotBlank String endDate,
                                                                                                    @PathVariable("branchId") @NotBlank Integer branchId){
        List<ReceivedChequesInterfaceResponse> getall = receivedChequesService.getByBankCodeAndDateAndBranchId(bankCode,startDate,endDate,branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("branchCode/{branchCode}/startDate/{startDate}/endDate/{endDate}/branchId/{branchId}")
    public ResponseEntity<List<ReceivedChequesInterfaceResponse>> getByBranchCodeAndDateAndBranchId(@PathVariable("branchCode") @NotBlank String branchCode,
                                                                                                    @PathVariable("startDate") @NotBlank String startDate,
                                                                                                    @PathVariable("endDate") @NotBlank String endDate,
                                                                                                    @PathVariable("branchId") @NotBlank Integer branchId){
        List<ReceivedChequesInterfaceResponse> getall = receivedChequesService.getByBranchCodeAndDateAndBranchId(branchCode,startDate,endDate,branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByCheques/startDate/{startDate}/endDate/{endDate}/branchId/{branchId}")
    public ResponseEntity<List<ReceivedChequesAndCustomerInterfaceResponse>> getByChequesAndDateAndBranchId(@PathVariable("startDate") @NotBlank String startDate,
                                                                                                            @PathVariable("endDate") @NotBlank String endDate,
                                                                                                            @PathVariable("branchId") @NotBlank Integer branchId){
        List<ReceivedChequesAndCustomerInterfaceResponse> getall = receivedChequesService.getByChequesAndDateAndBranchId(startDate,endDate,branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByChequeDate/startDate/{startDate}/endDate/{endDate}/branchId/{branchId}")
    public ResponseEntity<List<ReceivedChequesAndCustomerInterfaceResponse>> getByChequeDateAndDateAndBranchId(@PathVariable("startDate") @NotBlank String startDate,
                                                                                                            @PathVariable("endDate") @NotBlank String endDate,
                                                                                                            @PathVariable("branchId") @NotBlank Integer branchId){
        List<ReceivedChequesAndCustomerInterfaceResponse> getall = receivedChequesService.getByChequeDateAndDateAndBranchId(startDate,endDate,branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByThirdPartyPayment/startDate/{startDate}/endDate/{endDate}/branchId/{branchId}")
    public ResponseEntity<List<ReceivedChequesAndCustomerInterfaceResponse>> getByDepoDateAndDateAndBranchId(@PathVariable("startDate") @NotBlank String startDate,
                                                                                                               @PathVariable("endDate") @NotBlank String endDate,
                                                                                                               @PathVariable("branchId") @NotBlank Integer branchId){
        List<ReceivedChequesAndCustomerInterfaceResponse> getall = receivedChequesService.getByDepoDateAndDateAndBranchId(startDate,endDate,branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByBankDepositDate/startDate/{startDate}/endDate/{endDate}/branchId/{branchId}")
    public ResponseEntity<List<ReceivedChequesAndCustomerInterfaceResponse>> getByDepositDateAndDateAndBranchId(@PathVariable("startDate") @NotBlank String startDate,
                                                                                                             @PathVariable("endDate") @NotBlank String endDate,
                                                                                                             @PathVariable("branchId") @NotBlank Integer branchId){
        List<ReceivedChequesAndCustomerInterfaceResponse> getall = receivedChequesService.getByDepositDateAndDateAndBranchId(startDate,endDate,branchId);
        return ResponseEntity.ok(getall);
    }

}