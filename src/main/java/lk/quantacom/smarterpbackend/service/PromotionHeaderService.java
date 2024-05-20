package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.PromotionHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.PromotionHeaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PromotionHeaderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromotionHeaderService {

    PromotionHeaderResponse save(PromotionHeaderRequest request);

    PromotionHeaderResponse update(PromotionHeaderUpdateRequest request);

    PromotionHeaderResponse getById(Long id);

    List<PromotionHeaderResponse> getAll();

    String getMaxCode();

    Integer delete(Long id);
}