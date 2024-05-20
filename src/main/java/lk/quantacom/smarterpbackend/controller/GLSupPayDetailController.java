package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GLSupPayDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.GLSupPayDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GLSupPayDetailResponse;
import lk.quantacom.smarterpbackend.service.GLSupPayDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("GLSupPayDetail")
@RestController
@CrossOrigin
public class GLSupPayDetailController {

    @Autowired
    private GLSupPayDetailService gLSupPayDetailService;


    @PostMapping
    public ResponseEntity<GLSupPayDetailResponse> save(@Valid @RequestBody GLSupPayDetailRequest request){
        GLSupPayDetailResponse save = gLSupPayDetailService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<GLSupPayDetailResponse> update(@Valid @RequestBody GLSupPayDetailUpdateRequest request){
        GLSupPayDetailResponse updated = gLSupPayDetailService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<GLSupPayDetailResponse> getById(@PathVariable("id") @NotBlank Long id){
        GLSupPayDetailResponse get = gLSupPayDetailService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<GLSupPayDetailResponse>> getAll(){
        List<GLSupPayDetailResponse> getall = gLSupPayDetailService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = gLSupPayDetailService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}