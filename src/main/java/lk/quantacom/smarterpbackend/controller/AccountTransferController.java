package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.AccountTransferRequest;
import lk.quantacom.smarterpbackend.dto.request.AccountTransferSearchRequest;
import lk.quantacom.smarterpbackend.dto.request.AccountTransferUpdateRequest;
import lk.quantacom.smarterpbackend.dto.request.ReceiptHeaderSearchRequest;
import lk.quantacom.smarterpbackend.dto.response.AccountTransferDocumentNumberResponse;
import lk.quantacom.smarterpbackend.dto.response.AccountTransferResponse;
import lk.quantacom.smarterpbackend.dto.response.ReceiptHeaderDocumentNumberResponse;
import lk.quantacom.smarterpbackend.dto.response.ReceiptHeaderResponse;
import lk.quantacom.smarterpbackend.service.AccountTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequestMapping("AccountTransfer")
@RestController
@CrossOrigin
public class AccountTransferController {

    @Autowired
    private AccountTransferService accountTransferService;

    @GetMapping("Document/NextNumber/")
    public ResponseEntity<AccountTransferDocumentNumberResponse> getNextDocumentNumber() {
        AccountTransferDocumentNumberResponse documentNumberResponse = accountTransferService.getNextDocumentNumber();

        if (documentNumberResponse == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(documentNumberResponse);
    }

    @PostMapping
    public ResponseEntity<AccountTransferResponse> save(@Valid @RequestBody AccountTransferRequest request) {
        AccountTransferResponse save = accountTransferService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<AccountTransferResponse> update(@Valid @RequestBody AccountTransferUpdateRequest request) {
        AccountTransferResponse updated = accountTransferService.update(request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountTransferResponse> getById(@PathVariable("id") @NotBlank Long id) {
        AccountTransferResponse get = accountTransferService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<AccountTransferResponse>> getAll() {
        List<AccountTransferResponse> getall = accountTransferService.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("Paginated/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<AccountTransferResponse>> getPaginatedAll(
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage
    ) {
        Page<AccountTransferResponse> headers = accountTransferService.getPaginatedAll(pageNumber,countPerPage);
        return ResponseEntity.ok(headers);
    }

    @GetMapping("Paginated/Branch/{branchId}/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<AccountTransferResponse>> getPaginatedBranchAll(
            @PathVariable("branchId") @NotBlank Long branchId,
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage
    ) {
        Page<AccountTransferResponse> headers = accountTransferService.getPaginatedAll(branchId, pageNumber,countPerPage);
        return ResponseEntity.ok(headers);
    }

    @PostMapping("Search/Paginated/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<AccountTransferResponse>> getSearchPaginated(
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage,
            @Valid @RequestBody AccountTransferSearchRequest searchRequest
    ) {
        Page<AccountTransferResponse> headers = accountTransferService.getSearchPaginated (pageNumber,countPerPage, searchRequest);
        return ResponseEntity.ok(headers);
    }

    @PostMapping("Search/Paginated/Branch/{branchId}/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<AccountTransferResponse>> getSearchPaginatedBranch(
            @PathVariable("branchId") @NotBlank Long branchId,
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage,
            @Valid @RequestBody AccountTransferSearchRequest searchRequest
    ) {
        Page<AccountTransferResponse> headers = accountTransferService.getSearchPaginated(branchId, pageNumber,countPerPage, searchRequest);
        return ResponseEntity.ok(headers);
    }

    @PostMapping("Print/{id}")
    public ResponseEntity<ByteArrayResource> printSingle(@PathVariable("id") @NotBlank Long id) {
        File printGenerate = null;
        HttpHeaders headers = null;
        ByteArrayResource resource = null;

        try {
            printGenerate = accountTransferService.printSingle(id);
            if (printGenerate == null) {
                return ResponseEntity.notFound().build();
            }
            Path path = Paths.get(printGenerate.getPath());
            resource = new ByteArrayResource(Files.readAllBytes(path));
            headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Content-Disposition", "attachment; filename=" + printGenerate.getName());

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(printGenerate.length())
                    .contentType(MediaType
                            .parseMediaType("application/" + "pdf"))
                    .body(resource);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
//        int deleted = accountTransferService.delete(id);
//        if (deleted == 0) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(deleted);
//    }
}