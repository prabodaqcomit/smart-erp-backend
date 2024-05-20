package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.CurrencyRequest;
import lk.quantacom.smarterpbackend.dto.request.CurrencyUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.CurrencyResponse;
import lk.quantacom.smarterpbackend.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("Currency")
@RestController
@CrossOrigin
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;


    @PostMapping
    public ResponseEntity<CurrencyResponse> save(@Valid @RequestBody CurrencyRequest request){
        CurrencyResponse save = currencyService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<CurrencyResponse> update(@Valid @RequestBody CurrencyUpdateRequest request){
        CurrencyResponse updated = currencyService.update(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<CurrencyResponse> getById(@PathVariable("id") @NotBlank Long id){
        CurrencyResponse get = currencyService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<CurrencyResponse>> getAll(){
        List<CurrencyResponse> getall = currencyService.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("Active")
    public ResponseEntity<List<CurrencyResponse>> getAllActive(){
        List<CurrencyResponse> getall = currencyService.getAllActive();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = currencyService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}