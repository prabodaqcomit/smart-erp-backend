package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GeneralJournalHeaderSearchRequest;
import lk.quantacom.smarterpbackend.dto.request.ReceiptHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.ReceiptHeaderSearchRequest;
import lk.quantacom.smarterpbackend.dto.request.ReceiptHeaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalHeaderDocumentNumberResponse;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalHeaderResponse;
import lk.quantacom.smarterpbackend.dto.response.ReceiptHeaderDocumentNumberResponse;
import lk.quantacom.smarterpbackend.dto.response.ReceiptHeaderResponse;
import lk.quantacom.smarterpbackend.service.ReceiptHeaderService;
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

@RequestMapping("ReceiptHeader")
@RestController
@CrossOrigin
public class ReceiptHeaderController {

    @Autowired
    private ReceiptHeaderService receiptHeaderService;


    @PostMapping
    public ResponseEntity<ReceiptHeaderResponse> save(@Valid @RequestBody ReceiptHeaderRequest request) {
        ReceiptHeaderResponse save = receiptHeaderService.save(request);
        if (save == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<ReceiptHeaderResponse> update(@Valid @RequestBody ReceiptHeaderUpdateRequest request) {
        ReceiptHeaderResponse save = receiptHeaderService.update(request);
        if (save == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(save);
    }

    @GetMapping("Document/NextNumber/")
    public ResponseEntity<ReceiptHeaderDocumentNumberResponse> getNextDocumentNumber() {
        ReceiptHeaderDocumentNumberResponse documentNumberResponse = receiptHeaderService.getNextDocumentNumber();

        if (documentNumberResponse == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(documentNumberResponse);
    }

    @GetMapping("Document/DocumentNumbers/")
    public ResponseEntity<List<String>> getAvailableVoucherNumbers(
            @RequestParam(value = "search", required = false) String search
    ) {
        List<String> voucherNumbers = receiptHeaderService.getAvailableVoucherNumbers(search);

        if (voucherNumbers == null || voucherNumbers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(voucherNumbers);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReceiptHeaderResponse> getById(@PathVariable("id") @NotBlank Long id) {
        ReceiptHeaderResponse get = receiptHeaderService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<ReceiptHeaderResponse>> getAll() {
        List<ReceiptHeaderResponse> getall = receiptHeaderService.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("GeneralReceipt/Paginated/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<ReceiptHeaderResponse>> getPaginatedGeneralReceipts(
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage
    ) {
        Page<ReceiptHeaderResponse> headers = receiptHeaderService.getPaginatedGeneralReceipts(pageNumber, countPerPage);
        return ResponseEntity.ok(headers);
    }

    @GetMapping("GeneralReceipt/Paginated/Branch/{branchId}/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<ReceiptHeaderResponse>> getPaginatedBranchGeneralReceipt(
            @PathVariable("branchId") @NotBlank Long branchId,
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage
    ) {
        Page<ReceiptHeaderResponse> headers = receiptHeaderService.getPaginatedGeneralReceipts(branchId, pageNumber, countPerPage);
        return ResponseEntity.ok(headers);
    }

    @GetMapping("CustomerReceipt/Paginated/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<ReceiptHeaderResponse>> getPaginatedCustomerReceipts(
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage
    ) {
        Page<ReceiptHeaderResponse> headers = receiptHeaderService.getPaginatedCustomerReceipts(pageNumber, countPerPage);
        return ResponseEntity.ok(headers);
    }

    @GetMapping("CustomerReceipt/Paginated/Branch/{branchId}/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<ReceiptHeaderResponse>> getPaginatedBranchCustomerReceipt(
            @PathVariable("branchId") @NotBlank Long branchId,
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage
    ) {
        Page<ReceiptHeaderResponse> headers = receiptHeaderService.getPaginatedCustomerReceipts(branchId, pageNumber, countPerPage);
        return ResponseEntity.ok(headers);
    }

    @PostMapping("GeneralReceipt/Search/Paginated/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<ReceiptHeaderResponse>> getSearchPaginatedGeneralReceipts(
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage,
            @Valid @RequestBody ReceiptHeaderSearchRequest searchRequest
    ) {
        Page<ReceiptHeaderResponse> headers = receiptHeaderService.getSearchPaginatedGeneralReceipts(pageNumber, countPerPage, searchRequest);
        return ResponseEntity.ok(headers);
    }

    @PostMapping("GeneralReceipt/Search/Paginated/Branch/{branchId}/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<ReceiptHeaderResponse>> getSearchPaginatedBranchGeneralReceipts(
            @PathVariable("branchId") @NotBlank Long branchId,
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage,
            @Valid @RequestBody ReceiptHeaderSearchRequest searchRequest
    ) {
        Page<ReceiptHeaderResponse> headers = receiptHeaderService.getSearchPaginatedGeneralReceipts(branchId, pageNumber, countPerPage, searchRequest);
        return ResponseEntity.ok(headers);
    }

    @PostMapping("CustomerReceipt/Search/Paginated/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<ReceiptHeaderResponse>> getSearchPaginatedCustomerReceipts(
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage,
            @Valid @RequestBody ReceiptHeaderSearchRequest searchRequest
    ) {
        Page<ReceiptHeaderResponse> headers = receiptHeaderService.getSearchPaginatedCustomerReceipts(pageNumber, countPerPage, searchRequest);
        return ResponseEntity.ok(headers);
    }

    @PostMapping("CustomerReceipt/Search/Paginated/Branch/{branchId}/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<ReceiptHeaderResponse>> getSearchPaginatedBranchCustomerReceipts(
            @PathVariable("branchId") @NotBlank Long branchId,
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage,
            @Valid @RequestBody ReceiptHeaderSearchRequest searchRequest
    ) {
        Page<ReceiptHeaderResponse> headers = receiptHeaderService.getSearchPaginatedCustomerReceipts(branchId, pageNumber, countPerPage, searchRequest);
        return ResponseEntity.ok(headers);
    }

    @PostMapping("Print/{id}")
    public ResponseEntity<ByteArrayResource> printSingle(@PathVariable("id") @NotBlank Long id) {
        File printGenerate = null;
        HttpHeaders headers = null;
        ByteArrayResource resource = null;

        try {
            printGenerate = receiptHeaderService.printSingle(id);
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

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
        int deleted = receiptHeaderService.delete(id);
        if (deleted == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}