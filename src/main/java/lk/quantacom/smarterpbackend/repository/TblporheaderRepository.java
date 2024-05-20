package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.dto.response.getPorAccessoryResponse;
import lk.quantacom.smarterpbackend.entity.Tblporheader;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TblporheaderRepository extends JpaRepository<Tblporheader,Long> , JpaSpecificationExecutor {

    List<Tblporheader> findByIsDeleted(Deleted deleted);  //findByPorApprovedAndIsDeleted

    List<Tblporheader> findByPorApprovedAndIsDeleted(Boolean ap,Deleted deleted);

    Tblporheader findByPorId(String porId);

    List<Tblporheader> findByPorIdAndPorApproved(String porId,Boolean porApproved);

    List<Tblporheader> findByPorIdAndPorApprovedAndPorApproveRequest(String porId,Boolean porApproved,Boolean porApproveRequest);

    @Query(value = "select a.item_code, a.item_name, b.batch_no, b.stores_qty,b.branch_id,b.color_id," +
            " b.fit_id,b.size_id from item_master a inner " +
            " join stock b on a.item_code = b.item_code where b.item_code =?2 " +
            " and (b.batch_no =?1 or b.batch_no='Batch No 01') and b.branch_id =?3 and " +
            " b.size_id=?4 and b.fit_id=?5 and b.color_id=?6 and " +
            " a.is_deleted=0 ",nativeQuery = true)
    List<getPorAccessoryResponse> getByStockAndItemPorAccessory(String batchNo, String itemCode, Long branchId,
                                                                Long sizeId,Long fitId, Long colorId);


}