package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.PurchaseOrderRequest;
import lk.quantacom.smarterpbackend.dto.request.PurchaseOrderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PurchaseOrderResponse;
import lk.quantacom.smarterpbackend.service.PurchaseOrderService;
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

@RequestMapping("PurchaseOrder")
@RestController
@CrossOrigin
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;


    @PostMapping
    public ResponseEntity<List<PurchaseOrderResponse>> save(@Valid @RequestBody List<PurchaseOrderRequest> request){
        List<PurchaseOrderResponse> save = purchaseOrderService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<PurchaseOrderResponse> update(@Valid @RequestBody PurchaseOrderUpdateRequest request){
        PurchaseOrderResponse updated = purchaseOrderService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("Print/{poNo}/{branchId}")
    public ResponseEntity<Resource> print(@PathVariable("poNo") @NotBlank String poNo,
                                                @PathVariable("branchId") @NotBlank Long branchId){

        try {

            File printGenerate = purchaseOrderService.print(poNo,branchId);
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

    @GetMapping("{poId}")
    public ResponseEntity<List<PurchaseOrderResponse>> getByPOId(@PathVariable("poId") @NotBlank Long id){
        List<PurchaseOrderResponse> get = purchaseOrderService.getByPOId(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }




    @GetMapping()
    public ResponseEntity<List<PurchaseOrderResponse>> getAll(){
        List<PurchaseOrderResponse> getall = purchaseOrderService.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("HeadList/{type}")
    public ResponseEntity<List<PurchaseOrderResponse>> getHeadList(@PathVariable("type") @NotBlank String type){
        List<PurchaseOrderResponse> getall = purchaseOrderService.getHeadList(type);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("HeadListForGRN")
    public ResponseEntity<List<PurchaseOrderResponse>> getHeadListForGRN(){
        List<PurchaseOrderResponse> getall = purchaseOrderService.getHeadListForGrn();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = purchaseOrderService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @PutMapping("bulkApprove")
    public ResponseEntity<String> bulkApprove(@Valid @RequestBody List<Integer> poNo){
        String updated = purchaseOrderService.bulkApprove(poNo);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }
}