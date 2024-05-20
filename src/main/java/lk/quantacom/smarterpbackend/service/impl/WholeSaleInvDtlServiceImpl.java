package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.WholeSaleInvDtlRequest;
import lk.quantacom.smarterpbackend.dto.request.WholeSaleInvDtlUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.WholeSaleInvDtlResponse;
import lk.quantacom.smarterpbackend.entity.WholeSaleInvDtl;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.WholeSaleInvDtlRepository;
import lk.quantacom.smarterpbackend.service.WholeSaleInvDtlService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WholeSaleInvDtlServiceImpl implements WholeSaleInvDtlService {

    @Autowired
    private WholeSaleInvDtlRepository wholeSaleInvDtlRepository;

    private static WholeSaleInvDtlResponse convert(WholeSaleInvDtl wholeSaleInvDtl) {

        WholeSaleInvDtlResponse typeResponse = new WholeSaleInvDtlResponse();
        typeResponse.setItemCode(wholeSaleInvDtl.getItemCode());
        typeResponse.setInvno(wholeSaleInvDtl.getInvno());
        typeResponse.setItemName(wholeSaleInvDtl.getItemName());
        typeResponse.setSizeId(wholeSaleInvDtl.getSizeId());
        typeResponse.setSizeName(wholeSaleInvDtl.getSizeName());
        typeResponse.setQtyByitem(wholeSaleInvDtl.getQtyByitem());
        typeResponse.setSizeQty(wholeSaleInvDtl.getSizeQty());
        typeResponse.setMrp(wholeSaleInvDtl.getMrp());
        typeResponse.setDisPrecentage(wholeSaleInvDtl.getDisPrecentage());
        typeResponse.setDisAmount(wholeSaleInvDtl.getDisAmount());
        typeResponse.setAmount(wholeSaleInvDtl.getAmount());
        typeResponse.setId(wholeSaleInvDtl.getId());
        typeResponse.setCreatedBy(wholeSaleInvDtl.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(wholeSaleInvDtl.getCreatedDateTime()));
        typeResponse.setModifiedBy(wholeSaleInvDtl.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(wholeSaleInvDtl.getModifiedDateTime()));
        typeResponse.setIsDeleted(wholeSaleInvDtl.getIsDeleted());
        typeResponse.setColor(wholeSaleInvDtl.getColor());
        typeResponse.setFit(wholeSaleInvDtl.getFit());
        typeResponse.setBatchNo(wholeSaleInvDtl.getBatchNo());
        typeResponse.setItemCost(wholeSaleInvDtl.getItemCost());

        return typeResponse;
    }

    @Override
    @Transactional
    public WholeSaleInvDtlResponse save(WholeSaleInvDtlRequest request) {

        WholeSaleInvDtl wholeSaleInvDtl = new WholeSaleInvDtl();
        wholeSaleInvDtl.setItemCode(request.getItemCode());
//        String invNo =
//        wholeSaleInvDtl.setInvno();
        wholeSaleInvDtl.setItemName(request.getItemName());
        wholeSaleInvDtl.setSizeId(request.getSizeId());
        wholeSaleInvDtl.setSizeName(request.getSizeName());
        wholeSaleInvDtl.setQtyByitem(request.getQtyByitem());
        wholeSaleInvDtl.setSizeQty(request.getSizeQty());
        wholeSaleInvDtl.setMrp(request.getMrp());
        wholeSaleInvDtl.setDisPrecentage(request.getDisPrecentage());
        wholeSaleInvDtl.setDisAmount(request.getDisAmount());
        wholeSaleInvDtl.setAmount(request.getAmount());
        wholeSaleInvDtl.setIsDeleted(Deleted.NO);
        wholeSaleInvDtl.setColor(request.getColor());
        wholeSaleInvDtl.setFit(request.getFit());
        wholeSaleInvDtl.setBatchNo(request.getBatchNo());
        wholeSaleInvDtl.setItemCost(request.getItemCost());
        WholeSaleInvDtl save = wholeSaleInvDtlRepository.save(wholeSaleInvDtl);

        return convert(save);
    }

    @Override
    @Transactional
    public WholeSaleInvDtlResponse update(WholeSaleInvDtlUpdateRequest request) {

        WholeSaleInvDtl wholeSaleInvDtl = wholeSaleInvDtlRepository.findById(request.getId()).orElse(null);
        if (wholeSaleInvDtl == null) {
            return null;
        }

        wholeSaleInvDtl.setId(request.getId());
        wholeSaleInvDtl.setInvno(request.getInvno());
        wholeSaleInvDtl.setItemCode(request.getItemCode());
        wholeSaleInvDtl.setItemName(request.getItemName());
        wholeSaleInvDtl.setSizeId(request.getSizeId());
        wholeSaleInvDtl.setSizeName(request.getSizeName());
        wholeSaleInvDtl.setQtyByitem(request.getQtyByitem());
        wholeSaleInvDtl.setSizeQty(request.getSizeQty());
        wholeSaleInvDtl.setMrp(request.getMrp());
        wholeSaleInvDtl.setDisPrecentage(request.getDisPrecentage());
        wholeSaleInvDtl.setDisAmount(request.getDisAmount());
        wholeSaleInvDtl.setAmount(request.getAmount());
        wholeSaleInvDtl.setColor(request.getColor());
        wholeSaleInvDtl.setFit(request.getFit());
        wholeSaleInvDtl.setBatchNo(request.getBatchNo());
        wholeSaleInvDtl.setItemCost(request.getItemCost());
        WholeSaleInvDtl updated = wholeSaleInvDtlRepository.save(wholeSaleInvDtl);

        return (convert(updated));
    }

    @Override
    public WholeSaleInvDtlResponse getById(Long id) {

        return wholeSaleInvDtlRepository.findById(id).map(WholeSaleInvDtlServiceImpl::convert).orElse(null);
    }

    @Override
    public List<WholeSaleInvDtlResponse> getAll() {

        return wholeSaleInvDtlRepository.findByIsDeleted(Deleted.NO)
                .stream().map(WholeSaleInvDtlServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        WholeSaleInvDtl got = wholeSaleInvDtlRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        wholeSaleInvDtlRepository.save(got);

        return 1;
    }



}