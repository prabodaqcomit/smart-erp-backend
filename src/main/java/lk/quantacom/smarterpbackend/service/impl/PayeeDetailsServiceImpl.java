package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.PayeeDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.PayeeDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PayeeDetailsResponse;
import lk.quantacom.smarterpbackend.entity.PayeeDetails;
import lk.quantacom.smarterpbackend.repository.PayeeDetailsRepository;
import lk.quantacom.smarterpbackend.service.PayeeDetailsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PayeeDetailsServiceImpl implements PayeeDetailsService {

    @Autowired
    private PayeeDetailsRepository payeeDetailsRepository;



    @Override
@Transactional
    public PayeeDetailsResponse save(PayeeDetailsRequest request) {

        PayeeDetails payeeDetails=new PayeeDetails();
payeeDetails.setName(request.getName());
payeeDetails.setGender(request.getGender());
payeeDetails.setAdd1(request.getAdd1());
payeeDetails.setAdd2(request.getAdd2());
payeeDetails.setAdd3(request.getAdd3());
payeeDetails.setLocation(request.getLocation());
payeeDetails.setMobile(request.getMobile());
payeeDetails.setEmail(request.getEmail());
payeeDetails.setNic(request.getNic());
payeeDetails.setIsDeleted(Deleted.NO);
PayeeDetails save=payeeDetailsRepository.save(payeeDetails);

        return convert(save);
    }

    @Override
    @Transactional
    public PayeeDetailsResponse update(PayeeDetailsUpdateRequest request) {

PayeeDetails payeeDetails = payeeDetailsRepository.findById(request.getId()).orElse(null);
        if(payeeDetails==null){
            return null;
        }

payeeDetails.setId(request.getId());
payeeDetails.setName(request.getName());
payeeDetails.setGender(request.getGender());
payeeDetails.setAdd1(request.getAdd1());
payeeDetails.setAdd2(request.getAdd2());
payeeDetails.setAdd3(request.getAdd3());
payeeDetails.setLocation(request.getLocation());
payeeDetails.setMobile(request.getMobile());
payeeDetails.setEmail(request.getEmail());
payeeDetails.setNic(request.getNic());
PayeeDetails updated=payeeDetailsRepository.save(payeeDetails);

        return (convert(updated));
    }

    @Override
    public PayeeDetailsResponse getById(Long id) {

       return payeeDetailsRepository.findById(id).map(PayeeDetailsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<PayeeDetailsResponse> getAll() {

        return  payeeDetailsRepository.findByIsDeleted(Deleted.NO)
                .stream().map(PayeeDetailsServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

PayeeDetails got=payeeDetailsRepository.findById(id).orElse(null);
        if(got==null){
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        payeeDetailsRepository.save(got);

        return  1;
    }

private static PayeeDetailsResponse convert(PayeeDetails payeeDetails){

        PayeeDetailsResponse typeResponse=new PayeeDetailsResponse();
typeResponse.setName(payeeDetails.getName());
typeResponse.setGender(payeeDetails.getGender());
typeResponse.setAdd1(payeeDetails.getAdd1());
typeResponse.setAdd2(payeeDetails.getAdd2());
typeResponse.setAdd3(payeeDetails.getAdd3());
typeResponse.setLocation(payeeDetails.getLocation());
typeResponse.setMobile(payeeDetails.getMobile());
typeResponse.setEmail(payeeDetails.getEmail());
typeResponse.setNic(payeeDetails.getNic());
        typeResponse.setId(payeeDetails.getId());
        typeResponse.setCreatedBy(payeeDetails.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(payeeDetails.getCreatedDateTime()));
        typeResponse.setModifiedBy(payeeDetails.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(payeeDetails.getModifiedDateTime()));
        typeResponse.setIsDeleted(payeeDetails.getIsDeleted());

return typeResponse;
    }
}