package lk.quantacom.smarterpbackend.controller;


import lk.quantacom.smarterpbackend.dto.request.ActionTypeRequest;
import lk.quantacom.smarterpbackend.dto.request.ActionTypeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ActionTypeResponse;
import lk.quantacom.smarterpbackend.service.ActionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ActionType")
@RestController
@CrossOrigin
public class ActionTypeController {

    @Autowired
    private ActionTypeService actionTypeService;


    @PostMapping
    public ResponseEntity<ActionTypeResponse> save(@Valid @RequestBody ActionTypeRequest request){
        ActionTypeResponse save = actionTypeService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ActionTypeResponse> update(@Valid @RequestBody ActionTypeUpdateRequest request){
        ActionTypeResponse updated = actionTypeService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ActionTypeResponse> getById(@PathVariable("id") @NotBlank Long id){
        ActionTypeResponse get = actionTypeService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ActionTypeResponse>> getAll(){
        List<ActionTypeResponse> getall = actionTypeService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = actionTypeService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}