package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.GenaralPaymentsRequest;
import lk.quantacom.smarterpbackend.dto.request.GenaralPaymentsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GenaralPaymentsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenaralPaymentsService {

    GenaralPaymentsResponse save(GenaralPaymentsRequest request);

    GenaralPaymentsResponse update(GenaralPaymentsUpdateRequest request);

    GenaralPaymentsResponse getById(Long id);

    List<GenaralPaymentsResponse> getAll();


    Integer delete(Long id);
}