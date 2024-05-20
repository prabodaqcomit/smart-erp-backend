package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.Department1Request;
import lk.quantacom.smarterpbackend.dto.request.Department1UpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.Department1Response;
import lk.quantacom.smarterpbackend.entity.Department1;
import lk.quantacom.smarterpbackend.repository.Department1Repository;
import lk.quantacom.smarterpbackend.service.Department1Service;
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
public class Department1ServiceImpl implements Department1Service {

    @Autowired
    private Department1Repository department1Repository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Override
    @Transactional
    public Department1Response save(Department1Request request) {

        Department1 department1 = new Department1();
        department1.setDepartmentName(request.getDepartmentName());
        department1.setIsDeleted(Deleted.NO);
        Department1 save = department1Repository.save(department1);

        saveLog("Department1", "Data Saved - " + save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public Department1Response update(Department1UpdateRequest request) {

        Department1 department1 = department1Repository.findById(request.getId()).orElse(null);
        if (department1 == null) {
            return null;
        }

        department1.setId(request.getId());
        department1.setDepartmentName(request.getDepartmentName());
        department1.setIsDeleted(request.getIsDeleted());
        Department1 updated = department1Repository.save(department1);

        saveLog("Department1", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public Department1Response getById(Long id) {

        return department1Repository.findById(id).map(Department1ServiceImpl::convert).orElse(null);
    }

    @Override
    public List<Department1Response> getAll() {



        return department1Repository.findAll()
                .stream().map(Department1ServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<Department1Response> getAllActive() {
        return department1Repository.findByIsDeleted(Deleted.NO)
                .stream().map(Department1ServiceImpl::convert).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        Department1 got = department1Repository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        department1Repository.save(got);

        saveLog("Department1", "Data Deleted - " + id);

        return 1;
    }

    private static Department1Response convert(Department1 department1) {

        Department1Response typeResponse = new Department1Response();
        typeResponse.setDepartmentName(department1.getDepartmentName());

        typeResponse.setId(department1.getId());
        typeResponse.setCreatedBy(department1.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(department1.getCreatedDateTime()));
        typeResponse.setModifiedBy(department1.getModifiedBy());
        typeResponse.setIsDeleted(department1.getIsDeleted());

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