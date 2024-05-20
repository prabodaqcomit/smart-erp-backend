package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GLPaymentDetail;
import lk.quantacom.smarterpbackend.entity.GLPaymentDetailTemplate;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GLPaymentDetailTemplateRepository extends JpaRepository<GLPaymentDetailTemplate,Long> , JpaSpecificationExecutor {


    List<GLPaymentDetailTemplate> findByIsDeleted(Deleted deleted);


}