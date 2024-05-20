package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.TblporaccessoriesRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporaccessoriesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblporaccessoriesResponse;
import lk.quantacom.smarterpbackend.dto.response.getByStockAndTblPorAccessoriesResponse;
import lk.quantacom.smarterpbackend.service.TblporaccessoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Tblporaccessories")
@RestController
@CrossOrigin
public class TblporaccessoriesController {

    @Autowired
    private TblporaccessoriesService tblporaccessoriesService;


    @PostMapping
    public ResponseEntity<TblporaccessoriesResponse> save(@Valid @RequestBody TblporaccessoriesRequest request){
        TblporaccessoriesResponse save = tblporaccessoriesService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<TblporaccessoriesResponse> update(@Valid @RequestBody TblporaccessoriesUpdateRequest request){
        TblporaccessoriesResponse updated = tblporaccessoriesService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<TblporaccessoriesResponse> getById(@PathVariable("id") @NotBlank Long id){
        TblporaccessoriesResponse get = tblporaccessoriesService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<TblporaccessoriesResponse>> getAll(){
        List<TblporaccessoriesResponse> getall = tblporaccessoriesService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = tblporaccessoriesService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }


    @GetMapping("getByStockAndTblPorAccessory/{porId}")
    public ResponseEntity<List<getByStockAndTblPorAccessoriesResponse>> getByStockAndTblPorAccessory(@PathVariable("porId") @NotBlank String porId){
        List<getByStockAndTblPorAccessoriesResponse> getall = tblporaccessoriesService.getByStockAndTblPorAccessory(porId);
        return ResponseEntity.ok(getall);
    }

}