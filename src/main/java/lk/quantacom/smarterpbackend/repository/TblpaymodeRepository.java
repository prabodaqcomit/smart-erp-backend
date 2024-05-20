package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.Tblpaymode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.Deleted;
import java.util.Date;
import java.util.List;

public interface TblpaymodeRepository extends JpaRepository<Tblpaymode,Long> , JpaSpecificationExecutor {

    List<Tblpaymode> findByIsDeleted(Deleted deleted);

    Tblpaymode findByPaymodeCode(String paymodeCode);

    @Modifying
    @Query(value = "update tblpaymode set id = paymode_code", nativeQuery = true)
    Integer updateId();

}