package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.PromotionHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.PromotionHeaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PromotionHeaderResponse;
import lk.quantacom.smarterpbackend.service.PromotionHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("PromotionHeader")
@RestController
@CrossOrigin
public class PromotionHeaderController {

    @Autowired
    private PromotionHeaderService promotionHeaderService;


    @PostMapping
    public ResponseEntity<PromotionHeaderResponse> save(@Valid @RequestBody PromotionHeaderRequest request){
        PromotionHeaderResponse save = promotionHeaderService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<PromotionHeaderResponse> update(@Valid @RequestBody PromotionHeaderUpdateRequest request){
        PromotionHeaderResponse updated = promotionHeaderService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<PromotionHeaderResponse> getById(@PathVariable("id") @NotBlank Long id){
        PromotionHeaderResponse get = promotionHeaderService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }

    @GetMapping("MaxCode")
    public ResponseEntity<String> getMaxCode(){
        String max = promotionHeaderService.getMaxCode();
        return ResponseEntity.ok(max);
    }


    @GetMapping()
    public ResponseEntity<List<PromotionHeaderResponse>> getAll(){
        List<PromotionHeaderResponse> getall = promotionHeaderService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = promotionHeaderService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}