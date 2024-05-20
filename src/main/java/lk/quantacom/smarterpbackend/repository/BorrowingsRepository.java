package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.Borrowings;
import lk.quantacom.smarterpbackend.entity.ChequeIssueNote;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface BorrowingsRepository extends JpaRepository<Borrowings,Long> , JpaSpecificationExecutor {

    List<Borrowings> findByIsDeleted(Deleted deleted);

    List<Borrowings> findByBorrowerName(String borrowerName);

    List<Borrowings> findByReason(String reason);

    List<Borrowings> findByBorrowerNameAndStatusOrderByBorrowDateAsc(String borrowerName,String status);

    List<Borrowings> findByBorrowDateBetweenOrderByIdAsc(Date from, Date to);

    @Query(value = "select borrower_name from borrowings group by borrower_name order by borrower_name",nativeQuery = true)
    List<String> getBorrowerNames();


}