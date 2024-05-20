package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.Department3Request;
import lk.quantacom.smarterpbackend.dto.request.Department3UpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.Department1Response;
import lk.quantacom.smarterpbackend.dto.response.Department3Response;
import lk.quantacom.smarterpbackend.entity.Department3;
import lk.quantacom.smarterpbackend.repository.Department3Repository;
import lk.quantacom.smarterpbackend.service.Department3Service;
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
public class Department3ServiceImpl implements Department3Service {

    @Autowired
    private Department3Repository department3Repository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Override
    @Transactional
    public Department3Response save(Department3Request request) {

        Department3 department3 = new Department3();
        department3.setDepartmentName(request.getDepartmentName());
        department3.setIsDeleted(Deleted.NO);
        Department3 save = department3Repository.save(department3);

        saveLog("Department3", "Data Saved - " + save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public Department3Response update(Department3UpdateRequest request) {

        Department3 department3 = department3Repository.findById(request.getId()).orElse(null);
        if (department3 == null) {
            return null;
        }

        department3.setId(request.getId());
        department3.setDepartmentName(request.getDepartmentName());
        department3.setIsDeleted(request.getIsDeleted());
        Department3 updated = department3Repository.save(department3);

        saveLog("Department3", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public Department3Response getById(Long id) {

        return department3Repository.findById(id).map(Department3ServiceImpl::convert).orElse(null);
    }

    @Override
    public List<Department3Response> getAll() {

//        return department3Repository.findByIsDeleted(Deleted.NO)
//                .stream().map(Department3ServiceImpl::convert).collect(Collectors.toList());

        return department3Repository.findAll()
                .stream().map(Department3ServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<Department3Response> getAllActive() {
        return department3Repository.findByIsDeleted(Deleted.NO)
                .stream().map(Department3ServiceImpl::convert).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        Department3 got = department3Repository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        department3Repository.save(got);

        saveLog("Department3", "Data Deleted - " + id);

        return 1;
    }

    private static Department3Response convert(Department3 department3) {

        Department3Response typeResponse = new Department3Response();
        typeResponse.setDepartmentName(department3.getDepartmentName());

        typeResponse.setId(department3.getId());
        typeResponse.setCreatedBy(department3.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(department3.getCreatedDateTime()));
        typeResponse.setModifiedBy(department3.getModifiedBy());
        typeResponse.setIsDeleted(department3.getIsDeleted());

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