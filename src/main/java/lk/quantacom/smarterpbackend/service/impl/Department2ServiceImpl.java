package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.Department2Request;
import lk.quantacom.smarterpbackend.dto.request.Department2UpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.Department1Response;
import lk.quantacom.smarterpbackend.dto.response.Department2Response;
import lk.quantacom.smarterpbackend.entity.Department2;
import lk.quantacom.smarterpbackend.repository.Department2Repository;
import lk.quantacom.smarterpbackend.service.Department2Service;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Department2ServiceImpl implements Department2Service {

    @Autowired
    private Department2Repository department2Repository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Override
    @Transactional
    public Department2Response save(Department2Request request) {

        Department2 department2 = new Department2();
        department2.setDepartmentName(request.getDepartmentName());
        department2.setIsDeleted(Deleted.NO);
        Department2 save = department2Repository.save(department2);

        saveLog("Department2", "Data Saved - " + save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public Department2Response update(Department2UpdateRequest request) {

        Department2 department2 = department2Repository.findById(request.getId()).orElse(null);
        if (department2 == null) {
            return null;
        }

        department2.setId(request.getId());
        department2.setDepartmentName(request.getDepartmentName());
        department2.setIsDeleted(request.getIsDeleted());
        Department2 updated = department2Repository.save(department2);

        saveLog("Department2", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public Department2Response getById(Long id) {

        return department2Repository.findById(id).map(Department2ServiceImpl::convert).orElse(null);
    }

    @Override
    public List<Department2Response> getAll() {

//        return department2Repository.findByIsDeleted(Deleted.NO)
//                .stream().map(Department2ServiceImpl::convert).collect(Collectors.toList());

        return department2Repository.findAll()
                .stream().map(Department2ServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<Department2Response> getAllActive() {
        return department2Repository.findByIsDeleted(Deleted.NO)
                .stream().map(Department2ServiceImpl::convert).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        Department2 got = department2Repository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        department2Repository.save(got);

        saveLog("Department2", "Data Deleted - " + id);

        return 1;
    }

    private static Department2Response convert(Department2 department2) {

        Department2Response typeResponse = new Department2Response();
        typeResponse.setDepartmentName(department2.getDepartmentName());

        typeResponse.setId(department2.getId());
        typeResponse.setCreatedBy(department2.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(department2.getCreatedDateTime()));
        typeResponse.setModifiedBy(department2.getModifiedBy());
        typeResponse.setIsDeleted(department2.getIsDeleted());

        return typeResponse;
    }

    private void saveLog(String form, String action) {
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }
}