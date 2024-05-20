package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.PromotionDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.PromotionDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PromotionDetailsResponse;
import lk.quantacom.smarterpbackend.service.PromotionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("PromotionDetails")
@RestController
@CrossOrigin
public class PromotionDetailsController {

    @Autowired
    private PromotionDetailsService promotionDetailsService;


    @PostMapping
    public ResponseEntity<PromotionDetailsResponse> save(@Valid @RequestBody PromotionDetailsRequest request){
        PromotionDetailsResponse save = promotionDetailsService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<PromotionDetailsResponse> update(@Valid @RequestBody PromotionDetailsUpdateRequest request){
        PromotionDetailsResponse updated = promotionDetailsService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<PromotionDetailsResponse> getById(@PathVariable("id") @NotBlank Long id){
        PromotionDetailsResponse get = promotionDetailsService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<PromotionDetailsResponse>> getAll(){
        List<PromotionDetailsResponse> getall = promotionDetailsService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = promotionDetailsService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}