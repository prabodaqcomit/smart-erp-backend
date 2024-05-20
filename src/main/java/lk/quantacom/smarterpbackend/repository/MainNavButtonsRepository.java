package lk.quantacom.smarterpbackend.repository;


import lk.quantacom.smarterpbackend.entity.MainNavButtons;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MainNavButtonsRepository extends JpaRepository<MainNavButtons,Long> , JpaSpecificationExecutor {


    List<MainNavButtons> findByIsDeleted(Deleted deleted);


}