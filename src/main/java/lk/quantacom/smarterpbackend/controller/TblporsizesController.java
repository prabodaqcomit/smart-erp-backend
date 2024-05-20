package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.TblporsizesRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporsizesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblporsizesResponse;
import lk.quantacom.smarterpbackend.service.TblporsizesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Tblporsizes")
@RestController
@CrossOrigin
public class TblporsizesController {

    @Autowired
    private TblporsizesService tblporsizesService;


    @PostMapping
    public ResponseEntity<TblporsizesResponse> save(@Valid @RequestBody TblporsizesRequest request){
        TblporsizesResponse save = tblporsizesService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<TblporsizesResponse> update(@Valid @RequestBody TblporsizesUpdateRequest request){
        TblporsizesResponse updated = tblporsizesService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<TblporsizesResponse> getById(@PathVariable("id") @NotBlank Long id){
        TblporsizesResponse get = tblporsizesService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<TblporsizesResponse>> getAll(){
        List<TblporsizesResponse> getall = tblporsizesService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = tblporsizesService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("porId/{porId}")
    public ResponseEntity<List<TblporsizesResponse>> getByPorId(@PathVariable("porId") @NotBlank String porId){
        List<TblporsizesResponse> getall = tblporsizesService.getByPorId(porId);
        return ResponseEntity.ok(getall);
    }

}