package com.example.repository;

import com.example.dbmodel.PersonGlobalLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonGlobalLinkRepository extends JpaRepository<PersonGlobalLink, Long> {

}
