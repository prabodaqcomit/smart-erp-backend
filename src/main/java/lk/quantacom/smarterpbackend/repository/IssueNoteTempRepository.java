package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.IssueNoteTemp;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IssueNoteTempRepository extends JpaRepository<IssueNoteTemp,Long> , JpaSpecificationExecutor {

    @Query(value = "select max(id) FROM issue_note_temp",nativeQuery = true)
    Long getMaxId();

    List<IssueNoteTemp> findByIsDeleted(Deleted deleted);

 }