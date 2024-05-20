package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GLPayMethodDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.GLPayMethodDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GLPayMethodDetailsResponse;
import lk.quantacom.smarterpbackend.service.GLPayMethodDetailsService;
import lk.quantacom.smarterpbackend.service.GLPayMethodDetailsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("GLPayMethodDetailsTemplate")
@RestController
@CrossOrigin
public class GLPayMethodDetailsTemplateController {

    @Autowired
    private GLPayMethodDetailsTemplateService gLPayMethodDetailsService;


    @PostMapping
    public ResponseEntity<GLPayMethodDetailsResponse> save(@Valid @RequestBody GLPayMethodDetailsRequest request){
        GLPayMethodDetailsResponse save = gLPayMethodDetailsService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<GLPayMethodDetailsResponse> update(@Valid @RequestBody GLPayMethodDetailsUpdateRequest request){
        GLPayMethodDetailsResponse updated = gLPayMethodDetailsService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<GLPayMethodDetailsResponse> getById(@PathVariable("id") @NotBlank Long id){
        GLPayMethodDetailsResponse get = gLPayMethodDetailsService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<GLPayMethodDetailsResponse>> getAll(){
        List<GLPayMethodDetailsResponse> getall = gLPayMethodDetailsService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = gLPayMethodDetailsService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}