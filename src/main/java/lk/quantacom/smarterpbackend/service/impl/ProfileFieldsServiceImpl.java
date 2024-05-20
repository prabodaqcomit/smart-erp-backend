package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.ProfileFieldsRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileFieldsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileFieldsResponse;
import lk.quantacom.smarterpbackend.entity.ProfileFields;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.ProfileFieldsRepository;
import lk.quantacom.smarterpbackend.service.ProfileFieldsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileFieldsServiceImpl implements ProfileFieldsService {

    @Autowired
    private ProfileFieldsRepository profileFieldsRepository;



    @Override
@Transactional
    public ProfileFieldsResponse save(ProfileFieldsRequest request) {

        ProfileFields profileFields=new ProfileFields();
profileFields.setFieldname(request.getFieldname());
profileFields.setProfiletype(request.getProfiletype());
profileFields.setDefaultvalue(request.getDefaultvalue());
profileFields.setDescription(request.getDescription());
profileFields.setVersion(request.getVersion());
profileFields.setTypeinfoid(request.getTypeinfoid());
profileFields.setConstraintid(request.getConstraintid());
profileFields.setProgname(request.getProgname());
profileFields.setModulename(request.getModulename());
profileFields.setFunctionname(request.getFunctionname());
profileFields.setFieldlevel(request.getFieldlevel());
profileFields.setNotes(request.getNotes());
profileFields.setExodbversion(request.getExodbversion());
profileFields.setKeywords(request.getKeywords());
profileFields.setFieldproperties(request.getFieldproperties());
profileFields.setDbscope(request.getDbscope());
profileFields.setIsDeleted(Deleted.NO);
ProfileFields save=profileFieldsRepository.save(profileFields);

        return convert(save);
    }

    @Override
    @Transactional
    public ProfileFieldsResponse update(ProfileFieldsUpdateRequest request) {

ProfileFields profileFields = profileFieldsRepository.findById(request.getFieldname()).orElse(null);
        if(profileFields==null){
            return null;
        }

profileFields.setFieldname(request.getFieldname());
profileFields.setFieldname(request.getFieldname());
profileFields.setProfiletype(request.getProfiletype());
profileFields.setDefaultvalue(request.getDefaultvalue());
profileFields.setDescription(request.getDescription());
profileFields.setVersion(request.getVersion());
profileFields.setTypeinfoid(request.getTypeinfoid());
profileFields.setConstraintid(request.getConstraintid());
profileFields.setProgname(request.getProgname());
profileFields.setModulename(request.getModulename());
profileFields.setFunctionname(request.getFunctionname());
profileFields.setFieldlevel(request.getFieldlevel());
profileFields.setNotes(request.getNotes());
profileFields.setExodbversion(request.getExodbversion());
profileFields.setKeywords(request.getKeywords());
profileFields.setFieldproperties(request.getFieldproperties());
profileFields.setDbscope(request.getDbscope());
ProfileFields updated=profileFieldsRepository.save(profileFields);

        return (convert(updated));
    }

    @Override
    public ProfileFieldsResponse getById(String id) {

       return profileFieldsRepository.findById(id).map(ProfileFieldsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ProfileFieldsResponse> getAll() {

        return  profileFieldsRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ProfileFieldsServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(String id) {

ProfileFields got=profileFieldsRepository.findById(id).orElse(null);
        if(got==null){
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        profileFieldsRepository.save(got);

        return  1;
    }

private static ProfileFieldsResponse convert(ProfileFields profileFields){

        ProfileFieldsResponse typeResponse=new ProfileFieldsResponse();
typeResponse.setFieldname(profileFields.getFieldname());
typeResponse.setProfiletype(profileFields.getProfiletype());
typeResponse.setDefaultvalue(profileFields.getDefaultvalue());
typeResponse.setDescription(profileFields.getDescription());
typeResponse.setVersion(profileFields.getVersion());
typeResponse.setTypeinfoid(profileFields.getTypeinfoid());
typeResponse.setConstraintid(profileFields.getConstraintid());
typeResponse.setProgname(profileFields.getProgname());
typeResponse.setModulename(profileFields.getModulename());
typeResponse.setFunctionname(profileFields.getFunctionname());
typeResponse.setFieldlevel(profileFields.getFieldlevel());
typeResponse.setNotes(profileFields.getNotes());
typeResponse.setExodbversion(profileFields.getExodbversion());
typeResponse.setKeywords(profileFields.getKeywords());
typeResponse.setFieldproperties(profileFields.getFieldproperties());
typeResponse.setDbscope(profileFields.getDbscope());
        typeResponse.setFieldname(profileFields.getFieldname());
        typeResponse.setCreatedBy(profileFields.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(profileFields.getCreatedDateTime()));
        typeResponse.setModifiedBy(profileFields.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(profileFields.getModifiedDateTime()));
        typeResponse.setIsDeleted(profileFields.getIsDeleted());

return typeResponse;
    }
}