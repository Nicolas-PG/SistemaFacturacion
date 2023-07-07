package com.datajpa.springboot.app.Entity.dao;

import com.datajpa.springboot.app.Entity.Factura;
import org.springframework.data.repository.CrudRepository;

public interface IFacturaDao extends CrudRepository<Factura,Long> {
}
