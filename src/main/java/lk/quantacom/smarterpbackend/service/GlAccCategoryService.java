package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.GlAccCategoryRequest;
import lk.quantacom.smarterpbackend.dto.request.GlAccCategoryUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GlAccCategoryResponse;
import lk.quantacom.smarterpbackend.dto.response.GlMainAccTypeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GlAccCategoryService {

    GlAccCategoryResponse save(GlAccCategoryRequest request);

    GlAccCategoryResponse update(GlAccCategoryUpdateRequest request);

    GlAccCategoryResponse getById(Long id);

    List<GlAccCategoryResponse> getAll();

    Integer delete(Long id);

    List<GlAccCategoryResponse> getAllById(Long id);
}