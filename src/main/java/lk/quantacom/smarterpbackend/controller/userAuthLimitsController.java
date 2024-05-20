package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.userAuthLimitsRequest;
import lk.quantacom.smarterpbackend.dto.request.userAuthLimitsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.userAuthLimitsResponse;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.service.userAuthLimitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("userAuthLimits")
@RestController
@CrossOrigin
public class userAuthLimitsController {

    @Autowired
    private userAuthLimitsService userAuthLimitsService;


    @PostMapping
    public ResponseEntity<userAuthLimitsResponse> save(@Valid @RequestBody userAuthLimitsRequest request){
        userAuthLimitsResponse save = userAuthLimitsService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<userAuthLimitsResponse> update(@Valid @RequestBody userAuthLimitsUpdateRequest request){
        userAuthLimitsResponse updated = userAuthLimitsService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<userAuthLimitsResponse> getById(@PathVariable("id") @NotBlank Long id){
        userAuthLimitsResponse get = userAuthLimitsService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<userAuthLimitsResponse>> getAll(){
        List<userAuthLimitsResponse> getall = userAuthLimitsService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = userAuthLimitsService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("getByUsername/{username}")
    public ResponseEntity<List<userAuthLimitsResponse>> getByUsername(@PathVariable("username") @NotBlank String username){
        List<userAuthLimitsResponse> getall = userAuthLimitsService.getByUsername(username);
        return ResponseEntity.ok(getall);
    }

}