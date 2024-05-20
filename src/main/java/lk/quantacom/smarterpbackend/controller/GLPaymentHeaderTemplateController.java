package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.GLPaymentHeaderResponse;
import lk.quantacom.smarterpbackend.service.GLPaymentHeaderService;
import lk.quantacom.smarterpbackend.service.GLPaymentHeaderTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("GLPaymentHeaderTemplate")
@RestController
@CrossOrigin
public class GLPaymentHeaderTemplateController {

    @Autowired
    private GLPaymentHeaderTemplateService gLPaymentHeaderService;

    @PostMapping
    public ResponseEntity<GLPaymentHeaderResponse> save(@Valid @RequestBody GLPaymentHeaderRequest request){
        GLPaymentHeaderResponse save = gLPaymentHeaderService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<GLPaymentHeaderResponse> update(@Valid @RequestBody GLPaymentHeaderUpdateRequest request){
        GLPaymentHeaderResponse updated = gLPaymentHeaderService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping("{id}")
    public ResponseEntity<GLPaymentHeaderResponse> getById(@PathVariable("id") @NotBlank Long id){
        GLPaymentHeaderResponse get = gLPaymentHeaderService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }

    @GetMapping()
    public ResponseEntity<List<GLPaymentHeaderResponse>> getAll(){
        List<GLPaymentHeaderResponse> getall = gLPaymentHeaderService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = gLPaymentHeaderService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("getMaxVoucherNum")
    public ResponseEntity<String> getMaxVoucherNum(){
        String get = gLPaymentHeaderService.getMaxVoucherNum();
        if(get==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(get);
    }

    @PostMapping("saveGeneral")
    public ResponseEntity<GLPaymentHeaderResponse> saveGeneral(@Valid @RequestBody GLPaymentHeaderGeneralRequest request){
        GLPaymentHeaderResponse save = gLPaymentHeaderService.saveGeneral(request);
        return ResponseEntity.ok(save);
    }

    @PostMapping("saveSupplier")
    public ResponseEntity<GLPaymentHeaderResponse> saveSupplier(@Valid @RequestBody GLPaymentHeaderSupplierRequest request){
        GLPaymentHeaderResponse save = gLPaymentHeaderService.saveSupplier(request);
        return ResponseEntity.ok(save);
    }

    @PostMapping("filteredSearch")
    public ResponseEntity<List<GLPaymentHeaderResponse>> filteredSearch(@Valid @RequestBody GLPaymentFilterSearchRequest request){
        List<GLPaymentHeaderResponse> getall = gLPaymentHeaderService.filteredSearch(request);
        return ResponseEntity.ok(getall);
    }

}