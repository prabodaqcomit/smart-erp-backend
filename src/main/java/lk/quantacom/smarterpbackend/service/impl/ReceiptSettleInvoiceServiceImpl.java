package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.response.ReceiptSettleInvoiceResponse;
import lk.quantacom.smarterpbackend.dto.response.TblinvhedResponse;
import lk.quantacom.smarterpbackend.entity.ReceiptSettleInvoice;
import lk.quantacom.smarterpbackend.entity.Tblinvhed;
import lk.quantacom.smarterpbackend.repository.ReceiptSettleInvoiceRepository;
import lk.quantacom.smarterpbackend.repository.TblinvhedRepository;
import lk.quantacom.smarterpbackend.service.ReceiptSettleInvoiceService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceiptSettleInvoiceServiceImpl implements ReceiptSettleInvoiceService {

    @Autowired
    private ReceiptSettleInvoiceRepository receiptSettleInvoiceRepository;

    @Autowired
    private TblinvhedRepository tblinvhedRepository;

    @Override
    @Transactional
    public ReceiptSettleInvoice save(ReceiptSettleInvoice request) {

        ReceiptSettleInvoice receiptSettleInvoice = new ReceiptSettleInvoice();
        receiptSettleInvoice.setReceiptId(request.getReceiptId());
        receiptSettleInvoice.setInvoiceLocation(request.getInvoiceLocation());
        receiptSettleInvoice.setInvoiceNumber(request.getInvoiceNumber());
        receiptSettleInvoice.setInvoiceAmount(request.getInvoiceAmount());
        receiptSettleInvoice.setSettleAmount(request.getSettleAmount());
        receiptSettleInvoice.setBalanceAmount(request.getBalanceAmount());
        receiptSettleInvoice.setCustomerId(request.getCustomerId());
        receiptSettleInvoice.setIsReturnedInvoice(request.getIsReturnedInvoice());
        receiptSettleInvoice.setIsDeleted(Deleted.NO);
        ReceiptSettleInvoice save = receiptSettleInvoiceRepository.save(receiptSettleInvoice);

        return save;
    }

    @Override
    @Transactional
    public ReceiptSettleInvoice update(ReceiptSettleInvoice request) {

        ReceiptSettleInvoice receiptSettleInvoice = receiptSettleInvoiceRepository.findById(request.getId()).orElse(null);
        if (receiptSettleInvoice == null) {
            return null;
        }

        receiptSettleInvoice.setId(request.getId());
        receiptSettleInvoice.setReceiptId(request.getReceiptId());
        receiptSettleInvoice.setInvoiceLocation(request.getInvoiceLocation());
        receiptSettleInvoice.setInvoiceNumber(request.getInvoiceNumber());
        receiptSettleInvoice.setInvoiceAmount(request.getInvoiceAmount());
        receiptSettleInvoice.setSettleAmount(request.getSettleAmount());
        receiptSettleInvoice.setBalanceAmount(request.getBalanceAmount());
        receiptSettleInvoice.setCustomerId(request.getCustomerId());
        receiptSettleInvoice.setIsReturnedInvoice(request.getIsReturnedInvoice());
        ReceiptSettleInvoice updated = receiptSettleInvoiceRepository.save(receiptSettleInvoice);

        return updated;
    }

    @Override
    public List<ReceiptSettleInvoiceResponse> getPendingCreditInvoicesByCustomerId(Long customerId){

        List<Tblinvhed> _lstPendingInvoices = tblinvhedRepository.getPendingCreditInvoicesByCustomerId(customerId);
        if( _lstPendingInvoices == null || _lstPendingInvoices.isEmpty() ){ return null;}

        List<ReceiptSettleInvoiceResponse> _lstResponse = new ArrayList<>();

        for(Tblinvhed tblinvhed: _lstPendingInvoices){

            ReceiptSettleInvoice receiptSettleInvoice = receiptSettleInvoiceRepository.getLastPaidReceiptByInvoiceLocationAndInvoiceNumber(
                    tblinvhed.getFldLocation(),
                    tblinvhed.getFldInvno()
            );

            ReceiptSettleInvoiceResponse settleInvoiceResponse = new ReceiptSettleInvoiceResponse();
            settleInvoiceResponse.setInvoiceHeader(tblinvhed);
            settleInvoiceResponse.setPendingAmount(tblinvhed.getFldNetamount());

            if( receiptSettleInvoice != null ){
                settleInvoiceResponse.setPendingAmount(receiptSettleInvoice.getBalanceAmount());
            }
            _lstResponse.add(settleInvoiceResponse);
        }

        return _lstResponse;
    }

    @Override
    public ReceiptSettleInvoice getById(Long id) {

        return receiptSettleInvoiceRepository.findById(id).orElse(null);
    }

    @Override
    public List<ReceiptSettleInvoice> getAll() {

        return new ArrayList<>(receiptSettleInvoiceRepository.findByIsDeleted(Deleted.NO));

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        ReceiptSettleInvoice got = receiptSettleInvoiceRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        receiptSettleInvoiceRepository.save(got);

        return 1;
    }

//    private static ReceiptSettleInvoiceResponse convert(ReceiptSettleInvoice receiptSettleInvoice) {
//
//        ReceiptSettleInvoiceResponse typeResponse = new ReceiptSettleInvoiceResponse();
//        typeResponse.setReceiptId(receiptSettleInvoice.getReceiptId());
//        typeResponse.setInvoiceLocation(receiptSettleInvoice.getInvoiceLocation());
//        typeResponse.setInvoiceNumber(receiptSettleInvoice.getInvoiceNumber());
//        typeResponse.setInvoiceAmount(receiptSettleInvoice.getInvoiceAmount());
//        typeResponse.setSettleAmount(receiptSettleInvoice.getSettleAmount());
//        typeResponse.setBalanceAmount(receiptSettleInvoice.getBalanceAmount());
//        typeResponse.setCustomerId(receiptSettleInvoice.getCustomerId());
//        typeResponse.setIsReturnedInvoice(receiptSettleInvoice.getIsReturnedInvoice());
//        typeResponse.setId(receiptSettleInvoice.getId());
//        typeResponse.setCreatedBy(receiptSettleInvoice.getCreatedBy());
//        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(receiptSettleInvoice.getCreatedDateTime()));
//        typeResponse.setModifiedBy(receiptSettleInvoice.getModifiedBy());
//        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(receiptSettleInvoice.getModifiedDateTime()));
//        typeResponse.setIsDeleted(receiptSettleInvoice.getIsDeleted());
//
//        return typeResponse;
//    }
}