package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.dto.request.ReceiptHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.ReceiptHeaderSearchRequest;
import lk.quantacom.smarterpbackend.dto.response.ReceiptHeaderDocumentNumberResponse;
import lk.quantacom.smarterpbackend.dto.response.ReceiptHeaderResponse;
import lk.quantacom.smarterpbackend.dto.response.ReceiptSettleInvoiceResponse;
import lk.quantacom.smarterpbackend.service.ReceiptHeaderService;
import lk.quantacom.smarterpbackend.service.ReceiptSettleInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
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

@RequestMapping("ReceiptSettleInvoice")
@RestController
@CrossOrigin
public class ReceiptSettleInvoiceController {

    @Autowired
    private ReceiptSettleInvoiceService receiptSettleInvoiceService;


    @GetMapping("Credit/Pending/{customerId}")
    public ResponseEntity<List<ReceiptSettleInvoiceResponse>> getPendingCreditInvoicesByCustomerId(@PathVariable("customerId") @NotBlank Long customerId) {
        List<ReceiptSettleInvoiceResponse> receiptSettleInvoiceResponses = receiptSettleInvoiceService.getPendingCreditInvoicesByCustomerId(customerId);

        if( receiptSettleInvoiceResponses.isEmpty() ){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(receiptSettleInvoiceResponses);
    }

}