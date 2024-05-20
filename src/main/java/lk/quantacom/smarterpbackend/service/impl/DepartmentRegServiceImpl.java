package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.DepartmentRegRequest;
import lk.quantacom.smarterpbackend.dto.request.DepartmentRegUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.DepartmentRegResponse;
import lk.quantacom.smarterpbackend.entity.DepartmentReg;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.DepartmentRegRepository;
import lk.quantacom.smarterpbackend.service.DepartmentRegService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentRegServiceImpl implements DepartmentRegService {

    @Autowired
    private DepartmentRegRepository departmentRegRepository;


    @Override
    @Transactional
    public DepartmentRegResponse save(DepartmentRegRequest request) {

        DepartmentReg departmentReg = new DepartmentReg();
        departmentReg.setDepartment_name(request.getDepartment_name());
        departmentReg.setIsDeleted(Deleted.NO);
        DepartmentReg save = departmentRegRepository.save(departmentReg);

        return convert(save);
    }

    @Override
    @Transactional
    public DepartmentRegResponse update(DepartmentRegUpdateRequest request) {

        DepartmentReg departmentReg = departmentRegRepository.findById(request.getId()).orElse(null);
        if (departmentReg == null) {
            return null;
        }

        departmentReg.setId(request.getId());
        departmentReg.setDepartment_name(request.getDepartment_name());
        DepartmentReg updated = departmentRegRepository.save(departmentReg);

        return (convert(updated));
    }

    @Override
    public DepartmentRegResponse getById(Long id) {

        return departmentRegRepository.findById(id).map(DepartmentRegServiceImpl::convert).orElse(null);
    }

    @Override
    public List<DepartmentRegResponse> getAll() {

        return departmentRegRepository.findByIsDeleted(Deleted.NO)
                .stream().map(DepartmentRegServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        DepartmentReg got = departmentRegRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        departmentRegRepository.save(got);

        return 1;
    }

    private static DepartmentRegResponse convert(DepartmentReg departmentReg) {

        DepartmentRegResponse typeResponse = new DepartmentRegResponse();
        typeResponse.setDepartment_name(departmentReg.getDepartment_name());
        typeResponse.setId(departmentReg.getId());
        typeResponse.setCreatedBy(departmentReg.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(departmentReg.getCreatedDateTime()));
        typeResponse.setModifiedBy(departmentReg.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(departmentReg.getModifiedDateTime()));
        typeResponse.setIsDeleted(departmentReg.getIsDeleted());

        return typeResponse;
    }
}