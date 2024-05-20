package lk.quantacom.smarterpbackend.ExcelReport;

import lk.quantacom.smarterpbackend.dto.response.ExcelSupplierOutstandingResponse;
import lk.quantacom.smarterpbackend.dto.response.ageReportResponse;
import lk.quantacom.smarterpbackend.dto.response.getExcelCreditorOutstandingResponse;
import lk.quantacom.smarterpbackend.utils.Settings;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

public class AgeBalanceExcelGenerator {
    private static final DecimalFormat df = new DecimalFormat("#,##0.00");

    private List<ageReportResponse> responseList;

    private XSSFWorkbook workbook;

    private XSSFSheet sheet;

    public AgeBalanceExcelGenerator(List<ageReportResponse> responseList) {
        this.responseList = responseList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeader() {

        sheet = workbook.createSheet("StockAgeReport");

        writeHeaderTopic();

        Row row = sheet.createRow(15);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        style.setFillPattern(FillPatternType.FINE_DOTS);

        createCell(row, 0, "ITEM CODE", style);
        createCell(row, 1, "DESCRIPTION", style);
        createCell(row, 2, "BATCH NO", style);
//        createCell(row, 3, "SIZE", style);
//        createCell(row, 4, "FIT", style);
        createCell(row, 3, "OB DATE", style);
        createCell(row, 4, "OB QUANTITY", style);
        createCell(row, 5, "G.R.N DATE", style);
        createCell(row, 6, "G.R.N QUANTITY", style);
        createCell(row, 7, "BALANCE QUANTITY", style);
        createCell(row, 8, "AS AT DATE", style);
        createCell(row, 9, "AGE", style);
//        createCell(row, 12, "G.R.N AGE", style);
        createCell(row, 10, "M.R.P", style);

    }

    private void writeHeaderTopic() {

        String topic = Settings.readSettings("COMPANY_NAME");
        String address = Settings.readSettings("COMPANY_ADDRESS_ONE");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(18);
        font.setColor(IndexedColors.BLACK.getIndex());
        style.setFont(font);

        createCell(row, 0, topic, style);

        CellStyle style1 = workbook.createCellStyle();
        XSSFFont font1 = workbook.createFont();
        font1.setBold(true);
        font1.setFontHeight(12);
        font1.setColor(IndexedColors.BLACK.getIndex());
        style1.setFont(font1);

        int col1 = sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 5));
        Row row1 = sheet.createRow(2);
        createCell(row1, col1++, "Address", style1);
        createCell(row1, col1++, address, style1);

        CellStyle style2 = workbook.createCellStyle();
        XSSFFont font2 = workbook.createFont();
        font2.setBold(true);
        font2.setFontHeight(20);
        font2.setColor(IndexedColors.RED.getIndex());
        style2.setFont(font2);
        style2.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style2.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());

        int col = sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 4));
        Row row2 = sheet.createRow(5);
        createCell(row2, 0, "STOCK AGE REPORT", style2);

        CellStyle style3 = workbook.createCellStyle();
        XSSFFont font3 = workbook.createFont();
        font3.setFontHeight(10);
        style3.setFont(font3);

//        Row row3 = sheet.createRow(9);
//        createCell(row3, 0, "Date From :", style3);
//        createCell(row3, 1, responseList.get(0).getFromDate(), style3);
//        createCell(row3, 2, "Date To :", style3);
//        createCell(row3, 3, responseList.get(0).getToDate(), style3);

        Row row4 = sheet.createRow(11);
        createCell(row4, 0, "Print Date :", style3);
        createCell(row4, 1, LocalDate.now().toString(), style3);
        createCell(row4, 2, "Print Time :", style3);
        createCell(row4, 3, new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()), style3);

//        Row row5 = sheet.createRow(13);
//        createCell(row5, 0, "Location From :", style3);
//        createCell(row5, 1, "", style3);
//        createCell(row5, 2, "Location To :", style3);
//        createCell(row5, 3, "", style3);

    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else if (valueOfCell instanceof Double) {
            cell.setCellValue((Double) valueOfCell);
        } else if (valueOfCell instanceof Boolean) {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }

    private void write() {

        int rowCount = 16;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(10);
        style.setFont(font);

        CellStyle style1 = workbook.createCellStyle();
        XSSFFont font1 = workbook.createFont();
        font1.setBold(true);
        font1.setFontHeight(12);
        font1.setColor(IndexedColors.LIGHT_BLUE.getIndex());
        style1.setFont(font1);

        CellStyle style3 = workbook.createCellStyle();
        XSSFFont font3 = workbook.createFont();
        font3.setFontHeight(10);
        style3.setFont(font3);
        style3.setAlignment(HorizontalAlignment.RIGHT);

        double tot =0.00;

        for (ageReportResponse record : responseList) {
            if (record != null) {

                Row row = sheet.createRow(rowCount++);
                int columnCount = 0;
                createCell(row, columnCount++, record.getItem_id(), style);
                createCell(row, columnCount++, record.getItem_name(), style);
                createCell(row, columnCount++, record.getBatch_no(), style);
//                createCell(row, columnCount++, record.getSize(), style);
//                createCell(row, columnCount++, record.getFit(), style);
                createCell(row, columnCount++, record.getObdate(), style);
                createCell(row, columnCount++, record.getOb_qty(), style);
                createCell(row, columnCount++, record.getGrndate(), style);
                createCell(row, columnCount++, record.getQty(), style3);
                createCell(row, columnCount++, record.getTotal_qty(), style3);
                createCell(row, columnCount++, record.getToday(), style3);
                createCell(row,columnCount++,record.getAge(),style3);

//                if(record.getObage()!=0 || record.getGrnage()!=0){
//                    if ((record.getObage()>record.getGrnage()) && (record.getGrnage()==0)){
//                        createCell(row, columnCount++, record.getObage(), style3);
//                    }else if ((record.getObage()<record.getGrnage()) && (record.getObage()==0)){
//                        createCell(row, columnCount++, record.getGrnage(), style3);
//                    }else if  ((record.getObage()>record.getGrnage()) && (record.getGrnage()!=0)){
//                        createCell(row, columnCount++, record.getGrnage(), style3);
//                    }else if ((record.getObage()<record.getGrnage()) && (record.getObage()!=0)){
//                        createCell(row, columnCount++, record.getObage(), style3);
//                    }
//                }else {
//                    createCell(row, columnCount++, "0", style3);
//                }

//                createCell(row, columnCount++, record.getObage(), style3);
//                createCell(row, columnCount++, record.getGrnage(), style3);
                createCell(row, columnCount++, record.getUnit_price(), style3);

                tot = tot + record.getTotal_qty();
            }
        }
        Row row = sheet.createRow(rowCount++);
        int columnCount = 6;
        createCell(row, columnCount++, "Total quantity = ", style1);
        createCell(row, columnCount++, tot, style1);
    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}