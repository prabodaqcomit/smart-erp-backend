package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.PromotionPayDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.PromotionPayDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PromotionPayDetailsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromotionPayDetailsService {

    PromotionPayDetailsResponse save(PromotionPayDetailsRequest request);

    PromotionPayDetailsResponse update(PromotionPayDetailsUpdateRequest request);

    PromotionPayDetailsResponse getById(Long id);

    List<PromotionPayDetailsResponse> getAll();


    Integer delete(Long id);
}