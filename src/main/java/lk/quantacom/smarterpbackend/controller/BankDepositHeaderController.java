package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.BankDepositHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.BankDepositHeaderSearchRequest;
import lk.quantacom.smarterpbackend.dto.request.BankDepositHeaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalHeaderSearchRequest;
import lk.quantacom.smarterpbackend.dto.response.BankDepositHeaderDocumentNumberResponse;
import lk.quantacom.smarterpbackend.dto.response.BankDepositHeaderResponse;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalHeaderDocumentNumberResponse;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalHeaderResponse;
import lk.quantacom.smarterpbackend.service.BankDepositHeaderService;
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

@RequestMapping("BankDepositHeader")
@RestController
@CrossOrigin
public class BankDepositHeaderController {

    @Autowired
    private BankDepositHeaderService bankDepositHeaderService;

    @GetMapping("Document/NextNumber/")
    public ResponseEntity<BankDepositHeaderDocumentNumberResponse> getNextDocumentNumber() {
        BankDepositHeaderDocumentNumberResponse documentNumberResponse = bankDepositHeaderService.getNextDocumentNumber();

        if (documentNumberResponse == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(documentNumberResponse);
    }

    @PostMapping
    public ResponseEntity<BankDepositHeaderResponse> save(@Valid @RequestBody BankDepositHeaderRequest request) {
        BankDepositHeaderResponse save = bankDepositHeaderService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<BankDepositHeaderResponse> update(@Valid @RequestBody BankDepositHeaderUpdateRequest request) {
        BankDepositHeaderResponse updated = bankDepositHeaderService.update(request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping("{id}")
    public ResponseEntity<BankDepositHeaderResponse> getById(@PathVariable("id") @NotBlank Long id) {
        BankDepositHeaderResponse get = bankDepositHeaderService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }

    @GetMapping("Paginated/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<BankDepositHeaderResponse>> getPaginatedAll(
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage
    ) {
        Page<BankDepositHeaderResponse> headers = bankDepositHeaderService.getPaginatedAll(pageNumber,countPerPage);
        return ResponseEntity.ok(headers);
    }

    @GetMapping("Paginated/Branch/{branchId}/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<BankDepositHeaderResponse>> getPaginatedAll(
            @PathVariable("branchId") @NotBlank Long branchId,
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage
    ) {
        Page<BankDepositHeaderResponse> headers = bankDepositHeaderService.getPaginatedAll(branchId, pageNumber, countPerPage);
        return ResponseEntity.ok(headers);
    }

    @PostMapping("Search/Paginated/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<BankDepositHeaderResponse>> getSearchPaginated(
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage,
            @Valid @RequestBody BankDepositHeaderSearchRequest searchRequest
    ) {
        Page<BankDepositHeaderResponse> headers = bankDepositHeaderService.getSearchPaginated(pageNumber, countPerPage, searchRequest);
        return ResponseEntity.ok(headers);
    }

    @PostMapping("Search/Paginated/Branch/{branchId}/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<BankDepositHeaderResponse>> getSearchPaginated(
            @PathVariable("branchId") @NotBlank Long branchId,
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage,
            @Valid @RequestBody BankDepositHeaderSearchRequest searchRequest
    ) {
        Page<BankDepositHeaderResponse> headers = bankDepositHeaderService.getSearchPaginated(branchId, pageNumber, countPerPage, searchRequest);
        return ResponseEntity.ok(headers);
    }

    @GetMapping()
    public ResponseEntity<List<BankDepositHeaderResponse>> getAll() {
        List<BankDepositHeaderResponse> getall = bankDepositHeaderService.getAll();
        return ResponseEntity.ok(getall);
    }

    @PostMapping("Print/{id}")
    public ResponseEntity<ByteArrayResource> printSingle(@PathVariable("id") @NotBlank Long id){
        File printGenerate = null;
        HttpHeaders headers = null;
        ByteArrayResource resource = null;

        try {
            printGenerate = bankDepositHeaderService.printSingle(id);
            if(printGenerate==null){
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

        }catch (Exception ex){
            ex.printStackTrace();
            return  ResponseEntity.internalServerError().build();
        }
    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
//        int deleted = bankDepositHeaderService.delete(id);
//        if (deleted == 0) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(deleted);
//    }
}