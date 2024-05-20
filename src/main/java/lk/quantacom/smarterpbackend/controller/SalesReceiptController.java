package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.SalesReceiptRequest;
import lk.quantacom.smarterpbackend.dto.request.SalesReceiptUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SalesReceiptResponse;
import lk.quantacom.smarterpbackend.service.SalesReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("SalesReceipt")
@RestController
@CrossOrigin
public class SalesReceiptController {

    @Autowired
    private SalesReceiptService salesReceiptService;


    @PostMapping
    public ResponseEntity<SalesReceiptResponse> save(@Valid @RequestBody SalesReceiptRequest request){
        SalesReceiptResponse save = salesReceiptService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<SalesReceiptResponse> update(@Valid @RequestBody SalesReceiptUpdateRequest request){
        SalesReceiptResponse updated = salesReceiptService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<SalesReceiptResponse> getById(@PathVariable("id") @NotBlank Long id){
        SalesReceiptResponse get = salesReceiptService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<SalesReceiptResponse>> getAll(){
        List<SalesReceiptResponse> getall = salesReceiptService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = salesReceiptService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}