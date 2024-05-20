package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.SupplierSalesRepRequest;
import lk.quantacom.smarterpbackend.dto.request.SupplierSalesRepUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SupplierSalesRepResponse;
import lk.quantacom.smarterpbackend.service.SupplierSalesRepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("SupplierSalesRep")
@RestController
@CrossOrigin
public class SupplierSalesRepController {

    @Autowired
    private SupplierSalesRepService supplierSalesRepService;


    @PostMapping
    public ResponseEntity<SupplierSalesRepResponse> save(@Valid @RequestBody SupplierSalesRepRequest request){
        SupplierSalesRepResponse save = supplierSalesRepService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<SupplierSalesRepResponse> update(@Valid @RequestBody SupplierSalesRepUpdateRequest request){
        SupplierSalesRepResponse updated = supplierSalesRepService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<SupplierSalesRepResponse> getById(@PathVariable("id") @NotBlank Long id){
        SupplierSalesRepResponse get = supplierSalesRepService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<SupplierSalesRepResponse>> getAll(){
        List<SupplierSalesRepResponse> getall = supplierSalesRepService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = supplierSalesRepService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}