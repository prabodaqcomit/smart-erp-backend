package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.PayeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.Deleted;
import java.util.Date;
import java.util.List;

public interface PayeeDetailsRepository extends JpaRepository<PayeeDetails,Long> , JpaSpecificationExecutor {


    List<PayeeDetails> findByIsDeleted(Deleted deleted);


}