package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.ItemMasterRequest;
import lk.quantacom.smarterpbackend.dto.request.ItemMasterUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ItemMasterResponse;
import lk.quantacom.smarterpbackend.service.ItemMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ItemMaster")
@RestController
@CrossOrigin
public class ItemMasterController {

    @Autowired
    private ItemMasterService itemMasterService;


    @PostMapping
    public ResponseEntity<ItemMasterResponse> save(@Valid @RequestBody ItemMasterRequest request){
        ItemMasterResponse save = itemMasterService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ItemMasterResponse> update(@Valid @RequestBody ItemMasterUpdateRequest request){
        ItemMasterResponse updated = itemMasterService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ItemMasterResponse> getById(@PathVariable("id") @NotBlank String id){
        ItemMasterResponse get = itemMasterService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }

    @GetMapping("ByItemCode/{code}")
    public ResponseEntity<ItemMasterResponse> getByCode(@PathVariable("code") @NotBlank String code){
        ItemMasterResponse get = itemMasterService.getByItemCode(code);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ItemMasterResponse>> getAll(){
        List<ItemMasterResponse> getall = itemMasterService.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("All")
    public ResponseEntity<List<ItemMasterResponse>> getAllAll(){
        List<ItemMasterResponse> getall = itemMasterService.getAllAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank String id){
        int deleted = itemMasterService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}