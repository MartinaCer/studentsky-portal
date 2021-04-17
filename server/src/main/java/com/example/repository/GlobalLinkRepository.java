package com.example.repository;

import com.example.dbmodel.GlobalLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalLinkRepository extends JpaRepository<GlobalLink, Long> {

}
