package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.LedgerCashbookCreditRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerCashbookCreditUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerCashbookCreditResponse;
import lk.quantacom.smarterpbackend.service.LedgerCashbookCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("LedgerCashbookCredit")
@RestController
@CrossOrigin
public class LedgerCashbookCreditController {

    @Autowired
    private LedgerCashbookCreditService ledgerCashbookCreditService;


    @PostMapping
    public ResponseEntity<LedgerCashbookCreditResponse> save(@Valid @RequestBody LedgerCashbookCreditRequest request){
        LedgerCashbookCreditResponse save = ledgerCashbookCreditService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<LedgerCashbookCreditResponse> update(@Valid @RequestBody LedgerCashbookCreditUpdateRequest request){
        LedgerCashbookCreditResponse updated = ledgerCashbookCreditService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<LedgerCashbookCreditResponse> getById(@PathVariable("id") @NotBlank Long id){
        LedgerCashbookCreditResponse get = ledgerCashbookCreditService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<LedgerCashbookCreditResponse>> getAll(){
        List<LedgerCashbookCreditResponse> getall = ledgerCashbookCreditService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = ledgerCashbookCreditService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}