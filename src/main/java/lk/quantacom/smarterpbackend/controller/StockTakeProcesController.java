package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.StockTakeProcesRequest;
import lk.quantacom.smarterpbackend.dto.request.StockTakeProcesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.StockTakeProcesResponse;
import lk.quantacom.smarterpbackend.service.StockTakeProcesService;
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

@RequestMapping("StockTakeProces")
@RestController
@CrossOrigin
public class StockTakeProcesController {

    @Autowired
    private StockTakeProcesService stockTakeProcesService;


    @PostMapping
    public ResponseEntity<StockTakeProcesResponse> save(@Valid @RequestBody StockTakeProcesRequest request){
        StockTakeProcesResponse save = stockTakeProcesService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<StockTakeProcesResponse> update(@Valid @RequestBody StockTakeProcesUpdateRequest request){
        StockTakeProcesResponse updated = stockTakeProcesService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<StockTakeProcesResponse> getById(@PathVariable("id") @NotBlank Integer id){
        StockTakeProcesResponse get = stockTakeProcesService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<StockTakeProcesResponse>> getAll(){
        List<StockTakeProcesResponse> getall = stockTakeProcesService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Integer id){
        int deleted = stockTakeProcesService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @PostMapping("BulkSave")
    public ResponseEntity<List<StockTakeProcesResponse>> saveBulk(@Valid @RequestBody List<StockTakeProcesRequest> requests){
        List<StockTakeProcesResponse> save = stockTakeProcesService.saveBulk(requests);
        return ResponseEntity.ok(save);
    }

    @GetMapping("getProcesList")
    public ResponseEntity<List<StockTakeProcesResponse>> getProcesList(){
        List<StockTakeProcesResponse> getall = stockTakeProcesService.getProcesList();
        return ResponseEntity.ok(getall);
    }

    @PutMapping("updateBulk")
    public ResponseEntity<List<StockTakeProcesResponse>> updateBulk(@Valid @RequestBody List<StockTakeProcesRequest> requests){
        List<StockTakeProcesResponse> updated = stockTakeProcesService.updateBulk(requests);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("Print/printVarianceReport/{types}")
    public ResponseEntity<ByteArrayResource> printVarianceReport(@PathVariable("types") @NotBlank String types) {
        File printGenerate = null;
        HttpHeaders headers = null;
        ByteArrayResource resource = null;
        try {
            printGenerate = stockTakeProcesService.printVarianceReport(types);
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
}