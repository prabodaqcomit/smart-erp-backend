package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.Department3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.*;

import java.util.Date;
import java.util.List;

public interface Department3Repository extends JpaRepository<Department3,Long> , JpaSpecificationExecutor {



    List<Department3> findByIsDeleted(Deleted deleted);

 }