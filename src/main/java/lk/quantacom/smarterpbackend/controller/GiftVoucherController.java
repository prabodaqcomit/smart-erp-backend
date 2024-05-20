package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.GiftVoucherPrint;
import lk.quantacom.smarterpbackend.dto.request.GiftVoucherRequest;
import lk.quantacom.smarterpbackend.dto.request.GiftVoucherSearch;
import lk.quantacom.smarterpbackend.dto.request.GiftVoucherUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GiftVoucherResponse;
import lk.quantacom.smarterpbackend.service.GiftVoucherService;
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

@RequestMapping("GiftVoucher")
@RestController
@CrossOrigin
public class GiftVoucherController {

    @Autowired
    private GiftVoucherService giftVoucherService;

    @GetMapping("MaxSRNO")
    public ResponseEntity<String> getById(){
        String sn = giftVoucherService.getMaxSRNO();

        return ResponseEntity.ok(sn);
    }

    @PostMapping("SaveBulk")
    public ResponseEntity<?> saveBulk(@Valid @RequestBody List<GiftVoucherRequest> request){
        List<GiftVoucherResponse> save = giftVoucherService.saveBulk(request);
        return ResponseEntity.ok(save);
    }

    @PostMapping
    public ResponseEntity<GiftVoucherResponse> save(@Valid @RequestBody GiftVoucherRequest request){
        GiftVoucherResponse save = giftVoucherService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<GiftVoucherResponse> update(@Valid @RequestBody GiftVoucherUpdateRequest request){
        GiftVoucherResponse updated = giftVoucherService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<GiftVoucherResponse> getById(@PathVariable("id") @NotBlank Long id){
        GiftVoucherResponse get = giftVoucherService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }

    @PostMapping("Search")
    public ResponseEntity<List<GiftVoucherResponse>> searchAll(@Valid @RequestBody GiftVoucherSearch search){
        List<GiftVoucherResponse> getall = giftVoucherService.searchAll(search);
        if(getall==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getall);
    }

    @GetMapping()
    public ResponseEntity<List<GiftVoucherResponse>> getAll(){
        List<GiftVoucherResponse> getall = giftVoucherService.getAll();
        System.out.println(getall.size());
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = giftVoucherService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @PostMapping("Print")
    public ResponseEntity<Resource> print(@Valid @RequestBody GiftVoucherPrint request){

        try {

            File printGenerate = giftVoucherService.print(request);
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