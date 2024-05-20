package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.LedgerTypesRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerTypesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerTypesResponse;
import lk.quantacom.smarterpbackend.service.LedgerTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("LedgerTypes")
@RestController
@CrossOrigin
public class LedgerTypesController {

    @Autowired
    private LedgerTypesService ledgerTypesService;


    @PostMapping
    public ResponseEntity<LedgerTypesResponse> save(@Valid @RequestBody LedgerTypesRequest request){
        LedgerTypesResponse save = ledgerTypesService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<LedgerTypesResponse> update(@Valid @RequestBody LedgerTypesUpdateRequest request){
        LedgerTypesResponse updated = ledgerTypesService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<LedgerTypesResponse> getById(@PathVariable("id") @NotBlank Long id){
        LedgerTypesResponse get = ledgerTypesService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<LedgerTypesResponse>> getAll(){
        List<LedgerTypesResponse> getall = ledgerTypesService.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("ByCategory/{category}")
    public ResponseEntity<List<LedgerTypesResponse>> getByCategory(@PathVariable("category") @NotBlank String category){
        List<LedgerTypesResponse> getall = ledgerTypesService.getByCategory(category);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("ByMainAcc/{category}/{mainAcc}")
    public ResponseEntity<List<LedgerTypesResponse>> getByMain(@PathVariable("category") @NotBlank String category,@PathVariable("mainAcc") @NotBlank String mainAcc){
        List<LedgerTypesResponse> getall = ledgerTypesService.getByMain(category,mainAcc);
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = ledgerTypesService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}