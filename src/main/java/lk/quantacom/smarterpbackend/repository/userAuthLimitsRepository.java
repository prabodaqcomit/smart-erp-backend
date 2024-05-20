package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.userAuthLimits;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface userAuthLimitsRepository extends JpaRepository<userAuthLimits, Long>, JpaSpecificationExecutor {


    List<userAuthLimits> findByIsDeleted(Deleted deleted);

    userAuthLimits findByUsernameAndIsDeleted(String username, Deleted deleted);

}