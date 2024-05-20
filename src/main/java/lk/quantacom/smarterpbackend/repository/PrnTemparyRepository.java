package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.PrnTempary;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface PrnTemparyRepository extends JpaRepository<PrnTempary,Integer> , JpaSpecificationExecutor {


    @Query(value = "SELECT MAX(prn_tempary_id)FROM prn_tempary",nativeQuery = true)
    Integer getMaxId();

    List<PrnTempary> findByIsDeleted(Deleted deleted);


}