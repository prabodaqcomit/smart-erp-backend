package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.BranchNetworkRequest;
import lk.quantacom.smarterpbackend.dto.request.BranchNetworkUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BranchNetworkResponse;
import lk.quantacom.smarterpbackend.service.BranchNetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("BranchNetwork")
@RestController
@CrossOrigin
public class BranchNetworkController {

    @Autowired
    private BranchNetworkService branchNetworkService;


    @PostMapping
    public ResponseEntity<BranchNetworkResponse> save(@Valid @RequestBody BranchNetworkRequest request){
        BranchNetworkResponse save = branchNetworkService.save(request);
        if (save==null){
            return  ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<BranchNetworkResponse> update(@Valid @RequestBody BranchNetworkUpdateRequest request){
        BranchNetworkResponse updated = branchNetworkService.update(request);
        if(updated==null){
            return  ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<BranchNetworkResponse> getById(@PathVariable("id") @NotBlank Long id){
        BranchNetworkResponse get = branchNetworkService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<BranchNetworkResponse>> getAll(){
        List<BranchNetworkResponse> getall = branchNetworkService.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("Public")
    public ResponseEntity<List<BranchNetworkResponse>> getAllPublic(){
        List<BranchNetworkResponse> getall = branchNetworkService.getAllPublic();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = branchNetworkService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("branchType/{branchType}")
    public ResponseEntity<List<BranchNetworkResponse>> getByBranchType(@PathVariable("branchType") @NotBlank Long branchType){
        List<BranchNetworkResponse> getall = branchNetworkService.getByBranchType(branchType);
        return ResponseEntity.ok(getall);
    }

}