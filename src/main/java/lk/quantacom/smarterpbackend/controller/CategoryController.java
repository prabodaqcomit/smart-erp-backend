package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.CategoryRequest;
import lk.quantacom.smarterpbackend.dto.request.CategoryUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.CategoryResponse;
import lk.quantacom.smarterpbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("Category")
@RestController
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryRequest request){
        CategoryResponse save = categoryService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<CategoryResponse> update(@Valid @RequestBody CategoryUpdateRequest request){
        CategoryResponse updated = categoryService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable("id") @NotBlank Long id){
        CategoryResponse get = categoryService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAll(){
        List<CategoryResponse> getall = categoryService.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("Material")
    public ResponseEntity<List<CategoryResponse>> getAllMaT(){
        List<CategoryResponse> getall = categoryService.getAll();
        List<CategoryResponse> getall2=new ArrayList<>();
        for(CategoryResponse response:getall){
            if(response.getIsMaterialCategory()!=null){
                if(response.getIsMaterialCategory()){
                    getall2.add(response);
                }
            }
        }
        return ResponseEntity.ok(getall2);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = categoryService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}