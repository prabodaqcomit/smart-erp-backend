package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.BankRequest;
import lk.quantacom.smarterpbackend.dto.request.BankUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BankResponse;
import lk.quantacom.smarterpbackend.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Bank")
@RestController
@CrossOrigin
public class BankController {

    @Autowired
    private BankService bankService;


    @PostMapping
    public ResponseEntity<BankResponse> save(@Valid @RequestBody BankRequest request) {
        BankResponse save = bankService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<BankResponse> update(@Valid @RequestBody BankUpdateRequest request) {
        BankResponse updated = bankService.update(request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<BankResponse> getById(@PathVariable("id") @NotBlank Long id) {
        BankResponse get = bankService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<BankResponse>> getAll() {
        List<BankResponse> getall = bankService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
        int deleted = bankService.delete(id);
        if (deleted == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}