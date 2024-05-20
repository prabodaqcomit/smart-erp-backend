package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.TabFormRequest;
import lk.quantacom.smarterpbackend.dto.request.TabFormUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TabFormResponse;
import lk.quantacom.smarterpbackend.entity.TabForm;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.TabFormRepository;
import lk.quantacom.smarterpbackend.service.TabFormService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TabFormServiceImpl implements TabFormService {

    @Autowired
    private TabFormRepository tabFormRepository;


    @Override
    @Transactional
    public TabFormResponse save(TabFormRequest request) {

        TabForm tabForm = new TabForm();
        tabForm.setFormAlias(request.getFormAlias());
        tabForm.setRootFormAlias(request.getRootFormAlias());
        tabForm.setIsNavTab(request.getIsNavTab());
        tabForm.setIconHex(request.getIconHex());
        tabForm.setIconStyle(request.getIconStyle());
        tabForm.setNavIndex(request.getNavIndex());
        tabForm.setFormName(request.getFormName());
        tabForm.setIsDeleted(Deleted.NO);
        TabForm save = tabFormRepository.save(tabForm);

        return convert(save);
    }

    @Override
    @Transactional
    public TabFormResponse update(TabFormUpdateRequest request) {

        TabForm tabForm = tabFormRepository.findById(request.getId()).orElse(null);
        if (tabForm == null) {
            return null;
        }

        tabForm.setId(request.getId());
        tabForm.setFormAlias(request.getFormAlias());
        tabForm.setRootFormAlias(request.getRootFormAlias());
        tabForm.setIsNavTab(request.getIsNavTab());
        tabForm.setIconHex(request.getIconHex());
        tabForm.setIconStyle(request.getIconStyle());
        tabForm.setNavIndex(request.getNavIndex());
        tabForm.setFormName(request.getFormName());
        TabForm updated = tabFormRepository.save(tabForm);

        return (convert(updated));
    }

    @Override
    public TabFormResponse getById(Long id) {

        return tabFormRepository.findById(id).map(TabFormServiceImpl::convert).orElse(null);
    }

    @Override
    public List<TabFormResponse> getAll() {

        return tabFormRepository.findByIsDeleted(Deleted.NO)
                .stream().map(TabFormServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        TabForm got = tabFormRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        tabFormRepository.save(got);

        return 1;
    }

    private static TabFormResponse convert(TabForm tabForm) {

        TabFormResponse typeResponse = new TabFormResponse();
        typeResponse.setFormAlias(tabForm.getFormAlias());
        typeResponse.setRootFormAlias(tabForm.getRootFormAlias());
        typeResponse.setIsNavTab(tabForm.getIsNavTab());
        typeResponse.setIconHex(tabForm.getIconHex());
        typeResponse.setIconStyle(tabForm.getIconStyle());
        typeResponse.setNavIndex(tabForm.getNavIndex());
        typeResponse.setFormName(tabForm.getFormName());
        typeResponse.setId(tabForm.getId());
        typeResponse.setCreatedBy(tabForm.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(tabForm.getCreatedDateTime()));
        typeResponse.setModifiedBy(tabForm.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(tabForm.getModifiedDateTime()));
        typeResponse.setIsDeleted(tabForm.getIsDeleted());

        return typeResponse;
    }
}