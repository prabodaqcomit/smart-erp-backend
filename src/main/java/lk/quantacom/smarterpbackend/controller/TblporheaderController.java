package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.TblporheaderResponse;
import lk.quantacom.smarterpbackend.dto.response.getPorAccessoryResponse;
import lk.quantacom.smarterpbackend.service.TblporheaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
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

@RequestMapping("Tblporheader")
@RestController
@CrossOrigin
public class TblporheaderController {

    @Autowired
    private TblporheaderService tblporheaderService;


//    @PostMapping
//    public ResponseEntity<TblporheaderResponse> save(@Valid @RequestBody TblporheaderRequest request){
//        TblporheaderResponse save = tblporheaderService.save(request);
//        return ResponseEntity.ok(save);
//    }
//
//    @PutMapping
//    public ResponseEntity<TblporheaderResponse> update(@Valid @RequestBody TblporheaderUpdateRequest request){
//        TblporheaderResponse updated = tblporheaderService.update(request);
//        if(updated==null){
//            return  ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updated);
//    }


    @GetMapping("{id}")
    public ResponseEntity<TblporheaderResponse> getById(@PathVariable("id") @NotBlank Long id) {
        TblporheaderResponse get = tblporheaderService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<TblporheaderResponse>> getAll() {
        List<TblporheaderResponse> getall = tblporheaderService.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("Available")
    public ResponseEntity<List<TblporheaderResponse>> getAllAvailable() {
        List<TblporheaderResponse> getall = tblporheaderService.getAllAvailable();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("Approved")
    public ResponseEntity<List<TblporheaderResponse>> getAllApproved() {
        List<TblporheaderResponse> getall = tblporheaderService.getAllApproved();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
        int deleted = tblporheaderService.delete(id);
        if (deleted == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }


    @GetMapping("porId/{porId}/{approved}")
    public ResponseEntity<List<TblporheaderResponse>> getByPorId(@PathVariable("porId") @NotBlank String porId, @PathVariable("approved") @NotBlank Boolean approved) {
        List<TblporheaderResponse> getall = tblporheaderService.getByPorId(porId, approved);
        return ResponseEntity.ok(getall);
    }


    @PostMapping("saveToPorTables")
    public ResponseEntity<TblporheaderResponse> saveToPorTables(@Valid @RequestBody TblporheaderAllListRequest request) {
        TblporheaderResponse save = tblporheaderService.saveToPorTables(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping("reduceFromStock")
    public ResponseEntity<Integer> reduceFromStock(@Valid @RequestBody TblporheaderReduceFromStockRequest updateRequest) {
        Integer updated = tblporheaderService.reduceFromStock(updateRequest);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @PostMapping("requestApprovalSave")
    public ResponseEntity<TblporheaderResponse> requestApproval(@Valid @RequestBody TblporheaderRequestApprovalRequest requestApprovalRequest) {
        TblporheaderResponse save = tblporheaderService.requestApprovalForApproved(requestApprovalRequest);
        return ResponseEntity.ok(save);
    }

    @PutMapping("confirmApprovalPorId/{porId}")
    public ResponseEntity<Integer> confirmApproval(@PathVariable("porId") @NotBlank String porId) {
        int update = tblporheaderService.confirmApproval(porId);
        if (update == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(update);
    }

    @GetMapping("getByPorIdApproveRequest/{porId}")
    public ResponseEntity<List<TblporheaderResponse>> getByPorIdAndPorApprovedAndPorApproveRequest(@PathVariable("porId") @NotBlank String porId) {
        List<TblporheaderResponse> getall = tblporheaderService.getByPorIdAndPorApprovedAndPorApproveRequest(porId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("{batchNo}/{itemCode}/{branchId}/{sizeId}/{fitId}/{colorId}")
    public ResponseEntity<List<getPorAccessoryResponse>>
    getByStockAndItemPorAccessory(@PathVariable("batchNo") @NotBlank String batchNo,
                                  @PathVariable("itemCode") @NotBlank String itemCode,
                                  @PathVariable("branchId") @NotBlank Long branchId,
                                  @PathVariable("sizeId") @NotBlank Long sizeId,
                                  @PathVariable("fitId") @NotBlank Long fitId,
                                  @PathVariable("colorId") @NotBlank Long colorId) {

        List<getPorAccessoryResponse> getall = tblporheaderService.getByStockAndItemPorAccessory(batchNo.equals("0") ? "" : batchNo,
                itemCode, branchId, sizeId, fitId, colorId);
        return ResponseEntity.ok(getall);
    }

    @PutMapping("updateStatus/{porId}")
    public ResponseEntity<Integer> updateStatus(@PathVariable("porId") @NotBlank String porId) {
        int update = tblporheaderService.updateStatus(porId);
        if (update == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(update);
    }

    @GetMapping("Print/{porId}")
    public ResponseEntity<Resource> Print(@PathVariable("porId") @NotBlank String porId) {

        try {
            File got = tblporheaderService.printPOR(porId);
            Path path = Paths.get(got.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Content-Disposition", "attachment; filename=" + got.getName());

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(got.length())
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.notFound().build();
    }
}