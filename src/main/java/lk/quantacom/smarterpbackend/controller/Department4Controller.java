package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.Department4Request;
import lk.quantacom.smarterpbackend.dto.request.Department4UpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.Department4Response;
import lk.quantacom.smarterpbackend.service.Department4Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Department4")
@RestController
@CrossOrigin
public class Department4Controller {

    @Autowired
    private Department4Service department4Service;


    @PostMapping
    public ResponseEntity<Department4Response> save(@Valid @RequestBody Department4Request request){
        Department4Response save = department4Service.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<Department4Response> update(@Valid @RequestBody Department4UpdateRequest request){
        Department4Response updated = department4Service.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<Department4Response> getById(@PathVariable("id") @NotBlank Long id){
        Department4Response get = department4Service.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<Department4Response>> getAll(){
        List<Department4Response> getall = department4Service.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("Active")
    public ResponseEntity<List<Department4Response>> getAllActive(){
        List<Department4Response> getall = department4Service.getAllActive();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = department4Service.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}