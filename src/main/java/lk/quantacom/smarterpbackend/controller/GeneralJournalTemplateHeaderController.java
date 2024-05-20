package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GeneralJournalHeaderSearchRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalTemplateHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalTemplateHeaderSearchRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalTemplateHeaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalHeaderResponse;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalTemplateHeaderResponse;
import lk.quantacom.smarterpbackend.service.GeneralJournalTemplateHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("GeneralJournalTemplateHeader")
@RestController
@CrossOrigin
public class GeneralJournalTemplateHeaderController {

    @Autowired
    private GeneralJournalTemplateHeaderService generalJournalTemplateHeaderService;


    @PostMapping
    public ResponseEntity<GeneralJournalTemplateHeaderResponse> save(@Valid @RequestBody GeneralJournalTemplateHeaderRequest request) {
        GeneralJournalTemplateHeaderResponse save = generalJournalTemplateHeaderService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<GeneralJournalTemplateHeaderResponse> update(@Valid @RequestBody GeneralJournalTemplateHeaderUpdateRequest request) {
        GeneralJournalTemplateHeaderResponse updated = generalJournalTemplateHeaderService.update(request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<GeneralJournalTemplateHeaderResponse> getById(@PathVariable("id") @NotBlank Long id) {
        GeneralJournalTemplateHeaderResponse get = generalJournalTemplateHeaderService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<GeneralJournalTemplateHeaderResponse>> getAll() {
        List<GeneralJournalTemplateHeaderResponse> getall = generalJournalTemplateHeaderService.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("Paginated/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<GeneralJournalTemplateHeaderResponse>> getPaginatedAll(
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage
    ) {
        Page<GeneralJournalTemplateHeaderResponse> headers = generalJournalTemplateHeaderService.getPaginatedAll(pageNumber,countPerPage);
        return ResponseEntity.ok(headers);
    }

    @GetMapping("Paginated/Branch/{branchId}/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<GeneralJournalTemplateHeaderResponse>> getPaginatedAll(
            @PathVariable("branchId") @NotBlank Long branchId,
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage
    ) {
        Page<GeneralJournalTemplateHeaderResponse> headers = generalJournalTemplateHeaderService.getPaginatedAll(branchId, pageNumber, countPerPage);
        return ResponseEntity.ok(headers);
    }

    @PostMapping("Search/Paginated/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<GeneralJournalTemplateHeaderResponse>> getSearchPaginated(
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage,
            @Valid @RequestBody GeneralJournalTemplateHeaderSearchRequest searchRequest
    ) {
        Page<GeneralJournalTemplateHeaderResponse> headers = generalJournalTemplateHeaderService.getSearchPaginated(pageNumber, countPerPage, searchRequest);
        return ResponseEntity.ok(headers);
    }

    @PostMapping("Search/Paginated/Branch/{branchId}/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<GeneralJournalTemplateHeaderResponse>> getSearchPaginated(
            @PathVariable("branchId") @NotBlank Long branchId,
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage,
            @Valid @RequestBody GeneralJournalTemplateHeaderSearchRequest searchRequest
    ) {
        Page<GeneralJournalTemplateHeaderResponse> headers = generalJournalTemplateHeaderService.getSearchPaginated(branchId, pageNumber, countPerPage, searchRequest);
        return ResponseEntity.ok(headers);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
        int deleted = generalJournalTemplateHeaderService.delete(id);
        if (deleted == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}