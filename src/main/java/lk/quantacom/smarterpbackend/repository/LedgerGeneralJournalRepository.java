package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.LedgerGeneralJournal;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LedgerGeneralJournalRepository extends JpaRepository<LedgerGeneralJournal,Long> , JpaSpecificationExecutor {


    List<LedgerGeneralJournal> findByIsDeleted(Deleted deleted);


}