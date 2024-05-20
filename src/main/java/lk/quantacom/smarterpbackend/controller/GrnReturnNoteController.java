package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GrnReturnNoteRequest;
import lk.quantacom.smarterpbackend.dto.request.GrnReturnNoteUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GrnReturnNoteResponse;
import lk.quantacom.smarterpbackend.service.GrnReturnNoteService;
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

@RequestMapping("GrnReturnNote")
@RestController
@CrossOrigin
public class GrnReturnNoteController {

    @Autowired
    private GrnReturnNoteService grnReturnNoteService;

    @PostMapping
    public ResponseEntity<List<GrnReturnNoteResponse>> save(@Valid @RequestBody List<GrnReturnNoteRequest> request){
        List<GrnReturnNoteResponse> save = grnReturnNoteService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<GrnReturnNoteResponse> update(@Valid @RequestBody GrnReturnNoteUpdateRequest request){
        GrnReturnNoteResponse updated = grnReturnNoteService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<GrnReturnNoteResponse> getById(@PathVariable("id") @NotBlank Long id){
        GrnReturnNoteResponse get = grnReturnNoteService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<GrnReturnNoteResponse>> getAll(){
        List<GrnReturnNoteResponse> getall = grnReturnNoteService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = grnReturnNoteService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("Print/{id}")
    public ResponseEntity<Resource> print(@PathVariable("id")   Long id){

        try {

            File printGenerate = grnReturnNoteService.print(id);
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
}