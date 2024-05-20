package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GenaralPaymentsRequest;
import lk.quantacom.smarterpbackend.dto.request.GenaralPaymentsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GenaralPaymentsResponse;
import lk.quantacom.smarterpbackend.service.GenaralPaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("GenaralPayments")
@RestController
@CrossOrigin
public class GenaralPaymentsController {

    @Autowired
    private GenaralPaymentsService genaralPaymentsService;


    @PostMapping
    public ResponseEntity<GenaralPaymentsResponse> save(@Valid @RequestBody GenaralPaymentsRequest request){
        GenaralPaymentsResponse save = genaralPaymentsService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<GenaralPaymentsResponse> update(@Valid @RequestBody GenaralPaymentsUpdateRequest request){
        GenaralPaymentsResponse updated = genaralPaymentsService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<GenaralPaymentsResponse> getById(@PathVariable("id") @NotBlank Long id){
        GenaralPaymentsResponse get = genaralPaymentsService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<GenaralPaymentsResponse>> getAll(){
        List<GenaralPaymentsResponse> getall = genaralPaymentsService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = genaralPaymentsService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}