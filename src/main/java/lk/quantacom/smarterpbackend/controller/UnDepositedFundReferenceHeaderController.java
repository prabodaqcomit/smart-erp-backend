package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.UnDepositedFundReferenceHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.UnDepositedFundReferenceHeaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.UnDepositedFundReferenceHeaderResponse;
import lk.quantacom.smarterpbackend.service.UnDepositedFundReferenceHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("UnDepositedFundReferenceHeader")
@RestController
@CrossOrigin
public class UnDepositedFundReferenceHeaderController {

    @Autowired
    private UnDepositedFundReferenceHeaderService unDepositedFundReferenceHeaderService;


//    @PostMapping
//    public ResponseEntity<UnDepositedFundReferenceHeaderResponse> save(@Valid @RequestBody UnDepositedFundReferenceHeaderRequest request) {
//        UnDepositedFundReferenceHeaderResponse save = unDepositedFundReferenceHeaderService.save(request);
//        return ResponseEntity.ok(save);
//    }
//
//    @PutMapping
//    public ResponseEntity<UnDepositedFundReferenceHeaderResponse> update(@Valid @RequestBody UnDepositedFundReferenceHeaderUpdateRequest request) {
//        UnDepositedFundReferenceHeaderResponse updated = unDepositedFundReferenceHeaderService.update(request);
//        if (updated == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updated);
//    }


    @GetMapping("{id}")
    public ResponseEntity<UnDepositedFundReferenceHeaderResponse> getById(@PathVariable("id") @NotBlank Long id) {
        UnDepositedFundReferenceHeaderResponse get = unDepositedFundReferenceHeaderService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


//    @GetMapping()
//    public ResponseEntity<List<UnDepositedFundReferenceHeaderResponse>> getAll() {
//        List<UnDepositedFundReferenceHeaderResponse> getall = unDepositedFundReferenceHeaderService.getAll();
//        return ResponseEntity.ok(getall);
//    }

    @GetMapping("Pending/")
    public ResponseEntity<List<UnDepositedFundReferenceHeaderResponse>> getPending() {
        List<UnDepositedFundReferenceHeaderResponse> headers = unDepositedFundReferenceHeaderService.getPendingFunds();
        return ResponseEntity.ok(headers);
    }

    @GetMapping("Pending/Branch/{branchId}")
    public ResponseEntity<List<UnDepositedFundReferenceHeaderResponse>> getPending(@PathVariable("branchId") @NotBlank Long branchId) {
        List<UnDepositedFundReferenceHeaderResponse> headers = unDepositedFundReferenceHeaderService.getPendingFundsByBranch(branchId);
        return ResponseEntity.ok(headers);
    }

    @GetMapping("Pending/Paged/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<UnDepositedFundReferenceHeaderResponse>> getPagedPending(
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage
    ) {
        Page<UnDepositedFundReferenceHeaderResponse> headers = unDepositedFundReferenceHeaderService.getPagedPendingFunds(pageNumber, countPerPage);
        return ResponseEntity.ok(headers);
    }

    @GetMapping("Pending/Paged/Branch/{pageNumber}/{countPerPage}/{branchId}")
    public ResponseEntity<Page<UnDepositedFundReferenceHeaderResponse>> getPagedPending(
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage,
            @PathVariable("branchId") @NotBlank Long branchId
    ) {
        Page<UnDepositedFundReferenceHeaderResponse> headers = unDepositedFundReferenceHeaderService.getPagedPendingFundsByBranch(pageNumber, countPerPage, branchId);
        return ResponseEntity.ok(headers);
    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
//        int deleted = unDepositedFundReferenceHeaderService.delete(id);
//        if (deleted == 0) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(deleted);
//    }
}