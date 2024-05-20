package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.DiningTable;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DiningTableRepository extends JpaRepository<DiningTable,Long> , JpaSpecificationExecutor {


    List<DiningTable> findByIsDeleted(Deleted deleted);


}