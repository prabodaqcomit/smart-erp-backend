package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.Department1;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface Department1Repository extends JpaRepository<Department1,Long> , JpaSpecificationExecutor {



    List<Department1> findByIsDeleted(Deleted deleted);

 }