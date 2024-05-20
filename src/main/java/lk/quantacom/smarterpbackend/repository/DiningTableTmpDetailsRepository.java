package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.DiningTableTmpDetails;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface DiningTableTmpDetailsRepository extends JpaRepository<DiningTableTmpDetails,Long> , JpaSpecificationExecutor {


    List<DiningTableTmpDetails> findByIsDeleted(Deleted deleted);

    List<DiningTableTmpDetails> findByHeadIdAndIsDeleted(Long hid,Deleted deleted);
}