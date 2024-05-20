package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.DiningTableTmpDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.DiningTableTmpDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.DiningTableTmpDetailsResponse;
import lk.quantacom.smarterpbackend.service.DiningTableTmpDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("DiningTableTmpDetails")
@RestController
@CrossOrigin
public class DiningTableTmpDetailsController {

    @Autowired
    private DiningTableTmpDetailsService diningTableTmpDetailsService;


//    @PostMapping
//    public ResponseEntity<DiningTableTmpDetailsResponse> save(@Valid @RequestBody DiningTableTmpDetailsRequest request){
//        DiningTableTmpDetailsResponse save = diningTableTmpDetailsService.save(request);
//        return ResponseEntity.ok(save);
//    }

    @PutMapping
    public ResponseEntity<DiningTableTmpDetailsResponse> update(@Valid @RequestBody DiningTableTmpDetailsUpdateRequest request){
        DiningTableTmpDetailsResponse updated = diningTableTmpDetailsService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


//    @GetMapping("{id}")
//    public ResponseEntity<DiningTableTmpDetailsResponse> getById(@PathVariable("id") @NotBlank Long id){
//        DiningTableTmpDetailsResponse get = diningTableTmpDetailsService.getById(id);
//
//        if(get==null){
//
//            return  ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(get);
//    }


//    @GetMapping()
//    public ResponseEntity<List<DiningTableTmpDetailsResponse>> getAll(){
//        List<DiningTableTmpDetailsResponse> getall = diningTableTmpDetailsService.getAll();
//        return ResponseEntity.ok(getall);
//    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = diningTableTmpDetailsService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}