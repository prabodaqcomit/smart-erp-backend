package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.DiningTableTmpDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.DiningTableTmpDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.DiningTableTmpDetailsResponse;
import lk.quantacom.smarterpbackend.entity.DiningTableTmpDetails;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.DiningTableTmpDetailsRepository;
import lk.quantacom.smarterpbackend.service.DiningTableTmpDetailsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiningTableTmpDetailsServiceImpl implements DiningTableTmpDetailsService {

    @Autowired
    private DiningTableTmpDetailsRepository diningTableTmpDetailsRepository;


    @Override
    @Transactional
    public DiningTableTmpDetailsResponse save(DiningTableTmpDetailsRequest request) {

        DiningTableTmpDetails diningTableTmpDetails = new DiningTableTmpDetails();
        //diningTableTmpDetails.setHeadId(request.getHeadId());
        diningTableTmpDetails.setTableId(request.getTableId());
        diningTableTmpDetails.setItemCode(request.getItemCode());
        diningTableTmpDetails.setBatchNo(request.getBatchNo());
        diningTableTmpDetails.setSelectedPrice(request.getSelectedPrice());
        diningTableTmpDetails.setQuantity(request.getQuantity());
        diningTableTmpDetails.setIsKOT(request.getIsKOT());
        diningTableTmpDetails.setIsBOT(request.getIsBOT());
        diningTableTmpDetails.setInvStatus(request.getInvStatus());
        diningTableTmpDetails.setIsDeleted(Deleted.NO);
        DiningTableTmpDetails save = diningTableTmpDetailsRepository.save(diningTableTmpDetails);

        return convert(save);
    }

    @Override
    @Transactional
    public DiningTableTmpDetailsResponse update(DiningTableTmpDetailsUpdateRequest request) {

        DiningTableTmpDetails diningTableTmpDetails = diningTableTmpDetailsRepository.findById(request.getId()).orElse(null);
        if (diningTableTmpDetails == null) {
            return null;
        }

        diningTableTmpDetails.setId(request.getId());
        diningTableTmpDetails.setHeadId(request.getHeadId());
        diningTableTmpDetails.setTableId(request.getTableId());
        diningTableTmpDetails.setItemCode(request.getItemCode());
        diningTableTmpDetails.setBatchNo(request.getBatchNo());
        diningTableTmpDetails.setSelectedPrice(request.getSelectedPrice());
        diningTableTmpDetails.setQuantity(request.getQuantity());
        diningTableTmpDetails.setIsKOT(request.getIsKOT());
        diningTableTmpDetails.setIsBOT(request.getIsBOT());
        diningTableTmpDetails.setInvStatus(request.getInvStatus());
        DiningTableTmpDetails updated = diningTableTmpDetailsRepository.save(diningTableTmpDetails);

        return (convert(updated));
    }

    @Override
    public DiningTableTmpDetailsResponse getById(Long id) {

        return diningTableTmpDetailsRepository.findById(id).map(DiningTableTmpDetailsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<DiningTableTmpDetailsResponse> getAll() {

        return diningTableTmpDetailsRepository.findByIsDeleted(Deleted.NO)
                .stream().map(DiningTableTmpDetailsServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        DiningTableTmpDetails got = diningTableTmpDetailsRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        diningTableTmpDetailsRepository.save(got);

        return 1;
    }

    private static DiningTableTmpDetailsResponse convert(DiningTableTmpDetails diningTableTmpDetails) {

        DiningTableTmpDetailsResponse typeResponse = new DiningTableTmpDetailsResponse();
        typeResponse.setHeadId(diningTableTmpDetails.getHeadId());
        typeResponse.setTableId(diningTableTmpDetails.getTableId());
        typeResponse.setItemCode(diningTableTmpDetails.getItemCode());
        typeResponse.setBatchNo(diningTableTmpDetails.getBatchNo());
        typeResponse.setSelectedPrice(diningTableTmpDetails.getSelectedPrice());
        typeResponse.setQuantity(diningTableTmpDetails.getQuantity());
        typeResponse.setIsKOT(diningTableTmpDetails.getIsKOT());
        typeResponse.setIsBOT(diningTableTmpDetails.getIsBOT());
        typeResponse.setInvStatus(diningTableTmpDetails.getInvStatus());
        typeResponse.setId(diningTableTmpDetails.getId());
        typeResponse.setCreatedBy(diningTableTmpDetails.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(diningTableTmpDetails.getCreatedDateTime()));
        typeResponse.setModifiedBy(diningTableTmpDetails.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(diningTableTmpDetails.getModifiedDateTime()));
        typeResponse.setIsDeleted(diningTableTmpDetails.getIsDeleted());

        return typeResponse;
    }
}