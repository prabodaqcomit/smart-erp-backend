package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GeneralJournalTemplateHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.Deleted;
import java.util.Date;
import java.util.List;

public interface GeneralJournalTemplateHeaderRepository extends JpaRepository<GeneralJournalTemplateHeader,Long> , JpaSpecificationExecutor {


    List<GeneralJournalTemplateHeader> findByIsDeleted(Deleted deleted);


    //-------------------------------Paginated----------------------------------------------------------------
    Page<GeneralJournalTemplateHeader> findByIsDeleted(Deleted deleted, Pageable pageable);

    Page<GeneralJournalTemplateHeader> findByIsDeletedAndBranchId(Deleted deleted, Long branchId, Pageable pageable);

    Page<GeneralJournalTemplateHeader> findByIsDeletedAndTemplateNameContaining(Deleted deleted, String recurringName, Pageable pageable);

    Page<GeneralJournalTemplateHeader> findByIsDeletedAndBranchIdAndTemplateNameContaining(Deleted deleted, Long branchId, String recurringName, Pageable pageable);

    //-------------------------------Paginated----------------------------------------------------------------

}