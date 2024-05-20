package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GrnHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.GrnHeaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GrnHeaderResponse;
import lk.quantacom.smarterpbackend.service.GrnHeaderService;
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

@RequestMapping("GrnHeader")
@RestController
@CrossOrigin
public class GrnHeaderController {

    @Autowired
    private GrnHeaderService grnHeaderService;


    @PostMapping
    public ResponseEntity<GrnHeaderResponse> save(@Valid @RequestBody GrnHeaderRequest request){
        GrnHeaderResponse save = grnHeaderService.save(request);
        return ResponseEntity.ok(save);
    }

    @PostMapping("SupplierOB")
    public ResponseEntity<GrnHeaderResponse> saveSupOB(@Valid @RequestBody GrnHeaderRequest request){
        GrnHeaderResponse save = grnHeaderService.saveSupOB(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<GrnHeaderResponse> update(@Valid @RequestBody GrnHeaderUpdateRequest request){
        GrnHeaderResponse updated = grnHeaderService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<GrnHeaderResponse> getById(@PathVariable("id") @NotBlank Long id){
        GrnHeaderResponse get = grnHeaderService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<GrnHeaderResponse>> getAll(){
        List<GrnHeaderResponse> getall = grnHeaderService.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("Ids")
    public ResponseEntity<List<String>> getAllIds(){
        List<String> getall = grnHeaderService.getGrnIds();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = grnHeaderService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }


    @GetMapping("Print/grnReport/{grnNo}/{types}")
    public ResponseEntity<ByteArrayResource> printItemVoidReport(@PathVariable("grnNo") @NotBlank Integer grnNo,
                                                                 @PathVariable("types") @NotBlank String types) {
        File printGenerate = null;
        HttpHeaders headers = null;
        ByteArrayResource resource = null;
        try {
            printGenerate = grnHeaderService.grnReport(grnNo, types);
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