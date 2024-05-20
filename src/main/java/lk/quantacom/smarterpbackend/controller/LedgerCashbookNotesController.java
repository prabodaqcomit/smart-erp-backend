package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.LedgerCashbookNotesRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerCashbookNotesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerCashbookNotesResponse;
import lk.quantacom.smarterpbackend.service.LedgerCashbookNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("LedgerCashbookNotes")
@RestController
@CrossOrigin
public class LedgerCashbookNotesController {

    @Autowired
    private LedgerCashbookNotesService ledgerCashbookNotesService;


    @PostMapping
    public ResponseEntity<LedgerCashbookNotesResponse> save(@Valid @RequestBody LedgerCashbookNotesRequest request){
        LedgerCashbookNotesResponse save = ledgerCashbookNotesService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<LedgerCashbookNotesResponse> update(@Valid @RequestBody LedgerCashbookNotesUpdateRequest request){
        LedgerCashbookNotesResponse updated = ledgerCashbookNotesService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<LedgerCashbookNotesResponse> getById(@PathVariable("id") @NotBlank Integer id){
        LedgerCashbookNotesResponse get = ledgerCashbookNotesService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<LedgerCashbookNotesResponse>> getAll(){
        List<LedgerCashbookNotesResponse> getall = ledgerCashbookNotesService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Integer id){
        int deleted = ledgerCashbookNotesService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("getByPayeeName/{payeeName}")
    public ResponseEntity<List<LedgerCashbookNotesResponse>> getByPayeeName(@PathVariable("payeeName") @NotBlank String payeeName){
        List<LedgerCashbookNotesResponse> getall = ledgerCashbookNotesService.getAll();
        return ResponseEntity.ok(getall);
    }


}