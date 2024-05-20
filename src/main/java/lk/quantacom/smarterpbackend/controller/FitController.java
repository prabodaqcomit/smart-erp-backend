package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.FitRequest;
import lk.quantacom.smarterpbackend.dto.request.FitUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.FitResponse;
import lk.quantacom.smarterpbackend.service.FitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Fit")
@RestController
@CrossOrigin
public class FitController {

    @Autowired
    private FitService fitService;


    @PostMapping
    public ResponseEntity<FitResponse> save(@Valid @RequestBody FitRequest request){
        FitResponse save = fitService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<FitResponse> update(@Valid @RequestBody FitUpdateRequest request){
        FitResponse updated = fitService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<FitResponse> getById(@PathVariable("id") @NotBlank Long id){
        FitResponse get = fitService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<FitResponse>> getAll(){
        List<FitResponse> getall = fitService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = fitService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}