package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.OrderFormRequest;
import lk.quantacom.smarterpbackend.dto.request.OrderFormUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.OrderFormResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderFormService {

    List<OrderFormResponse> save(List<OrderFormRequest> request);

    OrderFormResponse update(OrderFormUpdateRequest request);

    OrderFormResponse getById(Long id);

    List<OrderFormResponse> getAll();


    Integer delete(Long id);
}