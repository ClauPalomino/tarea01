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
import com.mitocode.model.Producto;
import com.mitocode.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	@Autowired
	private IProductoService service;
	@GetMapping
	public ResponseEntity<List<Producto>> listar()
	{
		List<Producto> producto= service.listar();
		return new ResponseEntity<List<Producto>>(producto,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Producto> listrPorId(@PathVariable("id") Integer id)
	{
		Producto producto=service.leer(id);
		if(producto == null) {
			throw new ModeloNotFoundException("No se encontro ID: " + id);
		}
		return new ResponseEntity<Producto>(producto,HttpStatus.OK);
	}
    @PostMapping	
	public ResponseEntity<Producto> registrar(@Valid @RequestBody Producto producto)
	{
    	Producto pro=service.registrar(producto);
    	return new ResponseEntity<Producto>(pro,HttpStatus.OK);
	}
    @PutMapping
    public ResponseEntity<Producto> modificar (@Valid @RequestBody Producto producto)
    {
    	Producto pro=service.modificar(producto);
    	return new ResponseEntity<Producto>(pro,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id)
    {
    	service.eliminar(id);
    }
}
