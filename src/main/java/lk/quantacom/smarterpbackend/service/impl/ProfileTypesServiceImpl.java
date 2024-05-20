package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.ProfileTypesRequest;
import lk.quantacom.smarterpbackend.dto.request.ProfileTypesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ProfileTypesResponse;
import lk.quantacom.smarterpbackend.entity.ProfileTypes;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.ProfileTypesRepository;
import lk.quantacom.smarterpbackend.service.ProfileTypesService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileTypesServiceImpl implements ProfileTypesService {

    @Autowired
    private ProfileTypesRepository profileTypesRepository;


    @Override
    @Transactional
    public ProfileTypesResponse save(ProfileTypesRequest request) {

        ProfileTypes profileTypes = new ProfileTypes();
        //profileTypes.setId(request.getId());
        profileTypes.setDescription(request.getDescription());
        profileTypes.setDefprofileid(0);
        profileTypes.setIsDeleted(Deleted.NO);
        ProfileTypes save = profileTypesRepository.save(profileTypes);

        return convert(save);
    }

    @Override
    @Transactional
    public ProfileTypesResponse update(ProfileTypesUpdateRequest request) {

        ProfileTypes profileTypes = profileTypesRepository.findById(request.getId()).orElse(null);
        if (profileTypes == null) {
            return null;
        }

        profileTypes.setId(request.getId());
        profileTypes.setId(request.getId());
        profileTypes.setDescription(request.getDescription());
        profileTypes.setDefprofileid(request.getDefprofileid());
        ProfileTypes updated = profileTypesRepository.save(profileTypes);

        return (convert(updated));
    }

    @Override
    public ProfileTypesResponse getById(Integer id) {

        return profileTypesRepository.findById(id).map(ProfileTypesServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ProfileTypesResponse> getAll() {

        return profileTypesRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ProfileTypesServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Integer id) {

        ProfileTypes got = profileTypesRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        profileTypesRepository.save(got);

        return 1;
    }

    private static ProfileTypesResponse convert(ProfileTypes profileTypes) {

        ProfileTypesResponse typeResponse = new ProfileTypesResponse();
        typeResponse.setId(profileTypes.getId());
        typeResponse.setDescription(profileTypes.getDescription());
        typeResponse.setDefprofileid(profileTypes.getDefprofileid());
        typeResponse.setId(profileTypes.getId());
        typeResponse.setCreatedBy(profileTypes.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(profileTypes.getCreatedDateTime()));
        typeResponse.setModifiedBy(profileTypes.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(profileTypes.getModifiedDateTime()));
        typeResponse.setIsDeleted(profileTypes.getIsDeleted());

        return typeResponse;

    }
}