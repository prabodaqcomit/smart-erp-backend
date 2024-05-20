package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.BarcodesRequest;
import lk.quantacom.smarterpbackend.dto.request.BarcodesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BarcodesResponse;
import lk.quantacom.smarterpbackend.dto.response.ItemsByBarcodesResponse;
import lk.quantacom.smarterpbackend.service.BarcodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Barcodes")
@RestController
@CrossOrigin
public class BarcodesController {

    @Autowired
    private BarcodesService barcodesService;


    @PostMapping
    public ResponseEntity<BarcodesResponse> save(@Valid @RequestBody BarcodesRequest request){
        BarcodesResponse save = barcodesService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<BarcodesResponse> update(@Valid @RequestBody BarcodesUpdateRequest request){
        BarcodesResponse updated = barcodesService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<BarcodesResponse> getById(@PathVariable("id") @NotBlank Long id){
        BarcodesResponse get = barcodesService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<BarcodesResponse>> getAll(){
        List<BarcodesResponse> getall = barcodesService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = barcodesService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("getItemByBarcode/{barcode}")
    public ResponseEntity<ItemsByBarcodesResponse> getItemByBarcode(@PathVariable("barcode") @NotBlank String barcode){
        ItemsByBarcodesResponse getall = barcodesService.getItemByBarcode(barcode);
        if(getall==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getall);
    }


}