package lk.quantacom.smarterpbackend.controller;


import lk.quantacom.smarterpbackend.dto.request.ProfileValuesRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileValuesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileValuesResponse;
import lk.quantacom.smarterpbackend.service.ProfileValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ProfileValues")
@RestController
@CrossOrigin
public class ProfileValuesController {

    @Autowired
    private ProfileValuesService profileValuesService;


    @PostMapping
    public ResponseEntity<ProfileValuesResponse> save(@Valid @RequestBody ProfileValuesRequest request){
        ProfileValuesResponse save = profileValuesService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ProfileValuesResponse> update(@Valid @RequestBody ProfileValuesUpdateRequest request){
        ProfileValuesResponse updated = profileValuesService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ProfileValuesResponse> getById(@PathVariable("id") @NotBlank Integer id){
        ProfileValuesResponse get = profileValuesService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ProfileValuesResponse>> getAll(){
        List<ProfileValuesResponse> getall = profileValuesService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Integer id){
        int deleted = profileValuesService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}