package lk.quantacom.smarterpbackend.controller;


import lk.quantacom.smarterpbackend.dto.request.ProfileTypesRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileTypesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileTypesResponse;
import lk.quantacom.smarterpbackend.service.ProfileTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ProfileTypes")
@RestController
@CrossOrigin
public class ProfileTypesController {

    @Autowired
    private ProfileTypesService profileTypesService;


    @PostMapping
    public ResponseEntity<ProfileTypesResponse> save(@Valid @RequestBody ProfileTypesRequest request){
        ProfileTypesResponse save = profileTypesService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ProfileTypesResponse> update(@Valid @RequestBody ProfileTypesUpdateRequest request){
        ProfileTypesResponse updated = profileTypesService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ProfileTypesResponse> getById(@PathVariable("id") @NotBlank Integer id){
        ProfileTypesResponse get = profileTypesService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ProfileTypesResponse>> getAll(){
        List<ProfileTypesResponse> getall = profileTypesService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Integer id){
        int deleted = profileTypesService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}