package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.TblinvdtlRequest;
import lk.quantacom.smarterpbackend.dto.request.TblinvdtlUpdateRequest;
import lk.quantacom.smarterpbackend.dto.request.getMontlyInvRequest;
import lk.quantacom.smarterpbackend.dto.response.TblinvdtlResponse;
import lk.quantacom.smarterpbackend.dto.response.getMonthlyDtlsByAllResponse;
import lk.quantacom.smarterpbackend.dto.response.getMonthlyDtlsListAllResponse;
import lk.quantacom.smarterpbackend.service.TblinvdtlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Tblinvdtl")
@RestController
@CrossOrigin
public class TblinvdtlController {

    @Autowired
    private TblinvdtlService tblinvdtlService;


    @PostMapping
    public ResponseEntity<TblinvdtlResponse> save(@Valid @RequestBody TblinvdtlRequest request){
        TblinvdtlResponse save = tblinvdtlService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<TblinvdtlResponse> update(@Valid @RequestBody TblinvdtlUpdateRequest request){
        TblinvdtlResponse updated = tblinvdtlService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<TblinvdtlResponse> getById(@PathVariable("id") @NotBlank Long id){
        TblinvdtlResponse get = tblinvdtlService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<TblinvdtlResponse>> getAll(){
        List<TblinvdtlResponse> getall = tblinvdtlService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = tblinvdtlService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }


    @GetMapping("getAllByInvno/{fldInvno}")
    public ResponseEntity<List<TblinvdtlResponse>> getAllByInvno(@PathVariable("fldInvno") @NotBlank String fldInvno){
        List<TblinvdtlResponse> getall = tblinvdtlService.getAllByInvno(fldInvno);
        return ResponseEntity.ok(getall);
    }

    @PostMapping("getMonthlyInvDtl")
    public ResponseEntity<List<getMonthlyDtlsByAllResponse>> getMonthlyInvDtl(@Valid @RequestBody getMontlyInvRequest request){
        List<getMonthlyDtlsByAllResponse> save = tblinvdtlService.getMonthlyInvDtls(request);
        return ResponseEntity.ok(save);
    }




}