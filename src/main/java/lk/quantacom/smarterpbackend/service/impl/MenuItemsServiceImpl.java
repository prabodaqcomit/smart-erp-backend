package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.MenuItemsRequest;
import lk.quantacom.smarterpbackend.dto.request.MenuItemsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.MenuItemsResponse;
import lk.quantacom.smarterpbackend.entity.MenuItems;
import lk.quantacom.smarterpbackend.entity.ProfileMenuMapping;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.MenuItemsRepository;
import lk.quantacom.smarterpbackend.repository.ProfileMenuMappingRepository;
import lk.quantacom.smarterpbackend.service.MenuItemsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemsServiceImpl implements MenuItemsService {

    @Autowired
    private MenuItemsRepository menuItemsRepository;

    @Autowired
    private ProfileMenuMappingRepository profileMenuMappingRepository;


    @Override
    @Transactional
    public MenuItemsResponse save(MenuItemsRequest request) {

        MenuItems menuItems = new MenuItems();
        menuItems.setParentId(request.getParentId());
        menuItems.setParentMenuName(request.getParentMenuName());
        menuItems.setMenuName(request.getMenuName());
        menuItems.setIconHex(request.getIconHex());
        menuItems.setIconStyle(request.getIconStyle());
        menuItems.setRout(request.getRout());
        menuItems.setIsDeleted(Deleted.NO);
        menuItems.setOrderNo(request.getOrderNo());
        menuItems.setIsPopup(request.getIsPopup());
        MenuItems save = menuItemsRepository.save(menuItems);

        // assign parent mappings
        if(menuItems.getParentId()!=0){
            MenuItems parentMenu=new MenuItems();
            parentMenu.setId(menuItems.getParentId());
            List<ProfileMenuMapping> parentMap=profileMenuMappingRepository.findByMenuAndIsDeleted(parentMenu,Deleted.NO);
            for(ProfileMenuMapping exMap:parentMap){

                ProfileMenuMapping newMap=new ProfileMenuMapping();
                newMap.setMenu(save);
                newMap.setProfile(exMap.getProfile());
                newMap.setIsDeleted(Deleted.NO);
                profileMenuMappingRepository.save(newMap);
            }
        }

        return convert(save);
    }

    @Override
    @Transactional
    public MenuItemsResponse update(MenuItemsUpdateRequest request) {

        MenuItems menuItems = menuItemsRepository.findById(request.getId()).orElse(null);
        if (menuItems == null) {
            return null;
        }

        menuItems.setId(request.getId());
        menuItems.setParentId(request.getParentId());
        menuItems.setParentMenuName(request.getParentMenuName());
        menuItems.setMenuName(request.getMenuName());
        menuItems.setIconHex(request.getIconHex());
        menuItems.setIconStyle(request.getIconStyle());
        menuItems.setRout(request.getRout());
        menuItems.setOrderNo(request.getOrderNo());
        menuItems.setIsPopup(request.getIsPopup());
        MenuItems updated = menuItemsRepository.save(menuItems);



        return (convert(updated));
    }

    @Override
    public MenuItemsResponse getById(Long id) {

        return menuItemsRepository.findById(id).map(MenuItemsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<MenuItemsResponse> getAll() {

        return menuItemsRepository.findByIsDeletedOrderByOrderNoAsc(Deleted.NO)
                .stream().map(MenuItemsServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        MenuItems got = menuItemsRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        menuItemsRepository.save(got);

        return 1;
    }

    private static MenuItemsResponse convert(MenuItems menuItems) {

        MenuItemsResponse typeResponse = new MenuItemsResponse();
        typeResponse.setParentId(menuItems.getParentId());
        typeResponse.setParentMenuName(menuItems.getParentMenuName());
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
        typeResponse.setIsPopup(menuItems.getIsPopup());
        typeResponse.setOrderNo(menuItems.getOrderNo());

        return typeResponse;
    }
}