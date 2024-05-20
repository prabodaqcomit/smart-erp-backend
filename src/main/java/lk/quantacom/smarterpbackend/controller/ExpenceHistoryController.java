package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.ExpenceHistoryRequest;
import lk.quantacom.smarterpbackend.dto.request.ExpenceHistoryUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ExpenceHistoryResponse;
import lk.quantacom.smarterpbackend.service.ExpenceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ExpenceHistory")
@RestController
@CrossOrigin
public class ExpenceHistoryController {

    @Autowired
    private ExpenceHistoryService expenceHistoryService;


    @PostMapping
    public ResponseEntity<ExpenceHistoryResponse> save(@Valid @RequestBody ExpenceHistoryRequest request){
        ExpenceHistoryResponse save = expenceHistoryService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ExpenceHistoryResponse> update(@Valid @RequestBody ExpenceHistoryUpdateRequest request){
        ExpenceHistoryResponse updated = expenceHistoryService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ExpenceHistoryResponse> getById(@PathVariable("id") @NotBlank Long id){
        ExpenceHistoryResponse get = expenceHistoryService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ExpenceHistoryResponse>> getAll(){
        List<ExpenceHistoryResponse> getall = expenceHistoryService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = expenceHistoryService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}