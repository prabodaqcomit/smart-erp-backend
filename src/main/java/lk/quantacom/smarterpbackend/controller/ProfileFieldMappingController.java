package lk.quantacom.smarterpbackend.controller;


import lk.quantacom.smarterpbackend.dto.request.ProfileFieldMappingRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileFieldMappingUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ActionFieldResponse;
import lk.quantacom.smarterpbackend.dto.response.ProfileFieldMappingResponse;
import lk.quantacom.smarterpbackend.service.ProfileFieldMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ProfileFieldMapping")
@RestController
@CrossOrigin
public class ProfileFieldMappingController {

    @Autowired
    private ProfileFieldMappingService profileFieldMappingService;


    @PostMapping
    public ResponseEntity<List<ProfileFieldMappingResponse>> save(@Valid @RequestBody List<ProfileFieldMappingRequest> request){
        List<ProfileFieldMappingResponse> save = profileFieldMappingService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ProfileFieldMappingResponse> update(@Valid @RequestBody ProfileFieldMappingUpdateRequest request){
        ProfileFieldMappingResponse updated = profileFieldMappingService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ProfileFieldMappingResponse> getById(@PathVariable("id") @NotBlank Long id){
        ProfileFieldMappingResponse get = profileFieldMappingService.getById(id);
        if(get==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(get);
    }

    @GetMapping("Profile/{id}")
    public ResponseEntity<List<ActionFieldResponse>> getByProfile(@PathVariable("id") @NotBlank Integer id){
        List<ActionFieldResponse> getall = profileFieldMappingService.getFields(id);
        return ResponseEntity.ok(getall);
    }

    @GetMapping()
    public ResponseEntity<List<ProfileFieldMappingResponse>> getAll(){
        List<ProfileFieldMappingResponse> getall = profileFieldMappingService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{pid}/{tid}")
    public ResponseEntity<Integer> delete(@PathVariable("pid") @NotBlank Integer pid, @PathVariable("pid") @NotBlank Long tid){
        int deleted = profileFieldMappingService.delete(pid,tid);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}