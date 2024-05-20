package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.BankCardTypeDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.BankCardTypeDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BankCardTypeDetailResponse;
import lk.quantacom.smarterpbackend.service.BankCardTypeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("BankCardTypeDetail")
@RestController
@CrossOrigin
public class BankCardTypeDetailController {

    @Autowired
    private BankCardTypeDetailService bankCardTypeDetailService;


    @PostMapping
    public ResponseEntity<BankCardTypeDetailResponse> save(@Valid @RequestBody BankCardTypeDetailRequest request) {
        BankCardTypeDetailResponse save = bankCardTypeDetailService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<BankCardTypeDetailResponse> update(@Valid @RequestBody BankCardTypeDetailUpdateRequest request) {
        BankCardTypeDetailResponse updated = bankCardTypeDetailService.update(request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<BankCardTypeDetailResponse> getById(@PathVariable("id") @NotBlank Long id) {
        BankCardTypeDetailResponse get = bankCardTypeDetailService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<BankCardTypeDetailResponse>> getAll() {
        List<BankCardTypeDetailResponse> getall = bankCardTypeDetailService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
        int deleted = bankCardTypeDetailService.delete(id);
        if (deleted == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}