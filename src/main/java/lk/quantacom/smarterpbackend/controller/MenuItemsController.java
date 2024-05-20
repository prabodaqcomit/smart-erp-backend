package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.MenuItemsRequest;
import lk.quantacom.smarterpbackend.dto.request.MenuItemsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.MenuItemsResponse;
import lk.quantacom.smarterpbackend.service.MenuItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("MenuItems")
@RestController
@CrossOrigin
public class MenuItemsController {

    @Autowired
    private MenuItemsService menuItemsService;


    @PostMapping
    public ResponseEntity<MenuItemsResponse> save(@Valid @RequestBody MenuItemsRequest request){
        MenuItemsResponse save = menuItemsService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<MenuItemsResponse> update(@Valid @RequestBody MenuItemsUpdateRequest request){
        MenuItemsResponse updated = menuItemsService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<MenuItemsResponse> getById(@PathVariable("id") @NotBlank Long id){
        MenuItemsResponse get = menuItemsService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<MenuItemsResponse>> getAll(){
        List<MenuItemsResponse> getall = menuItemsService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = menuItemsService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}