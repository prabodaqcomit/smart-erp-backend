package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.MainNavButtonsRequest;
import lk.quantacom.smarterpbackend.dto.request.MainNavButtonsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.MainNavButtonsResponse;
import lk.quantacom.smarterpbackend.service.MainNavButtonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("MainNavButtons")
@RestController
@CrossOrigin
public class MainNavButtonsController {

    @Autowired
    private MainNavButtonsService mainNavButtonsService;


    @PostMapping
    public ResponseEntity<MainNavButtonsResponse> save(@Valid @RequestBody MainNavButtonsRequest request){
        MainNavButtonsResponse save = mainNavButtonsService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<MainNavButtonsResponse> update(@Valid @RequestBody MainNavButtonsUpdateRequest request){
        MainNavButtonsResponse updated = mainNavButtonsService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<MainNavButtonsResponse> getById(@PathVariable("id") @NotBlank Long id){
        MainNavButtonsResponse get = mainNavButtonsService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<MainNavButtonsResponse>> getAll(){
        List<MainNavButtonsResponse> getall = mainNavButtonsService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = mainNavButtonsService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}