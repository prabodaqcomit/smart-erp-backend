package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.WholeSaleInvHedRequest;
import lk.quantacom.smarterpbackend.dto.request.WholeSaleInvHedUpdateRequest;
import lk.quantacom.smarterpbackend.dto.request.WholeSaleInvRequest;
import lk.quantacom.smarterpbackend.dto.response.WholeSaleInvHedResponse;
import lk.quantacom.smarterpbackend.dto.response.getWholeSaleInvDtlResponse;
import lk.quantacom.smarterpbackend.service.WholeSaleInvHedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("WholeSaleInvHed")
@RestController
@CrossOrigin
public class WholeSaleInvHedController {

    @Autowired
    private WholeSaleInvHedService wholeSaleInvHedService;


    @PostMapping
    public ResponseEntity<WholeSaleInvHedResponse> save(@Valid @RequestBody WholeSaleInvHedRequest request){
        WholeSaleInvHedResponse save = wholeSaleInvHedService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<WholeSaleInvHedResponse> update(@Valid @RequestBody WholeSaleInvHedUpdateRequest request){
        WholeSaleInvHedResponse updated = wholeSaleInvHedService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<WholeSaleInvHedResponse> getById(@PathVariable("id") @NotBlank Integer id){
        WholeSaleInvHedResponse get = wholeSaleInvHedService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<WholeSaleInvHedResponse>> getAll(){
        List<WholeSaleInvHedResponse> getall = wholeSaleInvHedService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Integer id){
        int deleted = wholeSaleInvHedService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }


    @PostMapping("saveInvoice")
    public ResponseEntity<WholeSaleInvHedResponse> saveInvoice(@Valid @RequestBody WholeSaleInvRequest request){
        WholeSaleInvHedResponse save = wholeSaleInvHedService.saveInvoice(request);
        return ResponseEntity.ok(save);
    }

    @GetMapping("getWhInvNo")
    public ResponseEntity<Integer> getWhInvNo(){
        int deleted = wholeSaleInvHedService.getWhInvNo();
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }


    @GetMapping("getWholeSaleInvDtl")
    public ResponseEntity<List<getWholeSaleInvDtlResponse>> getWholeSaleInvDtl(){
        List<getWholeSaleInvDtlResponse> getall = wholeSaleInvHedService.getWholeSaleInvDtl();
        return ResponseEntity.ok(getall);
    }


    @PutMapping("updateCancel/{invNo}")
    public ResponseEntity<Integer> updateCancel(@PathVariable("invNo") @NotBlank String invNo){
        int deleted = wholeSaleInvHedService.updateCancel(invNo);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
 
    @PostMapping("returnWSInvoice")
    public ResponseEntity<WholeSaleInvHedResponse> returnWSInvoice(@Valid @RequestBody WholeSaleInvRequest request){
        WholeSaleInvHedResponse save = wholeSaleInvHedService.returnWSInvoice(request);
        return ResponseEntity.ok(save);
    }


    @GetMapping("getWhTXInvNo")
    public ResponseEntity<String> getWhTXInvNo(){
        String deleted = wholeSaleInvHedService.getWhTXInvNo();
        if(deleted==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("getWhRInvNo")
    public ResponseEntity<String> getWhRInvNo(){
        String deleted = wholeSaleInvHedService.getWhRInvNo();
        if(deleted==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}