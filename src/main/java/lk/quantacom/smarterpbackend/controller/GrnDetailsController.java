package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GrnDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.GrnDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GrnDetailsResponse;
import lk.quantacom.smarterpbackend.service.GrnDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("GrnDetails")
@RestController
@CrossOrigin
public class GrnDetailsController {

    @Autowired
    private GrnDetailsService grnDetailsService;


    @PostMapping
    public ResponseEntity<GrnDetailsResponse> save(@Valid @RequestBody GrnDetailsRequest request){
        GrnDetailsResponse save = grnDetailsService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<GrnDetailsResponse> update(@Valid @RequestBody GrnDetailsUpdateRequest request){
        GrnDetailsResponse updated = grnDetailsService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<GrnDetailsResponse> getById(@PathVariable("id") @NotBlank Long id){
        GrnDetailsResponse get = grnDetailsService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<GrnDetailsResponse>> getAll(){
        List<GrnDetailsResponse> getall = grnDetailsService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = grnDetailsService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}