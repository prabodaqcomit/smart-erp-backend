package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.GrnPaymentsRequest;
import lk.quantacom.smarterpbackend.dto.request.GrnPaymentsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GLPayMethodDetailsResponse;
import lk.quantacom.smarterpbackend.dto.response.GrnPaymentsResponse;
import lk.quantacom.smarterpbackend.dto.response.supplierPaymentInfoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GrnPaymentsService {

    GrnPaymentsResponse save(GrnPaymentsRequest request);

    GrnPaymentsResponse update(GrnPaymentsUpdateRequest request);

    GrnPaymentsResponse getById(Long id);

    List<GrnPaymentsResponse> getAll();

    List<GrnPaymentsResponse> getAllForSupOb();

    Integer delete(Long id);

    List<supplierPaymentInfoResponse> getAlSup();

    List<supplierPaymentInfoResponse> getAlBySup(Integer supId);

}