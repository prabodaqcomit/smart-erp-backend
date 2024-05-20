package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.TblpormainmaterialsRequest;
import lk.quantacom.smarterpbackend.dto.request.TblpormainmaterialsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblporfitsResponse;
import lk.quantacom.smarterpbackend.dto.response.TblpormainmaterialsResponse;
import lk.quantacom.smarterpbackend.dto.response.getPorByPorIdResponse;
import lk.quantacom.smarterpbackend.service.TblpormainmaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Tblpormainmaterials")
@RestController
@CrossOrigin
public class TblpormainmaterialsController {

    @Autowired
    private TblpormainmaterialsService tblpormainmaterialsService;


    @PostMapping
    public ResponseEntity<TblpormainmaterialsResponse> save(@Valid @RequestBody TblpormainmaterialsRequest request){
        TblpormainmaterialsResponse save = tblpormainmaterialsService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<TblpormainmaterialsResponse> update(@Valid @RequestBody TblpormainmaterialsUpdateRequest request){
        TblpormainmaterialsResponse updated = tblpormainmaterialsService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<TblpormainmaterialsResponse> getById(@PathVariable("id") @NotBlank Long id){
        TblpormainmaterialsResponse get = tblpormainmaterialsService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<TblpormainmaterialsResponse>> getAll(){
        List<TblpormainmaterialsResponse> getall = tblpormainmaterialsService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = tblpormainmaterialsService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("porId/{porId}")
    public ResponseEntity<List<getPorByPorIdResponse>> getPorByPorId(@PathVariable("porId") @NotBlank String porId){
        List<getPorByPorIdResponse> getall = tblpormainmaterialsService.getPorByPorId(porId);
        return ResponseEntity.ok(getall);
    }




}