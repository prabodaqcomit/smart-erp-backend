package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.PettyCashRequest;
import lk.quantacom.smarterpbackend.dto.request.PettyCashUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PettyCashResponse;
import lk.quantacom.smarterpbackend.entity.PettyCash;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.PettyCashRepository;
import lk.quantacom.smarterpbackend.service.PettyCashService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PettyCashServiceImpl implements PettyCashService {

    @Autowired
    private PettyCashRepository pettyCashRepository;

    private static PettyCashResponse convert(PettyCash pettyCash) {

        PettyCashResponse typeResponse = new PettyCashResponse();
        typeResponse.setAmountReceived(pettyCash.getAmountReceived());
        typeResponse.setBranchId(pettyCash.getBranchId());
        typeResponse.setDescription(pettyCash.getDescription());
        typeResponse.setEntertainment(pettyCash.getEntertainment());
        typeResponse.setFoods(pettyCash.getFoods());
        typeResponse.setGrandTotal(pettyCash.getGrandTotal());
        typeResponse.setIdpettyCash(pettyCash.getIdpettyCash());
        typeResponse.setNoSemiGrandTotal(pettyCash.getNoSemiGrandTotal());
        typeResponse.setNoSemiPayingAmount(pettyCash.getNoSemiPayingAmount());
        typeResponse.setOtherEx(pettyCash.getOtherEx());
        typeResponse.setPayeeName(pettyCash.getPayeeName());
        typeResponse.setPayingAmount(pettyCash.getPayingAmount());
        typeResponse.setPettyCashDate(pettyCash.getPettyCashDate());
        typeResponse.setPostage(pettyCash.getPostage());
        typeResponse.setReference(pettyCash.getReference());
        typeResponse.setStationary(pettyCash.getStationary());
        typeResponse.setTimeTake(pettyCash.getTimeTake());
        typeResponse.setTravel(pettyCash.getTravel());
        typeResponse.setVoucherNo(pettyCash.getVoucherNo());
        typeResponse.setId(pettyCash.getId());
        typeResponse.setCreatedBy(pettyCash.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(pettyCash.getCreatedDateTime()));
        typeResponse.setModifiedBy(pettyCash.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(pettyCash.getModifiedDateTime()));
        typeResponse.setIsDeleted(pettyCash.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public PettyCashResponse save(PettyCashRequest request) {

        PettyCash pettyCash = new PettyCash();
        pettyCash.setAmountReceived(request.getAmountReceived());
        pettyCash.setBranchId(request.getBranchId());
        pettyCash.setDescription(request.getDesctription());
        pettyCash.setEntertainment(request.getEntartainment());
        pettyCash.setFoods(request.getFoods());
        pettyCash.setGrandTotal(request.getGrandTotal());
        pettyCash.setIdpettyCash(request.getIdpettyCash());
        pettyCash.setNoSemiGrandTotal(request.getNoSemiGrandTotal());
        pettyCash.setNoSemiPayingAmount(request.getNoSemiPayingAmount());
        pettyCash.setOtherEx(request.getOtherEx());
        pettyCash.setPayeeName(request.getPayeeName());
        pettyCash.setPayingAmount(request.getPayingAmount());
        pettyCash.setPettyCashDate(request.getPettyCashDate() == null ? null : ConvertUtils.convertStrToDate(request.getPettyCashDate()));
        pettyCash.setPostage(request.getPostage());
        pettyCash.setReference(request.getReference());
        pettyCash.setStationary(request.getStationary());
        pettyCash.setTimeTake(request.getTimeTake() == null ? null : ConvertUtils.convertStrToDate(request.getTimeTake()));
        pettyCash.setTravel(request.getTravel());
        pettyCash.setVoucherNo(request.getVoucherNo());
        pettyCash.setIsDeleted(Deleted.NO);
        PettyCash save = pettyCashRepository.save(pettyCash);

        return convert(save);
    }

    @Override
    @Transactional
    public PettyCashResponse update(PettyCashUpdateRequest request) {

        PettyCash pettyCash = pettyCashRepository.findById(request.getId()).orElse(null);
        if (pettyCash == null) {
            return null;
        }

        pettyCash.setId(request.getId());
        pettyCash.setAmountReceived(request.getAmountReceived());
        pettyCash.setBranchId(request.getBranchId());
        pettyCash.setDescription(request.getDescription());
        pettyCash.setEntertainment(request.getEntertainment());
        pettyCash.setFoods(request.getFoods());
        pettyCash.setGrandTotal(request.getGrandTotal());
        pettyCash.setIdpettyCash(request.getIdpettyCash());
        pettyCash.setNoSemiGrandTotal(request.getNoSemiGrandTotal());
        pettyCash.setNoSemiPayingAmount(request.getNoSemiPayingAmount());
        pettyCash.setOtherEx(request.getOtherEx());
        pettyCash.setPayeeName(request.getPayeeName());
        pettyCash.setPayingAmount(request.getPayingAmount());
        pettyCash.setPettyCashDate(request.getPettyCashDate() == null ? null : ConvertUtils.convertStrToDate(request.getPettyCashDate()));
        pettyCash.setPostage(request.getPostage());
        pettyCash.setReference(request.getReference());
        pettyCash.setStationary(request.getStationary());
        pettyCash.setTimeTake(request.getTimeTake() == null ? null : ConvertUtils.convertStrToDate(request.getTimeTake()));
        pettyCash.setTravel(request.getTravel());
        pettyCash.setVoucherNo(request.getVoucherNo());
        PettyCash updated = pettyCashRepository.save(pettyCash);

        return (convert(updated));
    }

    @Override
    public PettyCashResponse getById(Long id) {

        return pettyCashRepository.findById(id).map(PettyCashServiceImpl::convert).orElse(null);
    }

    @Override
    public List<PettyCashResponse> getAll() {

        return pettyCashRepository.findByIsDeleted(Deleted.NO)
                .stream().map(PettyCashServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        PettyCash got = pettyCashRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        pettyCashRepository.save(got);

        return 1;
    }

    @Override
    public List<PettyCashResponse> findByPayeeName(String payeeName) {
        return pettyCashRepository.findByPayeeName(payeeName)
                .stream().map(PettyCashServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<PettyCashResponse> findByDesctription(String desctription) {
        return pettyCashRepository.findByDescription(desctription)
                .stream().map(PettyCashServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<PettyCashResponse> getByDateAndBranchId(String Date, Integer branchId) {
        return pettyCashRepository.getByDateAndBranchId(Date,branchId)
                .stream().map(PettyCashServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<PettyCashResponse> getByPayeeNameAndDateAndBranchId(String payeeName, String startDate, String endDate, Integer branchId) {
         startDate = startDate+" 00:00:00";
         endDate = endDate+" 23:59:59";
         return pettyCashRepository.getByPayeeNameAndDateAndBranchId(payeeName,startDate,endDate,branchId)
                .stream().map(PettyCashServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<PettyCashResponse> getAllByDateAndBranchId(String startDate, String endDate, Integer branchId) {
        startDate = startDate+" 00:00:00";
        endDate = endDate+" 23:59:59";
        return pettyCashRepository.getAllByDateAndBranchId(startDate,endDate,branchId)
                .stream().map(PettyCashServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<PettyCashResponse> getByDateRangeAndBranchId(String startDate, String endDate, Integer branchId) {
        startDate = startDate+" 00:00:00";
        endDate = endDate+" 23:59:59";
        return pettyCashRepository.getByDateRangeAndBranchId(startDate,endDate,branchId)
                .stream().map(PettyCashServiceImpl::convert).collect(Collectors.toList());
    }
}