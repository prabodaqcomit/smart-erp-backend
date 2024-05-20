package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.PayeeDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.PayeeDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PayeeDetailsResponse;
import lk.quantacom.smarterpbackend.service.PayeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("PayeeDetails")
@RestController
@CrossOrigin
public class PayeeDetailsController {

    @Autowired
    private PayeeDetailsService payeeDetailsService;


    @PostMapping
    public ResponseEntity<PayeeDetailsResponse> save(@Valid @RequestBody PayeeDetailsRequest request){
        PayeeDetailsResponse save = payeeDetailsService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<PayeeDetailsResponse> update(@Valid @RequestBody PayeeDetailsUpdateRequest request){
        PayeeDetailsResponse updated = payeeDetailsService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<PayeeDetailsResponse> getById(@PathVariable("id") @NotBlank Long id){
        PayeeDetailsResponse get = payeeDetailsService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<PayeeDetailsResponse>> getAll(){
        List<PayeeDetailsResponse> getall = payeeDetailsService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = payeeDetailsService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}