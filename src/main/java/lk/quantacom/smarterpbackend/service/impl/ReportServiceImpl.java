package lk.quantacom.smarterpbackend.service.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


import lk.quantacom.smarterpbackend.dto.request.ProfitabilityByItemExcelRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfitabilityByItemRequest;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.ReportService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.utils.JDBC;
import lk.quantacom.smarterpbackend.utils.SQLtoEXCEL;
import lk.quantacom.smarterpbackend.utils.Settings;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private WholeSaleInvDtlRepository wholeSaleInvDtlRepository;

    @Autowired
    private WholeSaleInvHedRepository wholeSaleInvHedRepository;

    @Autowired
    private TblinvdtlRepository tblinvdtlRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ItemMasterRepository itemMasterRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private FitRepository fitRepository;

    @Override
    public List<ExcelCustomerOutstandingResponse> ExcelOutstanding(String fromDate, String toDate, Long cusId) {

        List<ExcelCustomerOutstandingResponse> resp = new ArrayList<>();

//        List<Customer> customerList = customerRepository.findByIsDeleted(Deleted.NO);

        List<Customer> customerList = new ArrayList<>();
        if (cusId == 0) {
            customerList = customerRepository.findByIsDeleted(Deleted.NO);
        } else {
            Customer cus = customerRepository.findById(cusId).orElse(null);
            if (cus != null) {
                customerList.add(cus);
            }
        }


        for (Customer customer : customerList) {

            ExcelCustomerOutstandingResponse typeResponse1 = new ExcelCustomerOutstandingResponse();
            typeResponse1.setCusName(customer.getName());
            typeResponse1.setCusCode(customer.getId().toString());
            typeResponse1.setFromDate(fromDate);
            typeResponse1.setToDate(toDate);

            List<ExcelOutstandingResponse> resp1 = new ArrayList<>();

            String sql = " select fld_InvNo, Date(fld_Date) as fld_Date, datediff(now(),fld_Date) as days,fld_NetAmount as invoice_amount, " +
                    " (fld_Cash+fld_CreditCard+fld_Coupon+fld_Cheque) as amount_paid,(fld_NetAmount-(fld_Cash+fld_CreditCard+fld_Coupon+fld_Cheque)) " +
                    " as amount_payable from tblinvhed where fld_Cancel = 0 and fld_NetAmount > (fld_Cash+fld_CreditCard+fld_Coupon+fld_Cheque)  " +
                    " and fld_invNo in (select fld_InvNo from tblpaydtl where fld_PayTypeCode=" + customer.getId() + "" +
                    " group by fld_InvNo) and fld_Date between '" + fromDate + "' and '" + toDate + "'";

            Connection co = null;
            try {
                co = JDBC.con();
                Statement st = co.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {

                    ExcelOutstandingResponse typeResponse = new ExcelOutstandingResponse();

                    typeResponse.setFld_InvNo(rs.getString("fld_InvNo"));
                    typeResponse.setFld_Date(rs.getString("fld_Date"));
                    typeResponse.setDays(rs.getInt("days"));
                    typeResponse.setInvoice_amount(rs.getDouble("invoice_amount"));
                    typeResponse.setAmount_paid(rs.getDouble("amount_paid"));
                    typeResponse.setAmount_payable(rs.getDouble("amount_payable"));

                    resp1.add(typeResponse);
                    typeResponse1.setList(resp1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (co != null) {
                        co.close();
                    }
                } catch (Exception ex) {
                }
            }
            resp.add(typeResponse1);
        }

        return resp;
    }

    @Override
    public File printItemVoidReport(String fromDate, String toDate, String type) {

        File out = null;
        Connection co = null;
        try {

            String topic = Settings.readSettings("COMPANY_NAME");
            String address = Settings.readSettings("COMPANY_ADDRESS_ONE");
            String telNo = Settings.readSettings("MOBILE_NO");

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String uu = auth.getName();


            File file = new File("JRXML/report/item_void_report.jrxml");
            Map<String, Object> map = new HashMap<>();
            map.put("fromDate", fromDate.toString());
            map.put("toDate", toDate.toString());
            map.put("date", new Date().toString());
            map.put("companyName", topic);
            map.put("Address", address);
            map.put("telNo", telNo);
            map.put("user", uu);
            map.put("nowDate", LocalDate.now().toString());
            map.put("time", new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));


            co = JDBC.con();
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
            JasperPrint print = JasperFillManager.fillReport(jasperReport, map, co);

            String filepath = "";
            if (type.equals("pdf")) {
                filepath = "TMP/" + System.currentTimeMillis() + ".pdf";
                JasperExportManager.exportReportToPdfFile(print, filepath);
            } else if (type.equals("docx")) {
                filepath = "TMP/" + System.currentTimeMillis() + ".docx";
                JasperExportManager.exportReportToXml(print);
                JRDocxExporter exporter = new JRDocxExporter();
                exporter.setExporterInput(new SimpleExporterInput(print));
                File exportReportFile = new File(filepath);
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportReportFile));
                exporter.exportReport();
            }


            out = new File(filepath);


        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (co != null) {
                try {
                    co.close();
                } catch (Exception e) {

                }
            }
        }
        return out;
    }

    @Override
    public List<getExcelCreditorOutstandingResponse> ExcelSupOutstanding(String fromDate, String toDate, Long supId) {

        List<getExcelCreditorOutstandingResponse> resp = new ArrayList<>();

        List<Supplier> supplierList = new ArrayList<>();
        if (supId == 0) {
            supplierList = supplierRepository.findByIsDeleted(Deleted.NO);
        } else {
            Supplier sup = supplierRepository.findById(supId).orElse(null);
            if (sup != null) {
                supplierList.add(sup);
            }
        }

        for (Supplier supplier : supplierList) {

            getExcelCreditorOutstandingResponse typeResponse1 = new getExcelCreditorOutstandingResponse();
            typeResponse1.setSupName(supplier.getName());
            typeResponse1.setSupCode(supplier.getId().toString());
            typeResponse1.setFromDate(fromDate);
            typeResponse1.setToDate(toDate);

            List<ExcelSupplierOutstandingResponse> resp1 = new ArrayList<>();

            String sql = " select g.id as grn_no,Date(grn_date) as grn_date,Date(sup_in_date) as sup_in_date,sup_in_no,g.branch_id,p.net_amount,p.due_amount " +
                    " FROM grn_header g inner join grn_payments p on g.id=p.grn_id " +
                    " where supplier_id=" + supplier.getId() + " and p.pay_mode='CREDIT' and due_amount > 0 and  " +
                    " date(g.grn_date) between '" + fromDate + "' and '" + toDate + "' ";

            Connection co = null;
            try {
                co = JDBC.con();
                Statement st = co.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {

                    ExcelSupplierOutstandingResponse typeResponse = new ExcelSupplierOutstandingResponse();

                    typeResponse.setGrn_no(rs.getInt("grn_no"));
                    typeResponse.setGrn_date(rs.getString("grn_date"));
                    typeResponse.setSup_in_date(rs.getString("sup_in_date"));
                    typeResponse.setSup_in_no(rs.getString("sup_in_no"));
                    typeResponse.setBranch_id(rs.getInt("branch_id"));
                    typeResponse.setNet_amount(rs.getDouble("net_amount"));
                    typeResponse.setDue_amount(rs.getDouble("due_amount"));

                    resp1.add(typeResponse);
                    typeResponse1.setList(resp1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (co != null) {
                        co.close();
                    }
                } catch (Exception ex) {
                }
            }
            resp.add(typeResponse1);
        }
        return resp;
    }

    @Override
    public File printCustomerReturnReport(String fromDate, String toDate, Integer cusId, String type) {

        File out = null;
        Connection co = null;
        try {

            String topic = Settings.readSettings("COMPANY_NAME");
            String address = Settings.readSettings("COMPANY_ADDRESS_ONE");

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String uu = auth.getName();

            File file = new File("JRXML/report/customerReturn.jrxml");

            Map<String, Object> map = new HashMap<>();
            map.put("dateFrom", fromDate);
            map.put("DateTo", toDate);
            map.put("companyName", topic);
            map.put("address", address);
            map.put("printUser", uu);
            map.put("printDate", LocalDate.now().toString());
            map.put("printTime", new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
            if (cusId == 0) {
                map.put("customerName", "<All>");
            } else {
                Customer customer = customerRepository.findById(cusId.longValue()).orElse(null);
                map.put("customerName", customer == null ? "" : customer.getName());
            }

            List<HashMap<String, Object>> catList = new ArrayList<>();

            String sql = "";

            if (cusId == 0) {
                sql = " select * from stock s join tblinvdtl t on s.item_code=t.fld_ItemCode inner join tblpaydtl p on t.fld_InvNo=p.fld_InvNo  " +
                        " and s.color_id=t.fld_IntColorId and s.size_id=t.fld_IntSizeId and s.fit_id=t.fld_IntFitId and s.branch_id=t.fld_Location  " +
                        " where t.fld_Qty<0  and t.fld_Date between '" + fromDate + "' and '" + toDate + "'  ";

            } else {

                sql = " select * from stock s join tblinvdtl t on s.item_code=t.fld_ItemCode inner join tblpaydtl p on t.fld_InvNo=p.fld_InvNo  " +
                        " and s.color_id=t.fld_IntColorId and s.size_id=t.fld_IntSizeId and s.fit_id=t.fld_IntFitId and s.branch_id=t.fld_Location  " +
                        " where t.fld_Qty<0 and p.fld_PayTypeCode=" + cusId + " and t.fld_Date between '" + fromDate + "' and '" + toDate + "'  ";

            }

            try {
                co = JDBC.con();
                Statement st = co.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    System.out.println("data avb -------------------");
                    HashMap<String, Object> detailRow = new HashMap<>();
                    detailRow.put("inv", rs.getString("t.fld_InvNo"));
                    detailRow.put("code", rs.getString("t.fld_ItemCode"));
                    detailRow.put("descrpton", rs.getString("t.fld_ItemDescription"));
                    Size size = sizeRepository.getById(rs.getLong("s.size_id"));
                    detailRow.put("size", size.getSizeDesc());
                    detailRow.put("rtnqty", rs.getDouble("t.fld_Qty"));
                    detailRow.put("stkqty", rs.getDouble("total_qty"));
                    detailRow.put("billDisc", rs.getDouble("t.fld_BillDisPer"));
                    detailRow.put("lineDisc", rs.getDouble("t.fld_LineDisPer"));
                    Double unitPrice = rs.getDouble("t.fld_Price");
                    Double quantity = rs.getDouble("t.fld_Qty");
                    double billDiscPer = rs.getDouble("t.fld_BillDisPer");
                    double lineDiscPer = rs.getDouble("t.fld_LineDisPer");
                    detailRow.put("gross", unitPrice * quantity);
                    detailRow.put("net", (unitPrice * quantity) - (((unitPrice * quantity) * billDiscPer) / 100) - (((unitPrice * quantity) * lineDiscPer) / 100));
                    detailRow.put("user", rs.getString("t.fld_CashierId"));
                    detailRow.put("date", rs.getDate("t.fld_Date").toLocalDate().toString());
                    detailRow.put("time", new SimpleDateFormat("HH:mm:ss").format(ConvertUtils.convertStrToDate(rs.getString("t.fld_Time"))));

                    catList.add(detailRow);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(catList);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, map, dataSource);

//            String filepath = "TMP/" + System.currentTimeMillis() + ".pdf";
//            JasperExportManager.exportReportToPdfFile(print, filepath);
//            out = new File(filepath);

            String filepath = "";
            if (type.equals("pdf")) {
                filepath = "TMP/" + System.currentTimeMillis() + ".pdf";
                JasperExportManager.exportReportToPdfFile(print, filepath);
                out = new File(filepath);
            } else if (type.equals("xlsx")) {
                try {
                    co = JDBC.con();
                    out = new File("TMP/" + System.currentTimeMillis() + ".xlsx");
                    SQLtoEXCEL.ExportFile(co, sql, out);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (co != null) {
                try {
                    co.close();
                } catch (Exception e) {

                }
            }
        }
        return out;
    }

    @Override
    public File printWholeSaleReport(Integer invno, String type) {

        File out = null;
        Connection co = null;
        try {

            File file = new File("JRXML/report/wholesaleinvoice.jrxml");
            Map<String, Object> map = new HashMap<>();
            map.put("invno", invno);
            WholeSaleInvHed invHed = wholeSaleInvHedRepository.findByInvnoAndIsDeleted(invno, Deleted.NO);

            if (invHed != null) {
//                map.put("customer", invHed.getCustomer());
//                map.put("location", invHed.getLocation());
//                map.put("inv_date", invHed.getInvDate());
//                map.put("total_qty", invHed.getTotalQty());
//                map.put("num_of_item", invHed.getNumOfItem());
//                map.put("gross_amount", invHed.getGrossAmount());
//                map.put("bill_dis_precentage", invHed.getBillDisPrecentage());
//                map.put("bill_dis_amount", invHed.getBillDisAmount());
//                map.put("net_amount", invHed.getNetAmount());
//                map.put("pay_type", invHed.getPayTypeInfo());
//                map.put("sales_by", invHed.getSalesBy());
                map.put("customer", invHed.getCustomer());
                Customer customer = customerRepository.getById(invHed.getCustomerId().longValue());
                map.put("location", customer.getAddress());
                map.put("inv_date", invHed.getInvDate());
                map.put("total_qty", invHed.getTotalQty());
                map.put("num_of_item", invHed.getNumOfItem());
                map.put("gross_amount", invHed.getGrossAmount());
                map.put("bill_dis_precentage", invHed.getBillDisPrecentage());
                map.put("bill_dis_amount", invHed.getBillDisAmount());
                map.put("net_amount", invHed.getNetAmount());
                map.put("pay_type", invHed.getPayTypeInfo());
                map.put("sales_by", invHed.getSalesBy());
            }

            List<HashMap<String, Object>> catList = new ArrayList<>();
            String sql = "select * from wholesaleinvdtl where invno=" + invno + " group by item_code,mrp";

            try {
                co = JDBC.con();
                Statement st = co.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {

                    HashMap<String, Object> detailRow = new HashMap<>();
                    detailRow.put("ITEM", rs.getString("item_code"));
                    detailRow.put("DESCRIPTION", rs.getString("item_name"));
                    detailRow.put("LOT", rs.getString("batch_no"));
                    detailRow.put("QTY", rs.getDouble("qty_byitem"));
                    detailRow.put("RATE", rs.getDouble("mrp"));
                    detailRow.put("dispre", rs.getDouble("dis_precentage"));
                    detailRow.put("COST", rs.getDouble("mrp")*(100-rs.getDouble("dis_precentage"))/100);
                    detailRow.put("VALUE", rs.getDouble("amount"));

                    List<WholeSaleInvDtl> saleInvDtl = wholeSaleInvDtlRepository.findByInvnoAndItemCodeAndMrpAndIsDeleted(invno, rs.getString("item_code"), rs.getDouble("mrp"), Deleted.NO);
                   if (!saleInvDtl.isEmpty()) {
                        for (WholeSaleInvDtl dtl : saleInvDtl) {
                            if (dtl.getSizeId() == 1) {
                                if (dtl.getSizeQty() == null) {
                                    detailRow.put("S", "");
                                } else {
                                    detailRow.put("S", String.valueOf(dtl.getSizeQty().intValue()));
                                }
                            } else if (dtl.getSizeId() == 2) {
                                detailRow.put("M", dtl.getSizeQty() != null ? String.valueOf(dtl.getSizeQty().intValue()) : "");
                            } else if (dtl.getSizeId() == 3) {
                                detailRow.put("L", dtl.getSizeQty() != null ? String.valueOf(dtl.getSizeQty().intValue()) : "");
                            } else if (dtl.getSizeId() == 4) {
                                detailRow.put("XL", dtl.getSizeQty() != null ? String.valueOf(dtl.getSizeQty().intValue()) : "");
                            } else if (dtl.getSizeId() == 5) {
                                detailRow.put("2XL", dtl.getSizeQty() != null ? String.valueOf(dtl.getSizeQty().intValue()) : "");
                            } else if (dtl.getSizeId() == 6) {
                                detailRow.put("3XL", dtl.getSizeQty() != null ? String.valueOf(dtl.getSizeQty().intValue()) : "");
                            } else if (dtl.getSizeId() == 7) {
                                detailRow.put("4XL", dtl.getSizeQty() != null ? String.valueOf(dtl.getSizeQty().intValue()) : "");
                            } else if (dtl.getSizeId() == 8) {
                                detailRow.put("5XL", dtl.getSizeQty() != null ? String.valueOf(dtl.getSizeQty().intValue()) : "");
                            } else if (dtl.getSizeId() == 9) {
                                detailRow.put("6XL", dtl.getSizeQty() != null ? String.valueOf(dtl.getSizeQty().intValue()) : "");
                            }
                        }
                    }
//                    HashMap<String, Object> detailRow = new HashMap<>();
//                    detailRow.put("code", rs.getString("item_code"));
//                    detailRow.put("itemname", rs.getString("item_name"));
//                    detailRow.put("batchcode", rs.getString("batch_no"));
//                    detailRow.put("qty", rs.getDouble("qty_byitem"));
//                    detailRow.put("mrp", rs.getDouble("mrp"));
//                    detailRow.put("dispre", rs.getDouble("dis_precentage"));
//                    detailRow.put("disamnt", rs.getDouble("dis_amount"));
//                    detailRow.put("amount", rs.getDouble("amount"));
//
//                    List<WholeSaleInvDtl> saleInvDtl = wholeSaleInvDtlRepository.findByInvnoAndItemCodeAndMrpAndIsDeleted(invno, rs.getString("item_code"), rs.getDouble("mrp"), Deleted.NO);
//                    if (!saleInvDtl.isEmpty()) {
//                        for (WholeSaleInvDtl dtl : saleInvDtl) {
//                            if (dtl.getSizeId() == 1) {
//                                if (dtl.getSizeQty() == null) {
//                                    detailRow.put("s", "");
//                                } else {
//                                    detailRow.put("s", String.valueOf(dtl.getSizeQty().intValue()));
//                                }
//                            } else if (dtl.getSizeId() == 2) {
//                                detailRow.put("m", dtl.getSizeQty() != null ? String.valueOf(dtl.getSizeQty().intValue()) : "");
//                            } else if (dtl.getSizeId() == 3) {
//                                detailRow.put("l", dtl.getSizeQty() != null ? String.valueOf(dtl.getSizeQty().intValue()) : "");
//                            } else if (dtl.getSizeId() == 4) {
//                                detailRow.put("xl", dtl.getSizeQty() != null ? String.valueOf(dtl.getSizeQty().intValue()) : "");
//                            } else if (dtl.getSizeId() == 5) {
//                                detailRow.put("2xl", dtl.getSizeQty() != null ? String.valueOf(dtl.getSizeQty().intValue()) : "");
//                            } else if (dtl.getSizeId() == 6) {
//                                detailRow.put("3xl", dtl.getSizeQty() != null ? String.valueOf(dtl.getSizeQty().intValue()) : "");
//                            } else if (dtl.getSizeId() == 7) {
//                                detailRow.put("4xl", dtl.getSizeQty() != null ? String.valueOf(dtl.getSizeQty().intValue()) : "");
//                            } else if (dtl.getSizeId() == 8) {
//                                detailRow.put("5xl", dtl.getSizeQty() != null ? String.valueOf(dtl.getSizeQty().intValue()) : "");
//                            } else if (dtl.getSizeId() == 9) {
//                                detailRow.put("6xl", dtl.getSizeQty() != null ? String.valueOf(dtl.getSizeQty().intValue()) : "");
//                            }
//                        }
//                    }
                    catList.add(detailRow);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(catList);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, map, dataSource);

            String filepath = "TMP/" + System.currentTimeMillis() + ".pdf";
            JasperExportManager.exportReportToPdfFile(print, filepath);
            out = new File(filepath);

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (co != null) {
                try {
                    co.close();
                } catch (Exception e) {

                }
            }
        }
        return out;
    }

//    @Override
//    public File printWholeSaleReport(Integer invno, String type) {
//
//        File out = null;
//        Connection co = null;
//        try {
//
//            File file = new File("JRXML/report/wholesaleinvoice.jrxml");
//            Map<String, Object> map = new HashMap<>();
//            map.put("invno", invno);
//            WholeSaleInvHed invHed = wholeSaleInvHedRepository.findByInvnoAndIsDeleted(invno, Deleted.NO);
//
//            if (invHed != null) {
//                map.put("customer", invHed.getCustomer());
//                map.put("location", invHed.getLocation());
//                map.put("inv_date", invHed.getInvDate());
//                map.put("total_qty", invHed.getTotalQty());
//                map.put("num_of_item", invHed.getNumOfItem());
//                map.put("gross_amount", invHed.getGrossAmount());
//                map.put("bill_dis_precentage", invHed.getBillDisPrecentage());
//                map.put("bill_dis_amount", invHed.getBillDisAmount());
//                map.put("net_amount", invHed.getNetAmount());
//                map.put("pay_type", invHed.getPayTypeInfo());
//                map.put("sales_by", invHed.getSalesBy());
//            }
//
//            List<HashMap<String, Object>> catList = new ArrayList<>();
//            String sql = "select * from wholesaleinvdtl where invno=" + invno + " group by item_code";
//
//            try {
//                co = JDBC.con();
//                Statement st = co.createStatement();
//                ResultSet rs = st.executeQuery(sql);
//                while (rs.next()) {
//
//                    HashMap<String, Object> detailRow = new HashMap<>();
//                    detailRow.put("code", rs.getString("item_code"));
//                    detailRow.put("itemname", rs.getString("item_name"));
//                    detailRow.put("batchcode", rs.getString("batch_no"));
//                    detailRow.put("qty", rs.getDouble("qty_byitem"));
//                    detailRow.put("mrp", rs.getDouble("mrp"));
//                    detailRow.put("dispre", rs.getDouble("dis_precentage"));
//                    detailRow.put("disamnt", rs.getDouble("dis_amount"));
//                    detailRow.put("amount", rs.getDouble("amount"));
//
//                    List<WholeSaleInvDtl> saleInvDtl = wholeSaleInvDtlRepository.findByInvnoAndItemCodeAndIsDeleted(invno, rs.getString("item_code"), Deleted.NO);
//                    if (!saleInvDtl.isEmpty()) {
//                        for (WholeSaleInvDtl dtl : saleInvDtl) {
//                            if (dtl.getSizeId() == 1) {
//                                if (dtl.getSizeQty() == null) {
//                                    detailRow.put("s", "");
//                                } else {
//                                    detailRow.put("s", dtl.getSizeQty().toString());
//                                }
//                            } else if (dtl.getSizeId() == 2) {
//                                detailRow.put("m", dtl.getSizeQty() != null ? dtl.getSizeQty().toString() : "");
//                            } else if (dtl.getSizeId() == 3) {
//                                detailRow.put("l", dtl.getSizeQty() != null ? dtl.getSizeQty().toString() : "");
//                            } else if (dtl.getSizeId() == 4) {
//                                detailRow.put("xl", dtl.getSizeQty() != null ? dtl.getSizeQty().toString() : "");
//                            } else if (dtl.getSizeId() == 5) {
//                                detailRow.put("2xl", dtl.getSizeQty() != null ? dtl.getSizeQty().toString() : "");
//                            } else if (dtl.getSizeId() == 6) {
//                                detailRow.put("3xl", dtl.getSizeQty() != null ? dtl.getSizeQty().toString() : "");
//                            } else if (dtl.getSizeId() == 7) {
//                                detailRow.put("4xl", dtl.getSizeQty() != null ? dtl.getSizeQty().toString() : "");
//                            } else if (dtl.getSizeId() == 8) {
//                                detailRow.put("5xl", dtl.getSizeQty() != null ? dtl.getSizeQty().toString() : "");
//                            } else if (dtl.getSizeId() == 9) {
//                                detailRow.put("6xl", dtl.getSizeQty() != null ? dtl.getSizeQty().toString() : "");
//                            }
//                        }
//                    }
//
//                    catList.add(detailRow);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
//            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(catList);
//            JasperPrint print = JasperFillManager.fillReport(jasperReport, map, dataSource);
//
//            String filepath = "TMP/" + System.currentTimeMillis() + ".pdf";
//            JasperExportManager.exportReportToPdfFile(print, filepath);
//            out = new File(filepath);
//
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        } finally {
//            if (co != null) {
//                try {
//                    co.close();
//                } catch (Exception e) {
//
//                }
//            }
//        }
//        return out;
//    }

    @Override
    public File printDailyProfitByItemReport(ProfitabilityByItemRequest request) {

        File out = null;
        Connection co = null;
        try {

            String topic = Settings.readSettings("COMPANY_NAME");
            String address = Settings.readSettings("COMPANY_ADDRESS_ONE");
            String telNo = Settings.readSettings("MOBILE_NO");

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String uu = auth.getName();

            File file = new File("JRXML/report/profitability_by_item.jrxml");
            Map<String, Object> map = new HashMap<>();
            map.put("fromDate", request.getFromDate());
            map.put("toDate", request.getToDate());
            map.put("date", new Date().toString());
            map.put("companyName", topic);
            map.put("Address", address);
            map.put("telNo", telNo);
            map.put("user", uu);
            map.put("nowDate", LocalDate.now().toString());
            map.put("time", new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));

            List<HashMap<String, Object>> catList = new ArrayList<>();

            String sql = " select a.fld_itemcode, a.fld_itemdescription, sum(a.fld_qty) as fld_qty, cast(a.fld_price as decimal(20,2)) as fld_price, cast(round(sum(a.fld_price * a.fld_qty), 3)as decimal(20,2) ) as fld_sellingprice, " +
                    " cast(round(sum(a.fld_price * a.fld_qty) - sum((a.fld_linedisamt + a.fld_promodisamt) + (a.fld_price * (a.fld_billdisper / 100) * a.fld_qty)), 3) as decimal(20,2) ) as fld_sellingamount, " +
                    " cast(a.fld_costprice as decimal(20,2)) as fld_costprice, cast(round(sum(a.fld_costprice * a.fld_qty), 3) as decimal(20,2) ) as fld_costamount, " +
                    " cast(round((sum(a.fld_price * a.fld_qty) - sum((a.fld_linedisamt + a.fld_promodisamt) + (a.fld_price * (a.fld_billdisper / 100) * a.fld_qty)))-(sum(a.fld_costprice * a.fld_qty)),2) as decimal(20,2) ) as gp, " +
                    " cast(round((((round((sum(a.fld_price * a.fld_qty) - sum((a.fld_linedisamt + a.fld_promodisamt) + (a.fld_price * (a.fld_billdisper / 100) * a.fld_qty)))-(sum(a.fld_costprice * a.fld_qty)),2))/round(sum(a.fld_price * a.fld_qty) - sum((a.fld_linedisamt + a.fld_promodisamt) + (a.fld_price * (a.fld_billdisper / 100) * a.fld_qty)), 3))*100),2) as decimal(20,2) ) as gpmargin " +
                    " from tblinvdtl a where  a.fld_void = '0' and a.fld_cancel = '0'  ";

            if ((request.getFromDate() == null) || (request.getToDate() == null)) {
                sql = sql + " ";
            } else {
                sql = sql + " and a.fld_date between '" + request.getFromDate() + "' and '" + request.getToDate() + "'  ";
            }
            if (request.getLocationId() == null) {
                sql = sql + " ";
            } else {
                sql = sql + " and a.fld_location='" + request.getLocationId() + "'  ";
            }
            if (request.getItemCode() == null) {
                sql = sql + " ";
            } else {
                sql = sql + " and a.fld_itemcode='" + request.getItemCode() + "'  ";
            }

            sql = sql + " group by a.fld_itemcode, a.fld_price order by a.fld_itemcode ";

            try {
                co = JDBC.con();
                Statement st = co.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    HashMap<String, Object> detailRow = new HashMap<>();
                    detailRow.put("fld_itemcode", rs.getString("fld_itemcode"));
                    detailRow.put("fld_itemdescription", rs.getString("fld_itemdescription"));
                    detailRow.put("fld_qty", rs.getDouble("fld_qty"));
                    detailRow.put("fld_price", rs.getDouble("fld_price"));
                    detailRow.put("fld_sellingprice", rs.getDouble("fld_sellingprice"));
                    detailRow.put("fld_sellingamount", rs.getDouble("fld_sellingamount"));
                    detailRow.put("fld_costprice", rs.getDouble("fld_costprice"));
                    detailRow.put("fld_costamount", rs.getDouble("fld_costamount"));
                    detailRow.put("gp", rs.getDouble("gp"));
                    detailRow.put("gpmargin", rs.getDouble("gpmargin"));

                    catList.add(detailRow);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(catList);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, map, dataSource);

            String filepath = "";
            if (request.getTypes().equals("pdf")) {
                filepath = "TMP/" + System.currentTimeMillis() + ".pdf";
                JasperExportManager.exportReportToPdfFile(print, filepath);
                out = new File(filepath);
            } else if (request.getTypes().equals("xlsx")) {
                try {
//
//                    filepath = "TMP/" + System.currentTimeMillis() + ".pdf";
//                    JasperExportManager.exportReportToPdfFile(print, filepath);
//                    File out1 = new File(filepath);
//
//                    PdfDocument pdf = new PdfDocument();
//                    pdf.loadFromFile(filepath);
//                    String filepath1 = "TMP/" + System.currentTimeMillis() + ".xlsx";
//                    pdf.saveToFile(filepath1, FileFormat.XLSX);
//                    out = new File(filepath1);
//                    co = jdbcc.con();
//                    out = new File("TMP/" + System.currentTimeMillis() + ".xlsx");
//                    SQLtoEXCEL.ExportFile(co, sql, out);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (co != null) {
                try {
                    co.close();
                } catch (Exception e) {

                }
            }
        }
        return out;
    }

    @Override
    public File printBinCardSizeWiseReport(String fromDate, String toDate, Integer branchId, String itemCode, String type) {

        File out = null;
        Connection co = null;
        try {

            String topic = Settings.readSettings("COMPANY_NAME");
            String address = Settings.readSettings("COMPANY_ADDRESS_ONE");
            String telNo = Settings.readSettings("MOBILE_NO");

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String uu = auth.getName();

            File file = new File("JRXML/report/bincardreport.jrxml");
            Map<String, Object> map = new HashMap<>();
            if ((fromDate.equals("null")) || (toDate.equals("null"))) {
                map.put("fromDate", "All Date");
                map.put("toDate", "All Date");
            } else {
                map.put("fromDate", fromDate);
                map.put("toDate", toDate);
            }
            if (itemCode.equals("null")) {
                map.put("item", "All item");
            } else {
                map.put("item", itemCode);
            }
            map.put("date", new Date().toString());
            map.put("companyName", topic);
            map.put("Address", address);
            map.put("telNo", telNo);
            map.put("user", uu);
            map.put("nowDate", LocalDate.now().toString());
            map.put("time", new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));

            List<HashMap<String, Object>> catList = new ArrayList<>();

            String sql = " select s.item_code,i.item_name,s.batch_no from stock s left join item_master i on i.item_code=s.item_code " +
                    " where s.branch_id=" + branchId + " ";

            if ((fromDate.equals("null")) || (toDate.equals("null"))) {
                sql = sql + " ";
            } else {
                sql = sql + " and s.modified_date_time between '" + fromDate + "' and '" + toDate + "' ";
            }

            if (itemCode.equals("null")) {
                sql = sql + " ";
            } else {
                sql = sql + " and s.item_code='" + itemCode + "'  ";
            }

            sql = sql + " and s.is_deleted=0 group by s.item_code ";

            System.out.println(sql);
            try {
                co = JDBC.con();
                Statement st = co.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    HashMap<String, Object> detailRow = new HashMap<>();
                    detailRow.put("code", rs.getString("s.item_code"));
                    detailRow.put("name", rs.getString("i.item_name"));
                    detailRow.put("batch", rs.getString("s.batch_no"));

                    List<Stock> list = stockRepository.getByBranchAndItemCode(branchId.longValue(), rs.getString("s.item_code"));

                    if (!list.isEmpty()) {
                        for (Stock stock : list) {
                            if (stock.getStockPK().getSize() == 1) {
                                if (stock.getStoresQty() == null) {
                                    detailRow.put("s", "");
                                } else {
                                    detailRow.put("s", stock.getStoresQty());
                                }
                            } else if (stock.getStockPK().getSize() == 2) {
                                detailRow.put("m", stock.getStoresQty() != null ? stock.getStoresQty() : "");
                            } else if (stock.getStockPK().getSize() == 3) {
                                detailRow.put("l", stock.getStoresQty() != null ? stock.getStoresQty() : "");
                            } else if (stock.getStockPK().getSize() == 4) {
                                detailRow.put("xl", stock.getStoresQty() != null ? stock.getStoresQty() : "");
                            } else if (stock.getStockPK().getSize() == 5) {
                                detailRow.put("2xl", stock.getStoresQty() != null ? stock.getStoresQty() : "");
                            } else if (stock.getStockPK().getSize() == 6) {
                                detailRow.put("3xl", stock.getStoresQty() != null ? stock.getStoresQty() : "");
                            } else if (stock.getStockPK().getSize() == 7) {
                                detailRow.put("4xl", stock.getStoresQty() != null ? stock.getStoresQty() : "");
                            } else if (stock.getStockPK().getSize() == 8) {
                                detailRow.put("5xl", stock.getStoresQty() != null ? stock.getStoresQty() : "");
                            } else if (stock.getStockPK().getSize() == 9) {
                                detailRow.put("6xl", stock.getStoresQty() != null ? stock.getStoresQty() : "");
                            } else if (stock.getStockPK().getSize() == 11) {
                                detailRow.put("s", stock.getStoresQty() != null ? stock.getStoresQty() : "");
                            } else if (stock.getStockPK().getSize() == 12) {
                                detailRow.put("m", stock.getStoresQty() != null ? stock.getStoresQty() : "");
                            } else if (stock.getStockPK().getSize() == 13) {
                                detailRow.put("l", stock.getStoresQty() != null ? stock.getStoresQty() : "");
                            } else if (stock.getStockPK().getSize() == 14) {
                                detailRow.put("xl", stock.getStoresQty() != null ? stock.getStoresQty() : "");
                            } else if (stock.getStockPK().getSize() == 15) {
                                detailRow.put("2xl", stock.getStoresQty() != null ? stock.getStoresQty() : "");
                            } else if (stock.getStockPK().getSize() == 16) {
                                detailRow.put("3xl", stock.getStoresQty() != null ? stock.getStoresQty() : "");
                            }
                        }
                    }

                    Double StoresSumBySize = stockRepository.getStoresSumBySize(branchId.longValue(), rs.getString("s.item_code"));
                    if (StoresSumBySize != null) {
                        detailRow.put("total", StoresSumBySize);
                    }

                    catList.add(detailRow);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(catList);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, map, dataSource);

            String filepath = "";
            if (type.equals("pdf")) {
                filepath = "TMP/" + System.currentTimeMillis() + ".pdf";
                JasperExportManager.exportReportToPdfFile(print, filepath);
                out = new File(filepath);
            } else if (type.equals("xlsx")) {
                try {
                    co = JDBC.con();
                    out = new File("TMP/" + System.currentTimeMillis() + ".xlsx");
                    SQLtoEXCEL.ExportFile(co, sql, out);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (co != null) {
                try {
                    co.close();
                } catch (Exception e) {

                }
            }
        }
        return out;
    }

    @Override
    public List<LocationStockDetailsResponse> getLocationStockDetails(String itemCode,Long catId) {

        List<LocationStockDetailsResponse> resp = new ArrayList<>();

        List<StockResponse> list = getstockGroup(itemCode,catId);

        for (StockResponse stock : list) {
            LocationStockDetailsResponse response = new LocationStockDetailsResponse();
            response.setBranch_id(stock.getBranchId());
            response.setItem_code(stock.getItemCode());
            response.setBatch_no(stock.getBatchNo());
            response.setFit_id(stock.getFitId());
            if (stock.getFitId() == 0) {
                response.setFit("");
            } else {
                Fit fit = fitRepository.getById(stock.getFitId());
                response.setFit(fit.getFitDesc());
            }
            response.setSize_id(stock.getSizeId());
            if (stock.getSizeId() == 0) {
                response.setSize("");
            } else {
                Size size = sizeRepository.getById(stock.getSizeId());
                response.setSize(size.getSizeDesc());
            }
            response.setColor_id(stock.getColorId());
            if (stock.getColorId() == 0) {
                response.setColor("");
            } else {
                Color color = colorRepository.getById(stock.getColorId());
                response.setColor(color.getColorDesc());
            }
            ItemMaster itemMaster = itemMasterRepository.findByItemCode(stock.getItemCode());
            if (itemMaster != null) {
                response.setItem_name(itemMaster.getItemName());
            }
            response.setTotStores_qty(stockRepository.getStoresTotByLoc(stock.getBatchNo(), stock.getItemCode(), stock.getSizeId(),
                    stock.getFitId(), stock.getColorId()));

            List<Stock> list1 = stockRepository.getLocationStock(stock.getBatchNo(), stock.getItemCode(), stock.getSizeId(),
                    stock.getFitId(), stock.getColorId());
            List<StockLocationListResponse> responseList = new ArrayList<>();
            for (Stock stock1 : list1) {
                StockLocationListResponse listResponse = new StockLocationListResponse();
                listResponse.setBranchid(stock1.getStockPK().getBranch().getId().intValue());
                listResponse.setBranchName(stock1.getStockPK().getBranch().getBranchName());
                listResponse.setSoresQty(stock1.getStoresQty());
                responseList.add(listResponse);
            }

            response.setResponseList(responseList);

            resp.add(response);
        }

        return resp;
    }

    @Override
    public List<ageReportResponse> ageReport(String itemCode, Long catId,Long branchId) {

        List<ageReportResponse> resp = new ArrayList<>();

        String sql = " select s.item_code,im.item_name,s.batch_no,date(o.ob_date) as obdate,ob.sum_ob_qty as ob_qty,date(g.modified_date_time) as grndate, " +
                " gd.sum_qty as qty,(select sum(stores_qty) from stock where item_code=s.item_code and branch_id=s.branch_id) as stores_qty,curdate() as today,case when date(o.ob_date) > date(g.modified_date_time) then datediff(curdate(), date(g.modified_date_time))  " +
                " when date(g.modified_date_time) > date(o.ob_date) then datediff(curdate(), date(o.ob_date)) when o.ob_date is null then datediff(curdate(), date(g.modified_date_time)) " +
                " when g.modified_date_time is null then datediff(curdate(), date(o.ob_date))  else 0 end as age,s.avrg_price from stock s  " +
                " left join grn_details g on s.item_code = g.item_id  " +
                " left join opening_balance o on s.item_code = o.item_id left  " +
                " join (select item_id, sum(ob_qty) as sum_ob_qty from opening_balance group by item_id) ob on s.item_code = ob.item_id " +
                " left join (select item_id, sum(qty) as sum_qty from grn_details group by item_id) gd on s.item_code = gd.item_id " +
                " left join item_master im on s.item_code = im.item_code where s.stores_qty!=0 and s.total_qty!=0  ";

        if (!itemCode.equals("null")) {
            sql = sql + " and s.item_code ='" + itemCode + "'  ";
        } else {
            sql = sql + "  ";
        }

        if (catId!=0){
            sql = sql + " and im.category_id ="+catId+" ";
        }else {
            sql = sql + " ";
        }

        if (branchId!=0){
            sql = sql + " and s.branch_id="+branchId+" ";
        }else {
            sql = sql + "  ";
        }

        sql = sql + " group by s.item_code ";

        System.out.println(sql);
        Connection co = null;
        try {
            co = JDBC.con();
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                ageReportResponse typeResponse = new ageReportResponse();

                typeResponse.setItem_id(rs.getString("item_code"));
                typeResponse.setItem_name(rs.getString("item_name"));
                typeResponse.setBatch_no(rs.getString("batch_no"));
                typeResponse.setObdate(rs.getString("obdate"));
                typeResponse.setOb_qty(rs.getDouble("ob_qty"));
                typeResponse.setGrndate(rs.getString("grndate"));
                typeResponse.setQty(rs.getDouble("qty"));
                typeResponse.setTotal_qty(rs.getDouble("stores_qty"));
                typeResponse.setToday(rs.getString("today"));
                typeResponse.setAge(rs.getInt("age"));
                typeResponse.setUnit_price(rs.getDouble("avrg_price"));

                resp.add(typeResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (co != null) {
                    co.close();
                }
            } catch (Exception ex) {
            }
        }

        return resp;
    }

    @Override
    public List<ProfitByItemResponse> ProfitByItem(ProfitabilityByItemExcelRequest request) {

        List<ProfitByItemResponse> resp = new ArrayList<>();

        String sql = " select b.item_code,b.item_name,a.fld_price,(select stk_cash_price from stock where item_code = A.fld_ItemCode group by item_code ) as cost,sum(a.fld_Qty) as totQty, " +
                " cast(round(sum(a.fld_price * a.fld_qty) - sum((a.fld_linedisamt + a.fld_promodisamt) + (a.fld_price * (a.fld_billdisper / 100) * a.fld_qty)), 3) as decimal(20,2) ) as fld_sellingamount " +
                " from tblinvdtl a inner join item_master b on b.item_code = a.fld_itemcode inner join tblinvhed c on c.fld_invno = a.fld_invno and c.fld_location = a.fld_location  " +
                " where a.fld_qty!=0 AND a.fld_cancel = 0 AND  a.fld_void = 0  ";

        if ((request.getFromDate() == null) || (request.getToDate() == null)) {
            sql = sql + " ";
        } else {
            sql = sql + " and a.fld_date between '" + request.getFromDate() + "' and '" + request.getToDate() + "'  ";
        }

        if (request.getLocationId() == null) {
            sql = sql + " ";
        } else {
            sql = sql + " and a.fld_location='" + request.getLocationId() + "'  ";
        }

        if (request.getItemCode() == null) {
            sql = sql + " ";
        } else {
            sql = sql + " and a.fld_itemcode='" + request.getItemCode() + "'  ";
        }

        if (request.getCatId() == null) {
            sql = sql + " ";
        } else {
            sql = sql + "  and b.category_id=" + request.getCatId() + " ";
        }


        sql = sql + " group by b.item_code, a.fld_price HAVING SUM(a.fld_Qty) <> 0 order by c.fld_invno desc  ";


        System.out.println(sql);
        Connection co = null;
        try {
            co = JDBC.con();
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                ProfitByItemResponse response= new ProfitByItemResponse();

                response.setFld_itemcode(rs.getString("b.item_code"));
                response.setFld_itemdescription(rs.getString("b.item_name"));
                response.setFld_price(rs.getDouble("a.fld_price"));
                response.setFld_costprice(rs.getDouble("cost"));
                response.setFld_qty(rs.getDouble("totQty"));
                response.setFld_sellingamount(rs.getDouble("fld_sellingamount"));

               // double sellPrice = rs.getDouble("fld_qty")*rs.getDouble("a.fld_price");
//                response.setFld_sellingprice(sellPrice);

                double sellAmount = rs.getDouble("fld_sellingamount");
              //  response.setFld_sellingamount(sellAmount);

                double costAmount = rs.getDouble("totQty")*rs.getDouble("cost");
                response.setFld_costamount(costAmount);

                double gp = sellAmount - costAmount;
                response.setGp(gp);
                response.setGpmargin((gp/sellAmount)*100);

                resp.add(response);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (co != null) {
                    co.close();
                }
            } catch (Exception ex) {
            }
        }
        return resp;
    }

    @Override
    public File printWholeSaleOldReport(Integer invno, Integer location, String type) {

        File out = null;
        Connection co = null;
        try {

            File file = new File("JRXML/report/WholeSale_Invoice.jrxml");

            Map<String, Object> map = new HashMap<>();
            map.put("Invoice_id", invno.toString());
            map.put("branch_id", location.toString());

            co = JDBC.con();
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
            JasperPrint print = JasperFillManager.fillReport(jasperReport, map, co);

            String filepath = "";
            if (type.equals("pdf")) {
                filepath = "TMP/" + System.currentTimeMillis() + ".pdf";
                JasperExportManager.exportReportToPdfFile(print, filepath);
            } else if (type.equals("docx")) {
                filepath = "TMP/" + System.currentTimeMillis() + ".docx";
                JasperExportManager.exportReportToXml(print);
                JRDocxExporter exporter = new JRDocxExporter();
                exporter.setExporterInput(new SimpleExporterInput(print));
                File exportReportFile = new File(filepath);
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportReportFile));
                exporter.exportReport();
            }

            out = new File(filepath);

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (co != null) {
                try {
                    co.close();
                } catch (Exception e) {

                }
            }
        }
        return out;
    }

    public List<StockResponse> getstockGroup(String itemCode,Long catId) {

        List<StockResponse> resp = new ArrayList<>();

        String sql = " select * from stock s inner join item_master i on i.item_code = s.item_code  ";

        if (!itemCode.equals("null")) {
            sql = sql + " where s.item_code='" + itemCode + "'  ";
        } else {
            sql = sql + "  ";
        }

        if (catId!=0){
            sql = sql + " where i.category_id="+catId+" ";
        }else {
            sql = sql + "";
        }

        sql = sql + " group by s.batch_no, s.item_code, s.color_id, s.fit_id, s.size_id ";


//            String sql = " select * from stock ";
//
//            if (!itemCode.equals("null")){
//                sql = sql +" where item_code='"+itemCode+"'  ";
//            }else {
//                sql = sql + "  ";
//            }
//


        Connection co = null;
        try {
            co = JDBC.con();
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                StockResponse typeResponse = new StockResponse();

                typeResponse.setBranchId(rs.getLong("branch_id"));
                typeResponse.setBatchNo(rs.getString("batch_no"));
                typeResponse.setColorId(rs.getLong("color_id"));
                typeResponse.setFitId(rs.getLong("fit_id"));
                typeResponse.setSizeId(rs.getLong("size_id"));
                typeResponse.setItemCode(rs.getString("item_code"));
                typeResponse.setStoresQty(rs.getDouble("stores_qty"));

                resp.add(typeResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (co != null) {
                    co.close();
                }
            } catch (Exception ex) {
            }
        }

        return resp;
    }


}