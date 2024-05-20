package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GLSupPayDetail;
import lk.quantacom.smarterpbackend.entity.GLSupPayDetailTemplate;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GLSupPayDetailTemplateRepository extends JpaRepository<GLSupPayDetailTemplate,Long> , JpaSpecificationExecutor {


    List<GLSupPayDetailTemplate> findByIsDeleted(Deleted deleted);


}