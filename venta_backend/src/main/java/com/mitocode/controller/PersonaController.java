package com.mitocode.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Persona;
import com.mitocode.service.IPersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {
	@Autowired
	private IPersonaService service;
	
	@GetMapping
	public ResponseEntity <List<Persona>> listar ()
	{
		List<Persona> personas=service.listar();
		return new ResponseEntity<List<Persona>> (personas,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Persona> listarPorId(@PathVariable("id") Integer id)
	{
		Persona persona=service.leer(id);
		if(persona == null) {
			throw new ModeloNotFoundException("No se encontro ID: " + id);
		}
		return new ResponseEntity<Persona>(persona,HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Persona> registrar (@Valid @RequestBody Persona persona)
	{
		Persona per=service.registrar(persona);
		return new ResponseEntity<Persona>(per,HttpStatus.OK);
	}
	@PutMapping
	public ResponseEntity<Object> modificar (@Valid @RequestBody Persona persona)
	{
		Persona per=service.modificar(persona);
		return new ResponseEntity<Object>(per,HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public void eliminar (@PathVariable("id") Integer id)
	{
		service.eliminar(id);
		
	}
}
