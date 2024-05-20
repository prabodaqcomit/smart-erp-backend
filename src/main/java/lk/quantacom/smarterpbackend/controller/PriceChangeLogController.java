package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.PriceChangeLogRequest;
import lk.quantacom.smarterpbackend.dto.request.PriceChangeLogUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PriceChangeLogResponse;
import lk.quantacom.smarterpbackend.service.PriceChangeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("PriceChangeLog")
@RestController
@CrossOrigin
public class PriceChangeLogController {

    @Autowired
    private PriceChangeLogService priceChangeLogService;


    @PostMapping
    public ResponseEntity<PriceChangeLogResponse> save(@Valid @RequestBody PriceChangeLogRequest request){
        PriceChangeLogResponse save = priceChangeLogService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<PriceChangeLogResponse> update(@Valid @RequestBody PriceChangeLogUpdateRequest request){
        PriceChangeLogResponse updated = priceChangeLogService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<PriceChangeLogResponse> getById(@PathVariable("id") @NotBlank Long id){
        PriceChangeLogResponse get = priceChangeLogService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<PriceChangeLogResponse>> getAll(){
        List<PriceChangeLogResponse> getall = priceChangeLogService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = priceChangeLogService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}