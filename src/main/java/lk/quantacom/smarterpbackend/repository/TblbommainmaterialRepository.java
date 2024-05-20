package lk.quantacom.smarterpbackend.repository;

import feign.Param;
import lk.quantacom.smarterpbackend.dto.response.GetBomMaterialByStockMaterialDescResponse;
import lk.quantacom.smarterpbackend.dto.response.GetForBomAllItemCodeByDescResponse;
import lk.quantacom.smarterpbackend.entity.Tblbomfit;
import lk.quantacom.smarterpbackend.entity.Tblbomheader;
import lk.quantacom.smarterpbackend.entity.Tblbommainmaterial;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface TblbommainmaterialRepository extends JpaRepository<Tblbommainmaterial,Long> , JpaSpecificationExecutor {


    List<Tblbommainmaterial> findByIsDeleted(Deleted deleted);

    Tblbommainmaterial findByBomId(Integer bomId);

    @Query(value = "SELECT A.BOM_ID, A.BOM_MAIN_MATERIAL_ID, A.BOM_MAIN_MATERIAL_DESC, A.BOM_MAIN_MATERIAL_BATCH_ID, A.BOM_MAIN_MATERIAL_BRANCH_ID, B.MATERIAL_WIDTH, B.AVAILAB_QTY, B.COLOR_ID, B.FIT_ID, B.SIZE_ID " +
            "FROM tblbommainmaterial A  " +
            "INNER JOIN STOCK B ON A.BOM_MAIN_MATERIAL_ID = B.ITEM_CODE AND A.BOM_MAIN_MATERIAL_BATCH_ID = B.BATCH_NO AND A.BOM_MAIN_MATERIAL_BRANCH_ID = B.BRANCH_ID " +
            "WHERE BOM_ID = ?1",nativeQuery = true)
    List<GetBomMaterialByStockMaterialDescResponse> getBomMaterialsByBomId(@Param("bomId") Integer bomId);

}