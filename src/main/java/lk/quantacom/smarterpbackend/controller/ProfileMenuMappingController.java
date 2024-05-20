package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.ProfileMenuMappingRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileMenuMappingUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileMenuMappingResponse;
import lk.quantacom.smarterpbackend.service.ProfileMenuMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ProfileMenuMapping")
@RestController
@CrossOrigin
public class ProfileMenuMappingController {

    @Autowired
    private ProfileMenuMappingService profileMenuMappingService;


    @PostMapping
    public ResponseEntity<List<ProfileMenuMappingResponse>> save(@Valid @RequestBody List<ProfileMenuMappingRequest> request) {
        List<ProfileMenuMappingResponse> save = profileMenuMappingService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ProfileMenuMappingResponse> update(@Valid @RequestBody ProfileMenuMappingUpdateRequest request) {
        ProfileMenuMappingResponse updated = profileMenuMappingService.update(request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ProfileMenuMappingResponse> getById(@PathVariable("id") @NotBlank Long id) {
        ProfileMenuMappingResponse get = profileMenuMappingService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ProfileMenuMappingResponse>> getAll() {
        List<ProfileMenuMappingResponse> getall = profileMenuMappingService.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("/Profile/{id}")
    public ResponseEntity<List<ProfileMenuMappingResponse>> getAllByProfile(@PathVariable("id") @NotBlank Integer id) {
        List<ProfileMenuMappingResponse> getall = profileMenuMappingService.getByProfile(id);
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
        int deleted = profileMenuMappingService.delete(id);
        if (deleted == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}