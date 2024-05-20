package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.MasterDataTypesRequest;
import lk.quantacom.smarterpbackend.dto.request.MasterDataTypesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.MasterDataTypesResponse;
import lk.quantacom.smarterpbackend.entity.MasterDataTypes;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.MasterDataTypesRepository;
import lk.quantacom.smarterpbackend.service.MasterDataTypesService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterDataTypesServiceImpl implements MasterDataTypesService {

    @Autowired
    private MasterDataTypesRepository masterDataTypesRepository;



    @Override
@Transactional
    public MasterDataTypesResponse save(MasterDataTypesRequest request) {

        MasterDataTypes masterDataTypes=new MasterDataTypes();
masterDataTypes.setName(request.getName());
masterDataTypes.setIsDeleted(Deleted.NO);
MasterDataTypes save=masterDataTypesRepository.save(masterDataTypes);

        return convert(save);
    }

    @Override
    @Transactional
    public MasterDataTypesResponse update(MasterDataTypesUpdateRequest request) {

MasterDataTypes masterDataTypes = masterDataTypesRepository.findById(request.getId()).orElse(null);
        if(masterDataTypes==null){
            return null;
        }

masterDataTypes.setId(request.getId());
masterDataTypes.setName(request.getName());
MasterDataTypes updated=masterDataTypesRepository.save(masterDataTypes);

        return (convert(updated));
    }

    @Override
    public MasterDataTypesResponse getById(Long id) {

       return masterDataTypesRepository.findById(id).map(MasterDataTypesServiceImpl::convert).orElse(null);
    }

    @Override
    public List<MasterDataTypesResponse> getAll() {

        return  masterDataTypesRepository.findByIsDeleted(Deleted.NO)
                .stream().map(MasterDataTypesServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

MasterDataTypes got=masterDataTypesRepository.findById(id).orElse(null);
        if(got==null){
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        masterDataTypesRepository.save(got);

        return  1;
    }

private static MasterDataTypesResponse convert(MasterDataTypes masterDataTypes){

        MasterDataTypesResponse typeResponse=new MasterDataTypesResponse();
typeResponse.setName(masterDataTypes.getName());
        typeResponse.setId(masterDataTypes.getId());
        typeResponse.setCreatedBy(masterDataTypes.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(masterDataTypes.getCreatedDateTime()));
        typeResponse.setModifiedBy(masterDataTypes.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(masterDataTypes.getModifiedDateTime()));
        typeResponse.setIsDeleted(masterDataTypes.getIsDeleted());

return typeResponse;
    }
}