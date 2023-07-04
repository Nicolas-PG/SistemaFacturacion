package com.datajpa.springboot.app.Controllers;

import com.datajpa.springboot.app.Entity.Cliente;
import com.datajpa.springboot.app.Entity.Factura;
import com.datajpa.springboot.app.Entity.Producto;
import com.datajpa.springboot.app.Service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/form/{clienteId}")
    public String crear(@PathVariable(value = "clienteId") Long clienteId, Map<String,Object> model, RedirectAttributes flash){

        Cliente cliente = clienteService.findOne(clienteId);

        if (cliente == null){
            flash.addFlashAttribute("error","El cliente no existe en la base de datos");
            return "redirect:/listar";
        }

        Factura factura = new Factura();
        factura.setCliente(cliente);

        model.put("factura",factura);
        model.put("titulo", "Crear Factura");

        return "factura/form";
    }

    @GetMapping(value = "/cargar-productos/{term}", produces = {"application/json"})
    public @ResponseBody  List<Producto> cargarProductos(@PathVariable String term){   // el ResponseBody suprime las vista de thymeleaf

        return clienteService.findByNombre(term);
    }
}
