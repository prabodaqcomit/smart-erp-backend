package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.ProfileFormsRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileFormsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileFormsResponse;
import lk.quantacom.smarterpbackend.service.ProfileFormsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ProfileForms")
@RestController
@CrossOrigin
public class ProfileFormsController {

    @Autowired
    private ProfileFormsService profileFormsService;


    @PostMapping
    public ResponseEntity<ProfileFormsResponse> save(@Valid @RequestBody ProfileFormsRequest request){
        ProfileFormsResponse save = profileFormsService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ProfileFormsResponse> update(@Valid @RequestBody ProfileFormsUpdateRequest request){
        ProfileFormsResponse updated = profileFormsService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ProfileFormsResponse> getById(@PathVariable("id") @NotBlank Integer id){
        ProfileFormsResponse get = profileFormsService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ProfileFormsResponse>> getAll(){
        List<ProfileFormsResponse> getall = profileFormsService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Integer id){
        int deleted = profileFormsService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}