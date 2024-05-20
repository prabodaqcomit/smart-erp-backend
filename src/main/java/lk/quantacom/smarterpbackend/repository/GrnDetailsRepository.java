package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GrnDetails;
import lk.quantacom.smarterpbackend.entity.GrnHeader;
import lk.quantacom.smarterpbackend.entity.ItemMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.*;

import java.util.Date;
import java.util.List;

public interface GrnDetailsRepository extends JpaRepository<GrnDetails,Long> , JpaSpecificationExecutor {

    GrnDetails findByGrnAndItemAndBatchNo(GrnHeader grn, ItemMaster item,String batchNo);

    List<GrnDetails> findByIsDeleted(Deleted deleted);

 }