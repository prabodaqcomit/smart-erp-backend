package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.ProfileRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileResponse;
import lk.quantacom.smarterpbackend.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Profile")
@RestController
@CrossOrigin
public class ProfileController {

    @Autowired
    private ProfileService profileService;


    @PostMapping
    public ResponseEntity<ProfileResponse> save(@Valid @RequestBody ProfileRequest request){
        ProfileResponse save = profileService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ProfileResponse> update(@Valid @RequestBody ProfileUpdateRequest request){
        ProfileResponse updated = profileService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ProfileResponse> getById(@PathVariable("id") @NotBlank Integer id){
        ProfileResponse get = profileService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ProfileResponse>> getAll(){
        List<ProfileResponse> getall = profileService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Integer id){
        int deleted = profileService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}