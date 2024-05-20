package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GLPaymentHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.Deleted;

import java.util.List;

public interface GLPaymentHeaderRepository extends JpaRepository<GLPaymentHeader,Long> , JpaSpecificationExecutor {


    List<GLPaymentHeader> findByIsDeleted(Deleted deleted);

    @Query(value = "select max(cast(substring(voucher_number, 5) as unsigned)) as max_value from  glpayment_header",nativeQuery = true)
    Integer getMaxId();

    List<GLPaymentHeader> findByGlPaymentDetailTypeAndIsDeleted(String glPaymentDetailType,Deleted deleted);

    GLPaymentHeader findByIdAndGlPaymentDetailTypeAndIsDeleted(Long id,String glPaymentDetailType,Deleted deleted);

    @Query(
            value = "SELECT DISTINCT ph.voucher_number FROM glpayment_header ph WHERE ph.is_deleted = 0 \n" +
                    "AND CASE WHEN ?1 IS NOT NULL THEN ph.voucher_number LIKE %?1% ELSE TRUE END"
            ,nativeQuery = true
    )
    List<String> getAvailableVoucherNumbers(String searchNumber);
}