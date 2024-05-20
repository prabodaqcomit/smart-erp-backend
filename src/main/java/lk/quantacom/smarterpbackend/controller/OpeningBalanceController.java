package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.OpeningBalanceRequest;
import lk.quantacom.smarterpbackend.dto.request.OpeningBalanceUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.OpeningBalanceResponse;
import lk.quantacom.smarterpbackend.service.OpeningBalanceService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequestMapping("OpeningBalance")
@RestController
@CrossOrigin
public class OpeningBalanceController {

    @Autowired
    private OpeningBalanceService openingBalanceService;

    @PostMapping("SaveBulk")
    public ResponseEntity<List<OpeningBalanceResponse>> saveBulk(@Valid @RequestBody List<OpeningBalanceRequest> request) {
        List<OpeningBalanceResponse> save = openingBalanceService.saveBulk(request);
        return ResponseEntity.ok(save);
    }

    @PostMapping
    public ResponseEntity<OpeningBalanceResponse> save(@Valid @RequestBody OpeningBalanceRequest request) {
        OpeningBalanceResponse save = openingBalanceService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<OpeningBalanceResponse> update(@Valid @RequestBody OpeningBalanceUpdateRequest request) {
        OpeningBalanceResponse updated = openingBalanceService.update(request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<OpeningBalanceResponse> getById(@PathVariable("id") @NotBlank Long id) {
        OpeningBalanceResponse get = openingBalanceService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<OpeningBalanceResponse>> getAll() {
        List<OpeningBalanceResponse> getall = openingBalanceService.getAll();
        return ResponseEntity.ok(getall);
    }

    @GetMapping("GroupBy")
    public ResponseEntity<List<HashMap<String, Object>>> getAllGroupBy() {
        List<OpeningBalanceResponse> getall = openingBalanceService.getAll();

        List<HashMap<String, Object>> list = new ArrayList<>();

        for (OpeningBalanceResponse response : getall) {
            boolean exist = false;
            for (HashMap<String, Object> map : list) {
                if ((int) map.get("obNo") == response.getObNo()) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("obDate", ConvertUtils.convertStrToDate(response.getObDate()));
                map.put("obNo", response.getObNo());
                list.add(map);
            }
        }

        return ResponseEntity.ok(list);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
        int deleted = openingBalanceService.delete(id);
        if (deleted == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}