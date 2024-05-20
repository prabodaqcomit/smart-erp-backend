package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.SalesRefRequest;
import lk.quantacom.smarterpbackend.dto.request.SalesRefUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SalesRefResponse;
import lk.quantacom.smarterpbackend.service.SalesRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("SalesRef")
@RestController
@CrossOrigin
public class SalesRefController {

    @Autowired
    private SalesRefService salesRefService;


    @PostMapping
    public ResponseEntity<SalesRefResponse> save(@Valid @RequestBody SalesRefRequest request){
        SalesRefResponse save = salesRefService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<SalesRefResponse> update(@Valid @RequestBody SalesRefUpdateRequest request){
        SalesRefResponse updated = salesRefService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<SalesRefResponse> getById(@PathVariable("id") @NotBlank Long id){
        SalesRefResponse get = salesRefService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<SalesRefResponse>> getAll(){
        List<SalesRefResponse> getall = salesRefService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = salesRefService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}