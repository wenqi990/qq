package com.example.practice_9.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practice_9.entity.Atm;

@Repository
public interface AtmDao extends JpaRepository< Atm, String > {

}
