package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.TblbomheaderRequest;
import lk.quantacom.smarterpbackend.dto.request.TblbomheaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BOMAllResponse;
import lk.quantacom.smarterpbackend.dto.response.BomMainMaterialForPOR;
import lk.quantacom.smarterpbackend.dto.response.GetForBomAllItemCodeByDescResponse;
import lk.quantacom.smarterpbackend.dto.response.TblbomheaderResponse;
import lk.quantacom.smarterpbackend.service.TblbomheaderService;
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

@RequestMapping("Tblbomheader")
@RestController
@CrossOrigin
public class TblbomheaderController {

    @Autowired
    private TblbomheaderService tblbomheaderService;


    @PostMapping
    public ResponseEntity<TblbomheaderResponse> save(@Valid @RequestBody TblbomheaderRequest request){
        TblbomheaderResponse save = tblbomheaderService.save(request);
        return ResponseEntity.ok(save);
    }

//    @PutMapping
//    public ResponseEntity<TblbomheaderResponse> update(@Valid @RequestBody TblbomheaderUpdateRequest request){
//        TblbomheaderResponse updated = tblbomheaderService.update(request);
//        if(updated==null){
//            return  ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updated);
//    }


    @GetMapping("{id}")
    public ResponseEntity<TblbomheaderResponse> getById(@PathVariable("id") @NotBlank Integer id){
        TblbomheaderResponse get = tblbomheaderService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<TblbomheaderResponse>> getAll(){
        List<TblbomheaderResponse> getall = tblbomheaderService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{bomId}")
    public ResponseEntity<Integer> delete(@PathVariable("bomId") @NotBlank Integer bomId){
        int deleted = tblbomheaderService.delete(bomId);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("MainMaterials")
    public ResponseEntity<List<GetForBomAllItemCodeByDescResponse>> getMainMaterials(){
        List<GetForBomAllItemCodeByDescResponse> getall = tblbomheaderService.getAllItemCodeByDesc("");
        return ResponseEntity.ok(getall);
    }

    @GetMapping("Accessories")
    public ResponseEntity<List<GetForBomAllItemCodeByDescResponse>> getAccessories(){
        List<GetForBomAllItemCodeByDescResponse> getall = tblbomheaderService.getAllItemCodeByDesc2("");
        return ResponseEntity.ok(getall);
    }

    @GetMapping("MainMaterialsForPOR")
    public ResponseEntity<List<BomMainMaterialForPOR>> getMaterialForPOR() {
        return   ResponseEntity.ok(tblbomheaderService.getMaterialForPOR());
    }

    //BOMAllResponse
    @GetMapping("AllTables/{bomId}")
    public ResponseEntity<BOMAllResponse> getFromAllTables(@PathVariable("bomId") @NotBlank Integer bomId){
        BOMAllResponse getall = tblbomheaderService.getFromAllTables(bomId);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("Print/{bomId}")
    public ResponseEntity<Resource> Print(@PathVariable("bomId") @NotBlank Integer bomId) {

        try {
            File got = tblbomheaderService.printBomHeader(bomId);
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