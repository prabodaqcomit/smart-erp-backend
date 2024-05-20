package lk.quantacom.smarterpbackend.controller;


import lk.quantacom.smarterpbackend.dto.request.ModuleNamesRequest;
import lk.quantacom.smarterpbackend.dto.request.ModuleNamesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ModuleNamesResponse;
import lk.quantacom.smarterpbackend.service.ModuleNamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ModuleNames")
@RestController
@CrossOrigin
public class ModuleNamesController {

    @Autowired
    private ModuleNamesService moduleNamesService;


    @PostMapping
    public ResponseEntity<ModuleNamesResponse> save(@Valid @RequestBody ModuleNamesRequest request){
        ModuleNamesResponse save = moduleNamesService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ModuleNamesResponse> update(@Valid @RequestBody ModuleNamesUpdateRequest request){
        ModuleNamesResponse updated = moduleNamesService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ModuleNamesResponse> getById(@PathVariable("id") @NotBlank Long id){
        ModuleNamesResponse get = moduleNamesService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ModuleNamesResponse>> getAll(){
        List<ModuleNamesResponse> getall = moduleNamesService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = moduleNamesService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}