package lk.quantacom.smarterpbackend.repository;


import lk.quantacom.smarterpbackend.entity.MasterDataTypes;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MasterDataTypesRepository extends JpaRepository<MasterDataTypes,Long> , JpaSpecificationExecutor {


    List<MasterDataTypes> findByIsDeleted(Deleted deleted);


}