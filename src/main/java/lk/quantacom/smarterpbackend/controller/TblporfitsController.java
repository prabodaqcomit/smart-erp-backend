package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.TblporfitsRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporfitsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblporfitsResponse;
import lk.quantacom.smarterpbackend.dto.response.TblporsizesResponse;
import lk.quantacom.smarterpbackend.service.TblporfitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Tblporfits")
@RestController
@CrossOrigin
public class TblporfitsController {

    @Autowired
    private TblporfitsService tblporfitsService;


    @PostMapping
    public ResponseEntity<TblporfitsResponse> save(@Valid @RequestBody TblporfitsRequest request){
        TblporfitsResponse save = tblporfitsService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<TblporfitsResponse> update(@Valid @RequestBody TblporfitsUpdateRequest request){
        TblporfitsResponse updated = tblporfitsService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<TblporfitsResponse> getById(@PathVariable("id") @NotBlank Long id){
        TblporfitsResponse get = tblporfitsService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<TblporfitsResponse>> getAll(){
        List<TblporfitsResponse> getall = tblporfitsService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = tblporfitsService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("porId/{porId}")
    public ResponseEntity<List<TblporfitsResponse>> getByPorId(@PathVariable("porId") @NotBlank String porId){
        List<TblporfitsResponse> getall = tblporfitsService.getByPorId(porId);
        return ResponseEntity.ok(getall);
    }
}