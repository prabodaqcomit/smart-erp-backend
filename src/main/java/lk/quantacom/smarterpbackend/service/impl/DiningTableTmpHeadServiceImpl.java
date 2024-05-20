package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.DiningTableTmpHeadRequest;
import lk.quantacom.smarterpbackend.dto.request.DiningTableTmpHeadUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.DiningTableTmpDetailsResponse;
import lk.quantacom.smarterpbackend.dto.response.DiningTableTmpHeadResponse;
import lk.quantacom.smarterpbackend.entity.DiningTableTmpDetails;
import lk.quantacom.smarterpbackend.entity.DiningTableTmpHead;
import lk.quantacom.smarterpbackend.entity.ItemMaster;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.DiningTableTmpDetailsRepository;
import lk.quantacom.smarterpbackend.repository.DiningTableTmpHeadRepository;
import lk.quantacom.smarterpbackend.repository.ItemMasterRepository;
import lk.quantacom.smarterpbackend.service.DiningTableTmpHeadService;

import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiningTableTmpHeadServiceImpl implements DiningTableTmpHeadService {

    @Autowired
    private DiningTableTmpHeadRepository diningTableTmpHeadRepository;

    @Autowired
    private DiningTableTmpDetailsRepository diningTableTmpDetailsRepository;

    @Autowired
    private ItemMasterRepository itemMasterRepository;


    @Override
    @Transactional
    public DiningTableTmpHeadResponse save(DiningTableTmpHeadRequest request) {

        DiningTableTmpHead diningTableTmpHead;
        DiningTableTmpHead tmpHead= diningTableTmpHeadRepository.findByTableIdAndInvStatusAndIsDeleted(request.getTableId(),0,Deleted.NO);

        if(tmpHead!=null){
            diningTableTmpHead=tmpHead;
        }else{
            diningTableTmpHead = new DiningTableTmpHead();
        }

        diningTableTmpHead.setTableId(request.getTableId());
        diningTableTmpHead.setPaymentType(request.getPaymentType());
        diningTableTmpHead.setKotPrinted(request.getKotPrinted());
        diningTableTmpHead.setBotPrinted(request.getBotPrinted());
        diningTableTmpHead.setIsDeleted(Deleted.NO);
        diningTableTmpHead.setInvStatus(0);
        DiningTableTmpHead save = diningTableTmpHeadRepository.save(diningTableTmpHead);

        if(request.getItemDetails()!=null){
            DiningTableTmpDetails diningTableTmpDetails = new DiningTableTmpDetails();
            diningTableTmpDetails.setHeadId(save.getId());
            diningTableTmpDetails.setTableId(request.getItemDetails().getTableId());
            diningTableTmpDetails.setItemCode(request.getItemDetails().getItemCode());
            diningTableTmpDetails.setBatchNo(request.getItemDetails().getBatchNo());
            diningTableTmpDetails.setSelectedPrice(request.getItemDetails().getSelectedPrice());
            diningTableTmpDetails.setQuantity(request.getItemDetails().getQuantity());
            diningTableTmpDetails.setIsKOT(request.getItemDetails().getIsKOT());
            diningTableTmpDetails.setIsBOT(request.getItemDetails().getIsBOT());
            diningTableTmpDetails.setInvStatus(request.getItemDetails().getInvStatus());
            diningTableTmpDetails.setIsDeleted(Deleted.NO);
            diningTableTmpDetailsRepository.save(diningTableTmpDetails);
        }

        return convert(save);
    }

    @Override
    @Transactional
    public DiningTableTmpHeadResponse update(DiningTableTmpHeadUpdateRequest request) {

        DiningTableTmpHead diningTableTmpHead = diningTableTmpHeadRepository.findById(request.getId()).orElse(null);
        if (diningTableTmpHead == null) {
            return null;
        }

        diningTableTmpHead.setInvoiceNo(request.getInvoiceNo());
        diningTableTmpHead.setInvStatus(request.getInvStatus());
        diningTableTmpHead.setId(request.getId());
        diningTableTmpHead.setTableId(request.getTableId());
        diningTableTmpHead.setPaymentType(request.getPaymentType());
        diningTableTmpHead.setKotPrinted(request.getKotPrinted());
        diningTableTmpHead.setBotPrinted(request.getBotPrinted());
        DiningTableTmpHead updated = diningTableTmpHeadRepository.save(diningTableTmpHead);

        if(request.getItemDetails()!=null){
            DiningTableTmpDetails diningTableTmpDetails = new DiningTableTmpDetails();
            diningTableTmpDetails.setHeadId(updated.getId());
            diningTableTmpDetails.setTableId(request.getItemDetails().getTableId());
            diningTableTmpDetails.setItemCode(request.getItemDetails().getItemCode());
            diningTableTmpDetails.setBatchNo(request.getItemDetails().getBatchNo());
            diningTableTmpDetails.setSelectedPrice(request.getItemDetails().getSelectedPrice());
            diningTableTmpDetails.setQuantity(request.getItemDetails().getQuantity());
            diningTableTmpDetails.setIsKOT(request.getItemDetails().getIsKOT());
            diningTableTmpDetails.setIsBOT(request.getItemDetails().getIsBOT());
            diningTableTmpDetails.setInvStatus(request.getItemDetails().getInvStatus());
            diningTableTmpDetails.setIsDeleted(Deleted.NO);
            diningTableTmpDetailsRepository.save(diningTableTmpDetails);
        }
         
        return (convert(updated));
    }



    @Override
    public DiningTableTmpHeadResponse getById(Long id) {

        return diningTableTmpHeadRepository.findById(id).map(DiningTableTmpHeadServiceImpl::convert).orElse(null);
    }

    @Override
    public List<DiningTableTmpHeadResponse> getAll() {

        return diningTableTmpHeadRepository.findByIsDeleted(Deleted.NO)
                .stream().map(DiningTableTmpHeadServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        DiningTableTmpHead got = diningTableTmpHeadRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        diningTableTmpHeadRepository.save(got);

        return 1;
    }

    @Override
    public DiningTableTmpHeadResponse getByTableId(Long id) {

        DiningTableTmpHead tmpHead= diningTableTmpHeadRepository.findByTableIdAndInvStatusAndIsDeleted(id,0,Deleted.NO);

        if(tmpHead!=null){
            return convert2(tmpHead);
        }

        return null;
    }

    @Override
    @Transactional
    public DiningTableTmpHeadResponse closeBill(Long id) {

        DiningTableTmpHead head= diningTableTmpHeadRepository.findById(id).orElse(null);
        if(head!=null){
            head.setInvStatus(1);
            diningTableTmpHeadRepository.save(head);

            List<DiningTableTmpDetails> details= diningTableTmpDetailsRepository.findByHeadIdAndIsDeleted(id,Deleted.NO);
            for(DiningTableTmpDetails details1:details){
                details1.setInvStatus(1);
                diningTableTmpDetailsRepository.save(details1);
            }

            // for loop to set status 1


            // invoice entry

            return convert(head);
        }


        return null;
    }

    private  DiningTableTmpHeadResponse convert2(DiningTableTmpHead diningTableTmpHead) {

        DiningTableTmpHeadResponse typeResponse = new DiningTableTmpHeadResponse();
        typeResponse.setTableId(diningTableTmpHead.getTableId());
        typeResponse.setPaymentType(diningTableTmpHead.getPaymentType());
        typeResponse.setKotPrinted(diningTableTmpHead.getKotPrinted());
        typeResponse.setBotPrinted(diningTableTmpHead.getBotPrinted());
        typeResponse.setId(diningTableTmpHead.getId());
        typeResponse.setCreatedBy(diningTableTmpHead.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(diningTableTmpHead.getCreatedDateTime()));
        typeResponse.setModifiedBy(diningTableTmpHead.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(diningTableTmpHead.getModifiedDateTime()));
        typeResponse.setIsDeleted(diningTableTmpHead.getIsDeleted());
        typeResponse.setInvStatus(diningTableTmpHead.getInvStatus());
        typeResponse.setInvoiceNo(diningTableTmpHead.getInvoiceNo());


        List<DiningTableTmpDetailsResponse> responseList=new ArrayList<>();
        List<DiningTableTmpDetails> tmpDetails= diningTableTmpDetailsRepository.findByHeadIdAndIsDeleted(diningTableTmpHead.getId(),Deleted.NO);
        if(tmpDetails!=null){
            for(DiningTableTmpDetails details:tmpDetails){
                if(details.getInvStatus()==0){
                    responseList.add(convert(details));
                }
            }
        }
        typeResponse.setItemDetails(responseList);

        return typeResponse;
    }

    private DiningTableTmpDetailsResponse convert(DiningTableTmpDetails diningTableTmpDetails) {

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

        String temDsc="";
        ItemMaster itemMaster= itemMasterRepository.findById(diningTableTmpDetails.getItemCode()).orElse(null);
        if(itemMaster!=null){
            temDsc=itemMaster.getItemName();
        }
        typeResponse.setItemDesc(temDsc);

        return typeResponse;
    }

    private static DiningTableTmpHeadResponse convert(DiningTableTmpHead diningTableTmpHead) {

        DiningTableTmpHeadResponse typeResponse = new DiningTableTmpHeadResponse();
        typeResponse.setTableId(diningTableTmpHead.getTableId());
        typeResponse.setPaymentType(diningTableTmpHead.getPaymentType());
        typeResponse.setKotPrinted(diningTableTmpHead.getKotPrinted());
        typeResponse.setBotPrinted(diningTableTmpHead.getBotPrinted());
        typeResponse.setId(diningTableTmpHead.getId());
        typeResponse.setCreatedBy(diningTableTmpHead.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(diningTableTmpHead.getCreatedDateTime()));
        typeResponse.setModifiedBy(diningTableTmpHead.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(diningTableTmpHead.getModifiedDateTime()));
        typeResponse.setIsDeleted(diningTableTmpHead.getIsDeleted());
        typeResponse.setInvStatus(diningTableTmpHead.getInvStatus());
        typeResponse.setInvoiceNo(diningTableTmpHead.getInvoiceNo());

        return typeResponse;
    }
}