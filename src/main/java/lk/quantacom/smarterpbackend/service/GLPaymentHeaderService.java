package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.GLPaymentHeaderResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public interface GLPaymentHeaderService {

    GLPaymentHeaderResponse save(GLPaymentHeaderRequest request);

    GLPaymentHeaderResponse update(GLPaymentHeaderUpdateRequest request);

    GLPaymentHeaderResponse getById(Long id);

    List<GLPaymentHeaderResponse> getAll();

    Integer delete(Long id);

    String getMaxVoucherNum();

    List<String> getAvailableVoucherNumbers(String searchNumber);

    GLPaymentHeaderResponse saveGeneral(GLPaymentHeaderGeneralRequest request);

    GLPaymentHeaderResponse saveSupplier(GLPaymentHeaderSupplierRequest request);

    GLPaymentHeaderResponse updateSupplier(GLPaymentHeaderSupplierUpdateRequest request);

    GLPaymentHeaderResponse updateGeneral(GLPaymentHeaderGeneralUpdateRequest request);

    List<GLPaymentHeaderResponse> filteredSearch(GLPaymentFilterSearchRequest request);

    List<GLPaymentHeaderResponse> getAllGeneral();

    List<GLPaymentHeaderResponse> getAllSup();

    GLPaymentHeaderResponse getGeneralPayById(Long id);

    GLPaymentHeaderResponse getSupPayById(Long id);

    List<GLPaymentHeaderResponse> filteredSearchGeneral(GLPaymentFilterSearchRequest request);

    List<GLPaymentHeaderResponse> filteredSearchSupplier(GLPaymentFilterSearchRequest request);

    File printSingle(Long id);
}