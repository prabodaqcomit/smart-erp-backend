package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.MainNavButtonsRequest;
import lk.quantacom.smarterpbackend.dto.request.MainNavButtonsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.MainNavButtonsResponse;
import lk.quantacom.smarterpbackend.entity.MainNavButtons;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.MainNavButtonsRepository;
import lk.quantacom.smarterpbackend.service.MainNavButtonsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainNavButtonsServiceImpl implements MainNavButtonsService {

    @Autowired
    private MainNavButtonsRepository mainNavButtonsRepository;



    @Override
@Transactional
    public MainNavButtonsResponse save(MainNavButtonsRequest request) {

        MainNavButtons mainNavButtons=new MainNavButtons();
mainNavButtons.setRootTabName(request.getRootTabName());
mainNavButtons.setOrderNo(request.getOrderNo());
mainNavButtons.setCaption(request.getCaption());
mainNavButtons.setIconHex(request.getIconHex());
mainNavButtons.setIconStyle(request.getIconStyle());
mainNavButtons.setRout(request.getRout());
mainNavButtons.setIsDeleted(Deleted.NO);
MainNavButtons save=mainNavButtonsRepository.save(mainNavButtons);

        return convert(save);
    }

    @Override
    @Transactional
    public MainNavButtonsResponse update(MainNavButtonsUpdateRequest request) {

MainNavButtons mainNavButtons = mainNavButtonsRepository.findById(request.getId()).orElse(null);
        if(mainNavButtons==null){
            return null;
        }

mainNavButtons.setId(request.getId());
mainNavButtons.setRootTabName(request.getRootTabName());
mainNavButtons.setOrderNo(request.getOrderNo());
mainNavButtons.setCaption(request.getCaption());
mainNavButtons.setIconHex(request.getIconHex());
mainNavButtons.setIconStyle(request.getIconStyle());
mainNavButtons.setRout(request.getRout());
MainNavButtons updated=mainNavButtonsRepository.save(mainNavButtons);

        return (convert(updated));
    }

    @Override
    public MainNavButtonsResponse getById(Long id) {

       return mainNavButtonsRepository.findById(id).map(MainNavButtonsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<MainNavButtonsResponse> getAll() {

        return  mainNavButtonsRepository.findByIsDeleted(Deleted.NO)
                .stream().map(MainNavButtonsServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

MainNavButtons got=mainNavButtonsRepository.findById(id).orElse(null);
        if(got==null){
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        mainNavButtonsRepository.save(got);

        return  1;
    }

private static MainNavButtonsResponse convert(MainNavButtons mainNavButtons){

        MainNavButtonsResponse typeResponse=new MainNavButtonsResponse();
typeResponse.setRootTabName(mainNavButtons.getRootTabName());
typeResponse.setOrderNo(mainNavButtons.getOrderNo());
typeResponse.setCaption(mainNavButtons.getCaption());
typeResponse.setIconHex(mainNavButtons.getIconHex());
typeResponse.setIconStyle(mainNavButtons.getIconStyle());
typeResponse.setRout(mainNavButtons.getRout());
        typeResponse.setId(mainNavButtons.getId());
        typeResponse.setCreatedBy(mainNavButtons.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(mainNavButtons.getCreatedDateTime()));
        typeResponse.setModifiedBy(mainNavButtons.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(mainNavButtons.getModifiedDateTime()));
        typeResponse.setIsDeleted(mainNavButtons.getIsDeleted());

return typeResponse;
    }
}