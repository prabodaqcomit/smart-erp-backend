package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.LedgerPettyCashBookRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerPettyCashBookSaveListRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerPettyCashBookUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerPettyCashBookResponse;
import lk.quantacom.smarterpbackend.service.LedgerPettyCashBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("LedgerPettyCashBook")
@RestController
@CrossOrigin
public class LedgerPettyCashBookController {

    @Autowired
    private LedgerPettyCashBookService ledgerPettyCashBookService;


    @PostMapping
    public ResponseEntity<LedgerPettyCashBookResponse> save(@Valid @RequestBody LedgerPettyCashBookRequest request){
        LedgerPettyCashBookResponse save = ledgerPettyCashBookService.save(request);
        return ResponseEntity.ok(save);
    }

    @PostMapping("saveAll")
    public ResponseEntity<List<LedgerPettyCashBookResponse>> saveAll(@Valid @RequestBody List<LedgerPettyCashBookSaveListRequest> saveListRequests){
        List<LedgerPettyCashBookResponse> save = ledgerPettyCashBookService.saveAll1(saveListRequests);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<LedgerPettyCashBookResponse> update(@Valid @RequestBody LedgerPettyCashBookUpdateRequest request){
        LedgerPettyCashBookResponse updated = ledgerPettyCashBookService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<LedgerPettyCashBookResponse> getById(@PathVariable("id") @NotBlank Long id){
        LedgerPettyCashBookResponse get = ledgerPettyCashBookService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<LedgerPettyCashBookResponse>> getAll(){
        List<LedgerPettyCashBookResponse> getall = ledgerPettyCashBookService.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("Search/{fromDate}/{toDate}/{branchId}")
    public ResponseEntity<List<LedgerPettyCashBookResponse>> getAllByDates(@PathVariable("fromDate") @NotBlank String fromDate,
                                                                           @PathVariable("toDate") @NotBlank String toDate,
                                                                           @PathVariable("branchId") @NotBlank Long branchId){
        List<LedgerPettyCashBookResponse> getall = ledgerPettyCashBookService.getByDates(fromDate,toDate,branchId);
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = ledgerPettyCashBookService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}