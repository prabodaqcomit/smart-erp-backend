package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.TblmenudetailRequest;
import lk.quantacom.smarterpbackend.dto.request.TblmenudetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblmenudetailResponse;
import lk.quantacom.smarterpbackend.service.TblmenudetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Tblmenudetail")
@RestController
@CrossOrigin
public class TblmenudetailController {

    @Autowired
    private TblmenudetailService tblmenudetailService;


    @PostMapping
    public ResponseEntity<TblmenudetailResponse> save(@Valid @RequestBody TblmenudetailRequest request){
        TblmenudetailResponse save = tblmenudetailService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<TblmenudetailResponse> update(@Valid @RequestBody TblmenudetailUpdateRequest request){
        TblmenudetailResponse updated = tblmenudetailService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<TblmenudetailResponse> getById(@PathVariable("id") @NotBlank String id){
        TblmenudetailResponse get = tblmenudetailService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<TblmenudetailResponse>> getAll(){
        List<TblmenudetailResponse> getall = tblmenudetailService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank String id){
        int deleted = tblmenudetailService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}