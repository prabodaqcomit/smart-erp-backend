package lk.quantacom.smarterpbackend.ExcelReport;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import lk.quantacom.smarterpbackend.dto.response.ExcelCustomerOutstandingResponse;
import lk.quantacom.smarterpbackend.dto.response.ExcelOutstandingResponse;

import lk.quantacom.smarterpbackend.utils.Settings;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelGenerator {

    private static final DecimalFormat df = new DecimalFormat("#,##0.00");

    private List<ExcelCustomerOutstandingResponse> responseList;

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelGenerator(List<ExcelCustomerOutstandingResponse> responseList) {
        this.responseList = responseList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeader() {

        sheet = workbook.createSheet("OutStanding");

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

        createCell(row, 0, "INVOICE NO", style);
        createCell(row, 1, "INVOICE DATE", style);
        createCell(row, 2, "DAYS", style);
        createCell(row, 3, "INVOICE AMOUNT", style);
        createCell(row, 4, "AMOUNT PAID", style);
        createCell(row, 5, "AMOUNT PAYABLE", style);


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

        int col1 = sheet.addMergedRegion(new CellRangeAddress(2,2,1,5));
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

        int col = sheet.addMergedRegion(new CellRangeAddress(5,5,0,4));
        Row row2 = sheet.createRow(5);
        createCell(row2, 0, "Sales Debit Report", style2);

        CellStyle style3 = workbook.createCellStyle();
        XSSFFont font3 = workbook.createFont();
        font3.setFontHeight(10);
        style3.setFont(font3);

        Row row3 = sheet.createRow(9);
        createCell(row3, 0, "Date From :", style3);
        createCell(row3, 1, responseList.get(0).getFromDate(), style3);
        createCell(row3, 2, "Date To :", style3);
        createCell(row3, 3, responseList.get(0).getToDate(), style3);

        Row row4 = sheet.createRow(11);
        createCell(row4, 0, "Print Date :", style3);
        createCell(row4, 1, LocalDate.now().toString(), style3);
        createCell(row4, 2, "Print Time :", style3);
        createCell(row4, 3, new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) , style3);

        Row row5 = sheet.createRow(13);
        createCell(row5, 0, "Location From :", style3);
        createCell(row5, 1, "", style3);
        createCell(row5, 2, "Location To :", style3);
        createCell(row5, 3, "", style3);

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
        } else {
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
        style.setAlignment(HorizontalAlignment.RIGHT);

        CellStyle style2 = workbook.createCellStyle();
        XSSFFont font2 = workbook.createFont();
        font2.setFontHeight(10);
        style2.setFont(font2);

        for (ExcelCustomerOutstandingResponse record : responseList) {
            if (record.list != null) {

                CellStyle style1 = workbook.createCellStyle();
                XSSFFont font1 = workbook.createFont();
                font1.setBold(true);
                font1.setFontHeight(12);
                font1.setColor(IndexedColors.LIGHT_BLUE.getIndex());
                style1.setFont(font1);

                sheet.createRow(rowCount++);

                Row row1 = sheet.createRow(rowCount++);
                int columnCount1 = 0;
                createCell(row1, columnCount1++, record.getCusCode()+" - "+record.getCusName(), style1);

                for (ExcelOutstandingResponse response : record.getList()) {
                    Row row = sheet.createRow(rowCount++);
                    int columnCount = 0;
                    createCell(row, columnCount++, response.getFld_InvNo(), style2);
                    createCell(row, columnCount++, response.getFld_Date(), style);
                    createCell(row, columnCount++, response.getDays(), style);
                    createCell(row, columnCount++, df.format(response.getInvoice_amount().doubleValue()), style);
                    createCell(row, columnCount++, df.format(response.getAmount_paid().doubleValue()), style);
                    createCell(row, columnCount++, df.format(response.getAmount_payable().doubleValue()), style);
                }
            }
        }
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