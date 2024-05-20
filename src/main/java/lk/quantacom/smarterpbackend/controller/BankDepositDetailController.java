package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.BankDepositDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.BankDepositDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BankDepositDetailResponse;
import lk.quantacom.smarterpbackend.service.BankDepositDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("BankDepositDetail")
@RestController
@CrossOrigin
public class BankDepositDetailController {

    @Autowired
    private BankDepositDetailService bankDepositDetailService;


//    @PostMapping
//    public ResponseEntity<BankDepositDetailResponse> save(@Valid @RequestBody BankDepositDetailRequest request) {
//        BankDepositDetailResponse save = bankDepositDetailService.save(request);
//        return ResponseEntity.ok(save);
//    }
//
//    @PutMapping
//    public ResponseEntity<BankDepositDetailResponse> update(@Valid @RequestBody BankDepositDetailUpdateRequest request) {
//        BankDepositDetailResponse updated = bankDepositDetailService.update(request);
//        if (updated == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updated);
//    }


    @GetMapping("{id}")
    public ResponseEntity<BankDepositDetailResponse> getById(@PathVariable("id") @NotBlank Long id) {
        BankDepositDetailResponse get = bankDepositDetailService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<BankDepositDetailResponse>> getAll() {
        List<BankDepositDetailResponse> getall = bankDepositDetailService.getAll();
        return ResponseEntity.ok(getall);
    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
//        int deleted = bankDepositDetailService.delete(id);
//        if (deleted == 0) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(deleted);
//    }
}