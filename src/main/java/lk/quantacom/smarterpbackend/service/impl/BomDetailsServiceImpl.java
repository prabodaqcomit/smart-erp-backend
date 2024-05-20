package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.BomDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.BomDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BomDetailsResponse;
import lk.quantacom.smarterpbackend.entity.BomDetails;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.BomDetailsRepository;
import lk.quantacom.smarterpbackend.service.BomDetailsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BomDetailsServiceImpl implements BomDetailsService {

    @Autowired
    private BomDetailsRepository bomDetailsRepository;


    @Override
    @Transactional
    public BomDetailsResponse save(BomDetailsRequest request) {

        BomDetails bomDetails = new BomDetails();
        bomDetails.setSubItemCode(request.getSubItemCode());
        bomDetails.setBatchNo(request.getBatchNo());
        bomDetails.setUnitCost(request.getUnitCost());
        bomDetails.setUnit(request.getUnit());
        bomDetails.setQuantity(request.getQuantity());
        bomDetails.setCost(request.getCost());
        bomDetails.setInvType(request.getInvType());
        bomDetails.setIsDeleted(Deleted.NO);
        BomDetails save = bomDetailsRepository.save(bomDetails);

        return convert(save);
    }

    @Override
    @Transactional
    public BomDetailsResponse update(BomDetailsUpdateRequest request) {

        BomDetails bomDetails = bomDetailsRepository.findById(request.getId()).orElse(null);
        if (bomDetails == null) {
            return null;
        }

        bomDetails.setId(request.getId());
        bomDetails.setSubItemCode(request.getSubItemCode());
        bomDetails.setBatchNo(request.getBatchNo());
        bomDetails.setUnitCost(request.getUnitCost());
        bomDetails.setUnit(request.getUnit());
        bomDetails.setQuantity(request.getQuantity());
        bomDetails.setCost(request.getCost());
        bomDetails.setInvType(request.getInvType());
        BomDetails updated = bomDetailsRepository.save(bomDetails);

        return (convert(updated));
    }

    @Override
    public BomDetailsResponse getById(Long id) {

        return bomDetailsRepository.findById(id).map(BomDetailsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<BomDetailsResponse> getAll() {

        return bomDetailsRepository.findByIsDeleted(Deleted.NO)
                .stream().map(BomDetailsServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        BomDetails got = bomDetailsRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        bomDetailsRepository.save(got);

        return 1;
    }

    private static BomDetailsResponse convert(BomDetails bomDetails) {

        BomDetailsResponse typeResponse = new BomDetailsResponse();
        typeResponse.setSubItemCode(bomDetails.getSubItemCode());
        typeResponse.setBatchNo(bomDetails.getBatchNo());
        typeResponse.setUnitCost(bomDetails.getUnitCost());
        typeResponse.setUnit(bomDetails.getUnit());
        typeResponse.setQuantity(bomDetails.getQuantity());
        typeResponse.setCost(bomDetails.getCost());
        typeResponse.setInvType(bomDetails.getInvType());
        typeResponse.setId(bomDetails.getId());
        typeResponse.setCreatedBy(bomDetails.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(bomDetails.getCreatedDateTime()));
        typeResponse.setModifiedBy(bomDetails.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(bomDetails.getModifiedDateTime()));
        typeResponse.setIsDeleted(bomDetails.getIsDeleted());

        return typeResponse;
    }
}