package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GrnRecordUpdate;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface GrnRecordUpdateRepository extends JpaRepository<GrnRecordUpdate,Long> , JpaSpecificationExecutor {

    @Query(value = "SELECT MAX(idgrn_record_update)FROM grn_record_update",nativeQuery = true)
    Integer getMaxId();

    List<GrnRecordUpdate> findByIsDeleted(Deleted deleted);


}