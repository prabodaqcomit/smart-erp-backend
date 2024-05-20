package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.ProfileTabFormMapRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileTabFormMapUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileTabFormMapResponse;
import lk.quantacom.smarterpbackend.dto.response.TabFormResponse;
import lk.quantacom.smarterpbackend.entity.Profile;
import lk.quantacom.smarterpbackend.entity.ProfileTabFormMap;
import lk.quantacom.smarterpbackend.entity.TabForm;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.ProfileTabFormMapRepository;
import lk.quantacom.smarterpbackend.service.ProfileTabFormMapService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProfileTabFormMapServiceImpl implements ProfileTabFormMapService {

    @Autowired
    private ProfileTabFormMapRepository profileTabFormMapRepository;


    @Override
    @Transactional
    public List<ProfileTabFormMapResponse> save(List<ProfileTabFormMapRequest> requestList) {

        List<ProfileTabFormMapResponse> responses = new ArrayList<>();

        for (ProfileTabFormMapRequest request : requestList) {
            ProfileTabFormMap profileTabFormMap = new ProfileTabFormMap();
            Profile prf = new Profile();
            prf.setId(request.getProfileId());
            profileTabFormMap.setProfile(prf);
            TabForm tbf = new TabForm();
            tbf.setId(request.getTabFormId());
            profileTabFormMap.setTabForm(tbf);
            profileTabFormMap.setIsDeleted(Deleted.NO);
            ProfileTabFormMap save = profileTabFormMapRepository.save(profileTabFormMap);
            responses.add(convert(save));
        }

        return responses;
    }

    @Override
    @Transactional
    public ProfileTabFormMapResponse update(ProfileTabFormMapUpdateRequest request) {

        ProfileTabFormMap profileTabFormMap = profileTabFormMapRepository.findById(request.getId()).orElse(null);
        if (profileTabFormMap == null) {
            return null;
        }

        profileTabFormMap.setId(request.getId());
        Profile prf = new Profile();
        prf.setId(request.getProfileId());
        profileTabFormMap.setProfile(prf);
        TabForm tbf = new TabForm();
        tbf.setId(request.getTabFormId());
        profileTabFormMap.setTabForm(tbf);
        ProfileTabFormMap updated = profileTabFormMapRepository.save(profileTabFormMap);

        return (convert(updated));
    }

    @Override
    public ProfileTabFormMapResponse getById(Long id) {

        return profileTabFormMapRepository.findById(id).map(ProfileTabFormMapServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ProfileTabFormMapResponse> getAll() {

        return profileTabFormMapRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ProfileTabFormMapServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<TabFormResponse> getByProfile(Integer profileId) {

        Profile profile = new Profile();
        profile.setId(profileId);

        List<TabFormResponse> responses = new ArrayList<>();
        List<ProfileTabFormMap> list = profileTabFormMapRepository.findByProfileAndIsDeleted(profile, Deleted.NO);

        for (ProfileTabFormMap tabFormMap : list) {
            if (tabFormMap.getTabForm().getIsDeleted() == Deleted.NO) {
                responses.add(convert(tabFormMap.getTabForm()));
            }
        }

        return responses;
    }


    @Override
    @Transactional
    public Integer delete(Integer profileId,Long formId) {

        Profile profile=new Profile();
        profile.setId(profileId);

        TabForm tabForm=new TabForm();
        tabForm.setId(formId);

        ProfileTabFormMap got = profileTabFormMapRepository
                .findByProfileAndTabFormAndIsDeleted(profile,tabForm,Deleted.NO);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        profileTabFormMapRepository.save(got);

        List<ProfileTabFormMap> list= profileTabFormMapRepository
                .findByTabFormAndIsDeleted(tabForm,Deleted.NO);

        deleteSubProfileMenus(list,profileId,formId);


        return 1;
    }

    void deleteSubProfileMenus(List<ProfileTabFormMap> list, Integer profileId, Long formId) {

        for (ProfileTabFormMap mapping : list) {
            if (Objects.equals(mapping.getTabForm().getId(), formId) &&
                    Objects.equals(mapping.getProfile().getParentid(), profileId)) {
                mapping.setIsDeleted(Deleted.YES);
                profileTabFormMapRepository.save(mapping);
                deleteSubProfileMenus(list,mapping.getProfile().getId(),formId);
            }
        }

    }

    private static ProfileTabFormMapResponse convert(ProfileTabFormMap profileTabFormMap) {

        ProfileTabFormMapResponse typeResponse = new ProfileTabFormMapResponse();
        typeResponse.setProfileId(profileTabFormMap.getProfile().getId());
        typeResponse.setTabFormId(profileTabFormMap.getTabForm().getId());
        typeResponse.setId(profileTabFormMap.getId());
        typeResponse.setCreatedBy(profileTabFormMap.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(profileTabFormMap.getCreatedDateTime()));
        typeResponse.setModifiedBy(profileTabFormMap.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(profileTabFormMap.getModifiedDateTime()));
        typeResponse.setIsDeleted(profileTabFormMap.getIsDeleted());

        return typeResponse;
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