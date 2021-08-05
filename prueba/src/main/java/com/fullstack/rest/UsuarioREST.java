package com.fullstack.rest;

import java.util.List;


import org.hibernate.hql.internal.ast.tree.IsNullLogicOperatorNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.dao.RolDAO;
import com.fullstack.dao.UsuarioDAO;
import com.fullstack.model.Rol;
import com.fullstack.model.Usuario;

@RestController
@RequestMapping("usuario")
public class UsuarioREST {
	
	//Inyección de interface DAO
	@Autowired
	public UsuarioDAO dao;
	@Autowired
	public RolDAO daoRol;
	
	//Metodos HTTP
	
	/**
	 * Método para guardar un usuario, revisa primero si no hay 
	 * ya un usuario con ese nombre
	 * @param user
	 */
	@PostMapping("/save")
	public ResponseEntity<String> guardar(@RequestBody Usuario user) {
		Usuario u = dao.findUserByName(user.getNombre());
		if(u != null) {
			return new ResponseEntity<>("El usuario ya existe", HttpStatus.BAD_REQUEST);
		}else {
			dao.save(user);
			return new ResponseEntity<>("saved", HttpStatus.OK);
		}
		
		
	}
	
	/**
	 * Método para listar todos los usuarios
	 * @return
	 */
	@GetMapping("/all")
	public List<Usuario> listar(){
		return dao.findAll();
	}
	
	/**
	 * Método para listar los roles
	 * @return
	 */
	@GetMapping("/allroles")
	public List<Rol> listarRoles(){
		return daoRol.findAll();
	}
	
	/**
	 * Método para eliminar un usuario
	 * @param id
	 */
	@DeleteMapping(value = "/del/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
		dao.deleteById(id);
		return ResponseEntity.ok("deleted");
	}
	
	/**
	 * Método para editar la información de un usuario
	 * @param user
	 */
	@PutMapping("/edit")
	public ResponseEntity<String> editar(@RequestBody Usuario user) {
		Usuario u = dao.findUserByName(user.getNombre());
		if(u != null) {
			return new ResponseEntity<>("El usuario ya existe", HttpStatus.BAD_REQUEST);
		}else {
			dao.save(user);
			return new ResponseEntity<>("edited", HttpStatus.OK);
		}
	}
	
	@GetMapping("/hola")
	public Usuario holamundo() {
		Usuario us = dao.findUserByName("Juan"); 
		System.out.println(us);
		return us;
	}
	
	@GetMapping("/getUser/{_nombre}")
	public List<Usuario> getUsers(@PathVariable("_nombre") String name){
		return  dao.listadoUsuarios(name);
	}
	
}
