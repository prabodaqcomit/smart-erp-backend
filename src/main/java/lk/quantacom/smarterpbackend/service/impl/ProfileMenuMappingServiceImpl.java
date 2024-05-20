package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.ProfileMenuMappingRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileMenuMappingUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileMenuMappingResponse;
import lk.quantacom.smarterpbackend.entity.MenuItems;
import lk.quantacom.smarterpbackend.entity.Profile;
import lk.quantacom.smarterpbackend.entity.ProfileMenuMapping;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.ProfileMenuMappingRepository;
import lk.quantacom.smarterpbackend.service.ProfileMenuMappingService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProfileMenuMappingServiceImpl implements ProfileMenuMappingService {

    @Autowired
    private ProfileMenuMappingRepository profileMenuMappingRepository;


    @Override
    @Transactional
    public List<ProfileMenuMappingResponse> save(List<ProfileMenuMappingRequest> requestList) {

        List<ProfileMenuMappingResponse> responses = new ArrayList<>();

        for (ProfileMenuMappingRequest request : requestList) {
            ProfileMenuMapping profileMenuMapping = new ProfileMenuMapping();

            Profile profile = new Profile();
            profile.setId(request.getProfileId());
            profileMenuMapping.setProfile(profile);

            MenuItems menuItems = new MenuItems();
            menuItems.setId(request.getMenuId());
            profileMenuMapping.setMenu(menuItems);

            profileMenuMapping.setIsDeleted(Deleted.NO);
            ProfileMenuMapping save = profileMenuMappingRepository.save(profileMenuMapping);

            responses.add(convert(save));
        }


        return responses;
    }

    @Override
    @Transactional
    public ProfileMenuMappingResponse update(ProfileMenuMappingUpdateRequest request) {

        ProfileMenuMapping profileMenuMapping = profileMenuMappingRepository.findById(request.getId()).orElse(null);
        if (profileMenuMapping == null) {
            return null;
        }

        profileMenuMapping.setId(request.getId());
        Profile profile = new Profile();
        profile.setId(request.getProfileId());
        profileMenuMapping.setProfile(profile);

        MenuItems menuItems = new MenuItems();
        menuItems.setId(request.getMenuId());
        profileMenuMapping.setMenu(menuItems);

        ProfileMenuMapping updated = profileMenuMappingRepository.save(profileMenuMapping);

        return (convert(updated));
    }

    @Override
    public ProfileMenuMappingResponse getById(Long id) {

        return profileMenuMappingRepository.findById(id).map(ProfileMenuMappingServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ProfileMenuMappingResponse> getAll() {

        return profileMenuMappingRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ProfileMenuMappingServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<ProfileMenuMappingResponse> getByProfile(Integer profileId) {
        Profile profile = new Profile();
        profile.setId(profileId);
        return profileMenuMappingRepository.findByProfileAndIsDeleted(profile, Deleted.NO)
                .stream().map(ProfileMenuMappingServiceImpl::convert).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        ProfileMenuMapping got = profileMenuMappingRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        profileMenuMappingRepository.save(got);
        Long menuId = got.getMenu().getId();
//        Integer profId = got.getProfile().getId();

//        List<ProfileMenuMapping> list =
//                profileMenuMappingRepository.findByProfileAndIsDeleted(got.getProfile(), Deleted.NO);
//        if (list != null) {
//            for (ProfileMenuMapping mapping : list) {
//                if (Objects.equals(mapping.getMenu().getParentId(), menuId)) {
//                    mapping.setIsDeleted(Deleted.YES);
//                    profileMenuMappingRepository.save(mapping);
//                }
//            }
//        }
        List<ProfileMenuMapping> list =
                profileMenuMappingRepository.findByIsDeleted(Deleted.NO);

        deleteSubMenus(list,got.getProfile().getId(), menuId);
        deleteSubProfileMenus(list,got.getProfile().getId(), menuId);

//        List<ProfileMenuMapping> list2 =
//                profileMenuMappingRepository.findByMenuAndIsDeleted(got.getMenu(), Deleted.NO);
//
//        if (list2 != null) {
//            for (ProfileMenuMapping mapping : list2) {
//                if (Objects.equals(mapping.getProfile().getParentid(), profId)) {
//                    mapping.setIsDeleted(Deleted.YES);
//                    profileMenuMappingRepository.save(mapping);
//                }
//            }
//        }

        return 1;
    }

    void deleteSubMenus(List<ProfileMenuMapping> list,Integer profileId, Long menuId) {


        for (ProfileMenuMapping mapping : list) {
            if (Objects.equals(mapping.getMenu().getParentId(), menuId) &&
                    Objects.equals(mapping.getProfile().getId(), profileId)) {
                mapping.setIsDeleted(Deleted.YES);
                profileMenuMappingRepository.save(mapping);
                deleteSubMenus(list, profileId, mapping.getMenu().getId());
            }
        }

    }

    void deleteSubProfileMenus(List<ProfileMenuMapping> list,Integer profileId, Long menuId) {

        for (ProfileMenuMapping mapping : list) {
            if (Objects.equals(mapping.getMenu().getId(), menuId) &&
                    Objects.equals(mapping.getProfile().getParentid(), profileId)) {
                mapping.setIsDeleted(Deleted.YES);
                profileMenuMappingRepository.save(mapping);
                deleteSubMenus(list,profileId,menuId);
                deleteSubProfileMenus(list,mapping.getProfile().getId(),menuId);
            }
        }

    }

    private static ProfileMenuMappingResponse convert(ProfileMenuMapping profileMenuMapping) {

        ProfileMenuMappingResponse typeResponse = new ProfileMenuMappingResponse();
        typeResponse.setProfileId(profileMenuMapping.getProfile().getId());
        typeResponse.setProfileName(profileMenuMapping.getProfile().getProfilename());
        typeResponse.setParentProfileId(profileMenuMapping.getProfile().getParentid());
        typeResponse.setMenuId(profileMenuMapping.getMenu().getId());
        typeResponse.setMenuItemName(profileMenuMapping.getMenu().getMenuName());
        typeResponse.setParentMenuId(profileMenuMapping.getMenu().getParentId());
        typeResponse.setId(profileMenuMapping.getId());
        typeResponse.setCreatedBy(profileMenuMapping.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(profileMenuMapping.getCreatedDateTime()));
        typeResponse.setModifiedBy(profileMenuMapping.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(profileMenuMapping.getModifiedDateTime()));
        typeResponse.setIsDeleted(profileMenuMapping.getIsDeleted());

        return typeResponse;
    }
}