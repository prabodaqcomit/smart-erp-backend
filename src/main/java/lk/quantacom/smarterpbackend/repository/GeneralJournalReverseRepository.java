package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GeneralJournalReverse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.Deleted;

import java.util.Date;
import java.util.List;

public interface GeneralJournalReverseRepository extends JpaRepository<GeneralJournalReverse, Long>, JpaSpecificationExecutor<GeneralJournalReverse> {


    List<GeneralJournalReverse> findByIsDeleted(Deleted deleted);

    GeneralJournalReverse findByIsDeletedAndReverseForJournalId(Deleted deleted, Long reverseForJournalId);


}