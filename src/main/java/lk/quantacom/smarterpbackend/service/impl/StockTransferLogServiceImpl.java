package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.StockTransferLogRequest;
import lk.quantacom.smarterpbackend.dto.request.StockTransferLogUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.StockTransferLogResponse;
import lk.quantacom.smarterpbackend.entity.BranchNetwork;
import lk.quantacom.smarterpbackend.entity.Customer;
import lk.quantacom.smarterpbackend.entity.StockTransferLog;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.BranchNetworkRepository;
import lk.quantacom.smarterpbackend.repository.CustomerRepository;
import lk.quantacom.smarterpbackend.repository.StockTransferLogRepository;
import lk.quantacom.smarterpbackend.service.StockTransferLogService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.utils.JDBC;
import lk.quantacom.smarterpbackend.utils.Settings;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StockTransferLogServiceImpl implements StockTransferLogService {

    @Autowired
    private StockTransferLogRepository stockTransferLogRepository;

    @Autowired
    private BranchNetworkRepository branchNetworkRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private static StockTransferLogResponse convert(StockTransferLog stockTransferLog) {

        StockTransferLogResponse typeResponse = new StockTransferLogResponse();
        typeResponse.setUser(stockTransferLog.getUser());
        typeResponse.setItemCode(stockTransferLog.getItemCode());
        typeResponse.setItemName(stockTransferLog.getItemName());
        typeResponse.setDate(stockTransferLog.getDate());
        typeResponse.setColor(stockTransferLog.getColor());
        typeResponse.setSize(stockTransferLog.getSize());
        typeResponse.setFit(stockTransferLog.getFit());
        typeResponse.setFromBranch(stockTransferLog.getFromBranch());
        typeResponse.setToBranch(stockTransferLog.getToBranch());
        typeResponse.setPrevQtyfromBranch(stockTransferLog.getPrevQtyfromBranch());
        typeResponse.setNewQtytoBranch(stockTransferLog.getNewQtytoBranch());
        typeResponse.setPrevQtytobranch(stockTransferLog.getPrevQtytobranch());
        typeResponse.setNewQtyFromBranch(stockTransferLog.getNewQtyFromBranch());
        typeResponse.setId(stockTransferLog.getId());
        typeResponse.setCreatedBy(stockTransferLog.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(stockTransferLog.getCreatedDateTime()));
        typeResponse.setModifiedBy(stockTransferLog.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(stockTransferLog.getModifiedDateTime()));
        typeResponse.setIsDeleted(stockTransferLog.getIsDeleted());
        typeResponse.setIssueNumber(stockTransferLog.getIssueNumber());
        typeResponse.setUnitPrice(stockTransferLog.getUnitPrice());

        return typeResponse;
    }

    @Override
    @Transactional
    public StockTransferLogResponse save(StockTransferLogRequest request) {

        Integer issueNum = stockTransferLogRepository.getMaxId();
        if (issueNum!=null){
            issueNum=issueNum+1;
        }else {
            issueNum=1;
        }

        StockTransferLog stockTransferLog = new StockTransferLog();
        stockTransferLog.setUser(request.getUser());
        stockTransferLog.setItemCode(request.getItemCode());
        stockTransferLog.setItemName(request.getItemName());
        stockTransferLog.setDate(request.getDate() == null ? null : ConvertUtils.convertStrToDate(request.getDate()));
        stockTransferLog.setColor(request.getColor());
        stockTransferLog.setSize(request.getSize());
        stockTransferLog.setFit(request.getFit());
        stockTransferLog.setFromBranch(request.getFromBranch());
        stockTransferLog.setToBranch(request.getToBranch());
        stockTransferLog.setPrevQtyfromBranch(request.getPrevQtyfromBranch());
        stockTransferLog.setNewQtytoBranch(request.getNewQtytoBranch());
        stockTransferLog.setPrevQtytobranch(request.getPrevQtytobranch());
        stockTransferLog.setNewQtyFromBranch(request.getNewQtyFromBranch());
        stockTransferLog.setIsDeleted(Deleted.NO);
        stockTransferLog.setIssueNumber(issueNum);
        stockTransferLog.setUnitPrice(request.getUnitPrice());
        StockTransferLog save = stockTransferLogRepository.save(stockTransferLog);

        return convert(save);
    }

    @Override
    @Transactional
    public StockTransferLogResponse update(StockTransferLogUpdateRequest request) {

        Integer issueNum = stockTransferLogRepository.getMaxId();
        if (issueNum!=null){
            issueNum=issueNum+1;
        }else {
            issueNum=1;
        }

        StockTransferLog stockTransferLog = stockTransferLogRepository.findById(request.getId()).orElse(null);
        if (stockTransferLog == null) {
            return null;
        }

        stockTransferLog.setId(request.getId());
        stockTransferLog.setUser(request.getUser());
        stockTransferLog.setItemCode(request.getItemCode());
        stockTransferLog.setItemName(request.getItemName());
        stockTransferLog.setDate(request.getDate() == null ? null : ConvertUtils.convertStrToDate(request.getDate()));
        stockTransferLog.setColor(request.getColor());
        stockTransferLog.setSize(request.getSize());
        stockTransferLog.setFit(request.getFit());
        stockTransferLog.setFromBranch(request.getFromBranch());
        stockTransferLog.setToBranch(request.getToBranch());
        stockTransferLog.setPrevQtyfromBranch(request.getPrevQtyfromBranch());
        stockTransferLog.setNewQtytoBranch(request.getNewQtytoBranch());
        stockTransferLog.setPrevQtytobranch(request.getPrevQtytobranch());
        stockTransferLog.setNewQtyFromBranch(request.getNewQtyFromBranch());
        stockTransferLog.setIssueNumber(issueNum);
        stockTransferLog.setUnitPrice(request.getUnitPrice());
        StockTransferLog updated = stockTransferLogRepository.save(stockTransferLog);

        return (convert(updated));
    }

    @Override
    public StockTransferLogResponse getById(Long id) {

        return stockTransferLogRepository.findById(id).map(StockTransferLogServiceImpl::convert).orElse(null);
    }

    @Override
    public List<StockTransferLogResponse> getAll() {

        return stockTransferLogRepository.findByIsDeleted(Deleted.NO)
                .stream().map(StockTransferLogServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<StockTransferLogResponse> getAllByIssueNote() {
        List<StockTransferLogResponse> list = new ArrayList<>();
        List<StockTransferLog> transferLogs = stockTransferLogRepository.getAllIssueDetails();
        for (StockTransferLog transferLog:transferLogs){
            StockTransferLogResponse response = convert2(transferLog);
            list.add(response);
        }
        return list;

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        StockTransferLog got = stockTransferLogRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        stockTransferLogRepository.save(got);

        return 1;
    }

    @Override
    public Integer getIssueNumber() {
        return  stockTransferLogRepository.getMaxId();
    }

    @Override
    public File printIssueNoteReport(Integer issueNumber, String type) {

        File out = null;
        Connection co = null;
        try {

            String topic = Settings.readSettings("COMPANY_NAME");
            String address = Settings.readSettings("COMPANY_ADDRESS_ONE");
            String telNo = Settings.readSettings("MOBILE_NO");

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String uu = auth.getName();

            File file = new File("JRXML/report/issue_note.jrxml");
            Map<String, Object> map = new HashMap<>();
            map.put("date", new Date().toString());
            map.put("companyName", topic);
            map.put("Address", address);
            map.put("telNo", telNo);
            map.put("issueNumber", issueNumber);
            List<StockTransferLog> transferLog = stockTransferLogRepository.findByIssueNumberAndIsDeleted(issueNumber,Deleted.NO);

            BranchNetwork branchNetwork = branchNetworkRepository.getById(transferLog.get(0).getToBranch());
            List<Customer> customer = customerRepository.findByName(branchNetwork.getBranchName());
//            System.out.println(transferLog.get(0).getToBranch());
            map.put("name", branchNetwork.getBranchName());
            map.put("dealer_address", customer.get(0).getAddress());
//            map.put("user", uu);
//            map.put("nowDate", LocalDate.now().toString());
//            map.put("time", new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));

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


    private StockTransferLogResponse convert2(StockTransferLog stockTransferLog) {

        StockTransferLogResponse typeResponse = new StockTransferLogResponse();
        typeResponse.setUser(stockTransferLog.getUser());
        typeResponse.setItemCode(stockTransferLog.getItemCode());
        typeResponse.setItemName(stockTransferLog.getItemName());
        typeResponse.setDate(stockTransferLog.getDate());
        typeResponse.setColor(stockTransferLog.getColor());
        typeResponse.setSize(stockTransferLog.getSize());
        typeResponse.setFit(stockTransferLog.getFit());
        typeResponse.setFromBranch(stockTransferLog.getFromBranch());
        BranchNetwork branchNetwork = branchNetworkRepository.getById(stockTransferLog.getFromBranch());
        typeResponse.setFromBranchName(branchNetwork.getBranchName());
        typeResponse.setToBranch(stockTransferLog.getToBranch());
        BranchNetwork branchNetworkto = branchNetworkRepository.getById(stockTransferLog.getToBranch());
        typeResponse.setToBranchName(branchNetworkto.getBranchName());
        typeResponse.setPrevQtyfromBranch(stockTransferLog.getPrevQtyfromBranch());
        typeResponse.setNewQtytoBranch(stockTransferLog.getNewQtytoBranch());
        typeResponse.setPrevQtytobranch(stockTransferLog.getPrevQtytobranch());
        typeResponse.setNewQtyFromBranch(stockTransferLog.getNewQtyFromBranch());
        typeResponse.setId(stockTransferLog.getId());
        typeResponse.setCreatedBy(stockTransferLog.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(stockTransferLog.getCreatedDateTime()));
        typeResponse.setModifiedBy(stockTransferLog.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(stockTransferLog.getModifiedDateTime()));
        typeResponse.setIsDeleted(stockTransferLog.getIsDeleted());
        typeResponse.setIssueNumber(stockTransferLog.getIssueNumber());
        typeResponse.setUnitPrice(stockTransferLog.getUnitPrice());

        return typeResponse;
    }
}