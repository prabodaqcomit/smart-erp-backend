package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.TblpaydtlRequest;
import lk.quantacom.smarterpbackend.dto.request.TblpaydtlUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblpaydtlResponse;
import lk.quantacom.smarterpbackend.service.TblpaydtlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Tblpaydtl")
@RestController
@CrossOrigin
public class TblpaydtlController {

    @Autowired
    private TblpaydtlService tblpaydtlService;


    @PostMapping
    public ResponseEntity<TblpaydtlResponse> save(@Valid @RequestBody TblpaydtlRequest request){
        TblpaydtlResponse save = tblpaydtlService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<TblpaydtlResponse> update(@Valid @RequestBody TblpaydtlUpdateRequest request){
        TblpaydtlResponse updated = tblpaydtlService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<TblpaydtlResponse> getById(@PathVariable("id") @NotBlank String id){
        TblpaydtlResponse get = tblpaydtlService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<TblpaydtlResponse>> getAll(){
        List<TblpaydtlResponse> getall = tblpaydtlService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank String id){
        int deleted = tblpaydtlService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}