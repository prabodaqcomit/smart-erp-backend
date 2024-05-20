package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.OpeningBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.*;

import java.util.Date;
import java.util.List;

public interface OpeningBalanceRepository extends JpaRepository<OpeningBalance,Long> , JpaSpecificationExecutor {

    @Query(value = "select max(idopening_balance) from opening_balance",nativeQuery = true)
    Integer getMax();

    List<OpeningBalance> findByIsDeleted(Deleted deleted);

 }