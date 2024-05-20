package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.ProfitabilityByItemExcelRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfitabilityByItemRequest;
import lk.quantacom.smarterpbackend.dto.response.*;

import java.io.File;
import java.util.List;

public interface ReportService {

    List<ExcelCustomerOutstandingResponse> ExcelOutstanding(String fromDate, String toDate, Long cusId);

    File printItemVoidReport(String fromDate,String toDate, String type);

    List<getExcelCreditorOutstandingResponse> ExcelSupOutstanding(String fromDate, String toDate, Long supId);

    File printCustomerReturnReport(String fromDate,String toDate,Integer cusId, String type);

    File printWholeSaleReport(Integer invno,String type);

    File printDailyProfitByItemReport(ProfitabilityByItemRequest request);

    File printBinCardSizeWiseReport(String fromDate, String toDate,Integer branchId, String itemCode, String type);

    List<LocationStockDetailsResponse> getLocationStockDetails(String itemCode,Long catId);

    List<ageReportResponse> ageReport(String itemCode, Long catId,Long branchId);

    List<ProfitByItemResponse> ProfitByItem(ProfitabilityByItemExcelRequest request);

    File printWholeSaleOldReport(Integer invno,Integer location,String type);



}