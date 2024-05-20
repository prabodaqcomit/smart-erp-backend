package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.SupplierAgencyRequest;
import lk.quantacom.smarterpbackend.dto.request.SupplierAgencyUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SupplierAgencyResponse;
import lk.quantacom.smarterpbackend.entity.Supplier;
import lk.quantacom.smarterpbackend.entity.SupplierAgency;
import lk.quantacom.smarterpbackend.repository.SupplierAgencyRepository;
import lk.quantacom.smarterpbackend.service.SupplierAgencyService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierAgencyServiceImpl implements SupplierAgencyService {

    @Autowired
    private SupplierAgencyRepository supplierAgencyRepository;

    @Override
    public SupplierAgencyResponse save(SupplierAgencyRequest request) {

        SupplierAgency supplierAgency = new SupplierAgency();
        Supplier supplier=new Supplier();
        supplier.setId(request.getSupplierId());
        supplierAgency.setSupplier(supplier);
        supplierAgency.setSupAgencyName(request.getSupAgencyName());
        supplierAgency.setSupAgencyAddress(request.getSupAgencyAddress());
        supplierAgency.setSupAEmail(request.getSupAEmail());
        supplierAgency.setSupAMobileNo(request.getSupAMobileNo());
        supplierAgency.setSupAHomeNo(request.getSupAHomeNo());
        supplierAgency.setSupAFaxNo(request.getSupAFaxNo());
        supplierAgency.setSupAWebSite(request.getSupAWebSite());
        supplierAgency.setIsDeleted(Deleted.NO);
        SupplierAgency save = supplierAgencyRepository.save(supplierAgency);

        return convert(save);
    }

    @Override
    @Transactional
    public SupplierAgencyResponse update(SupplierAgencyUpdateRequest request) {

        SupplierAgency supplierAgency = supplierAgencyRepository.findById(request.getId()).orElse(null);
        if (supplierAgency == null) {
            return null;
        }

        supplierAgency.setId(request.getId());
        Supplier supplier=new Supplier();
        supplier.setId(request.getSupplierId());
        supplierAgency.setSupplier(supplier);
        supplierAgency.setSupplier(supplier);
        supplierAgency.setSupAgencyName(request.getSupAgencyName());
        supplierAgency.setSupAgencyAddress(request.getSupAgencyAddress());
        supplierAgency.setSupAEmail(request.getSupAEmail());
        supplierAgency.setSupAMobileNo(request.getSupAMobileNo());
        supplierAgency.setSupAHomeNo(request.getSupAHomeNo());
        supplierAgency.setSupAFaxNo(request.getSupAFaxNo());
        supplierAgency.setSupAWebSite(request.getSupAWebSite());
        SupplierAgency updated = supplierAgencyRepository.save(supplierAgency);

        return (convert(updated));
    }

    @Override
    public SupplierAgencyResponse getById(Long id) {

        return supplierAgencyRepository.findById(id).map(SupplierAgencyServiceImpl::convert).orElse(null);
    }

    @Override
    public List<SupplierAgencyResponse> getAll() {

        return supplierAgencyRepository.findAll()
                .stream().map(SupplierAgencyServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        SupplierAgency got = supplierAgencyRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        supplierAgencyRepository.save(got);

        return 1;
    }

    private static SupplierAgencyResponse convert(SupplierAgency supplierAgency) {

        SupplierAgencyResponse typeResponse = new SupplierAgencyResponse();
        typeResponse.setSupplierId(supplierAgency.getSupplier().getId());
        typeResponse.setSupAgencyName(supplierAgency.getSupAgencyName());
        typeResponse.setSupAgencyAddress(supplierAgency.getSupAgencyAddress());
        typeResponse.setSupAEmail(supplierAgency.getSupAEmail());
        typeResponse.setSupAMobileNo(supplierAgency.getSupAMobileNo());
        typeResponse.setSupAHomeNo(supplierAgency.getSupAHomeNo());
        typeResponse.setSupAFaxNo(supplierAgency.getSupAFaxNo());
        typeResponse.setSupAWebSite(supplierAgency.getSupAWebSite());
        typeResponse.setId(supplierAgency.getId());
        typeResponse.setCreatedBy(supplierAgency.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(supplierAgency.getCreatedDateTime()));
        typeResponse.setModifiedBy(supplierAgency.getModifiedBy());
        typeResponse.setIsDeleted(supplierAgency.getIsDeleted());

        return typeResponse;
    }
}