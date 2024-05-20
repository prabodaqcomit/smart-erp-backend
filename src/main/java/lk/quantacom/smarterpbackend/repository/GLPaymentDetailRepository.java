package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GLPaymentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.Deleted;

import java.util.List;

public interface GLPaymentDetailRepository extends JpaRepository<GLPaymentDetail,Long> , JpaSpecificationExecutor {


    List<GLPaymentDetail> findByIsDeleted(Deleted deleted);

    List<GLPaymentDetail> findByGlPayHeaderIdAndIsDeleted(Long glPayHeaderId,Deleted deleted);

    List<GLPaymentDetail> findByIsDeletedAndGlPayHeaderId(Deleted deleted, Long headerId);

    @Modifying
    @Query(value = "DELETE FROM glpayment_detail WHERE  gl_pay_header_id = ?1", nativeQuery = true)
    int deletePaymentGeneralPaymentDetailsByHeaderId(Long headerId);
}