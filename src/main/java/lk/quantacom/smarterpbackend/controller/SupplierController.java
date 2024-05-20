package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.SupplierRequest;
import lk.quantacom.smarterpbackend.dto.request.SupplierUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SupplierResponse;
import lk.quantacom.smarterpbackend.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequestMapping("Supplier")
@RestController
@CrossOrigin
public class SupplierController {

    @Autowired
    private SupplierService supplierService;


//    @GetMapping("MaxCrAccNo")
//    public ResponseEntity<String> getMacCreditAccNo(){
//        return ResponseEntity.ok(supplierService.getMaxCreditAccNo());
//    }


    @PostMapping
    public ResponseEntity<SupplierResponse> save(@Valid @RequestBody SupplierRequest request){
        SupplierResponse save = supplierService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<SupplierResponse> update(@Valid @RequestBody SupplierUpdateRequest request){
        SupplierResponse updated = supplierService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<SupplierResponse> getById(@PathVariable("id") @NotBlank Long id){
        SupplierResponse get = supplierService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<SupplierResponse>> getAll(){
        List<SupplierResponse> getall = supplierService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = supplierService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("Print/{id}")
    public ResponseEntity<Resource> print(@PathVariable("id")   Long id){

        try {

            File printGenerate = supplierService.printProfile(id);
            Path path = Paths.get(printGenerate.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Content-Disposition", "attachment; filename=" + printGenerate.getName());

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(printGenerate.length())
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.notFound().build();
    }

}