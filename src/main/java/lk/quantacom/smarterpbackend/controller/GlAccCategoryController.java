package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GlAccCategoryRequest;
import lk.quantacom.smarterpbackend.dto.request.GlAccCategoryUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GlAccCategoryResponse;
import lk.quantacom.smarterpbackend.dto.response.GlMainAccTypeResponse;
import lk.quantacom.smarterpbackend.service.GlAccCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("GlAccCategory")
@RestController
@CrossOrigin
public class GlAccCategoryController {

    @Autowired
    private GlAccCategoryService glAccCategoryService;


    @PostMapping
    public ResponseEntity<GlAccCategoryResponse> save(@Valid @RequestBody GlAccCategoryRequest request){
        GlAccCategoryResponse save = glAccCategoryService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<GlAccCategoryResponse> update(@Valid @RequestBody GlAccCategoryUpdateRequest request){
        GlAccCategoryResponse updated = glAccCategoryService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<GlAccCategoryResponse> getById(@PathVariable("id") @NotBlank Long id){
        GlAccCategoryResponse get = glAccCategoryService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<GlAccCategoryResponse>> getAll(){
        List<GlAccCategoryResponse> getall = glAccCategoryService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = glAccCategoryService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("getAllById/{id}")
    public ResponseEntity<List<GlAccCategoryResponse>> getAllById(@PathVariable("id") @NotBlank Long id){
        List<GlAccCategoryResponse> getall = glAccCategoryService.getAllById(id);
        return ResponseEntity.ok(getall);
    }


}