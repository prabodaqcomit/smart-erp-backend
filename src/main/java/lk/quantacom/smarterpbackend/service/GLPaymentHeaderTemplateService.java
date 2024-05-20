package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.GLPaymentHeaderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GLPaymentHeaderTemplateService {

    GLPaymentHeaderResponse save(GLPaymentHeaderRequest request);

    GLPaymentHeaderResponse update(GLPaymentHeaderUpdateRequest request);

    GLPaymentHeaderResponse getById(Long id);

    List<GLPaymentHeaderResponse> getAll();

    Integer delete(Long id);

    String getMaxVoucherNum();

    GLPaymentHeaderResponse saveGeneral(GLPaymentHeaderGeneralRequest request);

    GLPaymentHeaderResponse saveSupplier(GLPaymentHeaderSupplierRequest request);

    List<GLPaymentHeaderResponse> filteredSearch(GLPaymentFilterSearchRequest request);


}