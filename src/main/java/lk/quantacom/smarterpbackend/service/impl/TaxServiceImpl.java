package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.TaxRequest;
import lk.quantacom.smarterpbackend.dto.request.TaxUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TaxResponse;
import lk.quantacom.smarterpbackend.entity.Tax;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.TaxRepository;
import lk.quantacom.smarterpbackend.service.TaxService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaxServiceImpl implements TaxService {

    @Autowired
    private TaxRepository taxRepository;

    private static TaxResponse convert(Tax tax) {

        TaxResponse typeResponse = new TaxResponse();
        typeResponse.setTaxName(tax.getTaxName());
        typeResponse.setTaxRate(tax.getTaxRate());
        typeResponse.setTaxStatus(tax.getTaxStatus());
        typeResponse.setId(tax.getId());
        typeResponse.setCreatedBy(tax.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(tax.getCreatedDateTime()));
        typeResponse.setModifiedBy(tax.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(tax.getModifiedDateTime()));
        typeResponse.setIsDeleted(tax.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public TaxResponse save(TaxRequest request) {

        Tax tax = new Tax();
        tax.setTaxName(request.getTaxName());
        tax.setTaxRate(request.getTaxRate());
        tax.setTaxStatus(request.getTaxStatus());
        tax.setIsDeleted(Deleted.NO);
        Tax save = taxRepository.save(tax);

        return convert(save);
    }

    @Override
    @Transactional
    public TaxResponse update(TaxUpdateRequest request) {

        Tax tax = taxRepository.findById(request.getId()).orElse(null);
        if (tax == null) {
            return null;
        }

        tax.setId(request.getId());
        tax.setTaxName(request.getTaxName());
        tax.setTaxRate(request.getTaxRate());
        tax.setTaxStatus(request.getTaxStatus());
        Tax updated = taxRepository.save(tax);

        return (convert(updated));
    }

    @Override
    public TaxResponse getById(Long id) {

        return taxRepository.findById(id).map(TaxServiceImpl::convert).orElse(null);
    }

    @Override
    public List<TaxResponse> getAll() {

        return taxRepository.findByIsDeleted(Deleted.NO)
                .stream().map(TaxServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public TaxResponse getByName(String name) {
        Tax tax =  taxRepository.findByTaxNameAndIsDeleted(name,Deleted.NO);
        return convert(tax);
    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        Tax got = taxRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        taxRepository.save(got);

        return 1;
    }
}