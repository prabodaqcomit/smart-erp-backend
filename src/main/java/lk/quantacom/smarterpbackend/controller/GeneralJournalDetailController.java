package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GeneralJournalDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalDetailResponse;
import lk.quantacom.smarterpbackend.service.GeneralJournalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("GeneralJournalDetail")
@RestController
@CrossOrigin
public class GeneralJournalDetailController {

    @Autowired
    private GeneralJournalDetailService generalJournalDetailService;


//    @PostMapping
//    public ResponseEntity<GeneralJournalDetailResponse> save(@Valid @RequestBody GeneralJournalDetailRequest request) {
//        GeneralJournalDetailResponse save = generalJournalDetailService.save(request);
//        return ResponseEntity.ok(save);
//    }
//
//    @PutMapping
//    public ResponseEntity<GeneralJournalDetailResponse> update(@Valid @RequestBody GeneralJournalDetailUpdateRequest request) {
//        GeneralJournalDetailResponse updated = generalJournalDetailService.update(request);
//        if (updated == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updated);
//    }


    @GetMapping("{id}")
    public ResponseEntity<GeneralJournalDetailResponse> getById(@PathVariable("id") @NotBlank Long id) {
        GeneralJournalDetailResponse get = generalJournalDetailService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<GeneralJournalDetailResponse>> getAll() {
        List<GeneralJournalDetailResponse> getall = generalJournalDetailService.getAll();
        return ResponseEntity.ok(getall);
    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
//        int deleted = generalJournalDetailService.delete(id);
//        if (deleted == 0) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(deleted);
//    }
}