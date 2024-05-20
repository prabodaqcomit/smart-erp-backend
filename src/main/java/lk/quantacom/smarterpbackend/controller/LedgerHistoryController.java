package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.LedgerHistoryRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerHistoryUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerHistoryResponse;
import lk.quantacom.smarterpbackend.service.LedgerHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("LedgerHistory")
@RestController
@CrossOrigin
public class LedgerHistoryController {

    @Autowired
    private LedgerHistoryService ledgerHistoryService;


    @PostMapping
    public ResponseEntity<LedgerHistoryResponse> save(@Valid @RequestBody LedgerHistoryRequest request){
        LedgerHistoryResponse save = ledgerHistoryService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<LedgerHistoryResponse> update(@Valid @RequestBody LedgerHistoryUpdateRequest request){
        LedgerHistoryResponse updated = ledgerHistoryService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<LedgerHistoryResponse> getById(@PathVariable("id") @NotBlank Long id){
        LedgerHistoryResponse get = ledgerHistoryService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<LedgerHistoryResponse>> getAll(){
        List<LedgerHistoryResponse> getall = ledgerHistoryService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = ledgerHistoryService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}