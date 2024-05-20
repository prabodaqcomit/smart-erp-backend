package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.TblpaymodeRequest;
import lk.quantacom.smarterpbackend.dto.request.TblpaymodeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblpaymodeResponse;
import lk.quantacom.smarterpbackend.service.TblpaymodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Tblpaymode")
@RestController
@CrossOrigin
public class TblpaymodeController {

    @Autowired
    private TblpaymodeService tblpaymodeService;


    @PostMapping
    public ResponseEntity<TblpaymodeResponse> save(@Valid @RequestBody TblpaymodeRequest request){
        TblpaymodeResponse save = tblpaymodeService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<TblpaymodeResponse> update(@Valid @RequestBody TblpaymodeUpdateRequest request){
        TblpaymodeResponse updated = tblpaymodeService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<TblpaymodeResponse> getById(@PathVariable("id") @NotBlank Long id){
        TblpaymodeResponse get = tblpaymodeService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<TblpaymodeResponse>> getAll(){
        List<TblpaymodeResponse> getall = tblpaymodeService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = tblpaymodeService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}