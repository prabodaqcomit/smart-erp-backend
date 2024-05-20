package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.TblporheaderRequestApprovalRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporothercostsRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporothercostsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblporfitsResponse;
import lk.quantacom.smarterpbackend.dto.response.TblporheaderResponse;
import lk.quantacom.smarterpbackend.dto.response.TblporothercostsResponse;
import lk.quantacom.smarterpbackend.service.TblporothercostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Tblporothercosts")
@RestController
@CrossOrigin
public class TblporothercostsController {

    @Autowired
    private TblporothercostsService tblporothercostsService;


    @PostMapping
    public ResponseEntity<TblporothercostsResponse> save(@Valid @RequestBody TblporothercostsRequest request){
        TblporothercostsResponse save = tblporothercostsService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<TblporothercostsResponse> update(@Valid @RequestBody TblporothercostsUpdateRequest request){
        TblporothercostsResponse updated = tblporothercostsService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<TblporothercostsResponse> getById(@PathVariable("id") @NotBlank Integer id){
        TblporothercostsResponse get = tblporothercostsService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<TblporothercostsResponse>> getAll(){
        List<TblporothercostsResponse> getall = tblporothercostsService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Integer id){
        int deleted = tblporothercostsService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @PostMapping("requestApprovedSave")
    public ResponseEntity<TblporheaderResponse> requestApproval(@Valid @RequestBody TblporheaderRequestApprovalRequest requestApprovalRequest){
        TblporheaderResponse save = tblporothercostsService.requestApprovalForApproval(requestApprovalRequest);
        return ResponseEntity.ok(save);
    }

    @GetMapping("porId/{porId}")
    public ResponseEntity<List<TblporothercostsResponse>> getByPorId(@PathVariable("porId") @NotBlank String porId){
        List<TblporothercostsResponse> getall = tblporothercostsService.getByPorId(porId);
        return ResponseEntity.ok(getall);
    }
}