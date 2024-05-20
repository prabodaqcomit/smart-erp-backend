package lk.quantacom.smarterpbackend.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lk.quantacom.smarterpbackend.ExcelReport.*;
import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.service.ReportService;
import lk.quantacom.smarterpbackend.service.StockService;
import lk.quantacom.smarterpbackend.service.TblinvdtlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("Reports/New")
@RestController
@CrossOrigin
public class ReportController {

    @Autowired
    TblinvdtlService tblinvdtlService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private StockService stockService;

    @GetMapping("CustomerDebit/{fromDate}/{toDate}/{cusId}")
    public void exportIntoExcelFile(HttpServletResponse response, @PathVariable("fromDate") @NotBlank String fromDate,
                                    @PathVariable("toDate") @NotBlank String toDate,
                                    @PathVariable("cusId") @NotBlank Long cusId) throws IOException {

        List<ExcelCustomerOutstandingResponse> list = reportService.ExcelOutstanding(fromDate, toDate, cusId);
        if (list != null && !list.isEmpty()) {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=Customer_Outstanding_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);

            ExcelGenerator generator = new ExcelGenerator(list);
            generator.generateExcelFile(response);
        }else {
            response.sendError(404, "No Data");
        }

    }


    @GetMapping("SupplierCredit/{fromDate}/{toDate}/{supplierId}")
    public void supplierExcelFile(HttpServletResponse response, @PathVariable("fromDate") @NotBlank String fromDate,
                                  @PathVariable("toDate") @NotBlank String toDate,
                                  @PathVariable("supplierId") @NotBlank Long supplierId) throws IOException {


        List<getExcelCreditorOutstandingResponse> list = reportService.ExcelSupOutstanding(fromDate, toDate, supplierId);
        if (list != null && !list.isEmpty()) {

            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=Supplier_Outstanding_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);

            SupplierExcelGenerator generator = new SupplierExcelGenerator(list);
            generator.generateExcelFile(response);
        }else {
            response.sendError(404, "No Data");
        }
    }


    @GetMapping("Print/ItemVoid/{fromDate}/{toDate}/{types}")
    public ResponseEntity<ByteArrayResource> printItemVoidReport(@PathVariable("fromDate") @NotBlank String fromDate,
                                                                 @PathVariable("toDate") @NotBlank String toDate,
                                                                 @PathVariable("types") @NotBlank String types) {
        File printGenerate = null;
        HttpHeaders headers = null;
        ByteArrayResource resource = null;
        try {
            printGenerate = reportService.printItemVoidReport(fromDate, toDate, types);
            if (printGenerate == null) {
                return ResponseEntity.notFound().build();
            }
            Path path = Paths.get(printGenerate.getPath());
            resource = new ByteArrayResource(Files.readAllBytes(path));
            headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Content-Disposition", "attachment; filename=" + printGenerate.getName());
        } catch (Exception E) {
            E.printStackTrace();
        }
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(printGenerate.length())
                .contentType(MediaType
                        .parseMediaType("application/" + types))
                .body(resource);
    }


    @GetMapping("Print/CustomerReturn/{fromDate}/{toDate}/{cusId}/{types}")
    public ResponseEntity<ByteArrayResource> printCustomerReturnReport(@PathVariable("fromDate") @NotBlank String fromDate,
                                                                       @PathVariable("toDate") @NotBlank String toDate,
                                                                       @PathVariable("cusId") @NotBlank Integer cusId,
                                                                       @PathVariable("types") @NotBlank String types) {
        File printGenerate = null;
        HttpHeaders headers = null;
        ByteArrayResource resource = null;
        try {
            printGenerate = reportService.printCustomerReturnReport(fromDate, toDate, cusId, types);
            if (printGenerate == null) {
                return ResponseEntity.notFound().build();
            }
            Path path = Paths.get(printGenerate.getPath());
            resource = new ByteArrayResource(Files.readAllBytes(path));
            headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Content-Disposition", "attachment; filename=" + printGenerate.getName());
        } catch (Exception E) {
            E.printStackTrace();
        }
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(printGenerate.length())
                .contentType(MediaType
                        .parseMediaType("application/" + types))
                .body(resource);
    }


    @GetMapping("Print/WholeSaleReport/{invno}/{types}")
    public ResponseEntity<ByteArrayResource> WholeSaleReport(@PathVariable("invno") @NotBlank Integer invno,
                                                             @PathVariable("types") @NotBlank String types) {
        File printGenerate = null;
        HttpHeaders headers = null;
        ByteArrayResource resource = null;
        try {
            printGenerate = reportService.printWholeSaleReport(invno, types);
            if (printGenerate == null) {
                return ResponseEntity.notFound().build();
            }
            Path path = Paths.get(printGenerate.getPath());
            resource = new ByteArrayResource(Files.readAllBytes(path));
            headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Content-Disposition", "attachment; filename=" + printGenerate.getName());
        } catch (Exception E) {
            E.printStackTrace();
        }
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(printGenerate.length())
                .contentType(MediaType
                        .parseMediaType("application/" + types))
                .body(resource);
    }


    @PostMapping("Print/DailyProfitByItemReport")
    public ResponseEntity<ByteArrayResource> printDailyProfitByItemReport(@Valid @RequestBody ProfitabilityByItemRequest request) {
        File printGenerate = null;
        HttpHeaders headers = null;
        ByteArrayResource resource = null;
        try {
            printGenerate = reportService.printDailyProfitByItemReport(request);
            if (printGenerate == null) {
                return ResponseEntity.notFound().build();
            }
            Path path = Paths.get(printGenerate.getPath());
            resource = new ByteArrayResource(Files.readAllBytes(path));
            headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Content-Disposition", "attachment; filename=" + printGenerate.getName());
        } catch (Exception E) {
            E.printStackTrace();
        }
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(printGenerate.length())
                .contentType(MediaType
                        .parseMediaType("application/" + request.getTypes()))
                .body(resource);
    }


    @GetMapping("Print/BinCardSizeWiseReport/{fromDate}/{toDate}/{branchId}/{itemCode}/{types}")
    public ResponseEntity<ByteArrayResource> printBinCardSizeWiseReport(@PathVariable("fromDate") @NotBlank String fromDate,
                                                                        @PathVariable("toDate") @NotBlank String toDate,
                                                                        @PathVariable("branchId") @NotBlank Integer branchId,
                                                                        @PathVariable("itemCode") @NotBlank String itemCode,
                                                                        @PathVariable("types") @NotBlank String types) {
        File printGenerate = null;
        HttpHeaders headers = null;
        ByteArrayResource resource = null;
        try {
            printGenerate = reportService.printBinCardSizeWiseReport(fromDate, toDate, branchId, itemCode, types);
            if (printGenerate == null) {
                return ResponseEntity.notFound().build();
            }
            Path path = Paths.get(printGenerate.getPath());
            resource = new ByteArrayResource(Files.readAllBytes(path));
            headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Content-Disposition", "attachment; filename=" + printGenerate.getName());
        } catch (Exception E) {
            E.printStackTrace();
        }
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(printGenerate.length())
                .contentType(MediaType
                        .parseMediaType("application/" + types))
                .body(resource);
    }


    @GetMapping("StockByLocation/{itemCode}/{catId}")
    public void StockByLocation(HttpServletResponse response, @PathVariable("itemCode") @NotBlank String itemCode,
                                @PathVariable("catId") @NotBlank Long catId) throws IOException {


        List<stockLocationResponse> list = stockService.getByStockLocation();
        List<LocationStockDetailsResponse> list1 = reportService.getLocationStockDetails(itemCode, catId);
        List<Double> doubleList = stockService.getSumQtyOfLoc(itemCode, catId);
        Double aDouble = stockService.getTotStock(itemCode, catId);

        if (list != null && !list.isEmpty() && list1 != null && !list1.isEmpty()) {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=STOCK_BY_LOCATION_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);

            AllLocationStockExcelGenerator generator = new AllLocationStockExcelGenerator(list, list1, doubleList, aDouble);
            generator.generateExcelFile(response);
        }else {
            response.sendError(404, "No Data");
        }


    }

//    @GetMapping("getLocationStockDetails/{itemCode}/{catId}")
//    public ResponseEntity<List<LocationStockDetailsResponse>> getLocationStockDetails(@PathVariable("itemCode") @NotBlank String itemCode,
//                                                                                      @PathVariable("catId") @NotBlank Long catId){
//        List<LocationStockDetailsResponse> getall = reportService.getLocationStockDetails(itemCode,catId);
//        return ResponseEntity.ok(getall);
//    }

    @GetMapping("ageReport/{itemCode}/{catId}/{branchId}")
    public void exportIntoExcelFile(HttpServletResponse response, @PathVariable("itemCode") @NotBlank String itemCode,
                                    @PathVariable("catId") @NotBlank Long catId,@PathVariable("branchId") @NotBlank Long branchId) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=AGE_REPORT_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<ageReportResponse> list = reportService.ageReport(itemCode, catId, branchId);
        if (list != null && !list.isEmpty()) {
            AgeBalanceExcelGenerator generator = new AgeBalanceExcelGenerator(list);
            generator.generateExcelFile(response);
        }else {
            response.sendError(404, "No Data");
        }
    }

    @PostMapping("getMonthlyInvDtl")
    public void exportIntoExcelFile(HttpServletResponse response, @Valid @RequestBody getMontlyInvRequest request) throws IOException {

        List<getMonthlyDtlsByAllResponse> list = tblinvdtlService.getMonthlyInvDtls(request);
        if (list != null && !list.isEmpty()) {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=INVOICE_DETAILS_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);

            MonthlyInvDtlExcelGenerator generator = new MonthlyInvDtlExcelGenerator(list);
            generator.generateExcelFile(response);
        }else {
            response.sendError(404, "No Data");
        }
    }

    @PostMapping("getMonthlyInvDtlReturn")
    public void exportIntoExcelFile(HttpServletResponse response, @Valid @RequestBody getMontlyInvReturnRequest request) throws IOException {

        List<getMonthlyDtlsByAllResponse> list = tblinvdtlService.getMonthlyInvDtlsReturn(request);
        if (list != null && !list.isEmpty()) {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=INVOICE_RETURN_DETAILS_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);

            MonthlyInvDtlReturnExcelGenerator generator = new MonthlyInvDtlReturnExcelGenerator(list);
            generator.generateExcelFile(response);
        }else {
            response.sendError(404, "No Data");
        }

    }

    @PostMapping("BinCardSizeWiseReport")
    public void exportIntoExcelFile(HttpServletResponse response, @Valid @RequestBody BinCardItemStockRequest request) throws IOException {

        List<BinCardStockResponse> list = stockService.binCardStock(request);
        if (list != null && !list.isEmpty()) {

            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=BIN_CARD_SIZE" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);

            BinCardSizeWiseExcelGenerator generator = new BinCardSizeWiseExcelGenerator(list);
            generator.generateExcelFile(response);
        }else {
            response.sendError(404, "No Data");
        }
    }

    @PostMapping("ProfitByItem")
    public void exportIntoExcelFile(HttpServletResponse response, @Valid @RequestBody ProfitabilityByItemExcelRequest request) throws IOException {


        List<ProfitByItemResponse> list = reportService.ProfitByItem(request);
        if (list != null && !list.isEmpty()) {

            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=PROFITABILITY_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);

            ProfitByItemExcelGenerator generator = new ProfitByItemExcelGenerator(list);
            generator.generateExcelFile(response);
        }else {
            response.sendError(404, "No Data");
             
        }
    }


    @GetMapping("Print/WholeSaleReport/{invno}/{location}/{types}")
    public ResponseEntity<ByteArrayResource> printWholeSaleReport(@PathVariable("invno") @NotBlank Integer invno,
                                                                 @PathVariable("location") @NotBlank Integer location,
                                                                 @PathVariable("types") @NotBlank String types) {
        File printGenerate = null;
        HttpHeaders headers = null;
        ByteArrayResource resource = null;
        try {
            printGenerate = reportService.printWholeSaleOldReport(invno, location, types);
            if (printGenerate == null) {
                return ResponseEntity.notFound().build();
            }
            Path path = Paths.get(printGenerate.getPath());
            resource = new ByteArrayResource(Files.readAllBytes(path));
            headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Content-Disposition", "attachment; filename=" + printGenerate.getName());
        } catch (Exception E) {
            E.printStackTrace();
        }
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(printGenerate.length())
                .contentType(MediaType
                        .parseMediaType("application/" + "pdf"))
                .body(resource);
    }




}