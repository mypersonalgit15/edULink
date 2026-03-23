package com.cts.eduLink.application.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cts.eduLink.application.entity.AppUser;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Query("SELECT a FROM AppUser a WHERE a.userEmail = :userEmail")
    Optional<AppUser> findAppUserByUserEmail(@Param("userEmail") String userEmail);

    @Query("SELECT a FROM AppUser a WHERE a.phoneNumber = :phoneNumber")
    Optional<AppUser> findAppUserByPhoneNumber(@Param("phoneNumber") Long phoneNumber);
}