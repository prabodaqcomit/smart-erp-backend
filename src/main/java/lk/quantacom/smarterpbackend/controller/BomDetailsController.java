package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.BomDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.BomDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BomDetailsResponse;
import lk.quantacom.smarterpbackend.service.BomDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("BomDetails")
@RestController
@CrossOrigin
public class BomDetailsController {

    @Autowired
    private BomDetailsService bomDetailsService;


    @PostMapping
    public ResponseEntity<BomDetailsResponse> save(@Valid @RequestBody BomDetailsRequest request){
        BomDetailsResponse save = bomDetailsService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<BomDetailsResponse> update(@Valid @RequestBody BomDetailsUpdateRequest request){
        BomDetailsResponse updated = bomDetailsService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<BomDetailsResponse> getById(@PathVariable("id") @NotBlank Long id){
        BomDetailsResponse get = bomDetailsService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<BomDetailsResponse>> getAll(){
        List<BomDetailsResponse> getall = bomDetailsService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = bomDetailsService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}