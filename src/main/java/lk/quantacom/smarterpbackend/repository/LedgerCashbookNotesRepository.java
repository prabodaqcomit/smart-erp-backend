package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.LedgerCashbookNotes;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface LedgerCashbookNotesRepository extends JpaRepository<LedgerCashbookNotes,Integer> , JpaSpecificationExecutor {

    @Query(value = "select max(ledger_cashbook_notes_id) from ledger_cashbook_notes",nativeQuery = true)
    Integer getMaxId();


    List<LedgerCashbookNotes> findByIsDeleted(Deleted deleted);

    List<LedgerCashbookNotes> findByPayeeName(String payeeName);




}