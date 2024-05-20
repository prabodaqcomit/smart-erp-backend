package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.BomDetails;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface BomDetailsRepository extends JpaRepository<BomDetails,Long> , JpaSpecificationExecutor {


    List<BomDetails> findByIsDeleted(Deleted deleted);

    List<BomDetails> findByHeaderIdAndIsDeleted(Long head,Deleted deleted);
}