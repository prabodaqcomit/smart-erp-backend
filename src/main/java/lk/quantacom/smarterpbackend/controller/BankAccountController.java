package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.LedgerAccountNameRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerAccountNameUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BankAccountResponse;
import lk.quantacom.smarterpbackend.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("BankAccount")
@RestController
@CrossOrigin
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;


//    @PostMapping
//    public ResponseEntity<BankAccountResponse> save(@Valid @RequestBody BankAccountRequest request){
//        BankAccountResponse save = bankAccountService.save(request);
//        return ResponseEntity.ok(save);
//    }
//
//    @PutMapping
//    public ResponseEntity<BankAccountResponse> update(@Valid @RequestBody BankAccountUpdateRequest request){
//        BankAccountResponse updated = bankAccountService.update(request);
//        if(updated==null){
//            return  ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updated);
//    }


    @GetMapping("{id}")
    public ResponseEntity<BankAccountResponse> getById(@PathVariable("id") @NotBlank Long id){
        BankAccountResponse get = bankAccountService.getById(id);

        if(get==null){

            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<BankAccountResponse>> getAll(){
        List<BankAccountResponse> getall = bankAccountService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id){
        int deleted = bankAccountService.delete(id);
        if(deleted==0){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("BankName/{bankName}/AccNo/{accNo}")
    public ResponseEntity<List<BankAccountResponse>> getByBankNameAndAccNo(@PathVariable("bankName") @NotBlank String bankName,@PathVariable("accNo") @NotBlank String accNo){
        List<BankAccountResponse> getall = bankAccountService.getByBankNameAndAccNo(bankName,accNo);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("getByAccNo/{accNo}")
    public ResponseEntity<List<BankAccountResponse>> getByAccNo(@PathVariable("accNo") @NotBlank String accNo){
        List<BankAccountResponse> getall = bankAccountService.getByAccNo(accNo);
        return ResponseEntity.ok(getall);
    }

    @PostMapping()
    public ResponseEntity<List<BankAccountResponse>> saveWithLedgerAccountName(@Valid @RequestBody List<LedgerAccountNameRequest> requests){
        List<BankAccountResponse> save = bankAccountService.saveWithLedgerAccountName(requests);
        return ResponseEntity.ok(save);
    }

    @GetMapping("accNo/{accNo}")
    public ResponseEntity<BankAccountResponse> getByAccNum(@PathVariable("accNo") @NotBlank String accNo){
        BankAccountResponse get = bankAccountService.getByAccNum(accNo);
        if(get==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(get);
    }

    @PutMapping()
    public ResponseEntity<BankAccountResponse> updateBankLedgerAccount(@Valid @RequestBody LedgerAccountNameUpdateRequest request){
        BankAccountResponse updated = bankAccountService.updateBankLedgerAccount(request);
        if(updated==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping("BankName/{bankName}")
    public ResponseEntity<List<BankAccountResponse>> getByBankName(@PathVariable("bankName") @NotBlank String bankName){
        List<BankAccountResponse> getall = bankAccountService.getByBankName(bankName);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("branchName/{branchName}")
    public ResponseEntity<List<BankAccountResponse>> getByBranchName(@PathVariable("branchName") @NotBlank String branchName){
        List<BankAccountResponse> getall = bankAccountService.getByBranchName(branchName);
        return ResponseEntity.ok(getall);
    }

    @GetMapping("accName/{accName}")
    public ResponseEntity<List<BankAccountResponse>> getByAccName(@PathVariable("accName") @NotBlank String accName){
        List<BankAccountResponse> getall = bankAccountService.getByAccName(accName);
        return ResponseEntity.ok(getall);
    }





}