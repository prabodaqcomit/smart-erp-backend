package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.OrderFormRequest;
import lk.quantacom.smarterpbackend.dto.request.OrderFormUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.OrderFormResponse;
import lk.quantacom.smarterpbackend.service.OrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("OrderForm")
@RestController
@CrossOrigin
public class OrderFormController {

    @Autowired
    private OrderFormService orderFormService;


    @PostMapping
    public ResponseEntity<List<OrderFormResponse>> save(@Valid @RequestBody List<OrderFormRequest> request){
        List<OrderFormResponse> save = orderFormService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<OrderFormResponse> update(@Valid @RequestBody OrderFormUpdateRequest request){
        OrderFormResponse updated = orderFormService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<OrderFormResponse> getById(@PathVariable("id") @NotBlank Long id){
        OrderFormResponse get = orderFormService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<OrderFormResponse>> getAll(){
        List<OrderFormResponse> getall = orderFormService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = orderFormService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}