package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.UnitRefRequest;
import lk.quantacom.smarterpbackend.dto.request.UnitRefUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.UnitRefResponse;
import lk.quantacom.smarterpbackend.service.UnitRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("UnitRef")
@RestController
@CrossOrigin
public class UnitRefController {

    @Autowired
    private UnitRefService unitRefService;


    @PostMapping
    public ResponseEntity<UnitRefResponse> save(@Valid @RequestBody UnitRefRequest request){
        UnitRefResponse save = unitRefService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<UnitRefResponse> update(@Valid @RequestBody UnitRefUpdateRequest request){
        UnitRefResponse updated = unitRefService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<UnitRefResponse> getById(@PathVariable("id") @NotBlank Long id){
        UnitRefResponse get = unitRefService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<UnitRefResponse>> getAll(){
        List<UnitRefResponse> getall = unitRefService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = unitRefService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}