package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.LedgerAssetReg;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface LedgerAssetRegRepository extends JpaRepository<LedgerAssetReg,Long> , JpaSpecificationExecutor {


    List<LedgerAssetReg> findByIsDeleted(Deleted deleted);


}