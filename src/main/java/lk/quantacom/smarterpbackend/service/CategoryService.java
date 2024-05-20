package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.CategoryRequest;
import lk.quantacom.smarterpbackend.dto.request.CategoryUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    CategoryResponse save(CategoryRequest request);

    CategoryResponse update(CategoryUpdateRequest request);

    CategoryResponse getById(Long id);

    List<CategoryResponse> getAll();

    List<CategoryResponse> getByCategoryName(String categoryName);

    List<CategoryResponse> getByIsDeletedAndIsMaterialCategory();

    Integer delete(Long id);
}