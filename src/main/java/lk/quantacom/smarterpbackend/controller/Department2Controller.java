package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.Department2Request;
import lk.quantacom.smarterpbackend.dto.request.Department2UpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.Department2Response;
import lk.quantacom.smarterpbackend.service.Department2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Department2")
@RestController
@CrossOrigin
public class Department2Controller {

    @Autowired
    private Department2Service department2Service;


    @PostMapping
    public ResponseEntity<Department2Response> save(@Valid @RequestBody Department2Request request){
        Department2Response save = department2Service.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<Department2Response> update(@Valid @RequestBody Department2UpdateRequest request){
        Department2Response updated = department2Service.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<Department2Response> getById(@PathVariable("id") @NotBlank Long id){
        Department2Response get = department2Service.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<Department2Response>> getAll(){
        List<Department2Response> getall = department2Service.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("Active")
    public ResponseEntity<List<Department2Response>> getAllActive(){
        List<Department2Response> getall = department2Service.getAllActive();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = department2Service.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}