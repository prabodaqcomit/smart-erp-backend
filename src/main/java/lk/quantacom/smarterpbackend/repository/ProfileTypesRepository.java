package lk.quantacom.smarterpbackend.repository;


import lk.quantacom.smarterpbackend.entity.ProfileTypes;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProfileTypesRepository extends JpaRepository<ProfileTypes,Integer> , JpaSpecificationExecutor {


    List<ProfileTypes> findByIsDeleted(Deleted deleted);


}