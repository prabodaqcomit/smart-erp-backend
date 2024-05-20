package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.TblinvhedRequest;
import lk.quantacom.smarterpbackend.dto.request.TblinvhedUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblinvListDtlsResponse;
import lk.quantacom.smarterpbackend.dto.response.TblinvhedResponse;
import lk.quantacom.smarterpbackend.service.TblinvhedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Tblinvhed")
@RestController
@CrossOrigin
public class TblinvhedController {

    @Autowired
    private TblinvhedService tblinvhedService;


    @PostMapping
    public ResponseEntity<TblinvhedResponse> save(@Valid @RequestBody TblinvhedRequest request){
        TblinvhedResponse save = tblinvhedService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<TblinvhedResponse> update(@Valid @RequestBody TblinvhedUpdateRequest request){
        TblinvhedResponse updated = tblinvhedService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<TblinvhedResponse> getById(@PathVariable("id") @NotBlank String id){
        TblinvhedResponse get = tblinvhedService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<TblinvhedResponse>> getAll(){
        List<TblinvhedResponse> getall = tblinvhedService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank String id){
        int deleted = tblinvhedService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }


    @GetMapping("TblinvListDtls/{fldInvno}")
    public ResponseEntity<TblinvListDtlsResponse> TblinvListDtls(@PathVariable("fldInvno") @NotBlank String fldInvno){
        TblinvListDtlsResponse getall = tblinvhedService.TblinvListDtls(fldInvno);
        if(getall==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getall);
    }


}