package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.dto.response.getByStockAndTblPorAccessoriesResponse;
import lk.quantacom.smarterpbackend.entity.Tblporaccessories;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface TblporaccessoriesRepository extends JpaRepository<Tblporaccessories,Long> , JpaSpecificationExecutor {


    List<Tblporaccessories> findByIsDeleted(Deleted deleted);

    List<Tblporaccessories>
    findByPorIdAndPorAccItemCodeAndPorAccItemBatchIdAndPorAccItemBranchIdAndPorAccColorIdAndPorAccFitIdAndPorAccSizeId
            (String porId,String porAccItemCode,
             String porAccItemBatchId,String porAccItemBranchId,
             Integer color, Integer fit,Integer size);

    @Query(value = "select a.por_id, a.por_acc_item_code, a.por_acc_item_desc, a.por_acc_item_consumption_qty, a.por_acc_item_qty, " +
            " a.por_acc_item_batch_id, a.por_acc_item_branch_id, ifnull(b.stores_qty,0) as stores_qty,b.size_id,b.fit_id,b.color_id " +
            "from tblporaccessories a inner join stock b on a.por_acc_item_code = b.item_code and a.por_acc_item_batch_id = b.batch_no " +
            " and a.por_acc_item_branch_id = b.branch_id and a.por_acc_color_id=b.color_id and a.por_acc_fit_id=b.fit_id and " +
            " a.por_acc_size_id=b.size_id " +
            " where por_id =?1 and a.is_deleted=0 ",nativeQuery = true)
    List<getByStockAndTblPorAccessoriesResponse> getByStockAndTblPorAccessory(String porId);

}