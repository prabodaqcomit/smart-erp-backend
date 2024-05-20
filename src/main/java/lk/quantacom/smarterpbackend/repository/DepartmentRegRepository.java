package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.DepartmentReg;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface DepartmentRegRepository extends JpaRepository<DepartmentReg,Long> , JpaSpecificationExecutor {


    List<DepartmentReg> findByIsDeleted(Deleted deleted);


}