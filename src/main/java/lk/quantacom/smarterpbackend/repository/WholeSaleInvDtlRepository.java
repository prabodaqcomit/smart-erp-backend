package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.WholeSaleInvDtl;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface WholeSaleInvDtlRepository extends JpaRepository<WholeSaleInvDtl,Long> , JpaSpecificationExecutor {


    List<WholeSaleInvDtl> findByIsDeleted(Deleted deleted);

    List<WholeSaleInvDtl> findByInvnoAndItemCodeAndMrpAndIsDeleted(Integer invno,String itemCode,Double mrp,Deleted deleted);
}