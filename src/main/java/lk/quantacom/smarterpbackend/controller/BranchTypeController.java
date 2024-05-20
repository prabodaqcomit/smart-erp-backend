package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.BranchTypeRequest;
import lk.quantacom.smarterpbackend.dto.request.BranchTypeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BranchTypeResponse;
import lk.quantacom.smarterpbackend.service.BranchTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("BranchType")
@RestController
@CrossOrigin
public class BranchTypeController {

    @Autowired
    private BranchTypeService branchTypeService;


    @PostMapping
    public ResponseEntity<BranchTypeResponse> save(@Valid @RequestBody BranchTypeRequest request){
        BranchTypeResponse save = branchTypeService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<BranchTypeResponse> update(@Valid @RequestBody BranchTypeUpdateRequest request){
        BranchTypeResponse updated = branchTypeService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<BranchTypeResponse> getById(@PathVariable("id") @NotBlank Long id){
        BranchTypeResponse get = branchTypeService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<BranchTypeResponse>> getAll(){
        List<BranchTypeResponse> getall = branchTypeService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = branchTypeService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}