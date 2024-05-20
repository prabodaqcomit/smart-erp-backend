package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.PrnTemparyRequest;
import lk.quantacom.smarterpbackend.dto.request.PrnTemparyUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PrnTemparyResponse;
import lk.quantacom.smarterpbackend.service.PrnTemparyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("PrnTempary")
@RestController
@CrossOrigin
public class PrnTemparyController {

    @Autowired
    private PrnTemparyService prnTemparyService;


    @PostMapping
    public ResponseEntity<List<PrnTemparyResponse>> save(@Valid @RequestBody List<PrnTemparyRequest> requestList){
        List<PrnTemparyResponse> save = prnTemparyService.save(requestList);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<PrnTemparyResponse> update(@Valid @RequestBody PrnTemparyUpdateRequest request){
        PrnTemparyResponse updated = prnTemparyService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<PrnTemparyResponse> getById(@PathVariable("id") @NotBlank Integer id){
        PrnTemparyResponse get = prnTemparyService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<PrnTemparyResponse>> getAll(){
        List<PrnTemparyResponse> getall = prnTemparyService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Integer id){
        int deleted = prnTemparyService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}