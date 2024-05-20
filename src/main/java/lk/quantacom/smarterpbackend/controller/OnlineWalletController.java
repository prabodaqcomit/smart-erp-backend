package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.OnlineWalletRequest;
import lk.quantacom.smarterpbackend.dto.request.OnlineWalletUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.OnlineWalletResponse;
import lk.quantacom.smarterpbackend.service.OnlineWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("OnlineWallet")
@RestController
@CrossOrigin
public class OnlineWalletController {

    @Autowired
    private OnlineWalletService onlineWalletService;


    @PostMapping
    public ResponseEntity<OnlineWalletResponse> save(@Valid @RequestBody OnlineWalletRequest request) {
        OnlineWalletResponse save = onlineWalletService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<OnlineWalletResponse> update(@Valid @RequestBody OnlineWalletUpdateRequest request) {
        OnlineWalletResponse updated = onlineWalletService.update(request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<OnlineWalletResponse> getById(@PathVariable("id") @NotBlank Long id) {
        OnlineWalletResponse get = onlineWalletService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<OnlineWalletResponse>> getAll() {
        List<OnlineWalletResponse> getall = onlineWalletService.getAll();
        return ResponseEntity.ok(getall);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
        int deleted = onlineWalletService.delete(id);
        if (deleted == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}