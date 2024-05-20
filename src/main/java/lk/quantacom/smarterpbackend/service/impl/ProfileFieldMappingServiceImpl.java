package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.ProfileFieldMappingRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileFieldMappingUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ActionFieldResponse;
import lk.quantacom.smarterpbackend.dto.response.ProfileFieldMappingResponse;
import lk.quantacom.smarterpbackend.entity.ActionField;
import lk.quantacom.smarterpbackend.entity.Profile;
import lk.quantacom.smarterpbackend.entity.ProfileFieldMapping;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.ProfileFieldMappingRepository;
import lk.quantacom.smarterpbackend.service.ProfileFieldMappingService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProfileFieldMappingServiceImpl implements ProfileFieldMappingService {

    @Autowired
    private ProfileFieldMappingRepository profileFieldMappingRepository;


    @Override
    @Transactional
    public List<ProfileFieldMappingResponse> save(List<ProfileFieldMappingRequest> requestList) {

        List<ProfileFieldMappingResponse> responses = new ArrayList<>();

        for (ProfileFieldMappingRequest request : requestList) {
            ProfileFieldMapping profileFieldMapping = new ProfileFieldMapping();
            Profile profile = new Profile();
            profile.setId(request.getProfileId());
            profileFieldMapping.setProfile(profile);
            ActionField actionField = new ActionField();
            actionField.setId(request.getActionFieldId());
            profileFieldMapping.setActionField(actionField);
            profileFieldMapping.setIsDeleted(Deleted.NO);
            ProfileFieldMapping save = profileFieldMappingRepository.save(profileFieldMapping);

            responses.add(convert(save));
        }

        return responses;
    }

    @Override
    @Transactional
    public ProfileFieldMappingResponse update(ProfileFieldMappingUpdateRequest request) {

        ProfileFieldMapping profileFieldMapping = profileFieldMappingRepository.findById(request.getId()).orElse(null);
        if (profileFieldMapping == null) {
            return null;
        }

        profileFieldMapping.setId(request.getId());
        Profile profile = new Profile();
        profile.setId(request.getProfileId());
        profileFieldMapping.setProfile(profile);
        ActionField actionField = new ActionField();
        actionField.setId(request.getActionFieldId());
        profileFieldMapping.setActionField(actionField);
        ProfileFieldMapping updated = profileFieldMappingRepository.save(profileFieldMapping);

        return (convert(updated));
    }

    @Override
    public ProfileFieldMappingResponse getById(Long id) {

        return profileFieldMappingRepository.findById(id).map(ProfileFieldMappingServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ProfileFieldMappingResponse> getAll() {

        return profileFieldMappingRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ProfileFieldMappingServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<ActionFieldResponse> getFields(Integer profileId) {

        Profile profile = new Profile();
        profile.setId(profileId);

        List<ActionFieldResponse> responses = new ArrayList<>();

        List<ProfileFieldMapping> list = profileFieldMappingRepository
                .findByProfileAndIsDeleted(profile, Deleted.NO);
        for (ProfileFieldMapping mapping : list) {
            if (mapping.getActionField().getIsDeleted() == Deleted.NO) {
                responses.add(convert(mapping.getActionField()));
            }
        }

        return responses;
    }


    @Override
    @Transactional
    public Integer delete(Integer profileId, Long fieldId) {

        Profile profile = new Profile();
        profile.setId(profileId);

        ActionField actionField = new ActionField();
        actionField.setId(fieldId);

        ProfileFieldMapping got = profileFieldMappingRepository
                .findByProfileAndActionFieldAndIsDeleted(profile, actionField, Deleted.NO);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        profileFieldMappingRepository.save(got);

        List<ProfileFieldMapping> list = profileFieldMappingRepository
                .findByActionFieldAndIsDeleted(actionField, Deleted.NO);

        deleteSubProfileMenus(list, profileId, fieldId);


        return 1;
    }

    void deleteSubProfileMenus(List<ProfileFieldMapping> list, Integer profileId, Long fieldId) {

        for (ProfileFieldMapping mapping : list) {
            if (Objects.equals(mapping.getActionField().getId(), fieldId) &&
                    Objects.equals(mapping.getProfile().getParentid(), profileId)) {
                mapping.setIsDeleted(Deleted.YES);
                profileFieldMappingRepository.save(mapping);
                deleteSubProfileMenus(list, mapping.getProfile().getId(), fieldId);
            }
        }

    }

    private static ProfileFieldMappingResponse convert(ProfileFieldMapping profileFieldMapping) {

        ProfileFieldMappingResponse typeResponse = new ProfileFieldMappingResponse();
        typeResponse.setProfileId(profileFieldMapping.getProfile().getId());
        typeResponse.setActionFieldId(profileFieldMapping.getActionField().getId());
        typeResponse.setId(profileFieldMapping.getId());
        typeResponse.setCreatedBy(profileFieldMapping.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(profileFieldMapping.getCreatedDateTime()));
        typeResponse.setModifiedBy(profileFieldMapping.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(profileFieldMapping.getModifiedDateTime()));
        typeResponse.setIsDeleted(profileFieldMapping.getIsDeleted());

        return typeResponse;
    }

    private static ActionFieldResponse convert(ActionField actionField) {

        ActionFieldResponse typeResponse = new ActionFieldResponse();
        typeResponse.setActionAlias(actionField.getActionAlias());
        typeResponse.setActionDescription(actionField.getActionDescription());
        typeResponse.setReadOnly(actionField.getReadOnly());
        typeResponse.setIsInputUpperCase(actionField.getIsInputUpperCase());
        typeResponse.setIsHidden(actionField.getIsHidden());
        typeResponse.setReferenceAlias(actionField.getReferenceAlias());
        typeResponse.setId(actionField.getId());
        typeResponse.setCreatedBy(actionField.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(actionField.getCreatedDateTime()));
        typeResponse.setModifiedBy(actionField.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(actionField.getModifiedDateTime()));
        typeResponse.setIsDeleted(actionField.getIsDeleted());

        return typeResponse;
    }
}