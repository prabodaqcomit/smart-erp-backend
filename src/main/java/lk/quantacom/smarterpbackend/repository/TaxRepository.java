package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.Tax;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface TaxRepository extends JpaRepository<Tax,Long> , JpaSpecificationExecutor {


    List<Tax> findByIsDeleted(Deleted deleted);

    Tax findByTaxNameAndIsDeleted(String name,Deleted deleted);
}