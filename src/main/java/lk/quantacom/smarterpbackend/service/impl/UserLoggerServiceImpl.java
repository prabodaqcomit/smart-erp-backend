package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.UserLoggerRequest;
import lk.quantacom.smarterpbackend.dto.request.UserLoggerUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.UserLoggerResponse;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.service.UserLoggerService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserLoggerServiceImpl implements UserLoggerService {

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Override
    public UserLoggerResponse save(UserLoggerRequest request) {

        UserLogger userLogger = new UserLogger();
        userLogger.setForm(request.getForm());
        userLogger.setAction(request.getAction());
        userLogger.setIsDeleted(Deleted.NO);
        UserLogger save = userLoggerRepository.save(userLogger);

        return convert(save);
    }

    @Override
    @Transactional
    public UserLoggerResponse update(UserLoggerUpdateRequest request) {

        UserLogger userLogger = userLoggerRepository.findById(request.getId()).orElse(null);
        if (userLogger == null) {
            return null;
        }

        userLogger.setId(request.getId());
        userLogger.setForm(request.getForm());
        userLogger.setAction(request.getAction());
        UserLogger updated = userLoggerRepository.save(userLogger);

        return (convert(updated));
    }

    @Override
    public UserLoggerResponse getById(Long id) {

        return userLoggerRepository.findById(id).map(UserLoggerServiceImpl::convert).orElse(null);
    }

    @Override
    public List<UserLoggerResponse> getAll() {

        return userLoggerRepository.findAll()
                .stream().map(UserLoggerServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        UserLogger got = userLoggerRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        userLoggerRepository.save(got);

        return 1;
    }

    private static UserLoggerResponse convert(UserLogger userLogger) {

        UserLoggerResponse typeResponse = new UserLoggerResponse();
        typeResponse.setForm(userLogger.getForm());
        typeResponse.setAction(userLogger.getAction());
        typeResponse.setId(userLogger.getId());
        typeResponse.setCreatedBy(userLogger.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(userLogger.getCreatedDateTime()));
        typeResponse.setModifiedBy(userLogger.getModifiedBy());
        typeResponse.setIsDeleted(userLogger.getIsDeleted());

        return typeResponse;
    }



}