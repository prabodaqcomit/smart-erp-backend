package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.ModuleNamesRequest;
import lk.quantacom.smarterpbackend.dto.request.ModuleNamesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ModuleNamesResponse;
import lk.quantacom.smarterpbackend.entity.ModuleNames;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.ModuleNamesRepository;
import lk.quantacom.smarterpbackend.service.ModuleNamesService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModuleNamesServiceImpl implements ModuleNamesService {

    @Autowired
    private ModuleNamesRepository moduleNamesRepository;



    @Override
@Transactional
    public ModuleNamesResponse save(ModuleNamesRequest request) {

        ModuleNames moduleNames=new ModuleNames();
moduleNames.setName(request.getName());
moduleNames.setIsDeleted(Deleted.NO);
ModuleNames save=moduleNamesRepository.save(moduleNames);

        return convert(save);
    }

    @Override
    @Transactional
    public ModuleNamesResponse update(ModuleNamesUpdateRequest request) {

ModuleNames moduleNames = moduleNamesRepository.findById(request.getId()).orElse(null);
        if(moduleNames==null){
            return null;
        }

moduleNames.setId(request.getId());
moduleNames.setName(request.getName());
ModuleNames updated=moduleNamesRepository.save(moduleNames);

        return (convert(updated));
    }

    @Override
    public ModuleNamesResponse getById(Long id) {

       return moduleNamesRepository.findById(id).map(ModuleNamesServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ModuleNamesResponse> getAll() {

        return  moduleNamesRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ModuleNamesServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

ModuleNames got=moduleNamesRepository.findById(id).orElse(null);
        if(got==null){
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        moduleNamesRepository.save(got);

        return  1;
    }

private static ModuleNamesResponse convert(ModuleNames moduleNames){

        ModuleNamesResponse typeResponse=new ModuleNamesResponse();
typeResponse.setName(moduleNames.getName());
        typeResponse.setId(moduleNames.getId());
        typeResponse.setCreatedBy(moduleNames.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(moduleNames.getCreatedDateTime()));
        typeResponse.setModifiedBy(moduleNames.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(moduleNames.getModifiedDateTime()));
        typeResponse.setIsDeleted(moduleNames.getIsDeleted());

return typeResponse;
    }
}