package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GLSupPayDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.Deleted;

import java.util.List;

public interface GLSupPayDetailRepository extends JpaRepository<GLSupPayDetail,Long> , JpaSpecificationExecutor {


    List<GLSupPayDetail> findByIsDeleted(Deleted deleted);

    List<GLSupPayDetail> findByGlPayHeaderIdAndIsDeleted(Long glPayHeaderId, Deleted deleted);

    List<GLSupPayDetail> findByIsDeletedAndGlPayHeaderId(Deleted deleted, Long headerId);

    @Modifying
    @Query(value = "DELETE FROM glsup_pay_detail WHERE gl_pay_header_id = ?1", nativeQuery = true)
    int deletePaymentSupplierPaymentDetailsByHeaderId(Long headerId);
}