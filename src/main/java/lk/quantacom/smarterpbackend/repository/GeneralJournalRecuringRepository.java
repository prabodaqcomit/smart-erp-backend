package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GeneralJournalRecurring;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import lk.quantacom.smarterpbackend.enums.Deleted;

import java.util.List;

public interface GeneralJournalRecuringRepository extends JpaRepository<GeneralJournalRecurring,Long> , JpaSpecificationExecutor {


    List<GeneralJournalRecurring> findByIsDeleted(Deleted deleted);


    //-------------------------------Paginated----------------------------------------------------------------
    Page<GeneralJournalRecurring> findByIsDeleted(Deleted deleted, Pageable pageable);

    Page<GeneralJournalRecurring> findByIsDeletedAndBranchId(Deleted deleted, Long branchId, Pageable pageable);

    Page<GeneralJournalRecurring> findByIsDeletedAndRecurringNameContaining(Deleted deleted, String recurringName, Pageable pageable);

    Page<GeneralJournalRecurring> findByIsDeletedAndBranchIdAndRecurringNameContaining(Deleted deleted, Long branchId, String recurringName, Pageable pageable);

    //-------------------------------Paginated----------------------------------------------------------------
}