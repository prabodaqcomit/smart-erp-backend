package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.BankCardTypeRequest;
import lk.quantacom.smarterpbackend.dto.request.BankCardTypeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BankCardTypeResponse;
import lk.quantacom.smarterpbackend.service.BankCardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("BankCardType")
@RestController
@CrossOrigin
public class BankCardTypeController {

    @Autowired
    private BankCardTypeService bankCardTypeService;


    @PostMapping
    public ResponseEntity<BankCardTypeResponse> save(@Valid @RequestBody BankCardTypeRequest request) {
        BankCardTypeResponse save = bankCardTypeService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<BankCardTypeResponse> update(@Valid @RequestBody BankCardTypeUpdateRequest request) {
        BankCardTypeResponse updated = bankCardTypeService.update(request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<BankCardTypeResponse> getById(@PathVariable("id") @NotBlank Long id) {
        BankCardTypeResponse get = bankCardTypeService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<BankCardTypeResponse>> getAll() {
        List<BankCardTypeResponse> getall = bankCardTypeService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
        int deleted = bankCardTypeService.delete(id);
        if (deleted == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}