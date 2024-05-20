package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.BomHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.BomHeaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BomHeaderResponse;
import lk.quantacom.smarterpbackend.service.BomHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("BomHeader")
@RestController
@CrossOrigin
public class BomHeaderController {

    @Autowired
    private BomHeaderService bomHeaderService;


    @PostMapping
    public ResponseEntity<BomHeaderResponse> save(@Valid @RequestBody BomHeaderRequest request){
        BomHeaderResponse save = bomHeaderService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<BomHeaderResponse> update(@Valid @RequestBody BomHeaderUpdateRequest request){
        BomHeaderResponse updated = bomHeaderService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<BomHeaderResponse> getById(@PathVariable("id") @NotBlank Long id){
        BomHeaderResponse get = bomHeaderService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<BomHeaderResponse>> getAll(){
        List<BomHeaderResponse> getall = bomHeaderService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = bomHeaderService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}