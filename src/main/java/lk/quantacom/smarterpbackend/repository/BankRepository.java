package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.Deleted;

import java.util.Date;
import java.util.List;

public interface BankRepository extends JpaRepository<Bank, Long>, JpaSpecificationExecutor {


    List<Bank> findByIsDeleted(Deleted deleted);


}