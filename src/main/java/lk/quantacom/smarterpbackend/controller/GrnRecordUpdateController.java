package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GrnRecordUpdateRequest;
import lk.quantacom.smarterpbackend.dto.request.GrnRecordUpdateUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GrnRecordUpdateResponse;
import lk.quantacom.smarterpbackend.service.GrnRecordUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("GrnRecordUpdate")
@RestController
@CrossOrigin
public class GrnRecordUpdateController {

    @Autowired
    private GrnRecordUpdateService grnRecordUpdateService;


    @PostMapping
    public ResponseEntity<GrnRecordUpdateResponse> save(@Valid @RequestBody GrnRecordUpdateRequest request){
        GrnRecordUpdateResponse save = grnRecordUpdateService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<GrnRecordUpdateResponse> update(@Valid @RequestBody GrnRecordUpdateUpdateRequest request){
        GrnRecordUpdateResponse updated = grnRecordUpdateService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<GrnRecordUpdateResponse> getById(@PathVariable("id") @NotBlank Integer id){
        GrnRecordUpdateResponse get = grnRecordUpdateService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<GrnRecordUpdateResponse>> getAll(){
        List<GrnRecordUpdateResponse> getall = grnRecordUpdateService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Integer id){
        int deleted = grnRecordUpdateService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}