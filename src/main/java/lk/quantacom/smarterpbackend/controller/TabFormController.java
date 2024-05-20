package lk.quantacom.smarterpbackend.controller;


import lk.quantacom.smarterpbackend.dto.request.TabFormRequest;
import lk.quantacom.smarterpbackend.dto.request.TabFormUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TabFormResponse;
import lk.quantacom.smarterpbackend.service.TabFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("TabForm")
@RestController
@CrossOrigin
public class TabFormController {

    @Autowired
    private TabFormService tabFormService;


    @PostMapping
    public ResponseEntity<TabFormResponse> save(@Valid @RequestBody TabFormRequest request){
        TabFormResponse save = tabFormService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<TabFormResponse> update(@Valid @RequestBody TabFormUpdateRequest request){
        TabFormResponse updated = tabFormService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<TabFormResponse> getById(@PathVariable("id") @NotBlank Long id){
        TabFormResponse get = tabFormService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<TabFormResponse>> getAll(){
        List<TabFormResponse> getall = tabFormService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = tabFormService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}