package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.CustomerRequest;
import lk.quantacom.smarterpbackend.dto.request.CustomerUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.CustomerResponse;
import lk.quantacom.smarterpbackend.service.CustomerService;
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

@RequestMapping("Customer")
@RestController
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("MaxCrAccNo")
    public ResponseEntity<String> getMacCreditAccNo(){
        return ResponseEntity.ok(customerService.getMaxCreditAccNo());
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> save(@Valid @RequestBody CustomerRequest request){
        CustomerResponse save = customerService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest request){
        CustomerResponse updated = customerService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable("id") @NotBlank Long id){
        CustomerResponse get = customerService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }

    @GetMapping("Print/{id}")
    public ResponseEntity<Resource> print(@PathVariable("id")   Long id){

        try {

            File printGenerate = customerService.printProfile(id);
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

    @GetMapping()
    public ResponseEntity<List<CustomerResponse>> getAll(){
        List<CustomerResponse> getall = customerService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = customerService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<List<CustomerResponse>> getAll(@PathVariable("name") @NotBlank String name){
        List<CustomerResponse> getall = customerService.getByName(name);
        return ResponseEntity.ok(getall);
    }



}