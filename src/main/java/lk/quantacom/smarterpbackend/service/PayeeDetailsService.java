package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.PayeeDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.PayeeDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PayeeDetailsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PayeeDetailsService {

    PayeeDetailsResponse save(PayeeDetailsRequest request);

    PayeeDetailsResponse update(PayeeDetailsUpdateRequest request);

    PayeeDetailsResponse getById(Long id);

    List<PayeeDetailsResponse> getAll();


    Integer delete(Long id);
}