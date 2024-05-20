package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.LedgerGeneralJournalRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerGeneralJournalUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerGeneralJournalResponse;
import lk.quantacom.smarterpbackend.service.LedgerGeneralJournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("LedgerGeneralJournal")
@RestController
@CrossOrigin
public class LedgerGeneralJournalController {

    @Autowired
    private LedgerGeneralJournalService ledgerGeneralJournalService;


    @PostMapping
    public ResponseEntity<LedgerGeneralJournalResponse> save(@Valid @RequestBody LedgerGeneralJournalRequest request){
        LedgerGeneralJournalResponse save = ledgerGeneralJournalService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<LedgerGeneralJournalResponse> update(@Valid @RequestBody LedgerGeneralJournalUpdateRequest request){
        LedgerGeneralJournalResponse updated = ledgerGeneralJournalService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<LedgerGeneralJournalResponse> getById(@PathVariable("id") @NotBlank Long id){
        LedgerGeneralJournalResponse get = ledgerGeneralJournalService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<LedgerGeneralJournalResponse>> getAll(){
        List<LedgerGeneralJournalResponse> getall = ledgerGeneralJournalService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = ledgerGeneralJournalService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}