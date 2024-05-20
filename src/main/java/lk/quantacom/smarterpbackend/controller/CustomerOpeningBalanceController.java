package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.CustomerOpeningBalanceLedgerRequest;
import lk.quantacom.smarterpbackend.dto.request.CustomerOpeningBalanceRequest;
import lk.quantacom.smarterpbackend.dto.request.CustomerOpeningBalanceUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.CustomerAndCusOpeningBalResponse;
import lk.quantacom.smarterpbackend.dto.response.CustomerOpeningBalanceResponse;
import lk.quantacom.smarterpbackend.service.CustomerOpeningBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("CustomerOpeningBalance")
@RestController
@CrossOrigin
public class CustomerOpeningBalanceController {

    @Autowired
    private CustomerOpeningBalanceService customerOpeningBalanceService;


//    @PostMapping
//    public ResponseEntity<CustomerOpeningBalanceResponse> save(@Valid @RequestBody CustomerOpeningBalanceRequest request){
//        CustomerOpeningBalanceResponse save = customerOpeningBalanceService.save(request);
//        return ResponseEntity.ok(save);
//    }
//
//    @PutMapping
//    public ResponseEntity<CustomerOpeningBalanceResponse> update(@Valid @RequestBody CustomerOpeningBalanceUpdateRequest request){
//        CustomerOpeningBalanceResponse updated = customerOpeningBalanceService.update(request);
//        if(updated==null){
//            return  ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updated);
//    }
//
//
//    @GetMapping("{id}")
//    public ResponseEntity<CustomerOpeningBalanceResponse> getById(@PathVariable("id") @NotBlank Integer id){
//        CustomerOpeningBalanceResponse get = customerOpeningBalanceService.getById(id);
//
//        if(get==null){
//
//            return  ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(get);
//    }


//    @GetMapping()
//    public ResponseEntity<List<CustomerOpeningBalanceResponse>> getAll(){
//        List<CustomerOpeningBalanceResponse> getall = customerOpeningBalanceService.getAll();
//        return ResponseEntity.ok(getall);
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Integer id){
//        int deleted = customerOpeningBalanceService.delete(id);
//        if(deleted==0){
//            return  ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(deleted);
//    }
    @PostMapping()
    public ResponseEntity<List<CustomerOpeningBalanceResponse>> saveSupplierOpeningBalance(@Valid @RequestBody List<CustomerOpeningBalanceLedgerRequest> customerOpeningBalanceLedgerRequests){
        List<CustomerOpeningBalanceResponse> save = customerOpeningBalanceService.saveCustomerOpeningBalance(customerOpeningBalanceLedgerRequests);
        return ResponseEntity.ok(save);
    }

    @GetMapping("Check/{cusId}")
    public ResponseEntity<String> getFldCustomerId(@PathVariable("cusId") @NotBlank String cusId){
        String getAll = customerOpeningBalanceService.getFldCustomerId(cusId);
        if(getAll==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getAll);
    }

    @GetMapping()
    public ResponseEntity<List<CustomerAndCusOpeningBalResponse>> getCustomerAndCusOpeningBal(){
        List<CustomerAndCusOpeningBalResponse> getAll = customerOpeningBalanceService.getCustomerAndCusOpeningBal();
        return ResponseEntity.ok(getAll);
    }
}