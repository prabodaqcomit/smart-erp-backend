package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.dto.response.BomMainMaterialForPOR;
import lk.quantacom.smarterpbackend.dto.response.BomMainMaterialResponse;
import lk.quantacom.smarterpbackend.dto.response.GetForBomAllItemCodeByDescResponse;
import lk.quantacom.smarterpbackend.entity.Tblbomheader;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TblbomheaderRepository extends JpaRepository<Tblbomheader,Integer> , JpaSpecificationExecutor {


    List<Tblbomheader> findByIsDeleted(Deleted deleted);

    Tblbomheader findByBomId(Integer bomId);

    @Query(value = "select max(bom_id) from tblbomheader",nativeQuery = true)
    Integer getMaxId();

    @Modifying
    @Query(value = "delete from tblbomheader where bom_id =?1 ",nativeQuery = true)
    void deleteHdr(Integer bomId);

    @Modifying
    @Query(value = "delete from tblbomaccessory where bd_id =?1 ",nativeQuery = true)
    void deleteAcc(Integer bomId);

    @Modifying
    @Query(value = "delete from tblbomsize where bs_id =?1 ",nativeQuery = true)
    void deleteSize(Integer bomId);

    @Modifying
    @Query(value = "delete from tblbomfit where bf_id =?1 ",nativeQuery = true)
    void deleteFit(Integer bomId);

    @Modifying
    @Query(value = "delete from tblbommainmaterial where bom_id =?1 ",nativeQuery = true)
    void deleteMat(Integer bomId);

    @Query(value ="select a.item_code as item_code, a.item_name as item_name, b.supplier_id, \n" +
            "b.batch_no,\n" +
            "b.size_id, ifnull(s.size_desc,'') as size_desc, \n" +
            "b.fit_id, ifnull(f.fit_desc,'') as fit_desc,\n" +
            "b.color_id, ifnull(c.color_desc,'') as color_desc,\n" +
            "b.branch_id,b.stores_qty from item_master a  \n" +
            "inner join stock b on b.item_code = a.item_code \n" +
            "left join fit f on b.fit_id=f.id \n" +
            "left join size s on b.size_id=s.id \n" +
            "left join color c on b.color_id=c.id \n" +
            "where a.is_material = 1 and a.is_main_material = 0   \n" +
            "order by a.item_code ",nativeQuery = true)
    List<GetForBomAllItemCodeByDescResponse> getAllItemCodeByDesc();

    @Query(value = "select a.item_code as item_code, a.item_name as item_name, b.supplier_id, \n" +
            "b.batch_no,\n" +
            "b.size_id, ifnull(s.size_desc,'') as size_desc, \n" +
            "b.fit_id, ifnull(f.fit_desc,'') as fit_desc,\n" +
            "b.color_id, ifnull(c.color_desc,'') as color_desc,\n" +
            "b.branch_id,b.stores_qty from item_master a  \n" +
            "inner join stock b on b.item_code = a.item_code \n" +
            "left join fit f on b.fit_id=f.id \n" +
            "left join size s on b.size_id=s.id \n" +
            "left join color c on b.color_id=c.id \n" +
            "where a.is_material = 1 and a.is_main_material = 0   \n" +
            "order by a.item_code ",nativeQuery = true)
    List<GetForBomAllItemCodeByDescResponse> getAllItemCodeByDesc2();

    @Query(value = "select a.bom_main_size_id,a.bom_main_fit_id,a.bom_main_item_qty," +
            "a.bom_id, a.bom_main_material_id, a.bom_main_material_desc," +
            " a.bom_main_material_batch_id, a.bom_main_material_branch_id, " +
            "b.material_width, b.stores_qty, b.color_id, b.fit_id, b.size_id " +
            "from tblbommainmaterial a inner join stock b on a.bom_main_material_id = " +
            "b.item_code and a.bom_main_material_batch_id = b.batch_no and a.bom_main_material_branch_id = " +
            "b.branch_id where a.bom_id =?1",nativeQuery = true)
    List<BomMainMaterialResponse> getMainMaterial(Integer bomId);

    @Query(value = "select a.item_code as item_code,  \n" +
            "b.size_id, ifnull(s.size_desc,'') as size_desc, \n" +
            "b.fit_id, ifnull(f.fit_desc,'') as fit_desc,\n" +
            "b.color_id, ifnull(c.color_desc,'') as color_desc,\n" +
            " a.item_name as item_name, b.supplier_id, b.batch_no, \n" +
            " b.material_width, b.stores_qty, b.branch_id from item_master a \n" +
            " inner join stock b on b.item_code = a.item_code \n" +
            " left join color c on b.color_id =c.id \n" +
            " left join fit f on b.fit_id=f.id \n" +
            " left join size s on b.size_id=s.id \n" +
            " where a.is_material = 1\n" +
            " and a.is_main_material = 1 and b.stores_qty > 0 order by a.item_code",nativeQuery = true)
    List<BomMainMaterialForPOR> getMaterialForPOR();

}