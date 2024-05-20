package lk.quantacom.smarterpbackend.controller;


import lk.quantacom.smarterpbackend.dto.request.ProfileTypeInfoRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileTypeInfoUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileTypeInfoResponse;
import lk.quantacom.smarterpbackend.service.ProfileTypeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ProfileTypeInfo")
@RestController
@CrossOrigin
public class ProfileTypeInfoController {

    @Autowired
    private ProfileTypeInfoService profileTypeInfoService;


    @PostMapping
    public ResponseEntity<ProfileTypeInfoResponse> save(@Valid @RequestBody ProfileTypeInfoRequest request){
        ProfileTypeInfoResponse save = profileTypeInfoService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ProfileTypeInfoResponse> update(@Valid @RequestBody ProfileTypeInfoUpdateRequest request){
        ProfileTypeInfoResponse updated = profileTypeInfoService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ProfileTypeInfoResponse> getById(@PathVariable("id") @NotBlank Integer id){
        ProfileTypeInfoResponse get = profileTypeInfoService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ProfileTypeInfoResponse>> getAll(){
        List<ProfileTypeInfoResponse> getall = profileTypeInfoService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Integer id){
        int deleted = profileTypeInfoService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}