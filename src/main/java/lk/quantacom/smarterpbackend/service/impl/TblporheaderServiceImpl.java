package lk.quantacom.smarterpbackend.service.impl;

import com.google.gson.Gson;
import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.GetAllItemCodesByDescResponse;
import lk.quantacom.smarterpbackend.dto.response.TblporheaderResponse;
import lk.quantacom.smarterpbackend.dto.response.getPorAccessoryResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.TblporheaderService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.utils.JDBC;
import lk.quantacom.smarterpbackend.utils.Settings;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TblporheaderServiceImpl implements TblporheaderService {

    @Autowired
    private TblporheaderRepository tblporheaderRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private TblporothercostsRepository tblporothercostsRepository;

    @Autowired
    private TblpormainmaterialsRepository tblpormainmaterialsRepository;

    @Autowired
    private TblporaccessoriesRepository tblporaccessoriesRepository;

    @Autowired
    private TblporfitsRepository tblporfitsRepository;

    @Autowired
    private TblporsizesRepository tblporsizesRepository;

    @Autowired
    private BinCardRepository binCardRepository;

    @Autowired
    private ItemMasterRepository itemMasterRepository;

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

    @Override
    @Transactional
    public TblporheaderResponse save(TblporheaderRequest request) {

        Tblporheader tblporheader = new Tblporheader();
        tblporheader.setPorAdjustQty(request.getPorAdjustQty());
        tblporheader.setPorApproveRequest(request.getPorApproveRequest());
        tblporheader.setPorApproved(request.getPorApproved());
        tblporheader.setPorApprovedBranchId(request.getPorApprovedBranchId());
        tblporheader.setPorApprovedDate(request.getPorApprovedDate() == null ? null : ConvertUtils.convertStrToDate(request.getPorApprovedDate()));
        tblporheader.setPorApprovedUserId(request.getPorApprovedUserId());
        tblporheader.setPorConsumptionQty(request.getPorConsumptionQty());
        tblporheader.setPorConsumptionRate(request.getPorConsumptionRate());
        tblporheader.setPorConvertedToItem(request.getPorConvertedToItem());
        tblporheader.setPorCreatedBranchId(request.getPorCreatedBranchId());
        tblporheader.setPorCreatedDate(request.getPorCreatedDate() == null ? null : ConvertUtils.convertStrToDate(request.getPorCreatedDate()));
        tblporheader.setPorCreatedUserId(request.getPorCreatedUserId());
        tblporheader.setPorDamageQty(request.getPorDamageQty());
        tblporheader.setPorDescription(request.getPorDescription());
        tblporheader.setPorId(request.getPorId());
        tblporheader.setPorIsDamage(request.getPorIsDamage());
        tblporheader.setPorIsMaterialQty(request.getPorIsMaterialQty());
        tblporheader.setPorIsQuality(request.getPorIsQuality());
        tblporheader.setPorIsWidthLength(request.getPorIsWidthLength());
        tblporheader.setPorLongSleeve(request.getPorLongSleeve());
        tblporheader.setPorMaterialComment(request.getPorMaterialComment());
        tblporheader.setPorRemark(request.getPorRemark());
        tblporheader.setPorShortSleeve(request.getPorShortSleeve());
        tblporheader.setPorStyleDesc(request.getPorStyleDesc());
        tblporheader.setPorStyleId(request.getPorStyleId());
        tblporheader.setIsDeleted(Deleted.NO);
        Tblporheader save = tblporheaderRepository.save(tblporheader);

        return convert(save);
    }

    @Override
    @Transactional
    public TblporheaderResponse update(TblporheaderUpdateRequest request) {

        Tblporheader tblporheader = tblporheaderRepository.findById(request.getId()).orElse(null);
        if (tblporheader == null) {
            return null;
        }

        tblporheader.setId(request.getId());
        tblporheader.setPorAdjustQty(request.getPorAdjustQty());
        tblporheader.setPorApproveRequest(request.getPorApproveRequest());
        tblporheader.setPorApproved(request.getPorApproved());
        tblporheader.setPorApprovedBranchId(request.getPorApprovedBranchId());
        tblporheader.setPorApprovedDate(request.getPorApprovedDate() == null ? null : ConvertUtils.convertStrToDate(request.getPorApprovedDate()));
        tblporheader.setPorApprovedUserId(request.getPorApprovedUserId());
        tblporheader.setPorConsumptionQty(request.getPorConsumptionQty());
        tblporheader.setPorConsumptionRate(request.getPorConsumptionRate());
        tblporheader.setPorConvertedToItem(request.getPorConvertedToItem());
        tblporheader.setPorCreatedBranchId(request.getPorCreatedBranchId());
        tblporheader.setPorCreatedDate(request.getPorCreatedDate() == null ? null : ConvertUtils.convertStrToDate(request.getPorCreatedDate()));
        tblporheader.setPorCreatedUserId(request.getPorCreatedUserId());
        tblporheader.setPorDamageQty(request.getPorDamageQty());
        tblporheader.setPorDescription(request.getPorDescription());
        tblporheader.setPorId(request.getPorId());
        tblporheader.setPorIsDamage(request.getPorIsDamage());
        tblporheader.setPorIsMaterialQty(request.getPorIsMaterialQty());
        tblporheader.setPorIsQuality(request.getPorIsQuality());
        tblporheader.setPorIsWidthLength(request.getPorIsWidthLength());
        tblporheader.setPorLongSleeve(request.getPorLongSleeve());
        tblporheader.setPorMaterialComment(request.getPorMaterialComment());
        tblporheader.setPorRemark(request.getPorRemark());
        tblporheader.setPorShortSleeve(request.getPorShortSleeve());
        tblporheader.setPorStyleDesc(request.getPorStyleDesc());
        tblporheader.setPorStyleId(request.getPorStyleId());
        Tblporheader updated = tblporheaderRepository.save(tblporheader);

        return (convert(updated));
    }

    @Override
    public TblporheaderResponse getById(Long id) {
        return tblporheaderRepository.findById(id).map(TblporheaderServiceImpl::convert).orElse(null);
    }

    @Override
    public List<TblporheaderResponse> getAll() {
        return tblporheaderRepository.findByIsDeleted(Deleted.NO)
                .stream().map(TblporheaderServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<TblporheaderResponse> getAllAvailable() {
        return tblporheaderRepository.findByPorApprovedAndIsDeleted(false, Deleted.NO)
                .stream().map(TblporheaderServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<TblporheaderResponse> getAllApproved() {
        return tblporheaderRepository.findByPorApprovedAndIsDeleted(true, Deleted.NO)
                .stream().map(TblporheaderServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        Tblporheader got = tblporheaderRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        tblporheaderRepository.save(got);

        return 1;
    }

    @Override
    public List<TblporheaderResponse> getByPorId(String porId, Boolean approved) {
        return tblporheaderRepository.findByPorIdAndPorApproved(porId, approved)
                .stream().map(TblporheaderServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TblporheaderResponse saveToPorTables(TblporheaderAllListRequest request) {

//        Gson gson=new Gson();
//        System.out.println(gson.toJson(request));

        Tblporheader tblporheader = new Tblporheader();
        tblporheader.setPorAdjustQty(request.getPorAdjustQty());
        tblporheader.setPorApproveRequest(request.getPorApproveRequest());
        tblporheader.setPorApproved(request.getPorApproved());
        tblporheader.setPorApprovedBranchId(request.getPorApprovedBranchId());
        tblporheader.setPorApprovedDate(request.getPorApprovedDate() == null ? null : ConvertUtils.convertStrToDate(request.getPorApprovedDate()));
        tblporheader.setPorApprovedUserId(request.getPorApprovedUserId());
        tblporheader.setPorConsumptionQty(request.getPorConsumptionQty());
        tblporheader.setPorConsumptionRate(request.getPorConsumptionRate());
        tblporheader.setPorConvertedToItem(request.getPorConvertedToItem());
        tblporheader.setPorCreatedBranchId(request.getPorCreatedBranchId());
        tblporheader.setPorCreatedDate(request.getPorCreatedDate() == null ? null : ConvertUtils.convertStrToDate(request.getPorCreatedDate()));
        tblporheader.setPorCreatedUserId(request.getPorCreatedUserId());
        tblporheader.setPorDamageQty(request.getPorDamageQty());
        tblporheader.setPorDescription(request.getPorDescription());
        tblporheader.setPorId(request.getPorId());
        tblporheader.setPorIsDamage(request.getPorIsDamage());
        tblporheader.setPorIsMaterialQty(request.getPorIsMaterialQty());
        tblporheader.setPorIsQuality(request.getPorIsQuality());
        tblporheader.setPorIsWidthLength(request.getPorIsWidthLength());
        tblporheader.setPorLongSleeve(request.getPorLongSleeve());
        tblporheader.setPorMaterialComment(request.getPorMaterialComment());
        tblporheader.setPorRemark(request.getPorRemark());
        tblporheader.setPorShortSleeve(request.getPorShortSleeve());
        tblporheader.setPorStyleDesc(request.getPorStyleDesc());
        tblporheader.setPorStyleId(request.getPorStyleId());
        tblporheader.setIsDeleted(Deleted.NO);
        Tblporheader save = tblporheaderRepository.save(tblporheader);

        for (TblpormainmaterialsRequest tblpormainmaterialsRequest : request.getTblpormainmaterialsRequests()) {
            Tblpormainmaterials tblpormainmaterials = new Tblpormainmaterials();
            tblpormainmaterials.setPorId(request.getPorId());
            tblpormainmaterials.setPorMainColorId(tblpormainmaterialsRequest.getPorMainColorId());
            tblpormainmaterials.setPorMainItemBatchId(tblpormainmaterialsRequest.getPorMainItemBatchId());
            tblpormainmaterials.setPorMainItemBranchId(tblpormainmaterialsRequest.getPorMainItemBranchId());
            tblpormainmaterials.setPorMainSizeId(tblpormainmaterialsRequest.getPorMainSizeId());
            tblpormainmaterials.setPorMainFitId(tblpormainmaterialsRequest.getPorMainFitId());
            tblpormainmaterials.setPorMainItemCode(tblpormainmaterialsRequest.getPorMainItemCode());
            tblpormainmaterials.setPorMainItemDesc(tblpormainmaterialsRequest.getPorMainItemDesc());
            tblpormainmaterials.setPorMainItemQty(tblpormainmaterialsRequest.getPorMainItemQty());
            tblpormainmaterials.setIsDeleted(Deleted.NO);
            tblpormainmaterialsRepository.save(tblpormainmaterials);
        }

        for (TblporaccessoriesRequest tblporaccessoriesRequest : request.getTblporaccessoriesRequests()) {
            Tblporaccessories tblporaccessories = new Tblporaccessories();
            tblporaccessories.setPorAccItemBatchId(tblporaccessoriesRequest.getPorAccItemBatchId());
            tblporaccessories.setPorAccItemBranchId(tblporaccessoriesRequest.getPorAccItemBranchId());
            tblporaccessories.setPorAccItemCode(tblporaccessoriesRequest.getPorAccItemCode());
            tblporaccessories.setPorAccFitId(tblporaccessoriesRequest.getPorAccFitId());
            tblporaccessories.setPorAccColorId(tblporaccessoriesRequest.getPorAccColorId());
            tblporaccessories.setPorAccSizeId(tblporaccessoriesRequest.getPorAccSizeId());
            tblporaccessories.setPorAccItemConsumptionQty(tblporaccessoriesRequest.getPorAccItemConsumptionQty());
            tblporaccessories.setPorAccItemDesc(tblporaccessoriesRequest.getPorAccItemDesc());
            tblporaccessories.setPorAccItemQty(tblporaccessoriesRequest.getPorAccItemQty());
            tblporaccessories.setPorId(request.getPorId());
            tblporaccessories.setIsDeleted(Deleted.NO);
            tblporaccessoriesRepository.save(tblporaccessories);
        }

        for (TblporsizesRequest tblporsizesRequest : request.getTblporsizesRequests()) {
            Tblporsizes tblporsizes = new Tblporsizes();
            tblporsizes.setPorId(request.getPorId());
            tblporsizes.setPorQty(tblporsizesRequest.getPorQty());
            tblporsizes.setPorRatio(tblporsizesRequest.getPorRatio());
            tblporsizes.setPorSizeDesc(tblporsizesRequest.getPorSizeDesc());
            tblporsizes.setPorSizeId(tblporsizesRequest.getPorSizeId());
            tblporsizes.setIsDeleted(Deleted.NO);
            tblporsizesRepository.save(tblporsizes);
        }

        for (TblporfitsRequest tblporfitsRequest : request.getTblporfitsRequests()) {
            Tblporfits tblporfits = new Tblporfits();
            tblporfits.setPorFitDesc(tblporfitsRequest.getPorFitDesc());
            tblporfits.setPorFitId(tblporfitsRequest.getPorFitId());
            tblporfits.setPorId(request.getPorId());
            tblporfits.setIsDeleted(Deleted.NO);
            tblporfitsRepository.save(tblporfits);
        }

        return convert(save);
    }

    @Override
    @Transactional
    public Integer reduceFromStock(TblporheaderReduceFromStockRequest updateRequest) {

        Tblporheader tblporheader = tblporheaderRepository.findByPorId(updateRequest.getPorId());
        if (tblporheader == null) {
            return null;
        }
        tblporheader.setPorApproved(true);
        tblporheader.setPorApproveRequest(true);
        tblporheaderRepository.save(tblporheader);

        System.out.println(new Gson().toJson(updateRequest));


        for (TblpormainmaterialsReduceRequest request : updateRequest.getTblpormainmaterialsRequests()) {

            Stock stock = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(request.getPorMainItemBatchId(),
                    request.getPorMainItemCode(), request.getPorMainItemBranchId(),
                    request.getColourId(), request.getSizeId(), request.getFitId());
            if (stock != null) {
                stock.setStoresQty(stock.getStoresQty() - (request.getPorItemQty()) / 1.094);
                stock.setTotalQty(stock.getTotalQty()- (request.getPorItemQty()) / 1.094);
                Stock saved = stockRepository.save(stock);

                System.out.println(request.getPorMainItemCode());

                // Bin Card
                BinCard binCard = new BinCard();
                binCard.setItem(saved.getStockPK().getItem());
                binCard.setBinCardDate(new Date());
                binCard.setDocNo(updateRequest.getPorId() + "");
                binCard.setDocType("PRODUCTION");
                binCard.setRecQty(0.0);
                binCard.setIsueQty(request.getPorItemQty() / 1.094);
                binCard.setBalanceQty(saved.getStoresQty());
                binCard.setBatchNo(saved.getStockPK().getBatchNo());
                binCard.setBranch(saved.getStockPK().getBranch());
                binCard.setIsDeleted(Deleted.NO);
                binCardRepository.save(binCard);
            }

        }

        for (TblporaccessoriesReduceRequest request : updateRequest.getTblporaccessoriesRequests()) {

            Stock stock = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(request.getPorAccItemBatchId(), request.getPorAccItemCode(), request.getPorAccItemBranchId(),
                    request.getColourId(), request.getSizeId(), request.getFitId());
            if (stock != null) {
                stock.setStoresQty(stock.getStoresQty() - request.getPorItemQty());
                stock.setTotalQty(stock.getTotalQty()-  request.getPorItemQty());
                Stock saved = stockRepository.save(stock);

                System.out.println(request.getPorAccItemCode());

                // Bin Card
                BinCard binCard = new BinCard();
                binCard.setItem(saved.getStockPK().getItem());
                binCard.setBinCardDate(new Date());
                binCard.setDocNo(updateRequest.getPorId() + "");
                binCard.setDocType("PRODUCTION");
                binCard.setRecQty(0.0);
                binCard.setIsueQty(request.getPorItemQty());
                binCard.setBalanceQty(saved.getStoresQty());
                binCard.setBatchNo(saved.getStockPK().getBatchNo());
                binCard.setBranch(saved.getStockPK().getBranch());
                binCard.setIsDeleted(Deleted.NO);
                binCardRepository.save(binCard);
            }
        }

        return 1;
    }

    @Override
    @Transactional
    public TblporheaderResponse requestApprovalForApproved(TblporheaderRequestApprovalRequest requestApprovalRequest) {

        Tblporheader tblporheader = tblporheaderRepository.findByPorId(requestApprovalRequest.getPorId());

        tblporheader.setPorConsumptionRate(requestApprovalRequest.getPorConsumptionRate());
        tblporheader.setPorConsumptionQty(requestApprovalRequest.getPorConsumptionQty());
        tblporheader.setPorApproveRequest(requestApprovalRequest.getPorApproveRequest());
        tblporheader.setPorApproved(requestApprovalRequest.getPorApproved());
        tblporheader.setPorIsMaterialQty(requestApprovalRequest.getPorIsMaterialQty());
        tblporheader.setPorIsQuality(requestApprovalRequest.getPorIsQuality());
        tblporheader.setPorIsDamage(requestApprovalRequest.getPorIsDamage());
        tblporheader.setPorIsWidthLength(requestApprovalRequest.getPorIsWidthLength());
        tblporheader.setPorMaterialComment(requestApprovalRequest.getPorMaterialComment());
        Tblporheader save = tblporheaderRepository.save(tblporheader);

        for (TblporaccessoriesApprovalRequest tblporaccessoriesRequest : requestApprovalRequest.getTblporaccessoriesRequests()) {
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
            if (tblporaccessories != null) {
                for (Tblporaccessories tblporaccessories1 : tblporaccessories) {
                    tblporaccessories1.setPorAccItemConsumptionQty(requestApprovalRequest.getPorConsumptionQty());
                    tblporaccessoriesRepository.save(tblporaccessories1);
                }

            }

        }
        for (TblporsizesApprovalRequest tblporsizes : requestApprovalRequest.getTblporsizesRequests()) {
            Tblporsizes tblporsizes1 = tblporsizesRepository.findByPorIdAndPorSizeId(tblporsizes.getPorId(), tblporsizes.getPorSizeId());
            if (tblporsizes1 != null) {
                tblporsizes1.setPorQty(tblporsizes.getPorQty());
                tblporsizesRepository.save(tblporsizes1);
            }
        }
        for (TblporothercostsRequest request : requestApprovalRequest.getTblporothercostsRequests()) {
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

        return convert(save);
    }

    @Override
    @Transactional
    public Integer confirmApproval(String porId) {
        Tblporheader tblporheader = tblporheaderRepository.findByPorId(porId);
        if (tblporheader == null) {
            return 0;
        }
        tblporheader.setPorApproveRequest(true);
        tblporheader.setPorApproved(true);
        tblporheaderRepository.save(tblporheader);
        return 1;
    }

    @Override
    public List<TblporheaderResponse> getByPorIdAndPorApprovedAndPorApproveRequest(String porId) {
        return tblporheaderRepository.findByPorIdAndPorApprovedAndPorApproveRequest(porId, true, true)
                .stream().map(TblporheaderServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<getPorAccessoryResponse> getByStockAndItemPorAccessory(String batchNo, String itemCode, Long branchId,
                                                                       Long sizeId, Long fitId, Long colorId) {

        List<getPorAccessoryResponse> responses =
                tblporheaderRepository.getByStockAndItemPorAccessory(batchNo.equals("0") ? "" : batchNo, itemCode, branchId,
                        sizeId, fitId, colorId);
        return responses;
    }

    @Override
    public Integer updateStatus(String porId) {
        Tblporheader tblporheader = tblporheaderRepository.findByPorId(porId);
        if (tblporheader == null) {
            return 0;
        }
        tblporheader.setPorConvertedToItem(true);
        tblporheaderRepository.save(tblporheader);
        return 1;
    }

    @Override
    @Transactional
    public Integer reduceFromStockByConvertToGrn(TblporheaderReduceFromStockRequest updateRequest) {

        System.out.println("reduce stock");

        Tblporheader por = tblporheaderRepository.findByPorId(updateRequest.getPorId());
        if (por != null) {
            por.setPorApproved(true);
            por.setPorApproveRequest(true);
            tblporheaderRepository.save(por);
        }

        System.out.println(new Gson().toJson(updateRequest));

        for (TblpormainmaterialsReduceRequest request : updateRequest.getTblpormainmaterialsRequests()) {

            Stock stock = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(request.getPorMainItemBatchId(), request.getPorMainItemCode(), request.getPorMainItemBranchId(),
                    request.getColourId(), request.getSizeId(), request.getFitId());
            stock.setStoresQty(stock.getStoresQty() - (request.getPorMainItemQty()));
            stockRepository.save(stock);
        }

        for (TblporaccessoriesReduceRequest request : updateRequest.getTblporaccessoriesRequests()) {

            Stock stock = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(request.getPorAccItemBatchId(), request.getPorAccItemCode(), request.getPorAccItemBranchId(),
                    request.getColourId(), request.getSizeId(), request.getFitId());
            stock.setStoresQty(stock.getStoresQty() - request.getPorAccItemQty());
            stockRepository.save(stock);
        }

        return 1;
    }

    @Override
    public File printPOR(String porID) {

        File pdf = null;
        try {
            Tblporheader tblporheader = tblporheaderRepository.findByPorId(porID);
            if (tblporheader == null) {
                return null;
            }

            Connection co = JDBC.con();
            String reportSource = Settings.readSettings("REPORT_PATH") + "Reports/Production/Libera_Cut_Sheet.jrxml";
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("POR_ID", porID + "");
            params.put("COMPANY_NAME", Settings.readSettings("COMPANY_NAME"));
            params.put("SUBREPORT_DIR", Settings.readSettings("REPORT_PATH") + "Reports/Production/");
            params.put("COMPANY_ADDRESS_ONE", Settings.readSettings("COMPANY_ADDRESS_ONE"));
            params.put("COMPANY_ADDRESS_TWO", Settings.readSettings("COMPANY_ADDRESS_TWO"));
            params.put("MOBILE_NO", Settings.readSettings("MOBILE_NO"));
            params.put("EMAIL", Settings.readSettings("EMAIL"));
            params.put("LOGO_PATH", Settings.readSettings("LOGO_PATH"));

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, co);
            File perentFol = new File(System.getProperty("user.home") + "/smart_erp_reports");
            if (!perentFol.exists()) {
                perentFol.mkdirs();
            }
            pdf = new File(perentFol, System.currentTimeMillis() + ".pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(pdf));
            co.close();
            //  JasperViewer.viewReport(jasperPrint,false);
//            JRViewer v = new JRViewer(jasperPrint);
//            v.setZoomRatio((float) 0.60);
//            jTabbedPane1.add(v);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pdf;

    }


}

