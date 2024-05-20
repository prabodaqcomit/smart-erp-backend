package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.CategoryRequest;
import lk.quantacom.smarterpbackend.dto.request.CategoryUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.CategoryResponse;
import lk.quantacom.smarterpbackend.entity.Category;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.CategoryRepository;
import lk.quantacom.smarterpbackend.service.CategoryService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;


    private void saveLog(String form,String action){
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }

    @Override
    @Transactional
    public CategoryResponse save(CategoryRequest request) {

        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        category.setIsMaterialCategory(request.getIsMaterialCategory());
        category.setIsDeleted(Deleted.NO);
        Category save = categoryRepository.save(category);

        saveLog("Category","Data Saved - "+save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public CategoryResponse update(CategoryUpdateRequest request) {

        Category category = categoryRepository.findById(request.getId()).orElse(null);
        if (category == null) {
            return null;
        }

        category.setId(request.getId());
        category.setCategoryName(request.getCategoryName());
        category.setIsMaterialCategory(request.getIsMaterialCategory());
        category.setIsDeleted(request.getIsDeleted());
        Category updated = categoryRepository.save(category);

        saveLog("Category","Data Updated - "+updated.getId());

        return (convert(updated));
    }

    @Override
    public CategoryResponse getById(Long id) {

        return categoryRepository.findById(id).map(CategoryServiceImpl::convert).orElse(null);
    }

    @Override
    public List<CategoryResponse> getAll() {

        return categoryRepository.findAll()
                .stream().map(CategoryServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<CategoryResponse> getByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName).stream().map(CategoryServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        Category got = categoryRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        categoryRepository.save(got);

        saveLog("Category","Data Deleted - "+id);

        return 1;
    }

    @Override
    public List<CategoryResponse> getByIsDeletedAndIsMaterialCategory() {
        return categoryRepository.findByIsDeletedAndIsMaterialCategory(Deleted.NO,true)
                .stream().map(CategoryServiceImpl::convert).collect(Collectors.toList());
    }

    private static CategoryResponse convert(Category category) {

        CategoryResponse typeResponse = new CategoryResponse();
        typeResponse.setCategoryName(category.getCategoryName());

        typeResponse.setIsMaterialCategory(category.getIsMaterialCategory());

        typeResponse.setId(category.getId());
        typeResponse.setCreatedBy(category.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(category.getCreatedDateTime()));
        typeResponse.setModifiedBy(category.getModifiedBy());
        typeResponse.setIsDeleted(category.getIsDeleted());

        return typeResponse;
    }
}