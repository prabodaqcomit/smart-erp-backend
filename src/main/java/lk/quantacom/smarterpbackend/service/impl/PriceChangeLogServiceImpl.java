package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.PriceChangeLogRequest;
import lk.quantacom.smarterpbackend.dto.request.PriceChangeLogUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PriceChangeLogResponse;
import lk.quantacom.smarterpbackend.entity.PriceChangeLog;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.PriceChangeLogRepository;
import lk.quantacom.smarterpbackend.service.PriceChangeLogService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceChangeLogServiceImpl implements PriceChangeLogService {

    @Autowired
    private PriceChangeLogRepository priceChangeLogRepository;

    private static PriceChangeLogResponse convert(PriceChangeLog priceChangeLog) {

        PriceChangeLogResponse typeResponse = new PriceChangeLogResponse();
        typeResponse.setId(priceChangeLog.getId());
        typeResponse.setUser(priceChangeLog.getUser());
        typeResponse.setItemCode(priceChangeLog.getItemCode());
        typeResponse.setItemName(priceChangeLog.getItemName());
        typeResponse.setDate(ConvertUtils.convertDateToStr(priceChangeLog.getDate()));
        typeResponse.setPrevCostPrice(priceChangeLog.getPrevCostPrice());
        typeResponse.setPrevUnitPrice(priceChangeLog.getPrevUnitPrice());
        typeResponse.setColor(priceChangeLog.getColor());
        typeResponse.setSize(priceChangeLog.getSize());
        typeResponse.setFit(priceChangeLog.getFit());
        typeResponse.setBranch(priceChangeLog.getBranch());
        typeResponse.setBatchNo(priceChangeLog.getBatchNo());
        typeResponse.setStkCashPrice(priceChangeLog.getStkCashPrice());
        typeResponse.setStkCreditPrice(priceChangeLog.getStkCreditPrice());
        typeResponse.setStockUnitPriceRetail(priceChangeLog.getStockUnitPriceRetail());
        typeResponse.setStockUnitPriceWholesale(priceChangeLog.getStockUnitPriceWholesale());
        typeResponse.setCashDisValue(priceChangeLog.getCashDisValue());
        typeResponse.setCreditDisValue(priceChangeLog.getCreditDisValue());
        typeResponse.setSalesDiscoPresentage(priceChangeLog.getSalesDiscoPresentage());
        typeResponse.setNewCostPrice(priceChangeLog.getNewCostPrice());
        typeResponse.setNewUnitPrice(priceChangeLog.getNewUnitPrice());
        typeResponse.setId(priceChangeLog.getId());
        typeResponse.setCreatedBy(priceChangeLog.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(priceChangeLog.getCreatedDateTime()));
        typeResponse.setModifiedBy(priceChangeLog.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(priceChangeLog.getModifiedDateTime()));
        typeResponse.setIsDeleted(priceChangeLog.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public PriceChangeLogResponse save(PriceChangeLogRequest request) {

        PriceChangeLog priceChangeLog = new PriceChangeLog();
        priceChangeLog.setUser(request.getUser());
        priceChangeLog.setItemCode(request.getItemCode());
        priceChangeLog.setItemName(request.getItemName());
        priceChangeLog.setDate(new Date());
        priceChangeLog.setPrevCostPrice(request.getPrevCostPrice());
        priceChangeLog.setPrevUnitPrice(request.getPrevUnitPrice());
        priceChangeLog.setColor(request.getColor());
        priceChangeLog.setSize(request.getSize());
        priceChangeLog.setFit(request.getFit());
        priceChangeLog.setBranch(request.getBranch());
        priceChangeLog.setBatchNo(request.getBatchNo());
        priceChangeLog.setStkCashPrice(request.getStkCashPrice());
        priceChangeLog.setStkCreditPrice(request.getStkCreditPrice());
        priceChangeLog.setStockUnitPriceRetail(request.getStockUnitPriceRetail());
        priceChangeLog.setStockUnitPriceWholesale(request.getStockUnitPriceWholesale());
        priceChangeLog.setCashDisValue(request.getCashDisValue());
        priceChangeLog.setCreditDisValue(request.getCreditDisValue());
        priceChangeLog.setSalesDiscoPresentage(request.getSalesDiscoPresentage());
        priceChangeLog.setNewCostPrice(request.getNewCostPrice());
        priceChangeLog.setNewUnitPrice(request.getNewUnitPrice());
        priceChangeLog.setIsDeleted(Deleted.NO);
        PriceChangeLog save = priceChangeLogRepository.save(priceChangeLog);

        return convert(save);
    }

    @Override
    @Transactional
    public PriceChangeLogResponse update(PriceChangeLogUpdateRequest request) {

        PriceChangeLog priceChangeLog = priceChangeLogRepository.findById(request.getId()).orElse(null);
        if (priceChangeLog == null) {
            return null;
        }

        priceChangeLog.setId(request.getId());
        priceChangeLog.setUser(request.getUser());
        priceChangeLog.setItemCode(request.getItemCode());
        priceChangeLog.setItemName(request.getItemName());
        priceChangeLog.setDate(new Date());
        priceChangeLog.setPrevCostPrice(request.getPrevCostPrice());
        priceChangeLog.setPrevUnitPrice(request.getPrevUnitPrice());
        priceChangeLog.setColor(request.getColor());
        priceChangeLog.setSize(request.getSize());
        priceChangeLog.setFit(request.getFit());
        priceChangeLog.setBranch(request.getBranch());
        priceChangeLog.setBatchNo(request.getBatchNo());
        priceChangeLog.setStkCashPrice(request.getStkCashPrice());
        priceChangeLog.setStkCreditPrice(request.getStkCreditPrice());
        priceChangeLog.setStockUnitPriceRetail(request.getStockUnitPriceRetail());
        priceChangeLog.setStockUnitPriceWholesale(request.getStockUnitPriceWholesale());
        priceChangeLog.setCashDisValue(request.getCashDisValue());
        priceChangeLog.setCreditDisValue(request.getCreditDisValue());
        priceChangeLog.setSalesDiscoPresentage(request.getSalesDiscoPresentage());
        priceChangeLog.setNewCostPrice(request.getNewCostPrice());
        priceChangeLog.setNewUnitPrice(request.getNewUnitPrice());
        PriceChangeLog updated = priceChangeLogRepository.save(priceChangeLog);

        return (convert(updated));
    }

    @Override
    public PriceChangeLogResponse getById(Long id) {

        return priceChangeLogRepository.findById(id).map(PriceChangeLogServiceImpl::convert).orElse(null);
    }

    @Override
    public List<PriceChangeLogResponse> getAll() {

        return priceChangeLogRepository.findByIsDeleted(Deleted.NO)
                .stream().map(PriceChangeLogServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        PriceChangeLog got = priceChangeLogRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        priceChangeLogRepository.save(got);

        return 1;
    }
}