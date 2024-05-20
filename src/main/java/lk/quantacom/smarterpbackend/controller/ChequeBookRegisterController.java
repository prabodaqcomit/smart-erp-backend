package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.ChequeBookRegisterListRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeBookRegisterRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeBookRegisterUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ChequeBookRegisterResponse;
import lk.quantacom.smarterpbackend.service.ChequeBookRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ChequeBookRegister")
@RestController
@CrossOrigin
public class ChequeBookRegisterController {

    @Autowired
    private ChequeBookRegisterService chequeBookRegisterService;


    @PostMapping
    public ResponseEntity<ChequeBookRegisterResponse> save(@Valid @RequestBody ChequeBookRegisterRequest request){
        ChequeBookRegisterResponse save = chequeBookRegisterService.save(request);
        return ResponseEntity.ok(save);
    }

    @PostMapping("SaveList")
    public ResponseEntity<List<ChequeBookRegisterResponse>> saveList(@Valid @RequestBody List<ChequeBookRegisterListRequest> request){
        List<ChequeBookRegisterResponse> save = chequeBookRegisterService.saveList(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ChequeBookRegisterResponse> update(@Valid @RequestBody ChequeBookRegisterUpdateRequest request){
        ChequeBookRegisterResponse updated = chequeBookRegisterService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ChequeBookRegisterResponse> getById(@PathVariable("id") @NotBlank Long id){
        ChequeBookRegisterResponse get = chequeBookRegisterService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ChequeBookRegisterResponse>> getAll(){
        List<ChequeBookRegisterResponse> getall = chequeBookRegisterService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = chequeBookRegisterService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("getByBankAccountId/{bankAccountId}")
    public ResponseEntity<List<ChequeBookRegisterResponse>> getByBankAccountId(@PathVariable("bankAccountId") @NotBlank String bankAccountId){
        List<ChequeBookRegisterResponse> getall = chequeBookRegisterService.getByBankAccountId(bankAccountId);
        return ResponseEntity.ok(getall);
    }
}