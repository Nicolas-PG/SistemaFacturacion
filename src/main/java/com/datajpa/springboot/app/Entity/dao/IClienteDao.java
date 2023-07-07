package com.datajpa.springboot.app.Entity.dao;

import com.datajpa.springboot.app.Entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface IClienteDao extends CrudRepository<Cliente,Long>, PagingAndSortingRepository<Cliente,Long> {


}
