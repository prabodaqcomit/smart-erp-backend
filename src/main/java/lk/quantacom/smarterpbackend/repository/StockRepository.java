package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.dto.response.LocationStockDetailsResponse;
import lk.quantacom.smarterpbackend.dto.response.getAllBincardStockBycodeResponse;
import lk.quantacom.smarterpbackend.dto.response.getAllStockByItemResponse;
import lk.quantacom.smarterpbackend.dto.response.stockLocationResponse;
import lk.quantacom.smarterpbackend.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.*;

import java.util.Date;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock,StockPK> , JpaSpecificationExecutor {

//    Stock findByBatchNoAndItemAndBranchAndColorAndSizeAndFit(String batchNo, ItemMaster item, BranchNetwork branch, Color color, Size size,Fit fit);
//
//    Stock findByBatchNoAndItemAndBranch(String batchNo, ItemMaster item, BranchNetwork branch);
//
//    List<Stock> findByBranchAndIsDeleted(BranchNetwork branch,Deleted deleted);
//
//    List<Stock> findByIsDeleted(Deleted deleted);
//
//    List<Stock> findByBatchNoAndItemAndBranchAndIsDeleted(String batchNo, ItemMaster item, BranchNetwork branch,Deleted deleted);
//
//    List<Stock> findByBatchNoAndItemAndBranchAndColorAndSizeAndFitAndIsDeleted(String batchNo, ItemMaster item, BranchNetwork branch, Color color, Size size,Fit fit,Deleted deleted);

//----------------------------------------------------------

    @Query(value = "select * from stock where batch_no=?1  and item_code=?2 and branch_id=?3 and color_id=?4 and size_id=?5 and fit_id=?6 and is_deleted=0",nativeQuery = true)
    Stock getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(String batchNo, String itemCode, Long branchId, Long colorId, Long sizeId,Long fitId);

    @Query(value = "select * from stock where item_code=?1 and branch_id=?2 and color_id=?3 and size_id=?4 and fit_id=?5 and is_deleted=0",nativeQuery = true)
    Stock getByItemAndBranchAndColorAndSizeAndFit(String itemCode, Long branchId, Long colorId, Long sizeId,Long fitId);

    @Query(value = "select * from stock where batch_no=?1  and item_code=?2 and branch_id=?3 and is_deleted=0",nativeQuery = true)
    List<Stock> getByBatchNoAndItemAndBranch(String batchNo, String itemCode,  Long branchId);

    @Query(value = "select * from stock where  branch_id=?1 and is_deleted=0",nativeQuery = true)
    List<Stock> getByBranchAndIsDeleted(Long branchId);

    @Query(value = "select * from stock where  batch_no=?1  and item_code=?2 and branch_id=?3 and is_deleted=0",nativeQuery = true)
    List<Stock> getByBatchNoAndItemAndBranchAndIsDeleted(String batchNo, String itemCode,  Long branchId);

    @Query(value = "select * from stock where item_code=?1 and branch_id=?2 and is_deleted=0",nativeQuery = true)
    List<Stock> getByItemAndBranchAndIsDeleted(String itemCode,  Long branchId);

    @Query(value = "select * from stock where branch_id=?1 and item_code=?2 and is_deleted=0",nativeQuery = true)
    List<Stock> getByBranchAndItemCode(Long branchId, String itemCode);

    @Query(value = "select sum(stores_qty) from stock where branch_id=?1 and item_code=?2 and is_deleted=0",nativeQuery = true)
    Double getStoresSumBySize(Long branchId, String itemCode);

    @Query(value = " select s.branch_id,b.branch_name from stock s inner join branch_network b on b.id=s.branch_id group by branch_id order by branch_id ",nativeQuery = true)
    List<stockLocationResponse> getByStockLocation();

    @Query(value = " select * from stock where batch_no=?1 and item_code=?2 and size_id=?3 and fit_id=?4 and color_id=?5 ",nativeQuery = true)
    List<Stock> getLocationStock(String batchNo,String itemCode,Long size,Long fit,Long color);

    @Query(value = " select sum(stores_qty) from stock where batch_no=?1 and item_code=?2 and size_id=?3 and fit_id=?4 and color_id=?5 ",nativeQuery = true)
    Double getStoresTotByLoc(String batchNo,String itemCode,Long size,Long fit,Long color);

    @Query(value = "select s.batch_no,s.color_id,s.fit_id,s.size_id,s.item_code,s.stores_qty,s.branch_id,(select sum(a.stores_qty) from stock a where s.batch_no=a.batch_no and  " +
            " s.color_id=a.color_id and s.fit_id=a.fit_id and s.size_id=a.size_id and s.item_code=a.item_code) as qty " +
            " from stock s where s.item_code=?1 and is_deleted=0 group by s.batch_no",nativeQuery = true)
    List<getAllBincardStockBycodeResponse> getAllBincardStockBycode(String itemCode);

    @Query(value = "select s.item_code,i.item_name from stock s inner join item_master i on i.item_code=s.item_code where s.is_deleted=0 group by s.item_code",nativeQuery = true)
    List<getAllStockByItemResponse> getItemCode();

    @Query(value = "select * from  stock where item_code=?1 group by item_code",nativeQuery = true)
    Stock getByItemCode(String itemCode);

}