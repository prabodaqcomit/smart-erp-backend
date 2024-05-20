package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.LedgerTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.*;

import java.util.Date;
import java.util.List;

public interface LedgerTypesRepository extends JpaRepository<LedgerTypes,Long> , JpaSpecificationExecutor {

    @Query(value = "select * from ledger_types where acc_category=?1 and is_deleted=0 group by main_acc_type order by main_acc_type",nativeQuery = true)
    List<LedgerTypes> getByCategory(String cat);

    @Query(value = "select * from ledger_types where acc_category=?1 and main_acc_type=?2 and is_deleted=0 group by main_acc_type order by main_acc_type",nativeQuery = true)
    List<LedgerTypes> getByMain(String cat,String main);

    List<LedgerTypes> findByIsDeleted(Deleted deleted);

 }