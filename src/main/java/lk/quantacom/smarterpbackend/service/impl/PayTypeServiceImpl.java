package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.PayTypeRequest;
import lk.quantacom.smarterpbackend.dto.request.PayTypeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PayTypeResponse;
import lk.quantacom.smarterpbackend.entity.PayType;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.PayTypeRepository;
import lk.quantacom.smarterpbackend.service.PayTypeService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PayTypeServiceImpl implements PayTypeService {

    @Autowired
    private PayTypeRepository payTypeRepository;



    @Override
@Transactional
    public PayTypeResponse save(PayTypeRequest request) {

        PayType payType=new PayType();
payType.setName(request.getName());
payType.setIsDeleted(Deleted.NO);
PayType save=payTypeRepository.save(payType);

        return convert(save);
    }

    @Override
    @Transactional
    public PayTypeResponse update(PayTypeUpdateRequest request) {

PayType payType = payTypeRepository.findById(request.getId()).orElse(null);
        if(payType==null){
            return null;
        }

payType.setId(request.getId());
payType.setName(request.getName());
PayType updated=payTypeRepository.save(payType);

        return (convert(updated));
    }

    @Override
    public PayTypeResponse getById(Long id) {

       return payTypeRepository.findById(id).map(PayTypeServiceImpl::convert).orElse(null);
    }

    @Override
    public List<PayTypeResponse> getAll() {

        return  payTypeRepository.findByIsDeleted(Deleted.NO)
                .stream().map(PayTypeServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

PayType got=payTypeRepository.findById(id).orElse(null);
        if(got==null){
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        payTypeRepository.save(got);

        return  1;
    }

private static PayTypeResponse convert(PayType payType){

        PayTypeResponse typeResponse=new PayTypeResponse();
typeResponse.setName(payType.getName());
        typeResponse.setId(payType.getId());
        typeResponse.setCreatedBy(payType.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(payType.getCreatedDateTime()));
        typeResponse.setModifiedBy(payType.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(payType.getModifiedDateTime()));
        typeResponse.setIsDeleted(payType.getIsDeleted());

return typeResponse;
    }
}