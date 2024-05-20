package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.ColorRequest;
import lk.quantacom.smarterpbackend.dto.request.ColorUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ColorResponse;
import lk.quantacom.smarterpbackend.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Color")
@RestController
@CrossOrigin
public class ColorController {

    @Autowired
    private ColorService colorService;


    @PostMapping
    public ResponseEntity<ColorResponse> save(@Valid @RequestBody ColorRequest request){
        ColorResponse save = colorService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ColorResponse> update(@Valid @RequestBody ColorUpdateRequest request){
        ColorResponse updated = colorService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ColorResponse> getById(@PathVariable("id") @NotBlank Long id){
        ColorResponse get = colorService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ColorResponse>> getAll(){
        List<ColorResponse> getall = colorService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = colorService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}