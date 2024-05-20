package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.BomHeader;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface BomHeaderRepository extends JpaRepository<BomHeader,Long> , JpaSpecificationExecutor {


    List<BomHeader> findByIsDeleted(Deleted deleted);


}