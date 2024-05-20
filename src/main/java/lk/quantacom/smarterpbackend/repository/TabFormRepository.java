package lk.quantacom.smarterpbackend.repository;


import lk.quantacom.smarterpbackend.entity.TabForm;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TabFormRepository extends JpaRepository<TabForm,Long> , JpaSpecificationExecutor {


    List<TabForm> findByIsDeleted(Deleted deleted);


}