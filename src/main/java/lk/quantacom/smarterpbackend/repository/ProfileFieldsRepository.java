package lk.quantacom.smarterpbackend.repository;


import lk.quantacom.smarterpbackend.entity.ProfileFields;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileFieldsRepository extends JpaRepository<ProfileFields,String> , JpaSpecificationExecutor {


    List<ProfileFields> findByIsDeleted(Deleted deleted);

    @Query(value = " select * from profile_fields where fieldname=?1 ",nativeQuery = true)
    ProfileFields getProfileTypeByFieldName( String fieldname);

}