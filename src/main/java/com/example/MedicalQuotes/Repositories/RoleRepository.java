package com.example.MedicalQuotes.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MedicalQuotes.Models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
