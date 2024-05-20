package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.ItemMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.*;

import java.util.Date;
import java.util.List;

public interface ItemMasterRepository extends JpaRepository<ItemMaster,String> , JpaSpecificationExecutor {

    @Query(value = "select max(cast(item_code as UNSIGNED )) FROM item_master",nativeQuery = true)
    String getMaxId();

    ItemMaster findByItemCode(String itemCode);

    List<ItemMaster> findByIsDeleted(Deleted deleted);

 }