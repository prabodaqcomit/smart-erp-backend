package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GeneralJournalHeaderSearchRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalRecurringRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalRecurringSearchRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalRecurringUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalHeaderResponse;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalRecurringResponse;
import lk.quantacom.smarterpbackend.service.GeneralJournalRecurringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("GeneralJournalRecurring")
@RestController
@CrossOrigin
public class GeneralJournalRecurringController {

    @Autowired
    private GeneralJournalRecurringService generalJournalRecurringService;


    @PostMapping
    public ResponseEntity<GeneralJournalRecurringResponse> save(@Valid @RequestBody GeneralJournalRecurringRequest request) {
        GeneralJournalRecurringResponse save = generalJournalRecurringService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<GeneralJournalRecurringResponse> update(@Valid @RequestBody GeneralJournalRecurringUpdateRequest request) {
        GeneralJournalRecurringResponse updated = generalJournalRecurringService.update(request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<GeneralJournalRecurringResponse> getById(@PathVariable("id") @NotBlank Long id) {
        GeneralJournalRecurringResponse get = generalJournalRecurringService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<GeneralJournalRecurringResponse>> getAll() {
        List<GeneralJournalRecurringResponse> getall = generalJournalRecurringService.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("Paginated/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<GeneralJournalRecurringResponse>> getPaginatedAll(
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage
    ) {
        Page<GeneralJournalRecurringResponse> headers = generalJournalRecurringService.getPaginatedAll(pageNumber,countPerPage);
        return ResponseEntity.ok(headers);
    }

    @GetMapping("Paginated/Branch/{branchId}/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<GeneralJournalRecurringResponse>> getPaginatedAll(
            @PathVariable("branchId") @NotBlank Long branchId,
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage
    ) {
        Page<GeneralJournalRecurringResponse> headers = generalJournalRecurringService.getPaginatedAll(branchId, pageNumber, countPerPage);
        return ResponseEntity.ok(headers);
    }

    @PostMapping("Search/Paginated/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<GeneralJournalRecurringResponse>> getSearchPaginated(
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage,
            @Valid @RequestBody GeneralJournalRecurringSearchRequest searchRequest
    ) {
        Page<GeneralJournalRecurringResponse> headers = generalJournalRecurringService.getPaginatedAll(pageNumber,countPerPage);
        return ResponseEntity.ok(headers);
    }

    @PostMapping("Search/Paginated/Branch/{pageNumber}/{countPerPage}")
    public ResponseEntity<Page<GeneralJournalRecurringResponse>> getSearchPaginated(
            @PathVariable("branchId") @NotBlank Long branchId,
            @PathVariable("pageNumber") @NotBlank int pageNumber,
            @PathVariable("countPerPage") @NotBlank int countPerPage,
            @Valid @RequestBody GeneralJournalRecurringSearchRequest searchRequest
    ) {
        Page<GeneralJournalRecurringResponse> headers = generalJournalRecurringService.getPaginatedAll(branchId, pageNumber, countPerPage);
        return ResponseEntity.ok(headers);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
        int deleted = generalJournalRecurringService.delete(id);
        if (deleted == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}