package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.PromotionPayDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.PromotionPayDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PromotionPayDetailsResponse;
import lk.quantacom.smarterpbackend.service.PromotionPayDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("PromotionPayDetails")
@RestController
@CrossOrigin
public class PromotionPayDetailsController {

    @Autowired
    private PromotionPayDetailsService promotionPayDetailsService;


    @PostMapping
    public ResponseEntity<PromotionPayDetailsResponse> save(@Valid @RequestBody PromotionPayDetailsRequest request){
        PromotionPayDetailsResponse save = promotionPayDetailsService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<PromotionPayDetailsResponse> update(@Valid @RequestBody PromotionPayDetailsUpdateRequest request){
        PromotionPayDetailsResponse updated = promotionPayDetailsService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<PromotionPayDetailsResponse> getById(@PathVariable("id") @NotBlank Long id){
        PromotionPayDetailsResponse get = promotionPayDetailsService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<PromotionPayDetailsResponse>> getAll(){
        List<PromotionPayDetailsResponse> getall = promotionPayDetailsService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = promotionPayDetailsService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}