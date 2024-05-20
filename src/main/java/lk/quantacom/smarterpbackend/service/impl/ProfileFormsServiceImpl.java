package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.ProfileFormsRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileFormsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileFormsResponse;
import lk.quantacom.smarterpbackend.entity.ProfileForms;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.ProfileFormsRepository;
import lk.quantacom.smarterpbackend.service.ProfileFormsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileFormsServiceImpl implements ProfileFormsService {

    @Autowired
    private ProfileFormsRepository profileFormsRepository;


    @Override
    @Transactional
    public ProfileFormsResponse save(ProfileFormsRequest request) {

        ProfileForms profileForms = new ProfileForms();
        profileForms.setStaffno(request.getStaffno());
        profileForms.setLayoutname(request.getLayoutname());
        profileForms.setLastusedlayout(request.getLastusedlayout());
        profileForms.setModulename(request.getModulename());
        profileForms.setWidgetdata(request.getWidgetdata());
        profileForms.setLayoutdata(request.getLayoutdata());
        profileForms.setZoomsettings(request.getZoomsettings());
        profileForms.setWidgetsettings(request.getWidgetsettings());
        profileForms.setIsDeleted(Deleted.NO);
        ProfileForms save = profileFormsRepository.save(profileForms);

        return convert(save);
    }

    @Override
    @Transactional
    public ProfileFormsResponse update(ProfileFormsUpdateRequest request) {

        ProfileForms profileForms = profileFormsRepository.findById(request.getProfileid()).orElse(null);
        if (profileForms == null) {
            return null;
        }

        profileForms.setProfileid(request.getProfileid());
        profileForms.setProfileid(request.getProfileid());
        profileForms.setStaffno(request.getStaffno());
        profileForms.setLayoutname(request.getLayoutname());
        profileForms.setLastusedlayout(request.getLastusedlayout());
        profileForms.setModulename(request.getModulename());
        profileForms.setWidgetdata(request.getWidgetdata());
        profileForms.setLayoutdata(request.getLayoutdata());
        profileForms.setZoomsettings(request.getZoomsettings());
        profileForms.setWidgetsettings(request.getWidgetsettings());
        ProfileForms updated = profileFormsRepository.save(profileForms);

        return (convert(updated));
    }

    @Override
    public ProfileFormsResponse getById(Integer id) {

        return profileFormsRepository.findById(id).map(ProfileFormsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ProfileFormsResponse> getAll() {

        return profileFormsRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ProfileFormsServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Integer id) {

        ProfileForms got = profileFormsRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        profileFormsRepository.save(got);

        return 1;
    }

    private static ProfileFormsResponse convert(ProfileForms profileForms) {

        ProfileFormsResponse typeResponse = new ProfileFormsResponse();
        typeResponse.setProfileid(profileForms.getProfileid());
        typeResponse.setStaffno(profileForms.getStaffno());
        typeResponse.setLayoutname(profileForms.getLayoutname());
        typeResponse.setLastusedlayout(profileForms.getLastusedlayout());
        typeResponse.setModulename(profileForms.getModulename());
        typeResponse.setWidgetdata(profileForms.getWidgetdata());
        typeResponse.setLayoutdata(profileForms.getLayoutdata());
        typeResponse.setZoomsettings(profileForms.getZoomsettings());
        typeResponse.setWidgetsettings(profileForms.getWidgetsettings());
        typeResponse.setProfileid(profileForms.getProfileid());
        typeResponse.setCreatedBy(profileForms.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(profileForms.getCreatedDateTime()));
        typeResponse.setModifiedBy(profileForms.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(profileForms.getModifiedDateTime()));
        typeResponse.setIsDeleted(profileForms.getIsDeleted());

        return typeResponse;
    }
}