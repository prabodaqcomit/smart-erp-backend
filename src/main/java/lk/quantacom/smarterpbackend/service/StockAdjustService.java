package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.StockAdjustRequest;
import lk.quantacom.smarterpbackend.dto.request.StockAdjustUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.StockAdjustResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StockAdjustService {

    List<StockAdjustResponse> save(List<StockAdjustRequest> request);

    StockAdjustResponse update(StockAdjustUpdateRequest request);

    StockAdjustResponse getById(Long id);

    List<StockAdjustResponse> getAll();


    Integer delete(Long id);
}