package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.Tblpaydtl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface TblpaydtlRepository extends JpaRepository<Tblpaydtl,String> , JpaSpecificationExecutor {


//    List<Tblpaydtl> findByIsDeleted(Deleted deleted);

    @Modifying
    @Query(value = "update tblpaydtl set fld_Cancel=1 where fld_InvNo=?1", nativeQuery = true)
    Integer updateFldCancel(String invNo);
}