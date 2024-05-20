package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.BinCardRequest;
import lk.quantacom.smarterpbackend.dto.request.BinCardUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BinCardResponse;
import lk.quantacom.smarterpbackend.service.BinCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("BinCard")
@RestController
@CrossOrigin
public class BinCardController {

    @Autowired
    private BinCardService binCardService;


    @PostMapping
    public ResponseEntity<BinCardResponse> save(@Valid @RequestBody BinCardRequest request){
        BinCardResponse save = binCardService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<BinCardResponse> update(@Valid @RequestBody BinCardUpdateRequest request){
        BinCardResponse updated = binCardService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<BinCardResponse> getById(@PathVariable("id") @NotBlank Long id){
        BinCardResponse get = binCardService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<BinCardResponse>> getAll(){
        List<BinCardResponse> getall = binCardService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = binCardService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}