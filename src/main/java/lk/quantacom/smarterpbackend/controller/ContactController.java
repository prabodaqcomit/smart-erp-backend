package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.ContactRequest;
import lk.quantacom.smarterpbackend.dto.request.ContactUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ContactResponse;
import lk.quantacom.smarterpbackend.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Contact")
@RestController
@CrossOrigin
public class ContactController {

    @Autowired
    private ContactService contactService;


    @PostMapping
    public ResponseEntity<ContactResponse> save(@Valid @RequestBody ContactRequest request) {
        ContactResponse save = contactService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ContactResponse> update(@Valid @RequestBody ContactUpdateRequest request) {
        ContactResponse updated = contactService.update(request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<ContactResponse> getById(@PathVariable("id") @NotBlank Long id) {
        ContactResponse get = contactService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ContactResponse>> getAll() {
        List<ContactResponse> getall = contactService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
        int deleted = contactService.delete(id);
        if (deleted == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}