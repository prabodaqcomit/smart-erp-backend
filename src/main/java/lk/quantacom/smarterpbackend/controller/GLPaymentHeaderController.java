package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.GLPaymentHeaderResponse;
import lk.quantacom.smarterpbackend.service.GLPaymentHeaderService;
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
import java.util.Map;

@RequestMapping("GLPaymentHeader")
@RestController
@CrossOrigin
public class GLPaymentHeaderController {

    @Autowired
    private GLPaymentHeaderService gLPaymentHeaderService;

    @PostMapping
    public ResponseEntity<GLPaymentHeaderResponse> save(@Valid @RequestBody GLPaymentHeaderRequest request){
        GLPaymentHeaderResponse save = gLPaymentHeaderService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<GLPaymentHeaderResponse> update(@Valid @RequestBody GLPaymentHeaderUpdateRequest request){
        GLPaymentHeaderResponse updated = gLPaymentHeaderService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping("{id}")
    public ResponseEntity<GLPaymentHeaderResponse> getById(@PathVariable("id") @NotBlank Long id){
        GLPaymentHeaderResponse get = gLPaymentHeaderService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }

    @GetMapping()
    public ResponseEntity<List<GLPaymentHeaderResponse>> getAll(){
        List<GLPaymentHeaderResponse> getall = gLPaymentHeaderService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = gLPaymentHeaderService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("getMaxVoucherNum")
    public ResponseEntity<String> getMaxVoucherNum(){
        String get = gLPaymentHeaderService.getMaxVoucherNum();
        if(get==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(get);
    }

    @GetMapping("Document/DocumentNumbers/")
    public ResponseEntity<List<String>> getAvailableVoucherNumbers(
            @RequestParam(value = "search", required = false) String search
    ){
        List<String> voucherNumbers = gLPaymentHeaderService.getAvailableVoucherNumbers(search);

        if(voucherNumbers == null || voucherNumbers.isEmpty()){
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(voucherNumbers);
    }

    @PostMapping("saveGeneral")
    public ResponseEntity<GLPaymentHeaderResponse> saveGeneral(@Valid @RequestBody GLPaymentHeaderGeneralRequest request){
        GLPaymentHeaderResponse save = gLPaymentHeaderService.saveGeneral(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping("saveSupplier")
    public ResponseEntity<GLPaymentHeaderResponse> updateSupplier(@Valid @RequestBody GLPaymentHeaderSupplierUpdateRequest request){
        GLPaymentHeaderResponse save = gLPaymentHeaderService.updateSupplier(request);

        return ResponseEntity.ok(save);
    }

    @PutMapping("saveGeneral")
    public ResponseEntity<GLPaymentHeaderResponse> updateGeneral(@Valid @RequestBody GLPaymentHeaderGeneralUpdateRequest request){
        GLPaymentHeaderResponse save = gLPaymentHeaderService.updateGeneral(request);

        return ResponseEntity.ok(save);
    }

    @PostMapping("saveSupplier")
    public ResponseEntity<GLPaymentHeaderResponse> saveSupplier(@Valid @RequestBody GLPaymentHeaderSupplierRequest request){
        GLPaymentHeaderResponse save = gLPaymentHeaderService.saveSupplier(request);
        return ResponseEntity.ok(save);
    }

    @PostMapping("filteredSearch")
    public ResponseEntity<List<GLPaymentHeaderResponse>> filteredSearch(@Valid @RequestBody GLPaymentFilterSearchRequest request){
        List<GLPaymentHeaderResponse> getall = gLPaymentHeaderService.filteredSearch(request);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getAllGeneral")
    public ResponseEntity<List<GLPaymentHeaderResponse>> getAllGeneral(){
        List<GLPaymentHeaderResponse> getall = gLPaymentHeaderService.getAllGeneral();
        return ResponseEntity.ok(getall);
    }


    @GetMapping("getAllSup")
    public ResponseEntity<List<GLPaymentHeaderResponse>> getAllSup(){
        List<GLPaymentHeaderResponse> getall = gLPaymentHeaderService.getAllSup();
        return ResponseEntity.ok(getall);
    }


    @GetMapping("getGeneralPayById/{id}")
    public ResponseEntity<GLPaymentHeaderResponse> getGeneralPayById(@PathVariable("id") @NotBlank Long id){
        GLPaymentHeaderResponse get = gLPaymentHeaderService.getGeneralPayById(id);
        if(get==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(get);
    }

    @GetMapping("getSupPayById/{id}")
    public ResponseEntity<GLPaymentHeaderResponse> getSupPayById(@PathVariable("id") @NotBlank Long id){
        GLPaymentHeaderResponse get = gLPaymentHeaderService.getSupPayById(id);
        if(get==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(get);
    }


    @PostMapping("filteredSearchGeneral")
    public ResponseEntity<List<GLPaymentHeaderResponse>> filteredSearchGeneral(@Valid @RequestBody GLPaymentFilterSearchRequest request){
        List<GLPaymentHeaderResponse> getall = gLPaymentHeaderService.filteredSearchGeneral(request);
        return ResponseEntity.ok(getall);
    }

    @PostMapping("filteredSearchSupplier")
    public ResponseEntity<List<GLPaymentHeaderResponse>> filteredSearchSupplier(@Valid @RequestBody GLPaymentFilterSearchRequest request){
        List<GLPaymentHeaderResponse> getall = gLPaymentHeaderService.filteredSearchSupplier(request);
        return ResponseEntity.ok(getall);
    }


    @PostMapping("Print/{id}")
    public ResponseEntity<ByteArrayResource> printSingle(@PathVariable("id") @NotBlank Long id){
        File printGenerate = null;
        HttpHeaders headers = null;
        ByteArrayResource resource = null;

        try {
            printGenerate = gLPaymentHeaderService.printSingle(id);
            if(printGenerate==null){
                return ResponseEntity.notFound().build();
            }
            Path path = Paths.get(printGenerate.getPath());
            resource = new ByteArrayResource(Files.readAllBytes(path));
            headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Content-Disposition", "attachment; filename=" + printGenerate.getName());

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(printGenerate.length())
                    .contentType(MediaType
                            .parseMediaType("application/" + "pdf"))
                    .body(resource);

        }catch (Exception ex){
            ex.printStackTrace();
            return  ResponseEntity.internalServerError().build();
        }
    }
}