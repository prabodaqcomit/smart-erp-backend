package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GlSubAccTypeRequest;
import lk.quantacom.smarterpbackend.dto.request.GlSubAccTypeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GlMainAccTypeResponse;
import lk.quantacom.smarterpbackend.dto.response.GlSubAccTypeResponse;
import lk.quantacom.smarterpbackend.service.GlSubAccTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("GlSubAccType")
@RestController
@CrossOrigin
public class GlSubAccTypeController {

    @Autowired
    private GlSubAccTypeService glSubAccTypeService;


    @PostMapping
    public ResponseEntity<GlSubAccTypeResponse> save(@Valid @RequestBody GlSubAccTypeRequest request){
        GlSubAccTypeResponse save = glSubAccTypeService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<GlSubAccTypeResponse> update(@Valid @RequestBody GlSubAccTypeUpdateRequest request){
        GlSubAccTypeResponse updated = glSubAccTypeService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<GlSubAccTypeResponse> getById(@PathVariable("id") @NotBlank Long id){
        GlSubAccTypeResponse get = glSubAccTypeService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<GlSubAccTypeResponse>> getAll(){
        List<GlSubAccTypeResponse> getall = glSubAccTypeService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = glSubAccTypeService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }


    @GetMapping("getByCatAndMain/{catId}/{mainAccId}")
    public ResponseEntity<List<GlSubAccTypeResponse>> getByCatAndMain(@PathVariable("catId") @NotBlank Long catId,
                                                                       @PathVariable("mainAccId") @NotBlank Long mainAccId){
        List<GlSubAccTypeResponse> getall = glSubAccTypeService.getByCatAndMain(catId,mainAccId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getAllById/{id}")
    public ResponseEntity<List<GlSubAccTypeResponse>> getAllById(@PathVariable("id") @NotBlank Long id){
        List<GlSubAccTypeResponse> getall = glSubAccTypeService.getAllById(id);
        return ResponseEntity.ok(getall);
    }



}