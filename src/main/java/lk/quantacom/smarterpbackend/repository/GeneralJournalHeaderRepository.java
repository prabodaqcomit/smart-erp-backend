package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GeneralJournalHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.Deleted;
import java.util.Date;
import java.util.List;

public interface GeneralJournalHeaderRepository extends JpaRepository<GeneralJournalHeader,Long> , JpaSpecificationExecutor {

    List<GeneralJournalHeader> findByIsDeleted(Deleted deleted, Sort sort);

    @Query(value = "select journal_number from general_journal_header " +
            " where id = (select max(id) from general_journal_header)",nativeQuery = true)
    String getMaximumJournalNumber();

    List<GeneralJournalHeader> findByIsDeletedAndTransactionDateBetween(Deleted deleted, Date transactionFromDate, Date transactionToDate, Sort sort);

    List<GeneralJournalHeader> findByIsDeletedAndBranchIdAndTransactionDateBetween(Deleted deleted, Long branchId, Date transactionFromDate, Date transactionToDate, Sort sort);

    //-------------------------------Paginated----------------------------------------------------------------
    Page<GeneralJournalHeader> findByIsDeleted(Deleted deleted, Pageable pageable);

    Page<GeneralJournalHeader> findByIsDeletedAndBranchId(Deleted deleted, Long branchId, Pageable pageable);

//    Page<GeneralJournalHeader> findByIsDeletedAndTransactionDateBetween(Deleted deleted, Date transactionFromDate, Date transactionToDate, Pageable pageable);
//
//    Page<GeneralJournalHeader> findByIsDeletedAndBranchIdAndTransactionDateBetween(Deleted deleted, Long branchId, Date transactionFromDate, Date transactionToDate, Pageable pageable);

    //-------------------------------Paginated----------------------------------------------------------------

}