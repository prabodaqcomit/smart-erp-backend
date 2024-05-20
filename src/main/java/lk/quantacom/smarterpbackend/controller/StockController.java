package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.BinCardStockResponse;
import lk.quantacom.smarterpbackend.dto.response.StockFullResponse;
import lk.quantacom.smarterpbackend.dto.response.StockResponse;
import lk.quantacom.smarterpbackend.dto.response.getAllStockByItemResponse;
import lk.quantacom.smarterpbackend.service.StockService;
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

@RequestMapping("Stock")
@RestController
@CrossOrigin
public class StockController {

    @Autowired
    private StockService stockService;


    @PostMapping
    public ResponseEntity<StockResponse> save(@Valid @RequestBody StockRequest request){
        StockResponse save = stockService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<StockResponse> update(@Valid @RequestBody StockUpdateRequest request){
        StockResponse updated = stockService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<StockResponse> getById(@PathVariable("id") @NotBlank Long id){
        StockResponse get = stockService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<StockResponse>> getAll(){
        List<StockResponse> getall = stockService.getAll();
        return ResponseEntity.ok(getall);
    }

    @PostMapping("Search")
    public ResponseEntity<List<StockFullResponse>> searchStock(@Valid @RequestBody StockSearchRequest request){
        List<StockFullResponse> getall = stockService.searchStock(request);
        return ResponseEntity.ok(getall);
    }

    @PostMapping("Search/Expire")
    public ResponseEntity<List<StockFullResponse>> searchStockExp(@Valid @RequestBody StockSearchRequest request){
        List<StockFullResponse> getall = stockService.searchStockExp(request);
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = stockService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @PostMapping("Print/CurrentStock")
    public ResponseEntity<Resource> print(@Valid @RequestBody StockSearchRequest request){

        try {

            File printGenerate = stockService.printStockRpt(request,"stock");
            Path path = Paths.get(printGenerate.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Content-Disposition", "attachment; filename=" + printGenerate.getName());

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(printGenerate.length())
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("Print/CurrentStockExp")
    public ResponseEntity<Resource> printExp(@Valid @RequestBody StockSearchRequest request){

        try {

            File printGenerate = stockService.printStockRpt(request,"exp");
            Path path = Paths.get(printGenerate.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Content-Disposition", "attachment; filename=" + printGenerate.getName());

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(printGenerate.length())
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping("getPriceChangesList")
    public ResponseEntity<List<StockResponse>> getPriceChangesList(@Valid @RequestBody StockPriceChangesRequest request){
        List<StockResponse> getall = stockService.getPriceChangesList(request);
        return ResponseEntity.ok(getall);
    }

    @PutMapping("updatePriceChange")
    public ResponseEntity<List<StockResponse>> updatePriceChange(@Valid @RequestBody List<StockPriceChangeUpdateRequest> requests){
        List<StockResponse> updated = stockService.updatePriceChange(requests);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @PutMapping("stockTransfer")
    public ResponseEntity<List<StockResponse>> stockTransfer(@Valid @RequestBody StockTransferRequest request){
        List<StockResponse> updated = stockService.stockTransfer(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping("getByBranch/{branchId}")
    public ResponseEntity<List<StockResponse>> getByBranchAndIsDeleted(@PathVariable("branchId") @NotBlank Long branchId){
        List<StockResponse> getall = stockService.getByBranchAndIsDeleted(branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByItemCodeAndBranch/{itemCode}/{branchId}")
    public ResponseEntity<List<StockResponse>> getAll(@PathVariable("itemCode") @NotBlank String itemCode,
                                                      @PathVariable("branchId") @NotBlank Long branchId){
        List<StockResponse> getall = stockService.getByItemCodeAndBranch(itemCode,branchId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getAllByItemCode")
    public ResponseEntity<List<getAllStockByItemResponse>> getItemCode(){
        List<getAllStockByItemResponse> getall = stockService.getItemCode();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByItemCode/{itemCode}")
    public ResponseEntity<StockResponse> getByItemCode(@PathVariable("itemCode") @NotBlank String itemCode){
        StockResponse getall = stockService.getByItemCode(itemCode);
        return ResponseEntity.ok(getall);
    }
}