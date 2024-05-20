package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GlMainAccTypeRequest;
import lk.quantacom.smarterpbackend.dto.request.GlMainAccTypeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GlMainAccTypeResponse;
import lk.quantacom.smarterpbackend.service.GlMainAccTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("GlMainAccType")
@RestController
@CrossOrigin
public class GlMainAccTypeController {

    @Autowired
    private GlMainAccTypeService glMainAccTypeService;


    @PostMapping
    public ResponseEntity<GlMainAccTypeResponse> save(@Valid @RequestBody GlMainAccTypeRequest request){
        GlMainAccTypeResponse save = glMainAccTypeService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<GlMainAccTypeResponse> update(@Valid @RequestBody GlMainAccTypeUpdateRequest request){
        GlMainAccTypeResponse updated = glMainAccTypeService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<GlMainAccTypeResponse> getById(@PathVariable("id") @NotBlank Long id){
        GlMainAccTypeResponse get = glMainAccTypeService.getById(id);
        if(get==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(get);
    }

    @GetMapping()
    public ResponseEntity<List<GlMainAccTypeResponse>> getAll(){
        List<GlMainAccTypeResponse> getall = glMainAccTypeService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = glMainAccTypeService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }


    @GetMapping("getAllByCat/{catId}")
    public ResponseEntity<List<GlMainAccTypeResponse>> getAllByCat(@PathVariable("catId") @NotBlank Long catId){
        List<GlMainAccTypeResponse> getall = glMainAccTypeService.getAllByCat(catId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getAllById/{id}")
    public ResponseEntity<List<GlMainAccTypeResponse>> getAllById(@PathVariable("id") @NotBlank Long id){
        List<GlMainAccTypeResponse> getall = glMainAccTypeService.getAllById(id);
        return ResponseEntity.ok(getall);
    }


}