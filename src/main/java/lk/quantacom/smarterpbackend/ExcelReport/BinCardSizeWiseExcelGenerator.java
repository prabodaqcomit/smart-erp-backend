package lk.quantacom.smarterpbackend.ExcelReport;

import lk.quantacom.smarterpbackend.dto.response.BinCardSizeStockResponse;
import lk.quantacom.smarterpbackend.dto.response.BinCardStockResponse;
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

public class BinCardSizeWiseExcelGenerator {

    private static final DecimalFormat df = new DecimalFormat("#,##0.00");

    private List<BinCardStockResponse> responseList;

    private XSSFWorkbook workbook;

    private XSSFSheet sheet;

    public BinCardSizeWiseExcelGenerator(List<BinCardStockResponse> responseList) {
        this.responseList = responseList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeader() {

        sheet = workbook.createSheet(" BIN CARD SIZE WISE REPORT ");

        writeHeaderTopic();

        Row row = sheet.createRow(10);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        style.setFillPattern(FillPatternType.FINE_DOTS);


        createCell(row, 0, "LOT NO", style);
        createCell(row, 1, "DESCRIPTION", style);
        createCell(row, 2, "S", style);
        createCell(row, 3, "M", style);
        createCell(row, 4, "L", style);
        createCell(row, 5, "XL", style);
        createCell(row, 6, "2XL", style);
        createCell(row, 7, "3XL", style);
        createCell(row, 8, "4XL", style);
        createCell(row, 9, "5XL", style);
        createCell(row, 10,"6XL", style);
        createCell(row, 11,"TOTAL", style);

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

        int col = sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 6));
        Row row2 = sheet.createRow(5);
        createCell(row2, 0, " BIN CARD SIZE WISE REPORT ", style2);

        CellStyle style3 = workbook.createCellStyle();
        XSSFFont font3 = workbook.createFont();
        font3.setFontHeight(10);
        style3.setFont(font3);

//        Row row3 = sheet.createRow(9);
//        createCell(row3, 0, "Date From :", style3);
//        createCell(row3, 1, responseList.get(0).getFromDate(), style3);
//        createCell(row3, 2, "Date To :", style3);
//        createCell(row3, 3, responseList.get(0).getToDate(), style3);

        Row row4 = sheet.createRow(8);
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

        int rowCount = 11;

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

        CellStyle style4 = workbook.createCellStyle();
        XSSFFont font4 = workbook.createFont();
        font4.setBold(true);
        font4.setFontHeight(14);
        font4.setColor(IndexedColors.DARK_RED.getIndex());
        style4.setFont(font4);

        CellStyle style3 = workbook.createCellStyle();
        XSSFFont font3 = workbook.createFont();
        font3.setFontHeight(10);
        style3.setFont(font3);
        style3.setAlignment(HorizontalAlignment.RIGHT);


        Double totsum=0.00;

        for (BinCardStockResponse record : responseList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, 0, record.getItemCode(), style);
            createCell(row, 1, record.getItemName(), style);
//            createCell(row, 2, record.getItem_code(), style);
            Double totQty=0.00;
            for (BinCardSizeStockResponse response:record.getResponseList()){


                if (response.getSizeId()==1){
                    createCell(row, 2, response.getQty(), style);
                }else if (response.getSizeId()==2){
                    createCell(row, 3, response.getQty(), style);
                }else if (response.getSizeId()==3){
                    createCell(row, 4, response.getQty(), style);
                }else if (response.getSizeId()==4){
                    createCell(row, 5, response.getQty(), style);
                }else if (response.getSizeId()==5){
                    createCell(row, 6, response.getQty(), style);
                }else if (response.getSizeId()==6){
                    createCell(row, 7, response.getQty(), style);
                }else if (response.getSizeId()==7){
                    createCell(row, 8, response.getQty(), style);
                }else if (response.getSizeId()==8){
                    createCell(row, 9, response.getQty(), style);
                }else if (response.getSizeId()==9){
                    createCell(row, 10, response.getQty(), style);
                }
                totQty = totQty+ response.getQty();
            }

            createCell(row, 11, totQty, style);
//            createCell(row, 13, df.format(record.getFld_Price()), style);
//            createCell(row, 14, df.format(record.getFld_CostPrice()), style);
//            createCell(row, 15, df.format(record.getCostvalue()), style);

//            totQty=totQty+ record.getTot();
//            totVal=totVal+ record.getCostvalue();
        }

//        Row row = sheet.createRow(rowCount++);
//
//        createCell(row, 12, totQty, style4);
//        createCell(row, 15, df.format(totVal), style4);
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