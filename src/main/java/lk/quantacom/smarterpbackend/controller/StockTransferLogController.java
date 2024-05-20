package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.StockTransferLogRequest;
import lk.quantacom.smarterpbackend.dto.request.StockTransferLogUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.StockTransferLogResponse;
import lk.quantacom.smarterpbackend.service.StockTransferLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
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

@RequestMapping("StockTransferLog")
@RestController
@CrossOrigin
public class StockTransferLogController {

    @Autowired
    private StockTransferLogService stockTransferLogService;


    @PostMapping
    public ResponseEntity<StockTransferLogResponse> save(@Valid @RequestBody StockTransferLogRequest request){
        StockTransferLogResponse save = stockTransferLogService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<StockTransferLogResponse> update(@Valid @RequestBody StockTransferLogUpdateRequest request){
        StockTransferLogResponse updated = stockTransferLogService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<StockTransferLogResponse> getById(@PathVariable("id") @NotBlank Long id){
        StockTransferLogResponse get = stockTransferLogService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<StockTransferLogResponse>> getAll(){
        List<StockTransferLogResponse> getall = stockTransferLogService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = stockTransferLogService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("Print/IssueNote/{issueNumber}/{types}")
    public ResponseEntity<ByteArrayResource> printIssueNoteReport(@PathVariable("issueNumber") @NotBlank Integer issueNumber,
                                                                 @PathVariable("types") @NotBlank String types) {
        File printGenerate = null;
        HttpHeaders headers = null;
        ByteArrayResource resource = null;
        try {
            printGenerate = stockTransferLogService.printIssueNoteReport(issueNumber, types);
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
        } catch (Exception E) {
            E.printStackTrace();
        }
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(printGenerate.length())
                .contentType(MediaType
                        .parseMediaType("application/" + types))
                .body(resource);
    }

    @GetMapping("issueNumber")
    public ResponseEntity<Integer> getIssueNumber(){
        Integer getall = stockTransferLogService.getIssueNumber();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getAllByIssueNumber")
    public ResponseEntity<List<StockTransferLogResponse>> getAllByIssueNote(){
        List<StockTransferLogResponse> getall = stockTransferLogService.getAllByIssueNote();
        return ResponseEntity.ok(getall);
    }

}