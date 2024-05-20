package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.ActionProfileMapRequest;
import lk.quantacom.smarterpbackend.dto.request.ActionProfileMapUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ActionProfileMapResponse;
import lk.quantacom.smarterpbackend.service.ActionProfileMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ActionProfileMap")
@RestController
@CrossOrigin
public class ActionProfileMapController {

    @Autowired
    private ActionProfileMapService actionProfileMapService;


    @PostMapping
    public ResponseEntity<ActionProfileMapResponse> save(@Valid @RequestBody ActionProfileMapRequest request){
        ActionProfileMapResponse save = actionProfileMapService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ActionProfileMapResponse> update(@Valid @RequestBody ActionProfileMapUpdateRequest request){
        ActionProfileMapResponse updated = actionProfileMapService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ActionProfileMapResponse> getById(@PathVariable("id") @NotBlank Long id){
        ActionProfileMapResponse get = actionProfileMapService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ActionProfileMapResponse>> getAll(){
        List<ActionProfileMapResponse> getall = actionProfileMapService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = actionProfileMapService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}