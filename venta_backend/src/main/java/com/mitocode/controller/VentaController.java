package com.mitocode.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.model.Venta;
import com.mitocode.service.IVentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {
	@Autowired
	private IVentaService service;
	
	@GetMapping
	public ResponseEntity <List<Venta>> listar ()
	{
		List<Venta> ventas=service.listar();
		return new ResponseEntity<List<Venta>> (ventas,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Venta> listarPorId(@PathVariable("id") Integer id)
	{
		Venta Venta=service.leer(id);
		return new ResponseEntity<Venta>(Venta,HttpStatus.OK);
	}
	@PostMapping(produces="application/json",consumes="application/json")
	
	public ResponseEntity<Venta> registrar (@Valid @RequestBody Venta venta)
	{
		Venta v=service.registrar(venta);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(v.getIdVenta()).toUri();
		return  ResponseEntity.created(location).build();
	}
	@PutMapping
	public ResponseEntity<Object> modificar (@Valid @RequestBody Venta Venta)
	{
		Venta per=service.modificar(Venta);
		return new ResponseEntity<Object>(per,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public void eliminar (@PathVariable("id") Integer id)
	{
		service.eliminar(id);
		
	}
}
