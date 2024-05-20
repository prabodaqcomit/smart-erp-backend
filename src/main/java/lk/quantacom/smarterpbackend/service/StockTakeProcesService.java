package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.StockTakeProcesRequest;
import lk.quantacom.smarterpbackend.dto.request.StockTakeProcesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.StockTakeProcesResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface StockTakeProcesService {

    StockTakeProcesResponse save(StockTakeProcesRequest request);

    StockTakeProcesResponse update(StockTakeProcesUpdateRequest request);

    StockTakeProcesResponse getById(Integer id);

    List<StockTakeProcesResponse> getAll();

    Integer delete(Integer id);

    List<StockTakeProcesResponse> saveBulk(List<StockTakeProcesRequest> requests);

    List<StockTakeProcesResponse> getProcesList();

    List<StockTakeProcesResponse> updateBulk(List<StockTakeProcesRequest> requests);

    File printVarianceReport(String types);
}