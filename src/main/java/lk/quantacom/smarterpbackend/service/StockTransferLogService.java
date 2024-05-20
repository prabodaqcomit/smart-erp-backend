package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.StockTransferLogRequest;
import lk.quantacom.smarterpbackend.dto.request.StockTransferLogUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.StockTransferLogResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface StockTransferLogService {

    StockTransferLogResponse save(StockTransferLogRequest request);

    StockTransferLogResponse update(StockTransferLogUpdateRequest request);

    StockTransferLogResponse getById(Long id);

    List<StockTransferLogResponse> getAll();

    List<StockTransferLogResponse> getAllByIssueNote();

    Integer delete(Long id);

    Integer getIssueNumber();

    File printIssueNoteReport(Integer issueNumber, String type);

}