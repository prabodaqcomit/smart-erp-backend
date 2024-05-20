package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.ProfileFieldsRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileFieldsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileFieldsResponse;
import lk.quantacom.smarterpbackend.service.ProfileFieldsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ProfileFields")
@RestController
@CrossOrigin
public class ProfileFieldsController {

    @Autowired
    private ProfileFieldsService profileFieldsService;


    @PostMapping
    public ResponseEntity<ProfileFieldsResponse> save(@Valid @RequestBody ProfileFieldsRequest request){
        ProfileFieldsResponse save = profileFieldsService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ProfileFieldsResponse> update(@Valid @RequestBody ProfileFieldsUpdateRequest request){
        ProfileFieldsResponse updated = profileFieldsService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ProfileFieldsResponse> getById(@PathVariable("id") @NotBlank String id){
        ProfileFieldsResponse get = profileFieldsService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ProfileFieldsResponse>> getAll(){
        List<ProfileFieldsResponse> getall = profileFieldsService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank String id){
        int deleted = profileFieldsService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}