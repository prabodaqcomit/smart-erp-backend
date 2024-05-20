package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.NumberToWordRequest;
import lk.quantacom.smarterpbackend.dto.request.ReceiptHeaderRequest;
import lk.quantacom.smarterpbackend.dto.response.NumberToWordResponse;
import lk.quantacom.smarterpbackend.dto.response.ReceiptHeaderDocumentNumberResponse;
import lk.quantacom.smarterpbackend.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.allegro.finance.tradukisto.ValueConverters;

import javax.validation.Valid;

@RequestMapping("Utility")
@RestController
@CrossOrigin
public class UtilityController {

    @Autowired
    private UtilityService utilityService;

    @PostMapping("Number/NumberToWord/")
    public ResponseEntity<NumberToWordResponse> convertNumberToWord(
            @Valid @RequestBody NumberToWordRequest request
    ) {

        NumberToWordResponse wordResponse = utilityService.convertNumberToWord(request);

        return ResponseEntity.ok(wordResponse);
    }

}
