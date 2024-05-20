package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GeneralJournalHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalHeaderSearchRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalHeaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalHeaderDocumentNumberResponse;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalHeaderResponse;
import lk.quantacom.smarterpbackend.service.GeneralJournalHeaderService;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.poi.util.NotImplemented;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequestMapping("GeneralJournalHeader")
@RestController
@CrossOrigin
public class GeneralJournalHeaderController {

    @Autowired
    private GeneralJournalHeaderService generalJournalHeaderService;


    @PostMapping
    public ResponseEntity<GeneralJournalHeaderResponse> save(@Valid @RequestBody GeneralJournalHeaderRequest request) {

        GeneralJournalHeaderResponse save = generalJournalHeaderService.save(request);
        return ResponseEntity.ok(save);
    }

//    @PutMapping
//    public ResponseEntity<GeneralJournalHeaderResponse> update(@Valid @RequestBody GeneralJournalHeaderUpdateRequest request) {
//        GeneralJournalHeaderResponse updated = generalJournalHeaderService.update(request);
//        if (updated == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updated);
//    }

    @GetMapping("Document/NextNumber/")
    public ResponseEntity<GeneralJournalHeaderDocumentNumberResponse> getNextDocumentNumber() {
        GeneralJournalHeaderDocumentNumberResponse documentNumberResponse = generalJournalHeaderService.getNextDocumentNumber();

        if (documentNumberResponse == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(documentNumberResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<GeneralJournalHeaderResponse> getById(@PathVariable("id") @NotBlank Long id) {
        GeneralJournalHeaderResponse get = generalJournalHeaderService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }

    @GetMapping()
    public ResponseEntity<List<GeneralJournalHeaderResponse>> getAll() {
        List<GeneralJournalHeaderResponse> getAll = generalJournalHeaderService.getAll();
        return ResponseEntity.ok(getAll);
    }

    @GetMapping("Paginated/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<GeneralJournalHeaderResponse>> getPaginatedAll(
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage
    ) {
        Page<GeneralJournalHeaderResponse> headers = generalJournalHeaderService.getPaginatedAll(pageNumber,countPerPage);
        return ResponseEntity.ok(headers);
    }

    @GetMapping("Paginated/Branch/{branchId}/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<GeneralJournalHeaderResponse>> getPaginatedAll(
            @PathVariable("branchId") @NotBlank Long branchId,
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage
    ) {
        Page<GeneralJournalHeaderResponse> headers = generalJournalHeaderService.getPaginatedAll(branchId, pageNumber, countPerPage);
        return ResponseEntity.ok(headers);
    }

    @PostMapping("Search/Paginated/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<GeneralJournalHeaderResponse>> getSearchPaginated(
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage,
            @Valid @RequestBody GeneralJournalHeaderSearchRequest searchRequest
    ) {
        Page<GeneralJournalHeaderResponse> headers = generalJournalHeaderService.getSearchPaginated(pageNumber, countPerPage, searchRequest);
        return ResponseEntity.ok(headers);
    }

    @PostMapping("Search/Paginated/Branch/{branchId}/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<GeneralJournalHeaderResponse>> getSearchPaginated(
            @PathVariable("branchId") @NotBlank Long branchId,
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage,
            @Valid @RequestBody GeneralJournalHeaderSearchRequest searchRequest
    ) {
        Page<GeneralJournalHeaderResponse> headers = generalJournalHeaderService.getSearchPaginated(branchId, pageNumber, countPerPage, searchRequest);
        return ResponseEntity.ok(headers);
    }

    @PostMapping("Print/{id}")
    public ResponseEntity<ByteArrayResource> printSingle(@PathVariable("id") @NotBlank Long id){
        File printGenerate = null;
        HttpHeaders headers = null;
        ByteArrayResource resource = null;

        try {
            printGenerate = generalJournalHeaderService.printSingle(id);
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
//        int deleted = generalJournalHeaderService.delete(id);
//        if (deleted == 0) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(deleted);
//    }

    @DeleteMapping("Reverse/{id}")
    public ResponseEntity<Integer> reverseGeneralJournal(@PathVariable("id") @NotBlank Long id) {
        int deleted = generalJournalHeaderService.delete(id);
        //TODOO
        if (deleted == 0) {
            return ResponseEntity.notFound().build();
        }

        //Already reversed
        if (deleted == 3) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(deleted);
    }
}