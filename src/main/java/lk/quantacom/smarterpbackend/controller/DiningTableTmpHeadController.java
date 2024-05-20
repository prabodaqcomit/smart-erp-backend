package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.DiningTableTmpHeadRequest;
import lk.quantacom.smarterpbackend.dto.request.DiningTableTmpHeadUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.DiningTableTmpHeadResponse;
import lk.quantacom.smarterpbackend.service.DiningTableTmpHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("DiningTableTmpHead")
@RestController
@CrossOrigin
public class DiningTableTmpHeadController {

    @Autowired
    private DiningTableTmpHeadService diningTableTmpHeadService;


    @PostMapping
    public ResponseEntity<DiningTableTmpHeadResponse> save(@Valid @RequestBody DiningTableTmpHeadRequest request){
        DiningTableTmpHeadResponse save = diningTableTmpHeadService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<DiningTableTmpHeadResponse> update(@Valid @RequestBody DiningTableTmpHeadUpdateRequest request){
        DiningTableTmpHeadResponse updated = diningTableTmpHeadService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @PutMapping("CloseBill/{headId}")
    public ResponseEntity<DiningTableTmpHeadResponse> closeBill(@PathVariable("headId") @NotBlank Long headId){
        DiningTableTmpHeadResponse updated = diningTableTmpHeadService.closeBill(headId);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("ByTableId/{tid}")
    public ResponseEntity<DiningTableTmpHeadResponse> getByTableId(@PathVariable("tid") @NotBlank Long tid){
        DiningTableTmpHeadResponse get = diningTableTmpHeadService.getByTableId(tid);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<DiningTableTmpHeadResponse>> getAll(){
        List<DiningTableTmpHeadResponse> getall = diningTableTmpHeadService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = diningTableTmpHeadService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}