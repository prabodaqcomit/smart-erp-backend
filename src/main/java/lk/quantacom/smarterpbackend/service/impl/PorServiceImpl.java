package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.PorheaderRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporaccessoriesRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporothercostsRequest;
import lk.quantacom.smarterpbackend.dto.request.TblporsizesRequest;
import lk.quantacom.smarterpbackend.dto.response.IssueNoteResponse;
import lk.quantacom.smarterpbackend.dto.response.TblporheaderResponse;
import lk.quantacom.smarterpbackend.entity.Tblporaccessories;
import lk.quantacom.smarterpbackend.entity.Tblporheader;
import lk.quantacom.smarterpbackend.entity.Tblporothercosts;
import lk.quantacom.smarterpbackend.entity.Tblporsizes;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.TblporaccessoriesRepository;
import lk.quantacom.smarterpbackend.repository.TblporheaderRepository;
import lk.quantacom.smarterpbackend.repository.TblporothercostsRepository;
import lk.quantacom.smarterpbackend.repository.TblporsizesRepository;
import lk.quantacom.smarterpbackend.service.PorServices;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PorServiceImpl implements PorServices {

    @Autowired
    private TblporheaderRepository tblporheaderRepository;

    @Autowired
    private TblporaccessoriesRepository tblporaccessoriesRepository;

    @Autowired
    private TblporsizesRepository tblporsizesRepository;

    @Autowired
    private TblporothercostsRepository tblporothercostsRepository;


    @Override
    public TblporheaderResponse save(PorheaderRequest request) {

        Tblporheader tblporheader = tblporheaderRepository.findByPorId(request.getTblporheaderRequest().getPorId());
        tblporheader.setPorConsumptionRate(request.getTblporheaderRequest().getPorConsumptionRate());
        tblporheader.setPorConsumptionQty(request.getTblporheaderRequest().getPorConsumptionQty());
        tblporheader.setPorApproveRequest(request.getTblporheaderRequest().getPorApproveRequest());
        tblporheader.setPorIsMaterialQty(request.getTblporheaderRequest().getPorIsMaterialQty());
        tblporheader.setPorApproved(request.getTblporheaderRequest().getPorApproved());
        tblporheader.setPorIsMaterialQty(request.getTblporheaderRequest().getPorIsMaterialQty());
        tblporheader.setPorIsQuality(request.getTblporheaderRequest().getPorIsQuality());
        tblporheader.setPorIsDamage(request.getTblporheaderRequest().getPorIsDamage());
        tblporheader.setPorIsWidthLength(request.getTblporheaderRequest().getPorIsWidthLength());
        tblporheader.setPorMaterialComment(request.getTblporheaderRequest().getPorMaterialComment());
        Tblporheader update =tblporheaderRepository.save(tblporheader);

        for(TblporaccessoriesRequest tblporaccessoriesRequest : request.getTblporaccessoriesRequest()){
//            Tblporaccessories tblporaccessories=tblporaccessoriesRepository.findByPorIdAndPorAccItemCodeAndPorAccItemBatchIdAndPorAccItemBranchId(tblporaccessoriesRequest.getPorId(),tblporaccessoriesRequest.getPorAccItemCode(),tblporaccessoriesRequest.getPorAccItemBatchId(),tblporaccessoriesRequest.getPorAccItemBranchId());
//            tblporaccessories.setPorAccItemConsumptionQty(tblporaccessoriesRequest.getPorAccItemConsumptionQty());
//            tblporaccessoriesRepository.save(tblporaccessories);

            List<Tblporaccessories> tblporaccessories = tblporaccessoriesRepository.
                    findByPorIdAndPorAccItemCodeAndPorAccItemBatchIdAndPorAccItemBranchIdAndPorAccColorIdAndPorAccFitIdAndPorAccSizeId
                            (tblporaccessoriesRequest.getPorId(),
                                    tblporaccessoriesRequest.getPorAccItemCode(),
                                    tblporaccessoriesRequest.getPorAccItemBatchId(),
                                    tblporaccessoriesRequest.getPorAccItemBranchId(),
                                    tblporaccessoriesRequest.getPorAccColorId(),
                                    tblporaccessoriesRequest.getPorAccFitId(),
                                    tblporaccessoriesRequest.getPorAccSizeId()
                            );
            if(tblporaccessories!=null){
                for(Tblporaccessories tblporaccessories1:tblporaccessories){
                    tblporaccessories1.setPorAccItemConsumptionQty(tblporaccessoriesRequest.getPorAccItemConsumptionQty());
                    tblporaccessoriesRepository.save(tblporaccessories1);
                }

            }
        }
        for(TblporsizesRequest tblporsizesRequest: request.getTblporsizesRequest()){
            Tblporsizes tblporsizes=tblporsizesRepository.findByPorIdAndPorSizeId(tblporsizesRequest.getPorId(),tblporsizesRequest.getPorSizeId());
            tblporsizes.setPorQty(tblporsizesRequest.getPorQty());
        }
        for(TblporothercostsRequest tblporothercostsRequest: request.getTblporothercostsRequest()){
            Tblporothercosts tblporothercosts = new Tblporothercosts();
            tblporothercosts.setDescription(tblporothercostsRequest.getDescription());
            tblporothercosts.setLineNo(tblporothercostsRequest.getLineNo());
            tblporothercosts.setPerUnitCost(tblporothercostsRequest.getPerUnitCost());
            tblporothercosts.setPorId(tblporothercostsRequest.getPorId());
            tblporothercosts.setTotalCost(tblporothercostsRequest.getTotalCost());
            tblporothercosts.setUnitQty(tblporothercostsRequest.getUnitQty());
            tblporothercosts.setIsDeleted(Deleted.NO);
            tblporothercostsRepository.save(tblporothercosts);
        }


        return convert(update);
    }

    private static TblporheaderResponse convert(Tblporheader tblporheader) {

        TblporheaderResponse typeResponse = new TblporheaderResponse();
        typeResponse.setPorAdjustQty(tblporheader.getPorAdjustQty());
        typeResponse.setPorApproveRequest(tblporheader.getPorApproveRequest());
        typeResponse.setPorApproved(tblporheader.getPorApproved());
        typeResponse.setPorApprovedBranchId(tblporheader.getPorApprovedBranchId());
        typeResponse.setPorApprovedDate(tblporheader.getPorApprovedDate());
        typeResponse.setPorApprovedUserId(tblporheader.getPorApprovedUserId());
        typeResponse.setPorConsumptionQty(tblporheader.getPorConsumptionQty());
        typeResponse.setPorConsumptionRate(tblporheader.getPorConsumptionRate());
        typeResponse.setPorConvertedToItem(tblporheader.getPorConvertedToItem());
        typeResponse.setPorCreatedBranchId(tblporheader.getPorCreatedBranchId());
        typeResponse.setPorCreatedDate(tblporheader.getPorCreatedDate());
        typeResponse.setPorCreatedUserId(tblporheader.getPorCreatedUserId());
        typeResponse.setPorDamageQty(tblporheader.getPorDamageQty());
        typeResponse.setPorDescription(tblporheader.getPorDescription());
        typeResponse.setPorId(tblporheader.getPorId());
        typeResponse.setPorIsDamage(tblporheader.getPorIsDamage());
        typeResponse.setPorIsMaterialQty(tblporheader.getPorIsMaterialQty());
        typeResponse.setPorIsQuality(tblporheader.getPorIsQuality());
        typeResponse.setPorIsWidthLength(tblporheader.getPorIsWidthLength());
        typeResponse.setPorLongSleeve(tblporheader.getPorLongSleeve());
        typeResponse.setPorMaterialComment(tblporheader.getPorMaterialComment());
        typeResponse.setPorRemark(tblporheader.getPorRemark());
        typeResponse.setPorShortSleeve(tblporheader.getPorShortSleeve());
        typeResponse.setPorStyleDesc(tblporheader.getPorStyleDesc());
        typeResponse.setPorStyleId(tblporheader.getPorStyleId());
        typeResponse.setId(tblporheader.getId());
        typeResponse.setCreatedBy(tblporheader.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(tblporheader.getCreatedDateTime()));
        typeResponse.setModifiedBy(tblporheader.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(tblporheader.getModifiedDateTime()));
        typeResponse.setIsDeleted(tblporheader.getIsDeleted());

        return typeResponse;
    }
}