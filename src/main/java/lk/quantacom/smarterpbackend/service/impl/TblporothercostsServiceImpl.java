package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.TblporheaderResponse;
import lk.quantacom.smarterpbackend.dto.response.TblporothercostsResponse;
import lk.quantacom.smarterpbackend.entity.Tblporaccessories;
import lk.quantacom.smarterpbackend.entity.Tblporheader;
import lk.quantacom.smarterpbackend.entity.Tblporothercosts;
import lk.quantacom.smarterpbackend.entity.Tblporsizes;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.TblporothercostsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TblporothercostsServiceImpl implements TblporothercostsService {

    @Autowired
    private TblporothercostsRepository tblporothercostsRepository;

    @Autowired
    private TblporheaderRepository tblporheaderRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private TblpormainmaterialsRepository tblpormainmaterialsRepository;

    @Autowired
    private TblporaccessoriesRepository tblporaccessoriesRepository;

    @Autowired
    private TblporfitsRepository tblporfitsRepository;

    @Autowired
    private TblporsizesRepository tblporsizesRepository;

    private static TblporothercostsResponse convert(Tblporothercosts tblporothercosts) {

        TblporothercostsResponse typeResponse = new TblporothercostsResponse();
        typeResponse.setDescription(tblporothercosts.getDescription());
        typeResponse.setLineNo(tblporothercosts.getLineNo());
        typeResponse.setPerUnitCost(tblporothercosts.getPerUnitCost());
        typeResponse.setPorId(tblporothercosts.getPorId());
        typeResponse.setTotalCost(tblporothercosts.getTotalCost());
        typeResponse.setUnitQty(tblporothercosts.getUnitQty());
        typeResponse.setPorId(tblporothercosts.getPorId());
        typeResponse.setCreatedBy(tblporothercosts.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(tblporothercosts.getCreatedDateTime()));
        typeResponse.setModifiedBy(tblporothercosts.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(tblporothercosts.getModifiedDateTime()));
        typeResponse.setIsDeleted(tblporothercosts.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public TblporothercostsResponse save(TblporothercostsRequest request) {

        Tblporothercosts tblporothercosts = new Tblporothercosts();
        tblporothercosts.setDescription(request.getDescription());
        tblporothercosts.setLineNo(request.getLineNo());
        tblporothercosts.setPerUnitCost(request.getPerUnitCost());
        tblporothercosts.setPorId(request.getPorId());
        tblporothercosts.setTotalCost(request.getTotalCost());
        tblporothercosts.setUnitQty(request.getUnitQty());
        tblporothercosts.setIsDeleted(Deleted.NO);
        Tblporothercosts save = tblporothercostsRepository.save(tblporothercosts);

        return convert(save);
    }

    @Override
    @Transactional
    public TblporothercostsResponse update(TblporothercostsUpdateRequest request) {

        Tblporothercosts tblporothercosts = tblporothercostsRepository.findById(request.getId().intValue()).orElse(null);
        if (tblporothercosts == null) {
            return null;
        }

        tblporothercosts.setPorId(request.getPorId());
        tblporothercosts.setDescription(request.getDescription());
        tblporothercosts.setLineNo(request.getLineNo());
        tblporothercosts.setPerUnitCost(request.getPerUnitCost());
        tblporothercosts.setPorId(request.getPorId());
        tblporothercosts.setTotalCost(request.getTotalCost());
        tblporothercosts.setUnitQty(request.getUnitQty());
        Tblporothercosts updated = tblporothercostsRepository.save(tblporothercosts);

        return (convert(updated));
    }

    @Override
    public TblporothercostsResponse getById(Integer id) {

        return tblporothercostsRepository.findById(id).map(TblporothercostsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<TblporothercostsResponse> getAll() {

        return tblporothercostsRepository.findByIsDeleted(Deleted.NO)
                .stream().map(TblporothercostsServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Integer id) {

        Tblporothercosts got = tblporothercostsRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        tblporothercostsRepository.save(got);

        return 1;
    }

    @Override
    public TblporheaderResponse requestApprovalForApproval(TblporheaderRequestApprovalRequest requestApprovalRequest) {

        Tblporheader tblporheader= tblporheaderRepository.findByPorId(requestApprovalRequest.getPorId());

        tblporheader.setPorConsumptionRate(requestApprovalRequest.getPorConsumptionRate());
        tblporheader.setPorConsumptionQty(requestApprovalRequest.getPorConsumptionQty());
        tblporheader.setPorApproveRequest(true);
        tblporheader.setPorApproved(requestApprovalRequest.getPorApproved());
        tblporheader.setPorIsMaterialQty(requestApprovalRequest.getPorIsMaterialQty());
        tblporheader.setPorIsQuality(requestApprovalRequest.getPorIsQuality());
        tblporheader.setPorIsDamage(requestApprovalRequest.getPorIsDamage());
        tblporheader.setPorIsWidthLength(requestApprovalRequest.getPorIsWidthLength());
        tblporheader.setPorMaterialComment(requestApprovalRequest.getPorMaterialComment());
        Tblporheader save=tblporheaderRepository.save(tblporheader);

        for(TblporaccessoriesApprovalRequest tblporaccessoriesRequest: requestApprovalRequest.getTblporaccessoriesRequests()){
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
                    tblporaccessories1.setPorAccItemConsumptionQty(requestApprovalRequest.getPorConsumptionQty());
                    tblporaccessoriesRepository.save(tblporaccessories1);
                }

            }
        }
        for(TblporsizesApprovalRequest tblporsizes:requestApprovalRequest.getTblporsizesRequests()){
            Tblporsizes tblporsizes1 = tblporsizesRepository.findByPorIdAndPorSizeId(tblporsizes.getPorId(),tblporsizes.getPorSizeId());
            tblporsizes1.setPorQty(tblporsizes.getPorQty());
            tblporsizesRepository.save(tblporsizes1);
        }
        for(TblporothercostsRequest request:requestApprovalRequest.getTblporothercostsRequests()){
            Tblporothercosts tblporothercosts = new Tblporothercosts();
            tblporothercosts.setDescription(request.getDescription());
            tblporothercosts.setLineNo(request.getLineNo());
            tblporothercosts.setPerUnitCost(request.getPerUnitCost());
            tblporothercosts.setPorId(request.getPorId());
            tblporothercosts.setTotalCost(request.getTotalCost());
            tblporothercosts.setUnitQty(request.getUnitQty());
            tblporothercosts.setIsDeleted(Deleted.NO);
            tblporothercostsRepository.save(tblporothercosts);


        }

        return convert1(save);
    }

    @Override
    public List<TblporothercostsResponse> getByPorId(String porId) {
        return tblporothercostsRepository.findByPorId(porId)
                .stream().map(TblporothercostsServiceImpl::convert).collect(Collectors.toList());
    }

    private static TblporheaderResponse convert1(Tblporheader tblporheader) {

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