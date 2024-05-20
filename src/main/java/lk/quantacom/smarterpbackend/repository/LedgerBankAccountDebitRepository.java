package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.LedgerBankAccountDebit;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface LedgerBankAccountDebitRepository extends JpaRepository<LedgerBankAccountDebit,Long> , JpaSpecificationExecutor {


    List<LedgerBankAccountDebit> findByIsDeleted(Deleted deleted);


}