package lk.quantacom.smarterpbackend.utils;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;


public class SQLtoEXCEL {

//    public static void main(String[] args) {
//
//        try {
//
//            File f = new File("C:\\Users\\dimut\\Documents\\X\\items.xlsx");
//            Connection co = JDBC.con();
//
//            String ss = "SELECT ID,item_name_uni as item_name_sin,B.barcode FROM dsoft_pos_pro_max.items I left join barcode B on I.ID=B.item_id";
//
//            ExportFile(co, ss, f);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    public static void ExportFile(Connection co, String sql, File output_file) {
        System.out.println(sql);

        Statement st = null;

        try {
            output_file.createNewFile();

            st = co.createStatement();

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("SQL");
            XSSFRow rowhead = sheet.createRow(0);

            ResultSet rs1 = st.executeQuery(sql);
            ResultSetMetaData sqcol = rs1.getMetaData();


            for (int i = 0; i < sqcol.getColumnCount(); i++) {
                rowhead.createCell(i).setCellValue(sqcol.getColumnLabel(i + 1));
            }

            int x = 0;
            ResultSet rsq = st.executeQuery(sql);
            while (rsq.next()) {
                XSSFRow row = sheet.createRow(x + 1);
                x = x + 1;
                for (int i = 0; i < sqcol.getColumnCount(); i++) {
                    String dataType = sqcol.getColumnClassName(i + 1);
                    if (dataType.equals("java.lang.Double")) {
                        if ((rsq.getString(sqcol.getColumnLabel(i + 1)) == null)) {
                            row.createCell(i).setCellValue(new String());
                        } else {
                            row.createCell(i).setCellValue(rsq.getDouble(sqcol.getColumnLabel(i + 1)));
                        }
                    } else {
                            row.createCell(i).setCellValue(rsq.getString(sqcol.getColumnLabel(i + 1)));

                    }

                }
            }

            for (int i = 0; i < sqcol.getColumnCount(); i++) {
                sheet.autoSizeColumn(i);
            }
            rsq.close();

            if (x == 0) {
                //Notification.show("No results !");

            } else {
                //	Notification.show("Downloading Excel file..... !");
                FileOutputStream fileOut = new FileOutputStream(output_file);
                workbook.write(fileOut);
                fileOut.close();

            }

        } catch (Exception e) {
            e.printStackTrace();
            //Notification.show("Error", e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
            try {
                co.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } finally {
            try {

                if (st != null) {
                    st.close();
                }
                if (co != null) {
                    co.close();
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

}
