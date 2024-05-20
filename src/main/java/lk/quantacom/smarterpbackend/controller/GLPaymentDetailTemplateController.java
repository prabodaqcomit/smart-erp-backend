package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GLPaymentDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.GLPaymentDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GLPaymentDetailResponse;
import lk.quantacom.smarterpbackend.service.GLPaymentDetailService;
import lk.quantacom.smarterpbackend.service.GLPaymentDetailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("GLPaymentDetailTemplate")
@RestController
@CrossOrigin
public class GLPaymentDetailTemplateController {

    @Autowired
    private GLPaymentDetailTemplateService gLPaymentDetailService;


    @PostMapping
    public ResponseEntity<GLPaymentDetailResponse> save(@Valid @RequestBody GLPaymentDetailRequest request){
        GLPaymentDetailResponse save = gLPaymentDetailService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<GLPaymentDetailResponse> update(@Valid @RequestBody GLPaymentDetailUpdateRequest request){
        GLPaymentDetailResponse updated = gLPaymentDetailService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<GLPaymentDetailResponse> getById(@PathVariable("id") @NotBlank Long id){
        GLPaymentDetailResponse get = gLPaymentDetailService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<GLPaymentDetailResponse>> getAll(){
        List<GLPaymentDetailResponse> getall = gLPaymentDetailService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = gLPaymentDetailService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}