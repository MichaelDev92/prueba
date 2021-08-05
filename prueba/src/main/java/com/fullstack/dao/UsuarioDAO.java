package com.fullstack.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.model.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer>{
	
	@Query(value = "{call GetUsers(:_nombre)}", nativeQuery = true)
	List<Usuario> listadoUsuarios(@Param("_nombre") String  nombre);
	
	@Query(value = "SELECT u.id_usuario, u.id_rol, u.nombre, u.activo FROM usuario u WHERE u.nombre=?1", nativeQuery = true)
	Usuario findUserByName(String name);
}
