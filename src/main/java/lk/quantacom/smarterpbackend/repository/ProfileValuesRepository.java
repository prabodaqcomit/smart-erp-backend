package lk.quantacom.smarterpbackend.repository;


import lk.quantacom.smarterpbackend.entity.ProfileValues;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileValuesRepository extends JpaRepository<ProfileValues,Integer> , JpaSpecificationExecutor {


    List<ProfileValues> findByIsDeleted(Deleted deleted);

    @Query(value = " select count(*) from profile_values where fieldname=?1 and profileid=?2 ",nativeQuery = true)
    Integer getCountPV( String fieldname,Integer profileid);

    @Query(value = " select fieldvalue from profile_values where fieldname=?1 and profileid=?2  ",nativeQuery = true)
    String getFieldValByFn(String fieldname, Integer profileid);
}