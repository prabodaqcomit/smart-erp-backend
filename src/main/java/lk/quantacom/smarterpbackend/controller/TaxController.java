package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.TaxRequest;
import lk.quantacom.smarterpbackend.dto.request.TaxUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TaxResponse;
import lk.quantacom.smarterpbackend.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Tax")
@RestController
@CrossOrigin
public class TaxController {

    @Autowired
    private TaxService taxService;


    @PostMapping
    public ResponseEntity<TaxResponse> save(@Valid @RequestBody TaxRequest request){
        TaxResponse save = taxService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<TaxResponse> update(@Valid @RequestBody TaxUpdateRequest request){
        TaxResponse updated = taxService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<TaxResponse> getById(@PathVariable("id") @NotBlank Long id){
        TaxResponse get = taxService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<TaxResponse>> getAll(){
        List<TaxResponse> getall = taxService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = taxService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}