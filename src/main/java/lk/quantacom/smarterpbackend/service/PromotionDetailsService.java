package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.PromotionDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.PromotionDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PromotionDetailsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromotionDetailsService {

    PromotionDetailsResponse save(PromotionDetailsRequest request);

    PromotionDetailsResponse update(PromotionDetailsUpdateRequest request);

    PromotionDetailsResponse getById(Long id);

    List<PromotionDetailsResponse> getAll();


    Integer delete(Long id);
}