package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.TblbommainmaterialRequest;
import lk.quantacom.smarterpbackend.dto.request.TblbommainmaterialUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GetBomMaterialByStockMaterialDescResponse;
import lk.quantacom.smarterpbackend.dto.response.TblbommainmaterialResponse;
import lk.quantacom.smarterpbackend.service.TblbommainmaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Tblbommainmaterial")
@RestController
@CrossOrigin
public class TblbommainmaterialController {

    @Autowired
    private TblbommainmaterialService tblbommainmaterialService;


    @PostMapping
    public ResponseEntity<TblbommainmaterialResponse> save(@Valid @RequestBody TblbommainmaterialRequest request){
        TblbommainmaterialResponse save = tblbommainmaterialService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<TblbommainmaterialResponse> update(@Valid @RequestBody TblbommainmaterialUpdateRequest request){
        TblbommainmaterialResponse updated = tblbommainmaterialService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<TblbommainmaterialResponse> getById(@PathVariable("id") @NotBlank Long id){
        TblbommainmaterialResponse get = tblbommainmaterialService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<TblbommainmaterialResponse>> getAll(){
        List<TblbommainmaterialResponse> getall = tblbommainmaterialService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{bomId}")
    public ResponseEntity<Integer> delete(@PathVariable("bomId") @NotBlank Integer bomId){
        int deleted = tblbommainmaterialService.delete(bomId);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("bomId/{bomId}")
    public ResponseEntity<List<GetBomMaterialByStockMaterialDescResponse>> getBomMaterialsByBomId(@PathVariable("bomId") @NotBlank Integer bomId){
        List<GetBomMaterialByStockMaterialDescResponse> getall = tblbommainmaterialService.getBomMaterialsByBomId(bomId);
        return ResponseEntity.ok(getall);
    }
}