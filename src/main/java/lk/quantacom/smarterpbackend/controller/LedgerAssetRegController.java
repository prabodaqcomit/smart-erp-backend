package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.LedgerAssetRegRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerAssetRegUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerAssetRegResponse;
import lk.quantacom.smarterpbackend.service.LedgerAssetRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("LedgerAssetReg")
@RestController
@CrossOrigin
public class LedgerAssetRegController {

    @Autowired
    private LedgerAssetRegService ledgerAssetRegService;


    @PostMapping
    public ResponseEntity<LedgerAssetRegResponse> save(@Valid @RequestBody LedgerAssetRegRequest request){
        LedgerAssetRegResponse save = ledgerAssetRegService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<LedgerAssetRegResponse> update(@Valid @RequestBody LedgerAssetRegUpdateRequest request){
        LedgerAssetRegResponse updated = ledgerAssetRegService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<LedgerAssetRegResponse> getById(@PathVariable("id") @NotBlank Long id){
        LedgerAssetRegResponse get = ledgerAssetRegService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<LedgerAssetRegResponse>> getAll(){
        List<LedgerAssetRegResponse> getall = ledgerAssetRegService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = ledgerAssetRegService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}