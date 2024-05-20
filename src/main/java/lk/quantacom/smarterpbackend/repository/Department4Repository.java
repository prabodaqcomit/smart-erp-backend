package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.Department4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.*;

import java.util.Date;
import java.util.List;

public interface Department4Repository extends JpaRepository<Department4,Long> , JpaSpecificationExecutor {



    List<Department4> findByIsDeleted(Deleted deleted);

 }