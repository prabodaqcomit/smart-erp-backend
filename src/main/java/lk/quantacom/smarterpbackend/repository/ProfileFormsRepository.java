package lk.quantacom.smarterpbackend.repository;


import lk.quantacom.smarterpbackend.entity.ProfileForms;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProfileFormsRepository extends JpaRepository<ProfileForms,Integer> , JpaSpecificationExecutor {


    List<ProfileForms> findByIsDeleted(Deleted deleted);


}