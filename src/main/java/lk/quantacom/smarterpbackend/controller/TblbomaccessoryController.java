package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.TblbomaccessoryRequest;
import lk.quantacom.smarterpbackend.dto.request.TblbomaccessoryUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblbomaccessoryResponse;
import lk.quantacom.smarterpbackend.service.TblbomaccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Tblbomaccessory")
@RestController
@CrossOrigin
public class TblbomaccessoryController {

    @Autowired
    private TblbomaccessoryService tblbomaccessoryService;


    @PostMapping
    public ResponseEntity<TblbomaccessoryResponse> save(@Valid @RequestBody TblbomaccessoryRequest request){
        TblbomaccessoryResponse save = tblbomaccessoryService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<TblbomaccessoryResponse> update(@Valid @RequestBody TblbomaccessoryUpdateRequest request){
        TblbomaccessoryResponse updated = tblbomaccessoryService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<TblbomaccessoryResponse> getById(@PathVariable("id") @NotBlank Long id){
        TblbomaccessoryResponse get = tblbomaccessoryService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<TblbomaccessoryResponse>> getAll(){
        List<TblbomaccessoryResponse> getall = tblbomaccessoryService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{bdId}")
    public ResponseEntity<Integer> delete(@PathVariable("bdId") @NotBlank Integer bdId){
        int deleted = tblbomaccessoryService.delete(bdId);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}