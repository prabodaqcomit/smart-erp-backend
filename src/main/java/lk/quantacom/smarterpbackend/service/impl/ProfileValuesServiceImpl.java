package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.ProfileValuesRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileValuesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileValuesResponse;
import lk.quantacom.smarterpbackend.entity.ProfileValues;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.ProfileValuesRepository;
import lk.quantacom.smarterpbackend.service.ProfileValuesService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileValuesServiceImpl implements ProfileValuesService {

    @Autowired
    private ProfileValuesRepository profileValuesRepository;



    @Override
@Transactional
    public ProfileValuesResponse save(ProfileValuesRequest request) {

        ProfileValues profileValues=new ProfileValues();
profileValues.setProfileid(request.getProfileid());
profileValues.setFieldname(request.getFieldname());
profileValues.setFieldvalue(request.getFieldvalue());
profileValues.setIsDeleted(Deleted.NO);
ProfileValues save=profileValuesRepository.save(profileValues);

        return convert(save);
    }

    @Override
    @Transactional
    public ProfileValuesResponse update(ProfileValuesUpdateRequest request) {

ProfileValues profileValues = profileValuesRepository.findById(request.getProfileid()).orElse(null);
        if(profileValues==null){
            return null;
        }

profileValues.setProfileid(request.getProfileid());
profileValues.setProfileid(request.getProfileid());
profileValues.setFieldname(request.getFieldname());
profileValues.setFieldvalue(request.getFieldvalue());
ProfileValues updated=profileValuesRepository.save(profileValues);

        return (convert(updated));
    }

    @Override
    public ProfileValuesResponse getById(Integer id) {

       return profileValuesRepository.findById(id).map(ProfileValuesServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ProfileValuesResponse> getAll() {

        return  profileValuesRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ProfileValuesServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Integer id) {

ProfileValues got=profileValuesRepository.findById(id).orElse(null);
        if(got==null){
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        profileValuesRepository.save(got);

        return  1;
    }

private static ProfileValuesResponse convert(ProfileValues profileValues){

        ProfileValuesResponse typeResponse=new ProfileValuesResponse();
typeResponse.setProfileid(profileValues.getProfileid());
typeResponse.setFieldname(profileValues.getFieldname());
typeResponse.setFieldvalue(profileValues.getFieldvalue());
        typeResponse.setProfileid(profileValues.getProfileid());
        typeResponse.setCreatedBy(profileValues.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(profileValues.getCreatedDateTime()));
        typeResponse.setModifiedBy(profileValues.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(profileValues.getModifiedDateTime()));
        typeResponse.setIsDeleted(profileValues.getIsDeleted());

return typeResponse;
    }
}