package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.CCardDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.CCardDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.CCardDetailResponse;
import lk.quantacom.smarterpbackend.service.CCardDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("CCardDetail")
@RestController
@CrossOrigin
public class CCardDetailController {

    @Autowired
    private CCardDetailService cCardDetailService;


    @PostMapping
    public ResponseEntity<CCardDetailResponse> save(@Valid @RequestBody CCardDetailRequest request){
        CCardDetailResponse save = cCardDetailService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<CCardDetailResponse> update(@Valid @RequestBody CCardDetailUpdateRequest request){
        CCardDetailResponse updated = cCardDetailService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<CCardDetailResponse> getById(@PathVariable("id") @NotBlank String id){
        CCardDetailResponse get = cCardDetailService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<CCardDetailResponse>> getAll(){
        List<CCardDetailResponse> getall = cCardDetailService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank String id){
        int deleted = cCardDetailService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}