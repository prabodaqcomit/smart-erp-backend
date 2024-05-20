package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.GrnHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.*;

import java.util.Date;
import java.util.List;

public interface GrnHeaderRepository extends JpaRepository<GrnHeader,Long> , JpaSpecificationExecutor {



    List<GrnHeader> findByIsDeleted(Deleted deleted);

 }