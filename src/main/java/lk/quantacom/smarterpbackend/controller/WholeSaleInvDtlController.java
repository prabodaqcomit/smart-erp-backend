package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.WholeSaleInvDtlRequest;
import lk.quantacom.smarterpbackend.dto.request.WholeSaleInvDtlUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.WholeSaleInvDtlResponse;
import lk.quantacom.smarterpbackend.service.WholeSaleInvDtlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("WholeSaleInvDtl")
@RestController
@CrossOrigin
public class WholeSaleInvDtlController {

    @Autowired
    private WholeSaleInvDtlService wholeSaleInvDtlService;


    @PostMapping
    public ResponseEntity<WholeSaleInvDtlResponse> save(@Valid @RequestBody WholeSaleInvDtlRequest request){
        WholeSaleInvDtlResponse save = wholeSaleInvDtlService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<WholeSaleInvDtlResponse> update(@Valid @RequestBody WholeSaleInvDtlUpdateRequest request){
        WholeSaleInvDtlResponse updated = wholeSaleInvDtlService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<WholeSaleInvDtlResponse> getById(@PathVariable("id") @NotBlank Long id){
        WholeSaleInvDtlResponse get = wholeSaleInvDtlService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<WholeSaleInvDtlResponse>> getAll(){
        List<WholeSaleInvDtlResponse> getall = wholeSaleInvDtlService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = wholeSaleInvDtlService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}