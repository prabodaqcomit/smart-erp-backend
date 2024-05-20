package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.TblbomsizeRequest;
import lk.quantacom.smarterpbackend.dto.request.TblbomsizeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblbomsizeResponse;
import lk.quantacom.smarterpbackend.service.TblbomsizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Tblbomsize")
@RestController
@CrossOrigin
public class TblbomsizeController {

    @Autowired
    private TblbomsizeService tblbomsizeService;


    @PostMapping
    public ResponseEntity<TblbomsizeResponse> save(@Valid @RequestBody TblbomsizeRequest request){
        TblbomsizeResponse save = tblbomsizeService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<TblbomsizeResponse> update(@Valid @RequestBody TblbomsizeUpdateRequest request){
        TblbomsizeResponse updated = tblbomsizeService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<TblbomsizeResponse> getById(@PathVariable("id") @NotBlank Long id){
        TblbomsizeResponse get = tblbomsizeService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<TblbomsizeResponse>> getAll(){
        List<TblbomsizeResponse> getall = tblbomsizeService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{bsId}")
    public ResponseEntity<Integer> delete(@PathVariable("bsId") @NotBlank Integer bsId){
        int deleted = tblbomsizeService.delete(bsId);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}