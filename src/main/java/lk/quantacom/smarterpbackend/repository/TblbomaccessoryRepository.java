package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.Tblbomaccessory;
import lk.quantacom.smarterpbackend.entity.Tblbomheader;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface TblbomaccessoryRepository extends JpaRepository<Tblbomaccessory,Long> , JpaSpecificationExecutor {


    List<Tblbomaccessory> findByIsDeleted(Deleted deleted);

    List<Tblbomaccessory> findByBdId(Integer bdId);


}