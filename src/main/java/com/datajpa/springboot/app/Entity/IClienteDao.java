package com.datajpa.springboot.app.Entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface IClienteDao extends CrudRepository<Cliente,Long>, PagingAndSortingRepository<Cliente,Long> {


}
