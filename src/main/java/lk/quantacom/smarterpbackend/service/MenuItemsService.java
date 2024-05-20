package lk.quantacom.smarterpbackend.service;


import lk.quantacom.smarterpbackend.dto.request.MenuItemsRequest;
import lk.quantacom.smarterpbackend.dto.request.MenuItemsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.MenuItemsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuItemsService {

    MenuItemsResponse save(MenuItemsRequest request);

    MenuItemsResponse update(MenuItemsUpdateRequest request);

    MenuItemsResponse getById(Long id);

    List<MenuItemsResponse> getAll();


    Integer delete(Long id);
}