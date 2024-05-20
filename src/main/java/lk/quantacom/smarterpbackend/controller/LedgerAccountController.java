package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.LadgerAccountRequest;
import lk.quantacom.smarterpbackend.dto.request.LadgerAccountUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LadgerAccountResponse;
import lk.quantacom.smarterpbackend.service.LadgerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("LedgerAccount")
@RestController
@CrossOrigin
public class LedgerAccountController {

    @Autowired
    private LadgerAccountService ladgerAccountService;


    @PostMapping
    public ResponseEntity<LadgerAccountResponse> save(@Valid @RequestBody LadgerAccountRequest request) {
        LadgerAccountResponse save = ladgerAccountService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<LadgerAccountResponse> update(@Valid @RequestBody LadgerAccountUpdateRequest request) {
        LadgerAccountResponse updated = ladgerAccountService.update(request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<LadgerAccountResponse> getById(@PathVariable("id") @NotBlank Long id) {
        LadgerAccountResponse get = ladgerAccountService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }

    @GetMapping("ByAccNo/{accNo}")
    public ResponseEntity<LadgerAccountResponse> getByAccNo(@PathVariable("accNo") @NotBlank String accNo) {
        LadgerAccountResponse get = ladgerAccountService.getByAccNo(accNo);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<LadgerAccountResponse>> getAll() {
        List<LadgerAccountResponse> getall = ladgerAccountService.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("Search/{category}")
    public ResponseEntity<List<LadgerAccountResponse>> searchLedger(@PathVariable("category") @NotBlank String category) {
        List<LadgerAccountResponse> getall = ladgerAccountService.getAllByCategory(category, "");
        return ResponseEntity.ok(getall);
    }

    @GetMapping("BySubAccountType/BankAccount")
    public ResponseEntity<List<LadgerAccountResponse>> getBySubAccountTypeBankAccount() {
        final String subAccountType = "Bank Account";

        List<LadgerAccountResponse> _ledgerAccounts = ladgerAccountService
                .getAllBySubAccountType(subAccountType);

        return ResponseEntity.ok(_ledgerAccounts);
    }

    @GetMapping("BySubAccountType/OnlineWallet")
    public ResponseEntity<List<LadgerAccountResponse>> getBySubAccountTypeOnlineWallet() {
        final String subAccountType = "Online Wallet";

        List<LadgerAccountResponse> _ledgerAccounts = ladgerAccountService
                .getAllBySubAccountType(subAccountType);

        return ResponseEntity.ok(_ledgerAccounts);
    }

    @GetMapping("BySubAccountType/CreditCard")
    public ResponseEntity<List<LadgerAccountResponse>> getBySubAccountTypeCreditCard() {
        final String subAccountType = "CreditCard";

        List<LadgerAccountResponse> _ledgerAccounts = ladgerAccountService
                .getAllBySubAccountType(subAccountType);

        return ResponseEntity.ok(_ledgerAccounts);
    }

    @GetMapping("BySubAccountType/UnDepositedFund")
    public ResponseEntity<LadgerAccountResponse> getBySubAccountTypeUnDepositedFund() {
        final String subAccountType = "UnDeposited Fund";

        List<LadgerAccountResponse> _ledgerAccounts = ladgerAccountService
                .getAllBySubAccountType(subAccountType);

        LadgerAccountResponse unDepositedFundAccount = (_ledgerAccounts != null && !_ledgerAccounts.isEmpty()) ? _ledgerAccounts.get(0) : null;

        if(unDepositedFundAccount == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(unDepositedFundAccount);
    }

//    @GetMapping("SearchByName/{name}")
//        public ResponseEntity<List<LadgerAccountResponse>> searchAccountByName(@PathVariable("name") @NotBlank String name) {
//            List<LadgerAccountResponse> accounts = ladgerAccountService.getAllByNameLike(name);
//            return ResponseEntity.ok(accounts);
//    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
        int deleted = ladgerAccountService.delete(id);
        if (deleted == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}