package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.DiningTableRequest;
import lk.quantacom.smarterpbackend.dto.request.DiningTableUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.DiningTableResponse;
import lk.quantacom.smarterpbackend.service.DiningTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("DiningTable")
@RestController
@CrossOrigin
public class DiningTableController {

    @Autowired
    private DiningTableService diningTableService;


    @PostMapping
    public ResponseEntity<DiningTableResponse> save(@Valid @RequestBody DiningTableRequest request){
        DiningTableResponse save = diningTableService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<DiningTableResponse> update(@Valid @RequestBody DiningTableUpdateRequest request){
        DiningTableResponse updated = diningTableService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<DiningTableResponse> getById(@PathVariable("id") @NotBlank Long id){
        DiningTableResponse get = diningTableService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<DiningTableResponse>> getAll(){
        List<DiningTableResponse> getall = diningTableService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = diningTableService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}