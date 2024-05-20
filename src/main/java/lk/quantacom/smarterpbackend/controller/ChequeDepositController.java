package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.ChequeDepositAndReceivedRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeDepositRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeDepositUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ChequeDepoReq;
import lk.quantacom.smarterpbackend.dto.response.ChequeDepositResponse;
import lk.quantacom.smarterpbackend.service.ChequeDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ChequeDeposit")
@RestController
@CrossOrigin
public class ChequeDepositController {

    @Autowired
    private ChequeDepositService chequeDepositService;


    @PostMapping
    public ResponseEntity<ChequeDepositResponse> save(@Valid @RequestBody ChequeDepositRequest request){
        ChequeDepositResponse save = chequeDepositService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ChequeDepositResponse> update(@Valid @RequestBody ChequeDepositUpdateRequest request){
        ChequeDepositResponse updated = chequeDepositService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ChequeDepositResponse> getById(@PathVariable("id") @NotBlank Long id){
        ChequeDepositResponse get = chequeDepositService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ChequeDepositResponse>> getAll(){
        List<ChequeDepositResponse> getall = chequeDepositService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = chequeDepositService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("ForChRTN")
    ResponseEntity<List<ChequeDepoReq>> getForChRtn() {
        return ResponseEntity.ok(chequeDepositService.getForChRtn());
    }

    @PostMapping("saveAll")
    public ResponseEntity<List<ChequeDepositResponse>> saveAll(@Valid @RequestBody List<ChequeDepositAndReceivedRequest> requests){
        List<ChequeDepositResponse> save = chequeDepositService.saveAll(requests);
        return ResponseEntity.ok(save);
    }

}