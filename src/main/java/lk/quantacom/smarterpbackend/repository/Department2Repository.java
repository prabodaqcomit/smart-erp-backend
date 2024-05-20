package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.Department2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.*;

import java.util.Date;
import java.util.List;

public interface Department2Repository extends JpaRepository<Department2,Long> , JpaSpecificationExecutor {



    List<Department2> findByIsDeleted(Deleted deleted);

 }