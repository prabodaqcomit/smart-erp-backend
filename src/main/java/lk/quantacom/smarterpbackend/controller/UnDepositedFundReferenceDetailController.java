package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.UnDepositedFundReferenceDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.UnDepositedFundReferenceDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.UnDepositedFundReferenceDetailResponse;
import lk.quantacom.smarterpbackend.service.UnDepositedFundReferenceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("UnDepositedFundReferenceDetail")
@RestController
@CrossOrigin
public class UnDepositedFundReferenceDetailController {

    @Autowired
    private UnDepositedFundReferenceDetailService unDepositedFundReferenceDetailService;


//    @PostMapping
//    public ResponseEntity<UnDepositedFundReferenceDetailResponse> save(@Valid @RequestBody UnDepositedFundReferenceDetailRequest request) {
//        UnDepositedFundReferenceDetailResponse save = unDepositedFundReferenceDetailService.save(request);
//        return ResponseEntity.ok(save);
//    }
//
//    @PutMapping
//    public ResponseEntity<UnDepositedFundReferenceDetailResponse> update(@Valid @RequestBody UnDepositedFundReferenceDetailUpdateRequest request) {
//        UnDepositedFundReferenceDetailResponse updated = unDepositedFundReferenceDetailService.update(request);
//        if (updated == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updated);
//    }


    @GetMapping("{id}")
    public ResponseEntity<UnDepositedFundReferenceDetailResponse> getById(@PathVariable("id") @NotBlank Long id) {
        UnDepositedFundReferenceDetailResponse get = unDepositedFundReferenceDetailService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<UnDepositedFundReferenceDetailResponse>> getAll() {
        List<UnDepositedFundReferenceDetailResponse> getall = unDepositedFundReferenceDetailService.getAll();
        return ResponseEntity.ok(getall);
    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
//        int deleted = unDepositedFundReferenceDetailService.delete(id);
//        if (deleted == 0) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(deleted);
//    }
}