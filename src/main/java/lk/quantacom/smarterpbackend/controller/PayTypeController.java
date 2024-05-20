package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.PayTypeRequest;
import lk.quantacom.smarterpbackend.dto.request.PayTypeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PayTypeResponse;
import lk.quantacom.smarterpbackend.service.PayTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("PayType")
@RestController
@CrossOrigin
public class PayTypeController {

    @Autowired
    private PayTypeService payTypeService;


    @PostMapping
    public ResponseEntity<PayTypeResponse> save(@Valid @RequestBody PayTypeRequest request){
        PayTypeResponse save = payTypeService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<PayTypeResponse> update(@Valid @RequestBody PayTypeUpdateRequest request){
        PayTypeResponse updated = payTypeService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<PayTypeResponse> getById(@PathVariable("id") @NotBlank Long id){
        PayTypeResponse get = payTypeService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<PayTypeResponse>> getAll(){
        List<PayTypeResponse> getall = payTypeService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = payTypeService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}