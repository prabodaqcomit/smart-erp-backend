package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.userAuthLimitsRequest;
import lk.quantacom.smarterpbackend.dto.request.userAuthLimitsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.userAuthLimitsResponse;
import lk.quantacom.smarterpbackend.entity.userAuthLimits;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.userAuthLimitsRepository;
import lk.quantacom.smarterpbackend.service.userAuthLimitsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class userAuthLimitsServiceImpl implements userAuthLimitsService {

    @Autowired
    private userAuthLimitsRepository userAuthLimitsRepository;

    private static userAuthLimitsResponse convert(userAuthLimits userAuthLimits) {

        userAuthLimitsResponse typeResponse = new userAuthLimitsResponse();
        typeResponse.setUsername(userAuthLimits.getUsername());
        typeResponse.setPoAuthLimit(userAuthLimits.getPoAuthLimit());
        typeResponse.setPoAuthNextUser(userAuthLimits.getPoAuthNextUser());
        typeResponse.setGrnAuthLimit(userAuthLimits.getGrnAuthLimit());
        typeResponse.setGrnAuthNextUser(userAuthLimits.getGrnAuthNextUser());
        typeResponse.setId(userAuthLimits.getId());
        typeResponse.setCreatedBy(userAuthLimits.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(userAuthLimits.getCreatedDateTime()));
        typeResponse.setModifiedBy(userAuthLimits.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(userAuthLimits.getModifiedDateTime()));
        typeResponse.setIsDeleted(userAuthLimits.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public userAuthLimitsResponse save(userAuthLimitsRequest request) {

        userAuthLimits userAuthLimits = new userAuthLimits();
        userAuthLimits.setUsername(request.getUsername());
        userAuthLimits.setPoAuthLimit(request.getPoAuthLimit());
        userAuthLimits.setPoAuthNextUser(request.getPoAuthNextUser());
        userAuthLimits.setGrnAuthLimit(request.getGrnAuthLimit());
        userAuthLimits.setGrnAuthNextUser(request.getGrnAuthNextUser());
        userAuthLimits.setIsDeleted(Deleted.NO);
        userAuthLimits save = userAuthLimitsRepository.save(userAuthLimits);

        return convert(save);
    }

    @Override
    @Transactional
    public userAuthLimitsResponse update(userAuthLimitsUpdateRequest request) {

        userAuthLimits userAuthLimits = userAuthLimitsRepository.findById(request.getId()).orElse(null);
        if (userAuthLimits == null) {
            return null;
        }

        userAuthLimits.setId(request.getId());
        userAuthLimits.setUsername(request.getUsername());
        userAuthLimits.setPoAuthLimit(request.getPoAuthLimit());
        userAuthLimits.setPoAuthNextUser(request.getPoAuthNextUser());
        userAuthLimits.setGrnAuthLimit(request.getGrnAuthLimit());
        userAuthLimits.setGrnAuthNextUser(request.getGrnAuthNextUser());
        userAuthLimits updated = userAuthLimitsRepository.save(userAuthLimits);

        return (convert(updated));
    }

    @Override
    public userAuthLimitsResponse getById(Long id) {

        return userAuthLimitsRepository.findById(id).map(userAuthLimitsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<userAuthLimitsResponse> getAll() {

        return userAuthLimitsRepository.findByIsDeleted(Deleted.NO)
                .stream().map(userAuthLimitsServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        userAuthLimits got = userAuthLimitsRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        userAuthLimitsRepository.save(got);

        return 1;
    }

    @Override
    public List<userAuthLimitsResponse> getByUsername(String username) {

        List<userAuthLimitsResponse> res=new ArrayList<>();

        userAuthLimits userAuthLimitsResponse= userAuthLimitsRepository.findByUsernameAndIsDeleted(username,Deleted.NO);
        if(userAuthLimitsResponse!=null){
            res.add(convert(userAuthLimitsResponse));
        }

        return res;
    }
}