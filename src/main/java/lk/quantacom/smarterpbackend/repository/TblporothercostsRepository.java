package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.Tblporfits;
import lk.quantacom.smarterpbackend.entity.Tblporothercosts;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface TblporothercostsRepository extends JpaRepository<Tblporothercosts,Integer> , JpaSpecificationExecutor {


    List<Tblporothercosts> findByIsDeleted(Deleted deleted);

    List<Tblporothercosts> findByPorId(String porId);

}