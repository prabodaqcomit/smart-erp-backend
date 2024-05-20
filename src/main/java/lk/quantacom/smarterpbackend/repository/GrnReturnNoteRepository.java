package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GrnReturnNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.*;

import java.util.Date;
import java.util.List;

public interface GrnReturnNoteRepository extends JpaRepository<GrnReturnNote,Long> , JpaSpecificationExecutor {

    @Query(value = "select max(return_id) from grn_return_note",nativeQuery = true)
    Long getMax();

    List<GrnReturnNote> findByIsDeleted(Deleted deleted);

 }