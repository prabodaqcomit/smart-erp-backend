package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.Tblbomfit;
import lk.quantacom.smarterpbackend.entity.Tblbomsize;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface TblbomsizeRepository extends JpaRepository<Tblbomsize,Long> , JpaSpecificationExecutor {


    List<Tblbomsize> findByIsDeleted(Deleted deleted);

    List<Tblbomsize> findByBsId(Integer bsId);
}