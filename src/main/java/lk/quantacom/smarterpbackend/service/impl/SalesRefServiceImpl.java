package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.SalesRefRequest;
import lk.quantacom.smarterpbackend.dto.request.SalesRefUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SalesRefResponse;
import lk.quantacom.smarterpbackend.entity.SalesRef;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.SalesRefRepository;
import lk.quantacom.smarterpbackend.service.SalesRefService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesRefServiceImpl implements SalesRefService {

    @Autowired
    private SalesRefRepository salesRefRepository;

    private static SalesRefResponse convert(SalesRef salesRef) {

        SalesRefResponse typeResponse = new SalesRefResponse();
        typeResponse.setRepcode(salesRef.getRepcode());
        typeResponse.setName(salesRef.getName());
        typeResponse.setAddress(salesRef.getAddress());
        typeResponse.setGender(salesRef.getGender());
        typeResponse.setTHome(salesRef.getTHome());
        typeResponse.setTMobile(salesRef.getTMobile());
        typeResponse.setTOffice(salesRef.getTOffice());
        typeResponse.setFax(salesRef.getFax());
        typeResponse.setEmail(salesRef.getEmail());
        typeResponse.setTPartyName(salesRef.getTPartyName());
        typeResponse.setTPartyMobile(salesRef.getTPartyMobile());
        typeResponse.setTPartyEmail(salesRef.getTPartyEmail());
        typeResponse.setImage(salesRef.getImage());
        typeResponse.setNicNumbr(salesRef.getNicNumbr());
        typeResponse.setBranchId(salesRef.getBranchId());
        typeResponse.setBillDiscLimit(salesRef.getBillDiscLimit());
        typeResponse.setLineDiscLimit(salesRef.getLineDiscLimit());
        typeResponse.setSalesArea(salesRef.getSalesArea());
        typeResponse.setId(salesRef.getId());
        typeResponse.setCreatedBy(salesRef.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(salesRef.getCreatedDateTime()));
        typeResponse.setModifiedBy(salesRef.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(salesRef.getModifiedDateTime()));
        typeResponse.setIsDeleted(salesRef.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public SalesRefResponse save(SalesRefRequest request) {

        SalesRef salesRef = new SalesRef();
        salesRef.setRepcode(request.getRepcode());
        salesRef.setName(request.getName());
        salesRef.setAddress(request.getAddress());
        salesRef.setGender(request.getGender());
        salesRef.setTHome(request.getTHome());
        salesRef.setTMobile(request.getTMobile());
        salesRef.setTOffice(request.getTOffice());
        salesRef.setFax(request.getFax());
        salesRef.setEmail(request.getEmail());
        salesRef.setTPartyName(request.getTPartyName());
        salesRef.setTPartyMobile(request.getTPartyMobile());
        salesRef.setTPartyEmail(request.getTPartyEmail());
        salesRef.setImage(request.getImage());
        salesRef.setNicNumbr(request.getNicNumbr());
        salesRef.setBranchId(request.getBranchId());
        salesRef.setBillDiscLimit(request.getBillDiscLimit());
        salesRef.setLineDiscLimit(request.getLineDiscLimit());
        salesRef.setSalesArea(request.getSalesArea());
        salesRef.setIsDeleted(Deleted.NO);
        SalesRef save = salesRefRepository.save(salesRef);

        return convert(save);
    }

    @Override
    @Transactional
    public SalesRefResponse update(SalesRefUpdateRequest request) {

        SalesRef salesRef = salesRefRepository.findById(request.getId()).orElse(null);
        if (salesRef == null) {
            return null;
        }

        salesRef.setId(request.getId());
        salesRef.setRepcode(request.getRepcode());
        salesRef.setName(request.getName());
        salesRef.setAddress(request.getAddress());
        salesRef.setGender(request.getGender());
        salesRef.setTHome(request.getTHome());
        salesRef.setTMobile(request.getTMobile());
        salesRef.setTOffice(request.getTOffice());
        salesRef.setFax(request.getFax());
        salesRef.setEmail(request.getEmail());
        salesRef.setTPartyName(request.getTPartyName());
        salesRef.setTPartyMobile(request.getTPartyMobile());
        salesRef.setTPartyEmail(request.getTPartyEmail());
        salesRef.setImage(request.getImage());
        salesRef.setNicNumbr(request.getNicNumbr());
        salesRef.setBranchId(request.getBranchId());
        salesRef.setBillDiscLimit(request.getBillDiscLimit());
        salesRef.setLineDiscLimit(request.getLineDiscLimit());
        salesRef.setSalesArea(request.getSalesArea());
        SalesRef updated = salesRefRepository.save(salesRef);

        return (convert(updated));
    }

    @Override
    public SalesRefResponse getById(Long id) {

        return salesRefRepository.findById(id).map(SalesRefServiceImpl::convert).orElse(null);
    }

    @Override
    public List<SalesRefResponse> getAll() {

        return salesRefRepository.findByIsDeleted(Deleted.NO)
                .stream().map(SalesRefServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        SalesRef got = salesRefRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        salesRefRepository.save(got);

        return 1;
    }
}