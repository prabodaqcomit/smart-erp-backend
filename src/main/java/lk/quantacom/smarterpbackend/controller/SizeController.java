package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.SizeRequest;
import lk.quantacom.smarterpbackend.dto.request.SizeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SizeResponse;
import lk.quantacom.smarterpbackend.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Size")
@RestController
@CrossOrigin
public class SizeController {

    @Autowired
    private SizeService sizeService;


    @PostMapping
    public ResponseEntity<SizeResponse> save(@Valid @RequestBody SizeRequest request){
        SizeResponse save = sizeService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<SizeResponse> update(@Valid @RequestBody SizeUpdateRequest request){
        SizeResponse updated = sizeService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<SizeResponse> getById(@PathVariable("id") @NotBlank Long id){
        SizeResponse get = sizeService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<SizeResponse>> getAll(){
        List<SizeResponse> getall = sizeService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = sizeService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}