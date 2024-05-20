package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.PettyCashRequest;
import lk.quantacom.smarterpbackend.dto.request.PettyCashUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PettyCashResponse;
import lk.quantacom.smarterpbackend.dto.response.ReceivedChequesInterfaceResponse;
import lk.quantacom.smarterpbackend.service.PettyCashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("PettyCash")
@RestController
@CrossOrigin
public class PettyCashController {

    @Autowired
    private PettyCashService pettyCashService;


    @PostMapping
    public ResponseEntity<PettyCashResponse> save(@Valid @RequestBody PettyCashRequest request){
        PettyCashResponse save = pettyCashService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<PettyCashResponse> update(@Valid @RequestBody PettyCashUpdateRequest request){
        PettyCashResponse updated = pettyCashService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<PettyCashResponse> getById(@PathVariable("id") @NotBlank Long id){
        PettyCashResponse get = pettyCashService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<PettyCashResponse>> getAll(){
        List<PettyCashResponse> getall = pettyCashService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = pettyCashService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
    @GetMapping("getByPayeeName/{payeeName}")
    public ResponseEntity<List<PettyCashResponse>> findByPayeeName(@PathVariable("payeeName") @NotBlank String payeeName){
        List<PettyCashResponse> getall = pettyCashService.findByPayeeName(payeeName);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByDesctription/{desctription}")
    public ResponseEntity<List<PettyCashResponse>> findByDesctription(@PathVariable("desctription") @NotBlank String desctription){
        List<PettyCashResponse> getall = pettyCashService.findByDesctription(desctription);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByDate/{Date}/branch/{branchId}")
    public ResponseEntity<List<PettyCashResponse>> getByDateAndBranchId(@PathVariable("Date") @NotBlank String Date,
                                                                        @PathVariable("branchId") @NotBlank Integer branchId){
        List<PettyCashResponse> getall = pettyCashService.getByDateAndBranchId(Date,branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("payeeName/{payeeName}/startDate/{startDate}/endDate/{endDate}/branchId/{branchId}")
    public ResponseEntity<List<PettyCashResponse>> getByPayeeNameAndDateAndBranchId(@PathVariable("payeeName") @NotBlank String payeeName,
                                                                                                  @PathVariable("startDate") @NotBlank String startDate,
                                                                                                  @PathVariable("endDate") @NotBlank String endDate,
                                                                                                  @PathVariable("branchId") @NotBlank Integer branchId){
        List<PettyCashResponse> getall = pettyCashService.getByPayeeNameAndDateAndBranchId(payeeName,startDate,endDate,branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("startDate/{startDate}/endDate/{endDate}/branchId/{branchId}")
    public ResponseEntity<List<PettyCashResponse>> getAllByDateAndBranchId(@PathVariable("startDate") @NotBlank String startDate,
                                                                                    @PathVariable("endDate") @NotBlank String endDate,
                                                                                    @PathVariable("branchId") @NotBlank Integer branchId){
        List<PettyCashResponse> getall = pettyCashService.getAllByDateAndBranchId(startDate,endDate,branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByDateRange/startDate/{startDate}/endDate/{endDate}/branchId/{branchId}")
    public ResponseEntity<List<PettyCashResponse>> getByDateRangeAndBranchId(@PathVariable("startDate") @NotBlank String startDate,
                                                                           @PathVariable("endDate") @NotBlank String endDate,
                                                                           @PathVariable("branchId") @NotBlank Integer branchId){
        List<PettyCashResponse> getall = pettyCashService.getByDateRangeAndBranchId(startDate,endDate,branchId);
        return ResponseEntity.ok(getall);
    }
}