package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.GLPayMethodDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.GLPayMethodDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GLPayMethodDetailsResponse;
import lk.quantacom.smarterpbackend.entity.GLPaymentDetail;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GLPayMethodDetailsService {

    GLPayMethodDetailsResponse save(GLPayMethodDetailsRequest request);

    GLPayMethodDetailsResponse update(GLPayMethodDetailsUpdateRequest request);

    GLPayMethodDetailsResponse getById(Long id);

    List<GLPayMethodDetailsResponse> getAll();

    Integer delete(Long id);


}