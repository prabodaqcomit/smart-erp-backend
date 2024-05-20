package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GLPaymentHeader;
import lk.quantacom.smarterpbackend.entity.GLPaymentHeaderTemplate;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GLPaymentHeaderTemplateRepository extends JpaRepository<GLPaymentHeaderTemplate,Long> , JpaSpecificationExecutor {


    List<GLPaymentHeaderTemplate> findByIsDeleted(Deleted deleted);

    @Query(value = "select max(cast(substring(voucher_number, 5) as unsigned)) as max_value from  glpayment_header",nativeQuery = true)
    Integer getMaxId();

}