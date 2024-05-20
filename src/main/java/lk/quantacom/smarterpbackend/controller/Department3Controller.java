package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.Department3Request;
import lk.quantacom.smarterpbackend.dto.request.Department3UpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.Department3Response;
import lk.quantacom.smarterpbackend.service.Department3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Department3")
@RestController
@CrossOrigin
public class Department3Controller {

    @Autowired
    private Department3Service department3Service;


    @PostMapping
    public ResponseEntity<Department3Response> save(@Valid @RequestBody Department3Request request){
        Department3Response save = department3Service.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<Department3Response> update(@Valid @RequestBody Department3UpdateRequest request){
        Department3Response updated = department3Service.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<Department3Response> getById(@PathVariable("id") @NotBlank Long id){
        Department3Response get = department3Service.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<Department3Response>> getAll(){
        List<Department3Response> getall = department3Service.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("Active")
    public ResponseEntity<List<Department3Response>> getAllActive(){
        List<Department3Response> getall = department3Service.getAllActive();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = department3Service.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}