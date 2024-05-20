package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.SupplierSalesRepRequest;
import lk.quantacom.smarterpbackend.dto.request.SupplierSalesRepUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SupplierSalesRepResponse;
import lk.quantacom.smarterpbackend.entity.Supplier;
import lk.quantacom.smarterpbackend.entity.SupplierSalesRep;
import lk.quantacom.smarterpbackend.repository.SupplierSalesRepRepository;
import lk.quantacom.smarterpbackend.service.SupplierSalesRepService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierSalesRepServiceImpl implements SupplierSalesRepService {

    @Autowired
    private SupplierSalesRepRepository supplierSalesRepRepository;

    @Override
    public SupplierSalesRepResponse save(SupplierSalesRepRequest request) {

        SupplierSalesRep supplierSalesRep = new SupplierSalesRep();
        Supplier supplier=new Supplier();
        supplier.setId(request.getSupplierId());
        supplierSalesRep.setSupplier(supplier);
        supplierSalesRep.setSalesrepName(request.getSalesrepName());
        supplierSalesRep.setRepNicNo(request.getRepNicNo());
        supplierSalesRep.setRepVehicleNo(request.getRepVehicleNo());
        supplierSalesRep.setRepEmail(request.getRepEmail());
        supplierSalesRep.setRepMobileNo(request.getRepMobileNo());
        supplierSalesRep.setRepHomeNo(request.getRepHomeNo());
        supplierSalesRep.setRepFaxNo(request.getRepFaxNo());
        supplierSalesRep.setIsDeleted(Deleted.NO);
        SupplierSalesRep save = supplierSalesRepRepository.save(supplierSalesRep);

        return convert(save);
    }

    @Override
    @Transactional
    public SupplierSalesRepResponse update(SupplierSalesRepUpdateRequest request) {

        SupplierSalesRep supplierSalesRep = supplierSalesRepRepository.findById(request.getId()).orElse(null);
        if (supplierSalesRep == null) {
            return null;
        }

        supplierSalesRep.setId(request.getId());
        Supplier supplier=new Supplier();
        supplier.setId(request.getSupplierId());
        supplierSalesRep.setSupplier(supplier);
        supplierSalesRep.setSalesrepName(request.getSalesrepName());
        supplierSalesRep.setRepNicNo(request.getRepNicNo());
        supplierSalesRep.setRepVehicleNo(request.getRepVehicleNo());
        supplierSalesRep.setRepEmail(request.getRepEmail());
        supplierSalesRep.setRepMobileNo(request.getRepMobileNo());
        supplierSalesRep.setRepHomeNo(request.getRepHomeNo());
        supplierSalesRep.setRepFaxNo(request.getRepFaxNo());
        SupplierSalesRep updated = supplierSalesRepRepository.save(supplierSalesRep);

        return (convert(updated));
    }

    @Override
    public SupplierSalesRepResponse getById(Long id) {

        return supplierSalesRepRepository.findById(id).map(SupplierSalesRepServiceImpl::convert).orElse(null);
    }

    @Override
    public List<SupplierSalesRepResponse> getAll() {

        return supplierSalesRepRepository.findAll()
                .stream().map(SupplierSalesRepServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        SupplierSalesRep got = supplierSalesRepRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        supplierSalesRepRepository.save(got);

        return 1;
    }

    private static SupplierSalesRepResponse convert(SupplierSalesRep supplierSalesRep) {

        SupplierSalesRepResponse typeResponse = new SupplierSalesRepResponse();
        typeResponse.setSupplierId(supplierSalesRep.getSupplier().getId());
        typeResponse.setSalesrepName(supplierSalesRep.getSalesrepName());
        typeResponse.setRepNicNo(supplierSalesRep.getRepNicNo());
        typeResponse.setRepVehicleNo(supplierSalesRep.getRepVehicleNo());
        typeResponse.setRepEmail(supplierSalesRep.getRepEmail());
        typeResponse.setRepMobileNo(supplierSalesRep.getRepMobileNo());
        typeResponse.setRepHomeNo(supplierSalesRep.getRepHomeNo());
        typeResponse.setRepFaxNo(supplierSalesRep.getRepFaxNo());
        typeResponse.setId(supplierSalesRep.getId());
        typeResponse.setCreatedBy(supplierSalesRep.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(supplierSalesRep.getCreatedDateTime()));
        typeResponse.setModifiedBy(supplierSalesRep.getModifiedBy());
        typeResponse.setIsDeleted(supplierSalesRep.getIsDeleted());

        return typeResponse;
    }
}