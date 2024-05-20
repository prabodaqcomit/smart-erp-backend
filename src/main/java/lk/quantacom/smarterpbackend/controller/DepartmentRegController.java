package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.DepartmentRegRequest;
import lk.quantacom.smarterpbackend.dto.request.DepartmentRegUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.DepartmentRegResponse;
import lk.quantacom.smarterpbackend.service.DepartmentRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("DepartmentReg")
@RestController
@CrossOrigin
public class DepartmentRegController {

    @Autowired
    private DepartmentRegService departmentRegService;


    @PostMapping
    public ResponseEntity<DepartmentRegResponse> save(@Valid @RequestBody DepartmentRegRequest request){
        DepartmentRegResponse save = departmentRegService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<DepartmentRegResponse> update(@Valid @RequestBody DepartmentRegUpdateRequest request){
        DepartmentRegResponse updated = departmentRegService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<DepartmentRegResponse> getById(@PathVariable("id") @NotBlank Long id){
        DepartmentRegResponse get = departmentRegService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<DepartmentRegResponse>> getAll(){
        List<DepartmentRegResponse> getall = departmentRegService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = departmentRegService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}