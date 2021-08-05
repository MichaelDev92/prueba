package com.fullstack.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.model.Rol;

public interface RolDAO extends JpaRepository<Rol, Integer> {

}
