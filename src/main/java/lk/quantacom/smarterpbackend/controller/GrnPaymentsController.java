package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GrnPaymentsRequest;
import lk.quantacom.smarterpbackend.dto.request.GrnPaymentsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GLPayMethodDetailsResponse;
import lk.quantacom.smarterpbackend.dto.response.GrnPaymentsResponse;
import lk.quantacom.smarterpbackend.dto.response.supplierPaymentInfoResponse;
import lk.quantacom.smarterpbackend.service.GrnPaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("GrnPayments")
@RestController
@CrossOrigin
public class GrnPaymentsController {

    @Autowired
    private GrnPaymentsService grnPaymentsService;


    @PostMapping
    public ResponseEntity<GrnPaymentsResponse> save(@Valid @RequestBody GrnPaymentsRequest request){
        GrnPaymentsResponse save = grnPaymentsService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<GrnPaymentsResponse> update(@Valid @RequestBody GrnPaymentsUpdateRequest request){
        GrnPaymentsResponse updated = grnPaymentsService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<GrnPaymentsResponse> getById(@PathVariable("id") @NotBlank Long id){
        GrnPaymentsResponse get = grnPaymentsService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<GrnPaymentsResponse>> getAll(){
        List<GrnPaymentsResponse> getall = grnPaymentsService.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("SupplierOB")
    public ResponseEntity<List<GrnPaymentsResponse>> getAllForSupOB(){
        List<GrnPaymentsResponse> getall = grnPaymentsService.getAllForSupOb();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = grnPaymentsService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }


    @GetMapping("getAlSup")
    public ResponseEntity<List<supplierPaymentInfoResponse>> getAlSup(){
        List<supplierPaymentInfoResponse> getall = grnPaymentsService.getAlSup();
        return ResponseEntity.ok(getall);
    }


    @GetMapping("getAlBySup/{supId}")
    public ResponseEntity<List<supplierPaymentInfoResponse>> getAllForSupOB(@PathVariable("supId") @NotBlank Integer supId){
        List<supplierPaymentInfoResponse> getall = grnPaymentsService.getAlBySup(supId);
        return ResponseEntity.ok(getall);
    }


}