package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.StockAdjustRequest;
import lk.quantacom.smarterpbackend.dto.request.StockAdjustUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.StockAdjustResponse;
import lk.quantacom.smarterpbackend.service.StockAdjustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("StockAdjust")
@RestController
@CrossOrigin
public class StockAdjustController {

    @Autowired
    private StockAdjustService stockAdjustService;


    @PostMapping
    public ResponseEntity<List<StockAdjustResponse>> save(@Valid @RequestBody List<StockAdjustRequest> request){
        List<StockAdjustResponse> save = stockAdjustService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<StockAdjustResponse> update(@Valid @RequestBody StockAdjustUpdateRequest request){
        StockAdjustResponse updated = stockAdjustService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<StockAdjustResponse> getById(@PathVariable("id") @NotBlank Long id){
        StockAdjustResponse get = stockAdjustService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<StockAdjustResponse>> getAll(){
        List<StockAdjustResponse> getall = stockAdjustService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = stockAdjustService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}