package lk.quantacom.smarterpbackend.repository;


import lk.quantacom.smarterpbackend.entity.ModuleNames;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ModuleNamesRepository extends JpaRepository<ModuleNames,Long> , JpaSpecificationExecutor {


    List<ModuleNames> findByIsDeleted(Deleted deleted);


}