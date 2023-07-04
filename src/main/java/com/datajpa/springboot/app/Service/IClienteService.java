package com.datajpa.springboot.app.Service;

import com.datajpa.springboot.app.Entity.Cliente;
import com.datajpa.springboot.app.Entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {

    public List<Cliente> findAll();
    public Page<Cliente> findAll(Pageable pageable);
    public void save(Cliente cliente);
    public Cliente findOne(Long id);
    public void delete(Long id);

    public List<Producto> findByNombre(String term);
}
