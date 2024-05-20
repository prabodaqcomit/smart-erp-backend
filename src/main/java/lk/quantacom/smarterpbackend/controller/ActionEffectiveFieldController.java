package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.ActionEffectiveFieldRequest;
import lk.quantacom.smarterpbackend.dto.request.ActionEffectiveFieldUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ActionEffectiveFieldResponse;
import lk.quantacom.smarterpbackend.service.ActionEffectiveFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ActionEffectiveField")
@RestController
@CrossOrigin
public class ActionEffectiveFieldController {

    @Autowired
    private ActionEffectiveFieldService actionEffectiveFieldService;


    @PostMapping
    public ResponseEntity<ActionEffectiveFieldResponse> save(@Valid @RequestBody ActionEffectiveFieldRequest request){
        ActionEffectiveFieldResponse save = actionEffectiveFieldService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ActionEffectiveFieldResponse> update(@Valid @RequestBody ActionEffectiveFieldUpdateRequest request){
        ActionEffectiveFieldResponse updated = actionEffectiveFieldService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ActionEffectiveFieldResponse> getById(@PathVariable("id") @NotBlank Long id){
        ActionEffectiveFieldResponse get = actionEffectiveFieldService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ActionEffectiveFieldResponse>> getAll(){
        List<ActionEffectiveFieldResponse> getall = actionEffectiveFieldService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = actionEffectiveFieldService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}