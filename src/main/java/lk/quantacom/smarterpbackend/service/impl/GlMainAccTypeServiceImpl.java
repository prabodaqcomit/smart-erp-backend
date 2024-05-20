package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.GlMainAccTypeRequest;
import lk.quantacom.smarterpbackend.dto.request.GlMainAccTypeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GlMainAccTypeResponse;
import lk.quantacom.smarterpbackend.entity.GlMainAccType;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.GlMainAccTypeRepository;
import lk.quantacom.smarterpbackend.service.GlMainAccTypeService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GlMainAccTypeServiceImpl implements GlMainAccTypeService {

    @Autowired
    private GlMainAccTypeRepository glMainAccTypeRepository;

    private static GlMainAccTypeResponse convert(GlMainAccType glMainAccType) {

        GlMainAccTypeResponse typeResponse = new GlMainAccTypeResponse();
        typeResponse.setCategoty(glMainAccType.getCategoty());
        typeResponse.setCategoryId(glMainAccType.getCategoryId());
        typeResponse.setCategoryAccount(glMainAccType.getCategoryAccount());
        typeResponse.setAccount(glMainAccType.getAccount());
        typeResponse.setName(glMainAccType.getName());
        typeResponse.setId(glMainAccType.getId());
        typeResponse.setCreatedBy(glMainAccType.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(glMainAccType.getCreatedDateTime()));
        typeResponse.setModifiedBy(glMainAccType.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(glMainAccType.getModifiedDateTime()));
        typeResponse.setIsDeleted(glMainAccType.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public GlMainAccTypeResponse save(GlMainAccTypeRequest request) {

        GlMainAccType glMainAccType = new GlMainAccType();
        glMainAccType.setCategoty(request.getCategoty());
        glMainAccType.setCategoryId(request.getCategoryId());
        glMainAccType.setCategoryAccount(request.getCategoryAccount());
        glMainAccType.setAccount(request.getAccount());
        glMainAccType.setName(request.getName());
        glMainAccType.setIsDeleted(Deleted.NO);
        GlMainAccType save = glMainAccTypeRepository.save(glMainAccType);

        return convert(save);
    }

    @Override
    @Transactional
    public GlMainAccTypeResponse update(GlMainAccTypeUpdateRequest request) {

        GlMainAccType glMainAccType = glMainAccTypeRepository.findById(request.getId()).orElse(null);
        if (glMainAccType == null) {
            return null;
        }

        glMainAccType.setId(request.getId());
        glMainAccType.setCategoty(request.getCategoty());
        glMainAccType.setCategoryId(request.getCategoryId());
        glMainAccType.setCategoryAccount(request.getCategoryAccount());
        glMainAccType.setAccount(request.getAccount());
        glMainAccType.setName(request.getName());
        GlMainAccType updated = glMainAccTypeRepository.save(glMainAccType);

        return (convert(updated));
    }

    @Override
    public GlMainAccTypeResponse getById(Long id) {

        return glMainAccTypeRepository.findById(id).map(GlMainAccTypeServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GlMainAccTypeResponse> getAll() {

        return glMainAccTypeRepository.findByIsDeleted(Deleted.NO)
                .stream().map(GlMainAccTypeServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        GlMainAccType got = glMainAccTypeRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        glMainAccTypeRepository.save(got);

        return 1;
    }

    @Override
    public List<GlMainAccTypeResponse> getAllByCat(Long catId) {
        return glMainAccTypeRepository.findByIsDeletedAndCategoryId(Deleted.NO,catId)
                .stream().map(GlMainAccTypeServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<GlMainAccTypeResponse> getAllById(Long id) {
        List<GlMainAccTypeResponse> responseList = new ArrayList<>();
        GlMainAccTypeResponse glMainAccTypeResponse = glMainAccTypeRepository.findById(id).map(GlMainAccTypeServiceImpl::convert).orElse(null);
        responseList.add(glMainAccTypeResponse);
        return responseList;
    }
}