package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GeneralJournalTemplateDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalTemplateDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalTemplateDetailResponse;
import lk.quantacom.smarterpbackend.service.GeneralJournalTemplateDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("GeneralJournalTemplateDetail")
@RestController
@CrossOrigin
public class GeneralJournalTemplateDetailController {

    @Autowired
    private GeneralJournalTemplateDetailService generalJournalTemplateDetailService;

//    @PostMapping
//    public ResponseEntity<GeneralJournalTemplateDetailResponse> save(@Valid @RequestBody GeneralJournalTemplateDetailRequest request) {
//        GeneralJournalTemplateDetailResponse save = generalJournalTemplateDetailService.save(request);
//        return ResponseEntity.ok(save);
//    }
//
//    @PutMapping
//    public ResponseEntity<GeneralJournalTemplateDetailResponse> update(@Valid @RequestBody GeneralJournalTemplateDetailUpdateRequest request) {
//        GeneralJournalTemplateDetailResponse updated = generalJournalTemplateDetailService.update(request);
//        if (updated == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updated);
//    }
//


    @GetMapping("{id}")
    public ResponseEntity<GeneralJournalTemplateDetailResponse> getById(@PathVariable("id") @NotBlank Long id) {
        GeneralJournalTemplateDetailResponse get = generalJournalTemplateDetailService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<GeneralJournalTemplateDetailResponse>> getAll() {
        List<GeneralJournalTemplateDetailResponse> getall = generalJournalTemplateDetailService.getAll();
        return ResponseEntity.ok(getall);
    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
//        int deleted = generalJournalTemplateDetailService.delete(id);
//        if (deleted == 0) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(deleted);
//    }
}