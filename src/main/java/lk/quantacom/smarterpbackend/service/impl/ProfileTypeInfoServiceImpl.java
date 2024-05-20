package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.ProfileTypeInfoRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileTypeInfoUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileTypeInfoResponse;
import lk.quantacom.smarterpbackend.entity.ProfileTypeInfo;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.ProfileTypeInfoRepository;
import lk.quantacom.smarterpbackend.service.ProfileTypeInfoService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileTypeInfoServiceImpl implements ProfileTypeInfoService {

    @Autowired
    private ProfileTypeInfoRepository profileTypeInfoRepository;



    @Override
    @Transactional
    public ProfileTypeInfoResponse save(ProfileTypeInfoRequest request) {

        ProfileTypeInfo profileTypeInfo=new ProfileTypeInfo();
        profileTypeInfo.setId(request.getId());
        profileTypeInfo.setTypename(request.getTypename());
        profileTypeInfo.setDatatype(request.getDatatype());
        profileTypeInfo.setTypesql(request.getTypesql());
        profileTypeInfo.setIsDeleted(Deleted.NO);
        ProfileTypeInfo save=profileTypeInfoRepository.save(profileTypeInfo);

        return convert(save);
    }

    @Override
    @Transactional
    public ProfileTypeInfoResponse update(ProfileTypeInfoUpdateRequest request) {

        ProfileTypeInfo profileTypeInfo = profileTypeInfoRepository.findById(request.getId()).orElse(null);
        if(profileTypeInfo==null){
            return null;
        }

        profileTypeInfo.setId(request.getId());
        profileTypeInfo.setId(request.getId());
        profileTypeInfo.setTypename(request.getTypename());
        profileTypeInfo.setDatatype(request.getDatatype());
        profileTypeInfo.setTypesql(request.getTypesql());
        ProfileTypeInfo updated=profileTypeInfoRepository.save(profileTypeInfo);

        return (convert(updated));
    }

    @Override
    public ProfileTypeInfoResponse getById(Integer id) {

        return profileTypeInfoRepository.findById(id).map(ProfileTypeInfoServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ProfileTypeInfoResponse> getAll() {

        return  profileTypeInfoRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ProfileTypeInfoServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Integer id) {

        ProfileTypeInfo got=profileTypeInfoRepository.findById(id).orElse(null);
        if(got==null){
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        profileTypeInfoRepository.save(got);

        return  1;
    }

    private static ProfileTypeInfoResponse convert(ProfileTypeInfo profileTypeInfo){

        ProfileTypeInfoResponse typeResponse=new ProfileTypeInfoResponse();
        typeResponse.setId(profileTypeInfo.getId());
        typeResponse.setTypename(profileTypeInfo.getTypename());
        typeResponse.setDatatype(profileTypeInfo.getDatatype());
        typeResponse.setTypesql(profileTypeInfo.getTypesql());
        typeResponse.setId(profileTypeInfo.getId());
        typeResponse.setCreatedBy(profileTypeInfo.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(profileTypeInfo.getCreatedDateTime()));
        typeResponse.setModifiedBy(profileTypeInfo.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(profileTypeInfo.getModifiedDateTime()));
        typeResponse.setIsDeleted(profileTypeInfo.getIsDeleted());

        return typeResponse;
    }
}