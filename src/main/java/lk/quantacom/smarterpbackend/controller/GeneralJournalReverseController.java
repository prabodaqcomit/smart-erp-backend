package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GeneralJournalReverseRequest;
import lk.quantacom.smarterpbackend.dto.request.GeneralJournalReverseUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GeneralJournalReverseResponse;
import lk.quantacom.smarterpbackend.service.GeneralJournalReverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("GeneralJournalReverse")
@RestController
@CrossOrigin
public class GeneralJournalReverseController {

    @Autowired
    private GeneralJournalReverseService generalJournalReverseService;


//    @PostMapping
//    public ResponseEntity<GeneralJournalReverseResponse> save(@Valid @RequestBody GeneralJournalReverseRequest request) {
//        GeneralJournalReverseResponse save = generalJournalReverseService.save(request);
//        return ResponseEntity.ok(save);
//    }

//    @PutMapping
//    public ResponseEntity<GeneralJournalReverseResponse> update(@Valid @RequestBody GeneralJournalReverseUpdateRequest request) {
//        GeneralJournalReverseResponse updated = generalJournalReverseService.update(request);
//        if (updated == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updated);
//    }


//    @GetMapping("{id}")
//    public ResponseEntity<GeneralJournalReverseResponse> getById(@PathVariable("id") @NotBlank Long id) {
//        GeneralJournalReverseResponse get = generalJournalReverseService.getById(id);
//
//        if (get == null) {
//
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(get);
//    }


    @GetMapping()
    public ResponseEntity<List<GeneralJournalReverseResponse>> getAll() {
        List<GeneralJournalReverseResponse> getall = generalJournalReverseService.getAll();
        return ResponseEntity.ok(getall);
    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
//        int deleted = generalJournalReverseService.delete(id);
//        if (deleted == 0) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(deleted);
//    }
}