package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.Department1Request;
import lk.quantacom.smarterpbackend.dto.request.Department1UpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.service.Department1Service;
import lk.quantacom.smarterpbackend.service.Department2Service;
import lk.quantacom.smarterpbackend.service.Department3Service;
import lk.quantacom.smarterpbackend.service.Department4Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Department1")
@RestController
@CrossOrigin
public class Department1Controller {

    @Autowired
    private Department1Service department1Service;

    @Autowired
    private Department2Service department2Service;

    @Autowired
    private Department3Service department3Service;

    @Autowired
    private Department4Service department4Service;


    @PostMapping
    public ResponseEntity<Department1Response> save(@Valid @RequestBody Department1Request request){
        Department1Response save = department1Service.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<Department1Response> update(@Valid @RequestBody Department1UpdateRequest request){
        Department1Response updated = department1Service.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<Department1Response> getById(@PathVariable("id") @NotBlank Long id){
        Department1Response get = department1Service.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }

    @GetMapping("Active/All")
    public ResponseEntity<DepartmentResponse> getAllActiveAll(){

        DepartmentResponse all=new DepartmentResponse();

        List<Department1Response> getall1 = department1Service.getAll();
        List<Department2Response> getall2 = department2Service.getAll();
        List<Department3Response> getall3 = department3Service.getAll();
        List<Department4Response> getall4 = department4Service.getAll();

        all.setDepartment1(getall1);
        all.setDepartment2(getall2);
        all.setDepartment3(getall3);
        all.setDepartment4(getall4);

        return ResponseEntity.ok( all);
    }

    @GetMapping()
    public ResponseEntity<List<Department1Response>> getAll(){
        List<Department1Response> getall = department1Service.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("Active")
    public ResponseEntity<List<Department1Response>> getAllActive(){
        List<Department1Response> getall = department1Service.getAllActive();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = department1Service.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}