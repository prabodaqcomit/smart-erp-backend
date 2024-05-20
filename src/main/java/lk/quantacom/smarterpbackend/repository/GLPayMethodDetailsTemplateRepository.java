package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GLPayMethodDetails;
import lk.quantacom.smarterpbackend.entity.GLPayMethodDetailsTemplate;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GLPayMethodDetailsTemplateRepository extends JpaRepository<GLPayMethodDetailsTemplate,Long> , JpaSpecificationExecutor {


    List<GLPayMethodDetailsTemplate> findByIsDeleted(Deleted deleted);


}