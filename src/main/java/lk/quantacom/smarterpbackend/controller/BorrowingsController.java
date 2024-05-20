package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.BorrowingsRequest;
import lk.quantacom.smarterpbackend.dto.request.BorrowingsSaveAllRequest;
import lk.quantacom.smarterpbackend.dto.request.BorrowingsSaveReturnRequest;
import lk.quantacom.smarterpbackend.dto.request.BorrowingsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BorrowingsResponse;
import lk.quantacom.smarterpbackend.service.BorrowingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@RequestMapping("Borrowings")
@RestController
@CrossOrigin
public class BorrowingsController {

    @Autowired
    private BorrowingsService borrowingsService;

//    @PostMapping
//    public ResponseEntity<BorrowingsResponse> save(@Valid @RequestBody BorrowingsRequest request){
//        BorrowingsResponse save = borrowingsService.save(request);
//        return ResponseEntity.ok(save);
//    }
//
//    @PutMapping
//    public ResponseEntity<BorrowingsResponse> update(@Valid @RequestBody BorrowingsUpdateRequest request){
//        BorrowingsResponse updated = borrowingsService.update(request);
//        if(updated==null){
//            return  ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updated);
//    }

    @GetMapping("{id}")
    public ResponseEntity<BorrowingsResponse> getById(@PathVariable("id") @NotBlank Long id){
        BorrowingsResponse get = borrowingsService.getById(id);
        if(get==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(get);
    }

    @GetMapping()
    public ResponseEntity<List<BorrowingsResponse>> getAll(){
        List<BorrowingsResponse> getall = borrowingsService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = borrowingsService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("getByBorrowerName/{borrowerName}")
    public ResponseEntity<List<BorrowingsResponse>> getByBorrowerName(@PathVariable("borrowerName") @NotBlank String borrowerName){
        List<BorrowingsResponse> getall = borrowingsService.getByBorrowerName(borrowerName);
        return ResponseEntity.ok(getall);
    }

    @PostMapping("NewBorrowingSave")
    public ResponseEntity<BorrowingsResponse> saveNewBorrowing(@Valid @RequestBody BorrowingsSaveAllRequest request){
        BorrowingsResponse save = borrowingsService.saveAll(request);
        return ResponseEntity.ok(save);
    }

    @GetMapping("getByReason/{reason}")
    public ResponseEntity<List<BorrowingsResponse>> getByReason(@PathVariable("reason") @NotBlank String reason){
        List<BorrowingsResponse> getall = borrowingsService.getByReason(reason);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByBorrowerNameAndStatusNwBr/{borrowerName}")
    public ResponseEntity<List<BorrowingsResponse>> getByBorrowerNameAndStatusNwBr(@PathVariable("borrowerName") @NotBlank String borrowerName){
        List<BorrowingsResponse> getall = borrowingsService.getByReason(borrowerName);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByDateRange/from/{from}/to/{to}")
    public ResponseEntity<List<BorrowingsResponse>> getByDateRangeOfBorrowDate(@PathVariable("from") @NotBlank Date from,
                                                                               @PathVariable("to") @NotBlank Date to) {
        List<BorrowingsResponse> getall = borrowingsService.getByDateRangeOfBorrowDate(from, to);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("BorrowerNames")
    public ResponseEntity<List<String>> getBorrowerNames() {
        List<String> getall = borrowingsService.getBorrowerNames();
        return ResponseEntity.ok(getall);
    }

    @PostMapping("saveReturn")
    public ResponseEntity<BorrowingsResponse> saveReturn(@Valid @RequestBody BorrowingsSaveReturnRequest request){
        BorrowingsResponse save = borrowingsService.saveReturn(request);
        return ResponseEntity.ok(save);
    }

}