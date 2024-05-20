package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.GlSubAccTypeRequest;
import lk.quantacom.smarterpbackend.dto.request.GlSubAccTypeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GlAccCategoryResponse;
import lk.quantacom.smarterpbackend.dto.response.GlSubAccTypeResponse;
import lk.quantacom.smarterpbackend.entity.GlSubAccType;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.GlSubAccTypeRepository;
import lk.quantacom.smarterpbackend.service.GlSubAccTypeService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GlSubAccTypeServiceImpl implements GlSubAccTypeService {

    @Autowired
    private GlSubAccTypeRepository glSubAccTypeRepository;

    private static GlSubAccTypeResponse convert(GlSubAccType glSubAccType) {

        GlSubAccTypeResponse typeResponse = new GlSubAccTypeResponse();
        typeResponse.setCategoty(glSubAccType.getCategoty());
        typeResponse.setCategoryId(glSubAccType.getCategoryId());
        typeResponse.setCategoryAccount(glSubAccType.getCategoryAccount());
        typeResponse.setMainAccount(glSubAccType.getMainAccount());
        typeResponse.setMainAccountId(glSubAccType.getMainAccountId());
        typeResponse.setMainAccName(glSubAccType.getMainAccName());
        typeResponse.setName(glSubAccType.getName());
        typeResponse.setAccount(glSubAccType.getAccount());
        typeResponse.setId(glSubAccType.getId());
        typeResponse.setCreatedBy(glSubAccType.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(glSubAccType.getCreatedDateTime()));
        typeResponse.setModifiedBy(glSubAccType.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(glSubAccType.getModifiedDateTime()));
        typeResponse.setIsDeleted(glSubAccType.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public GlSubAccTypeResponse save(GlSubAccTypeRequest request) {

        GlSubAccType glSubAccType = new GlSubAccType();
        glSubAccType.setCategoty(request.getCategoty());
        glSubAccType.setCategoryId(request.getCategoryId());
        glSubAccType.setCategoryAccount(request.getCategoryAccount());
        glSubAccType.setMainAccount(request.getMainAccount());
        glSubAccType.setMainAccountId(request.getMainAccountId());
        glSubAccType.setMainAccName(request.getMainAccName());
        glSubAccType.setName(request.getName());
        glSubAccType.setAccount(request.getAccount());
        glSubAccType.setIsDeleted(Deleted.NO);
        GlSubAccType save = glSubAccTypeRepository.save(glSubAccType);

        return convert(save);
    }

    @Override
    @Transactional
    public GlSubAccTypeResponse update(GlSubAccTypeUpdateRequest request) {

        GlSubAccType glSubAccType = glSubAccTypeRepository.findById(request.getId()).orElse(null);
        if (glSubAccType == null) {
            return null;
        }

        glSubAccType.setId(request.getId());
        glSubAccType.setCategoty(request.getCategoty());
        glSubAccType.setCategoryId(request.getCategoryId());
        glSubAccType.setCategoryAccount(request.getCategoryAccount());
        glSubAccType.setMainAccount(request.getMainAccount());
        glSubAccType.setMainAccountId(request.getMainAccountId());
        glSubAccType.setMainAccName(request.getMainAccName());
        glSubAccType.setName(request.getName());
        glSubAccType.setAccount(request.getAccount());
        GlSubAccType updated = glSubAccTypeRepository.save(glSubAccType);

        return (convert(updated));
    }

    @Override
    public GlSubAccTypeResponse getById(Long id) {

        return glSubAccTypeRepository.findById(id).map(GlSubAccTypeServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GlSubAccTypeResponse> getAll() {

        return glSubAccTypeRepository.findByIsDeleted(Deleted.NO)
                .stream().map(GlSubAccTypeServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        GlSubAccType got = glSubAccTypeRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        glSubAccTypeRepository.save(got);

        return 1;
    }

    @Override
    public List<GlSubAccTypeResponse> getByCatAndMain(Long catId, Long mainAccId) {
        return glSubAccTypeRepository.findByIsDeletedAndCategoryIdAndMainAccountId(Deleted.NO,catId,mainAccId)
                .stream().map(GlSubAccTypeServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<GlSubAccTypeResponse> getAllById(Long id) {
        List<GlSubAccTypeResponse> responseList = new ArrayList<>();
        GlSubAccTypeResponse glMainAccTypeResponse = glSubAccTypeRepository.findById(id).map(GlSubAccTypeServiceImpl::convert).orElse(null);
        responseList.add(glMainAccTypeResponse);
        return responseList;
    }


}