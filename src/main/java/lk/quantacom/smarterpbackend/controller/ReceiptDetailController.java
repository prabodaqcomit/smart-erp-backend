package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.ReceiptDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.ReceiptDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ReceiptDetailResponse;
import lk.quantacom.smarterpbackend.service.ReceiptDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("ReceiptDetail")
@RestController
@CrossOrigin
public class ReceiptDetailController {

    @Autowired
    private ReceiptDetailService receiptDetailService;


//    @PostMapping
//    public ResponseEntity<ReceiptDetailResponse> save(@Valid @RequestBody ReceiptDetailRequest request) {
//        ReceiptDetailResponse save = receiptDetailService.save(request);
//        return ResponseEntity.ok(save);
//    }

//    @PutMapping
//    public ResponseEntity<ReceiptDetailResponse> update(@Valid @RequestBody ReceiptDetailUpdateRequest request) {
//        ReceiptDetailResponse updated = receiptDetailService.update(request);
//        if (updated == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updated);
//    }


    @GetMapping("{id}")
    public ResponseEntity<ReceiptDetailResponse> getById(@PathVariable("id") @NotBlank Long id) {
        ReceiptDetailResponse get = receiptDetailService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ReceiptDetailResponse>> getAll() {
        List<ReceiptDetailResponse> getall = receiptDetailService.getAll();
        return ResponseEntity.ok(getall);
    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
//        int deleted = receiptDetailService.delete(id);
//        if (deleted == 0) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(deleted);
//    }
}