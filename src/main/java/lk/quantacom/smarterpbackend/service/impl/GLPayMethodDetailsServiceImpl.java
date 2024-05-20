package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.GLPayMethodDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.GLPayMethodDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GLPayMethodDetailsResponse;
import lk.quantacom.smarterpbackend.entity.GLPayMethodDetails;
import lk.quantacom.smarterpbackend.repository.GLPayMethodDetailsRepository;
import lk.quantacom.smarterpbackend.service.GLPayMethodDetailsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GLPayMethodDetailsServiceImpl implements GLPayMethodDetailsService {

    @Autowired
    private GLPayMethodDetailsRepository gLPayMethodDetailsRepository;

    private static GLPayMethodDetailsResponse convert(GLPayMethodDetails gLPayMethodDetails) {

        GLPayMethodDetailsResponse typeResponse = new GLPayMethodDetailsResponse();

        typeResponse.setPayMethodId(gLPayMethodDetails.getPayMethodId());
        typeResponse.setGlPayHeaderId(gLPayMethodDetails.getGlPayHeaderId());
        typeResponse.setPayMethodName(gLPayMethodDetails.getPayMethodName());
        typeResponse.setAmount(gLPayMethodDetails.getAmount());
        typeResponse.setChequeDate(gLPayMethodDetails.getChequeDate());
        typeResponse.setBank(gLPayMethodDetails.getBank());
        typeResponse.setAccount(gLPayMethodDetails.getAccount());
        typeResponse.setChequeNumber(gLPayMethodDetails.getChequeNumber());
        typeResponse.setReference(gLPayMethodDetails.getReference());
        typeResponse.setTransferDate(gLPayMethodDetails.getTransferDate());
        typeResponse.setFromBank(gLPayMethodDetails.getFromBank());
        typeResponse.setFromAccount(gLPayMethodDetails.getFromAccount());
        typeResponse.setToBank(gLPayMethodDetails.getToBank());
        typeResponse.setToAccount(gLPayMethodDetails.getToAccount());
        typeResponse.setFromWallet(gLPayMethodDetails.getFromWallet());
        typeResponse.setToWallet(gLPayMethodDetails.getToWallet());
        typeResponse.setFromCard(gLPayMethodDetails.getFromCard());
        typeResponse.setId(gLPayMethodDetails.getId());
        typeResponse.setCreatedBy(gLPayMethodDetails.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(gLPayMethodDetails.getCreatedDateTime()));
        typeResponse.setModifiedBy(gLPayMethodDetails.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(gLPayMethodDetails.getModifiedDateTime()));
        typeResponse.setIsDeleted(gLPayMethodDetails.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public GLPayMethodDetailsResponse save(GLPayMethodDetailsRequest request) {

        GLPayMethodDetails gLPayMethodDetails = new GLPayMethodDetails();
        gLPayMethodDetails.setGlPayHeaderId(request.getGlPayHeaderId());
        gLPayMethodDetails.setPayMethodId(request.getPayMethodId());
        gLPayMethodDetails.setPayMethodName(request.getPayMethodName());
        gLPayMethodDetails.setAmount(request.getAmount());
        gLPayMethodDetails.setChequeDate(request.getChequeDate() == null ? null : ConvertUtils.convertStrToDate(request.getChequeDate()));
        gLPayMethodDetails.setBank(request.getBank());
        gLPayMethodDetails.setAccount(request.getAccount());
        gLPayMethodDetails.setChequeNumber(request.getChequeNumber());
        gLPayMethodDetails.setReference(request.getReference());
        gLPayMethodDetails.setTransferDate(request.getTransferDate() == null ? null : ConvertUtils.convertStrToDate(request.getTransferDate()));
        gLPayMethodDetails.setFromBank(request.getFromBank());
        gLPayMethodDetails.setFromAccount(request.getFromAccount());
        gLPayMethodDetails.setToBank(request.getToBank());
        gLPayMethodDetails.setToAccount(request.getToAccount());
        gLPayMethodDetails.setFromWallet(request.getFromWallet());
        gLPayMethodDetails.setToWallet(request.getToWallet());
        gLPayMethodDetails.setFromCard(request.getFromCard());
        gLPayMethodDetails.setIsDeleted(Deleted.NO);
        GLPayMethodDetails save = gLPayMethodDetailsRepository.save(gLPayMethodDetails);

        return convert(save);
    }

    @Override
    @Transactional
    public GLPayMethodDetailsResponse update(GLPayMethodDetailsUpdateRequest request) {

        GLPayMethodDetails gLPayMethodDetails = gLPayMethodDetailsRepository.findById(request.getId()).orElse(null);
        if (gLPayMethodDetails == null) {
            return null;
        }

        gLPayMethodDetails.setGlPayHeaderId(request.getGlPayHeaderId());
        gLPayMethodDetails.setId(request.getId());
        gLPayMethodDetails.setPayMethodId(request.getPayMethodId());
        gLPayMethodDetails.setPayMethodName(request.getPayMethodName());
        gLPayMethodDetails.setAmount(request.getAmount());
        gLPayMethodDetails.setChequeDate(request.getChequeDate() == null ? null : ConvertUtils.convertStrToDate(request.getChequeDate()));
        gLPayMethodDetails.setBank(request.getBank());
        gLPayMethodDetails.setAccount(request.getAccount());
        gLPayMethodDetails.setChequeNumber(request.getChequeNumber());
        gLPayMethodDetails.setReference(request.getReference());
        gLPayMethodDetails.setTransferDate(request.getTransferDate() == null ? null : ConvertUtils.convertStrToDate(request.getTransferDate()));
        gLPayMethodDetails.setFromBank(request.getFromBank());
        gLPayMethodDetails.setFromAccount(request.getFromAccount());
        gLPayMethodDetails.setToBank(request.getToBank());
        gLPayMethodDetails.setToAccount(request.getToAccount());
        gLPayMethodDetails.setFromWallet(request.getFromWallet());
        gLPayMethodDetails.setToWallet(request.getToWallet());
        gLPayMethodDetails.setFromCard(request.getFromCard());
        GLPayMethodDetails updated = gLPayMethodDetailsRepository.save(gLPayMethodDetails);

        return (convert(updated));
    }

    @Override
    public GLPayMethodDetailsResponse getById(Long id) {

        return gLPayMethodDetailsRepository.findById(id).map(GLPayMethodDetailsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GLPayMethodDetailsResponse> getAll() {

        return gLPayMethodDetailsRepository.findByIsDeleted(Deleted.NO)
                .stream().map(GLPayMethodDetailsServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        GLPayMethodDetails got = gLPayMethodDetailsRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        gLPayMethodDetailsRepository.save(got);

        return 1;
    }
}