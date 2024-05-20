package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GLPayMethodDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.Deleted;

import java.util.List;

public interface GLPayMethodDetailsRepository extends JpaRepository<GLPayMethodDetails,Long> , JpaSpecificationExecutor {


    List<GLPayMethodDetails> findByIsDeleted(Deleted deleted);

    List<GLPayMethodDetails> findByGlPayHeaderIdAndIsDeleted(Long glPayHeaderId, Deleted deleted);

    @Modifying
    @Query(value = "DELETE FROM glpay_method_details WHERE gl_pay_header_id = ?1", nativeQuery = true)
    int deletePaymentPaymentDetailsByHeaderId(Long headerId);
}