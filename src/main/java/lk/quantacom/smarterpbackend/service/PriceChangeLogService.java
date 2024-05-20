package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.PriceChangeLogRequest;
import lk.quantacom.smarterpbackend.dto.request.PriceChangeLogUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PriceChangeLogResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PriceChangeLogService {

    PriceChangeLogResponse save(PriceChangeLogRequest request);

    PriceChangeLogResponse update(PriceChangeLogUpdateRequest request);

    PriceChangeLogResponse getById(Long id);

    List<PriceChangeLogResponse> getAll();

    Integer delete(Long id);
}