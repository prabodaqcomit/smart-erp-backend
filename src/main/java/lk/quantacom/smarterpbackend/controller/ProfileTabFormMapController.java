package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.ProfileTabFormMapRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileTabFormMapUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileTabFormMapResponse;
import lk.quantacom.smarterpbackend.dto.response.TabFormResponse;
import lk.quantacom.smarterpbackend.service.ProfileTabFormMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ProfileTabFormMap")
@RestController
@CrossOrigin
public class ProfileTabFormMapController {

    @Autowired
    private ProfileTabFormMapService profileTabFormMapService;


    @PostMapping
    public ResponseEntity<List<ProfileTabFormMapResponse>> save(@Valid @RequestBody List<ProfileTabFormMapRequest> request) {
        List<ProfileTabFormMapResponse> save = profileTabFormMapService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ProfileTabFormMapResponse> update(@Valid @RequestBody ProfileTabFormMapUpdateRequest request) {
        ProfileTabFormMapResponse updated = profileTabFormMapService.update(request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ProfileTabFormMapResponse> getById(@PathVariable("id") @NotBlank Long id) {
        ProfileTabFormMapResponse get = profileTabFormMapService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ProfileTabFormMapResponse>> getAll() {
        List<ProfileTabFormMapResponse> getall = profileTabFormMapService.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("Profile/{id}")
    public ResponseEntity<List<TabFormResponse>> getByProfile(@PathVariable("id") @NotBlank Integer id) {
        List<TabFormResponse> getall = profileTabFormMapService.getByProfile(id);
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{profileId}/{formId}")
    public ResponseEntity<Integer> delete(@PathVariable("profileId") @NotBlank Integer profileId,
                                          @PathVariable("formId") @NotBlank Long formId) {
        int deleted = profileTabFormMapService.delete(profileId,formId);
        if (deleted == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}