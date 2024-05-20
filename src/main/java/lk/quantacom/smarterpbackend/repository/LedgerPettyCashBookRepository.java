package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.LedgerPettyCashBook;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LedgerPettyCashBookRepository extends JpaRepository<LedgerPettyCashBook,Long> , JpaSpecificationExecutor {


    List<LedgerPettyCashBook> findByIsDeleted(Deleted deleted);

    List<LedgerPettyCashBook> findByLedgerPettyCashBookId(Integer id);

    @Query(value = "select max(ledger_petty_cash_book_id) from ledger_petty_cash_book ",nativeQuery = true)
    Integer getMax();

    @Query(value = "Select * from ledger_petty_cash_book where date(petty_cash_date) between ?1 and ?2   and  branch_id=?3 order by ledger_petty_cash_book_id asc",nativeQuery = true)
    List<LedgerPettyCashBook> getByDateRanges(String from, String to, Long branchId);
}