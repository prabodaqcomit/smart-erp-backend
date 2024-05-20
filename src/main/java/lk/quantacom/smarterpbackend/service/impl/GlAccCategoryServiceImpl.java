package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.GlAccCategoryRequest;
import lk.quantacom.smarterpbackend.dto.request.GlAccCategoryUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GlAccCategoryResponse;
import lk.quantacom.smarterpbackend.dto.response.GlMainAccTypeResponse;
import lk.quantacom.smarterpbackend.entity.GlAccCategory;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.GlAccCategoryRepository;
import lk.quantacom.smarterpbackend.service.GlAccCategoryService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GlAccCategoryServiceImpl implements GlAccCategoryService {

    @Autowired
    private GlAccCategoryRepository glAccCategoryRepository;

    private static GlAccCategoryResponse convert(GlAccCategory glAccCategory) {

        GlAccCategoryResponse typeResponse = new GlAccCategoryResponse();
        typeResponse.setAccount(glAccCategory.getAccount());
        typeResponse.setAccName(glAccCategory.getAccName());
        typeResponse.setId(glAccCategory.getId());
        typeResponse.setCreatedBy(glAccCategory.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(glAccCategory.getCreatedDateTime()));
        typeResponse.setModifiedBy(glAccCategory.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(glAccCategory.getModifiedDateTime()));
        typeResponse.setIsDeleted(glAccCategory.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public GlAccCategoryResponse save(GlAccCategoryRequest request) {

        GlAccCategory glAccCategory = new GlAccCategory();
        glAccCategory.setAccount(request.getAccount());
        glAccCategory.setAccName(request.getAccName());
        glAccCategory.setIsDeleted(Deleted.NO);
        GlAccCategory save = glAccCategoryRepository.save(glAccCategory);

        return convert(save);
    }

    @Override
    @Transactional
    public GlAccCategoryResponse update(GlAccCategoryUpdateRequest request) {

        GlAccCategory glAccCategory = glAccCategoryRepository.findById(request.getId()).orElse(null);
        if (glAccCategory == null) {
            return null;
        }

        glAccCategory.setAccount(request.getAccount());
        glAccCategory.setAccName(request.getAccName());
        GlAccCategory updated = glAccCategoryRepository.save(glAccCategory);

        return (convert(updated));
    }

    @Override
    public GlAccCategoryResponse getById(Long id) {

        return glAccCategoryRepository.findById(id).map(GlAccCategoryServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GlAccCategoryResponse> getAll() {

        return glAccCategoryRepository.findByIsDeleted(Deleted.NO)
                .stream().map(GlAccCategoryServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        GlAccCategory got = glAccCategoryRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        glAccCategoryRepository.save(got);

        return 1;
    }

    @Override
    public List<GlAccCategoryResponse> getAllById(Long id) {
        List<GlAccCategoryResponse> responseList = new ArrayList<>();
        GlAccCategoryResponse glMainAccTypeResponse = glAccCategoryRepository.findById(id).map(GlAccCategoryServiceImpl::convert).orElse(null);
        responseList.add(glMainAccTypeResponse);
        return responseList;
    }

}