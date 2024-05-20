package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.PayTypeRequest;
import lk.quantacom.smarterpbackend.dto.request.PayTypeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PayTypeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PayTypeService {

    PayTypeResponse save(PayTypeRequest request);

    PayTypeResponse update(PayTypeUpdateRequest request);

    PayTypeResponse getById(Long id);

    List<PayTypeResponse> getAll();


    Integer delete(Long id);
}