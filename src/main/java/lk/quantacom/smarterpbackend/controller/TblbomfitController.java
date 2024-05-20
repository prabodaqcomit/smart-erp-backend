package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.TblbomfitRequest;
import lk.quantacom.smarterpbackend.dto.request.TblbomfitUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblbomfitResponse;
import lk.quantacom.smarterpbackend.service.TblbomfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Tblbomfit")
@RestController
@CrossOrigin
public class TblbomfitController {

    @Autowired
    private TblbomfitService tblbomfitService;


    @PostMapping
    public ResponseEntity<TblbomfitResponse> save(@Valid @RequestBody TblbomfitRequest request){
        TblbomfitResponse save = tblbomfitService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<TblbomfitResponse> update(@Valid @RequestBody TblbomfitUpdateRequest request){
        TblbomfitResponse updated = tblbomfitService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<TblbomfitResponse> getById(@PathVariable("id") @NotBlank Long id){
        TblbomfitResponse get = tblbomfitService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<TblbomfitResponse>> getAll(){
        List<TblbomfitResponse> getall = tblbomfitService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{bfId}")
    public ResponseEntity<Integer> delete(@PathVariable("bfId") @NotBlank Integer bfId){
        int deleted = tblbomfitService.delete(bfId);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}