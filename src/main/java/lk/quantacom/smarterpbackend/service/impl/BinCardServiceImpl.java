package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.BinCardRequest;
import lk.quantacom.smarterpbackend.dto.request.BinCardUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BinCardResponse;
import lk.quantacom.smarterpbackend.entity.BinCard;
import lk.quantacom.smarterpbackend.entity.BranchNetwork;
import lk.quantacom.smarterpbackend.entity.ItemMaster;
import lk.quantacom.smarterpbackend.repository.BinCardRepository;
import lk.quantacom.smarterpbackend.service.BinCardService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BinCardServiceImpl implements BinCardService {

    @Autowired
    private BinCardRepository binCardRepository;

    @Override
    public BinCardResponse save(BinCardRequest request) {

        BinCard binCard = new BinCard();
        ItemMaster item=new ItemMaster();
        item.setId(request.getItemId());
        binCard.setItem(item);
        binCard.setBinCardDate(request.getBinCardDate() == null ? null : ConvertUtils.convertStrToDate(request.getBinCardDate()));
        binCard.setDocType(request.getDocType());
        binCard.setDocNo(request.getDocNo());
        binCard.setRecQty(request.getRecQty());
        binCard.setIsueQty(request.getIsueQty());
        binCard.setBalanceQty(request.getBalanceQty());
        binCard.setBatchNo(request.getBatchNo());

        BranchNetwork branchNetwork=new BranchNetwork();
        branchNetwork.setId(request.getBranchId());
        binCard.setBranch(branchNetwork);
        binCard.setIsDeleted(Deleted.NO);
        BinCard save = binCardRepository.save(binCard);

        return convert(save);
    }

    @Override
    @Transactional
    public BinCardResponse update(BinCardUpdateRequest request) {

        BinCard binCard = binCardRepository.findById(request.getId()).orElse(null);
        if (binCard == null) {
            return null;
        }

        binCard.setId(request.getId());
        ItemMaster item=new ItemMaster();
        item.setId(request.getItemId());
        binCard.setItem(item);

        binCard.setBinCardDate(request.getBinCardDate() == null ? null : ConvertUtils.convertStrToDate(request.getBinCardDate()));
        binCard.setDocType(request.getDocType());
        binCard.setDocNo(request.getDocNo());
        binCard.setRecQty(request.getRecQty());
        binCard.setIsueQty(request.getIsueQty());
        binCard.setBalanceQty(request.getBalanceQty());
        binCard.setBatchNo(request.getBatchNo());

        BranchNetwork branchNetwork=new BranchNetwork();
        branchNetwork.setId(request.getBranchId());
        binCard.setBranch(branchNetwork);
        BinCard updated = binCardRepository.save(binCard);

        return (convert(updated));
    }

    @Override
    public BinCardResponse getById(Long id) {

        return binCardRepository.findById(id).map(BinCardServiceImpl::convert).orElse(null);
    }

    @Override
    public List<BinCardResponse> getAll() {

        return binCardRepository.findAll()
                .stream().map(BinCardServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        BinCard got = binCardRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        binCardRepository.save(got);

        return 1;
    }

    private static BinCardResponse convert(BinCard binCard) {

        BinCardResponse typeResponse = new BinCardResponse();
        typeResponse.setItemId(binCard.getItem().getId());
        typeResponse.setBinCardDate(ConvertUtils.convertDateToStr(binCard.getBinCardDate()));
        typeResponse.setDocType(binCard.getDocType());
        typeResponse.setDocNo(binCard.getDocNo());
        typeResponse.setRecQty(binCard.getRecQty());
        typeResponse.setIsueQty(binCard.getIsueQty());
        typeResponse.setBalanceQty(binCard.getBalanceQty());
        typeResponse.setBatchNo(binCard.getBatchNo());
        typeResponse.setBranchId(binCard.getBranch().getId());
        typeResponse.setId(binCard.getId());
        typeResponse.setCreatedBy(binCard.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(binCard.getCreatedDateTime()));
        typeResponse.setModifiedBy(binCard.getModifiedBy());
        typeResponse.setIsDeleted(binCard.getIsDeleted());

        return typeResponse;
    }
}