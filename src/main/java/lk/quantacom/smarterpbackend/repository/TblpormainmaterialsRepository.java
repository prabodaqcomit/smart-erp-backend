package lk.quantacom.smarterpbackend.repository;

import feign.Param;
import lk.quantacom.smarterpbackend.dto.response.GetBomMaterialByStockMaterialDescResponse;
import lk.quantacom.smarterpbackend.dto.response.getPorByPorIdResponse;
import lk.quantacom.smarterpbackend.entity.Tblpormainmaterials;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TblpormainmaterialsRepository extends JpaRepository<Tblpormainmaterials, Long>, JpaSpecificationExecutor {


    List<Tblpormainmaterials> findByIsDeleted(Deleted deleted);

    @Query(value = "select a.por_id, a.por_main_item_code, a.por_main_item_desc, " +
            "a.por_main_item_batch_id, a.por_main_item_branch_id, a.por_main_item_qty, " +
            "b.color_id, b.fit_id, b.size_id, b.material_width, b.stores_qty " +
            "from tblpormainmaterials a inner join stock b on a.por_main_item_code = b.item_code and " +
            "a.por_main_item_batch_id = b.batch_no " +
            "and a.por_main_item_branch_id = b.branch_id and a.por_main_color_id=b.color_id and " +
            "a.por_main_size_id=b.size_id and a.por_main_fit_id=b.fit_id " +
            "where a.por_id =?1 and a.is_deleted=0", nativeQuery = true)
    List<getPorByPorIdResponse> getPorByPorId(@Param("porId") String porId);

}