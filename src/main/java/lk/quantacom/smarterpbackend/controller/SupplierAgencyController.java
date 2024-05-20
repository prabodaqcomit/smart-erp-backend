package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.SupplierAgencyRequest;
import lk.quantacom.smarterpbackend.dto.request.SupplierAgencyUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SupplierAgencyResponse;
import lk.quantacom.smarterpbackend.service.SupplierAgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("SupplierAgency")
@RestController
@CrossOrigin
public class SupplierAgencyController {

    @Autowired
    private SupplierAgencyService supplierAgencyService;


    @PostMapping
    public ResponseEntity<SupplierAgencyResponse> save(@Valid @RequestBody SupplierAgencyRequest request){
        SupplierAgencyResponse save = supplierAgencyService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<SupplierAgencyResponse> update(@Valid @RequestBody SupplierAgencyUpdateRequest request){
        SupplierAgencyResponse updated = supplierAgencyService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<SupplierAgencyResponse> getById(@PathVariable("id") @NotBlank Long id){
        SupplierAgencyResponse get = supplierAgencyService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<SupplierAgencyResponse>> getAll(){
        List<SupplierAgencyResponse> getall = supplierAgencyService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = supplierAgencyService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}