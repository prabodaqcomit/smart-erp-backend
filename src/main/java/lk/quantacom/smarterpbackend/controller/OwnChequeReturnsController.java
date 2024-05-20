package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.OwnChequeReturnsRequest;
import lk.quantacom.smarterpbackend.dto.request.OwnChequeReturnsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.service.OwnChequeReturnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("OwnChequeReturns")
@RestController
@CrossOrigin
public class OwnChequeReturnsController {

    @Autowired
    private OwnChequeReturnsService ownChequeReturnsService;


    @PostMapping
    public ResponseEntity<OwnChequeReturnsResponse> save(@Valid @RequestBody OwnChequeReturnsRequest request){
        OwnChequeReturnsResponse save = ownChequeReturnsService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<OwnChequeReturnsResponse> update(@Valid @RequestBody OwnChequeReturnsUpdateRequest request){
        OwnChequeReturnsResponse updated = ownChequeReturnsService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<OwnChequeReturnsResponse> getById(@PathVariable("id") @NotBlank Long id){
        OwnChequeReturnsResponse get = ownChequeReturnsService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<OwnChequeReturnsResponse>> getAll(){
        List<OwnChequeReturnsResponse> getall = ownChequeReturnsService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = ownChequeReturnsService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("chequeNo/{chequeNo}")
    public ResponseEntity<List<OwnChequeReturnsResponse>> findByChequeNo(@PathVariable("chequeNo") @NotBlank String chequeNo){
        List<OwnChequeReturnsResponse> getall = ownChequeReturnsService.findByChequeNo(chequeNo);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("chequeNo/{chequeNo}/branchId/{branchId}")
    public ResponseEntity<List<OwnChequeReturnsResponse>> findByChequeNoAndBranchId(@PathVariable("chequeNo") @NotBlank String chequeNo,
                                                                                    @PathVariable("branchId") @NotBlank String branchId){
        List<OwnChequeReturnsResponse> getall = ownChequeReturnsService.findByChequeNoAndBranchId(chequeNo,branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByPayeeName/{payeeName}")
    public ResponseEntity<List<getByPayeeNameResponse>> getByPayeeName(@PathVariable("payeeName") @NotBlank String payeeName){
        List<getByPayeeNameResponse> getall = ownChequeReturnsService.getByPayeeName(payeeName);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByChequeNo/{chequeNo}")
    public ResponseEntity<List<getByChequeNoResponse>> getByChequeNo(@PathVariable("chequeNo") @NotBlank String chequeNo){
        List<getByChequeNoResponse> getall = ownChequeReturnsService.getByChequeNo(chequeNo);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByBankCode/{bankCode}")
    public ResponseEntity<List<getByBankCodeResponse>> getByBankCode(@PathVariable("bankCode") @NotBlank String bankCode){
        List<getByBankCodeResponse> getall = ownChequeReturnsService.getByBankCode(bankCode);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByBranchCode/{BranchCode}")
    public ResponseEntity<List<getByBranchCodeResponse>> getByBranchCode(@PathVariable("BranchCode") @NotBlank String BranchCode){
        List<getByBranchCodeResponse> getall = ownChequeReturnsService.getByBranchCode(BranchCode);
        return ResponseEntity.ok(getall);
    }
}