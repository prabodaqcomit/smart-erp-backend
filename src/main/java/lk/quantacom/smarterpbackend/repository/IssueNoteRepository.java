package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.IssueNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.*;

import java.util.Date;
import java.util.List;

public interface IssueNoteRepository extends JpaRepository<IssueNote,Long> , JpaSpecificationExecutor {

    @Query(value = "select max(id) FROM issue_note",nativeQuery = true)
    Long getMaxId();

    List<IssueNote> findByIsDeleted(Deleted deleted);

 }