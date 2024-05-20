package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.Department4Request;
import lk.quantacom.smarterpbackend.dto.request.Department4UpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.Department1Response;
import lk.quantacom.smarterpbackend.dto.response.Department4Response;
import lk.quantacom.smarterpbackend.entity.Department4;
import lk.quantacom.smarterpbackend.repository.Department4Repository;
import lk.quantacom.smarterpbackend.service.Department4Service;
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
public class Department4ServiceImpl implements Department4Service {

    @Autowired
    private Department4Repository department4Repository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Override
    @Transactional
    public Department4Response save(Department4Request request) {

        Department4 department4 = new Department4();
        department4.setDepartmentName(request.getDepartmentName());
        department4.setIsDeleted(Deleted.NO);
        Department4 save = department4Repository.save(department4);

        saveLog("Department4", "Data Saved - " + save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public Department4Response update(Department4UpdateRequest request) {

        Department4 department4 = department4Repository.findById(request.getId()).orElse(null);
        if (department4 == null) {
            return null;
        }

        department4.setId(request.getId());
        department4.setDepartmentName(request.getDepartmentName());
        department4.setIsDeleted(request.getIsDeleted());
        Department4 updated = department4Repository.save(department4);

        saveLog("Department4", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public Department4Response getById(Long id) {

        return department4Repository.findById(id).map(Department4ServiceImpl::convert).orElse(null);
    }

    @Override
    public List<Department4Response> getAll() {

//        return department4Repository.findByIsDeleted(Deleted.NO)
//                .stream().map(Department4ServiceImpl::convert).collect(Collectors.toList());

        return department4Repository.findAll()
                .stream().map(Department4ServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<Department4Response> getAllActive() {
        return department4Repository.findByIsDeleted(Deleted.NO)
                .stream().map(Department4ServiceImpl::convert).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        Department4 got = department4Repository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        department4Repository.save(got);

        saveLog("Department4", "Data Deleted - " + id);

        return 1;
    }

    private static Department4Response convert(Department4 department4) {

        Department4Response typeResponse = new Department4Response();
        typeResponse.setDepartmentName(department4.getDepartmentName());
        typeResponse.setId(department4.getId());
        typeResponse.setCreatedBy(department4.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(department4.getCreatedDateTime()));
        typeResponse.setModifiedBy(department4.getModifiedBy());
        typeResponse.setIsDeleted(department4.getIsDeleted());

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