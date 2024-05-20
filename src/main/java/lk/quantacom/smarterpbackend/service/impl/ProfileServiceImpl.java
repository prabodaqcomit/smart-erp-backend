package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.ProfileRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.MenuItemsResponse;
import lk.quantacom.smarterpbackend.dto.response.ProfileResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.ProfileService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private MenuItemsRepository menuItemsRepository;

    @Autowired
    private ProfileMenuMappingRepository profileMenuMappingRepository;

    @Autowired
    private ProfileTabFormMapRepository profileTabFormMapRepository;

    @Autowired
    private ProfileFieldMappingRepository profileFieldMappingRepository;

    @Autowired
    private ActionFieldRepository actionFieldRepository;

    @Autowired
    private TabFormRepository tabFormRepository;


    @Override
    @Transactional
    public ProfileResponse save(ProfileRequest request) {

        Profile profile = new Profile();
        //profile.setId(request.getId());
        profile.setProfilename(request.getProfilename());
        profile.setDescription(request.getDescription());
        profile.setProfiletype(request.getProfiletype());
        profile.setPathref(request.getPathref());
        profile.setHashval(request.getHashval());
        profile.setParentid(request.getParentid());
        profile.setTokenExpiration(request.getTokenExpiration());
        profile.setIsDeleted(Deleted.NO);
        Profile save = profileRepository.save(profile);

        // assign parent mappings
        if (request.getParentid() != -1) {

            // menu items
            //Profile parentProfile =  profileRepository.findById(request.getParentid()).orElse(null);
            List<ProfileMenuMapping> menuMappings =
                    profileMenuMappingRepository.getByProfile(request.getParentid());
            if (menuMappings != null) {
                for (ProfileMenuMapping mapping : menuMappings) {
                    MenuItems menuItems= menuItemsRepository.findById(mapping.getMenu().getId()).orElse(null);
                    if(menuItems!=null){
                        ProfileMenuMapping map = new ProfileMenuMapping();
                        map.setProfile(save);
                        map.setMenu(menuItems);
                        map.setIsDeleted(Deleted.NO);
                        profileMenuMappingRepository.save(map);
                    }

                }
            }

            // forms
            System.out.println("parent id --- "+request.getParentid());
            List<ProfileTabFormMap> tabFormMaps =
                    profileTabFormMapRepository.getByProfile(request.getParentid());
            if (tabFormMaps != null) {
                for (ProfileTabFormMap mapping : tabFormMaps) {
                    System.out.println("tab form id --- "+mapping.getTabForm().getId());
                    TabForm tabForm= tabFormRepository.findById(mapping.getTabForm().getId()).orElse(null);
                    if(tabForm!=null){
                        ProfileTabFormMap map = new ProfileTabFormMap();
                        map.setProfile(save);
                        map.setTabForm(tabForm);
                        map.setIsDeleted(Deleted.NO);
                        profileTabFormMapRepository.save(map);
                    }
                }
            }

            // actions
            List<ProfileFieldMapping> fieldMappings =
                    profileFieldMappingRepository.getByProfile(request.getParentid());
            if (fieldMappings != null) {
                for (ProfileFieldMapping mapping : fieldMappings) {
                    ActionField act=actionFieldRepository.findById(mapping.getActionField().getId()).orElse(null);
                    if(act!=null){
                        ProfileFieldMapping map = new ProfileFieldMapping();
                        map.setProfile(save);
                        map.setActionField(act);
                        map.setIsDeleted(Deleted.NO);
                        profileFieldMappingRepository.save(map);
                    }

                }
            }


        }

        return convert(save);
    }


    @Override
    @Transactional
    public ProfileResponse update(ProfileUpdateRequest request) {

        Profile profile = profileRepository.findById(request.getId()).orElse(null);
        if (profile == null) {
            return null;
        }

        profile.setId(request.getId());
        profile.setId(request.getId());
        profile.setProfilename(request.getProfilename());
        profile.setDescription(request.getDescription());
        profile.setProfiletype(request.getProfiletype());
        profile.setPathref(request.getPathref());
        profile.setHashval(request.getHashval());
        profile.setParentid(request.getParentid());
        profile.setTokenExpiration(request.getTokenExpiration());
        Profile updated = profileRepository.save(profile);

        return (convert(updated));
    }

    @Override
    public ProfileResponse getById(Integer id) {

        return profileRepository.findById(id).map(ProfileServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ProfileResponse> getAll() {

        return profileRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ProfileServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Integer id) {

        Profile got = profileRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        profileRepository.save(got);

        return 1;
    }


    private static ProfileResponse convert(Profile profile) {

        ProfileResponse typeResponse = new ProfileResponse();
        typeResponse.setId(profile.getId());
        typeResponse.setProfilename(profile.getProfilename());
        typeResponse.setDescription(profile.getDescription());
        typeResponse.setProfiletype(profile.getProfiletype());
        typeResponse.setPathref(profile.getPathref());
        typeResponse.setHashval(profile.getHashval());
        typeResponse.setParentid(profile.getParentid());
        typeResponse.setId(profile.getId());
        typeResponse.setCreatedBy(profile.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(profile.getCreatedDateTime()));
        typeResponse.setModifiedBy(profile.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(profile.getModifiedDateTime()));
        typeResponse.setTokenExpiration(profile.getTokenExpiration());
        typeResponse.setIsDeleted(profile.getIsDeleted());

        if (profile.getMenuMapping() != null) {
            List<MenuItemsResponse> menuItems = new ArrayList<>();
            for (ProfileMenuMapping menuMapping : profile.getMenuMapping()) {
                menuItems.add(convert(menuMapping.getMenu()));
            }
            typeResponse.setMenuItems(menuItems);
        }

        return typeResponse;
    }

    private static MenuItemsResponse convert(MenuItems menuItems) {

        MenuItemsResponse typeResponse = new MenuItemsResponse();
        typeResponse.setParentId(menuItems.getParentId());
        typeResponse.setMenuName(menuItems.getMenuName());
        typeResponse.setIconHex(menuItems.getIconHex());
        typeResponse.setIconStyle(menuItems.getIconStyle());
        typeResponse.setRout(menuItems.getRout());
        typeResponse.setId(menuItems.getId());
        typeResponse.setCreatedBy(menuItems.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(menuItems.getCreatedDateTime()));
        typeResponse.setModifiedBy(menuItems.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(menuItems.getModifiedDateTime()));
        typeResponse.setIsDeleted(menuItems.getIsDeleted());

        return typeResponse;
    }
}