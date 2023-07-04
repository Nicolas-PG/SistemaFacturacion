package com.datajpa.springboot.app.Controllers;

import com.datajpa.springboot.app.Entity.Cliente;
import com.datajpa.springboot.app.Entity.IClienteDao;
import com.datajpa.springboot.app.Service.IClienteService;
import com.datajpa.springboot.app.Util.PageRender;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;


@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @RequestMapping(value = "/listar",method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model){

        Pageable pageRequest = PageRequest.of(page,5);
        Page<Cliente> clientes = clienteService.findAll(pageRequest);
        PageRender<Cliente> pageRender = new PageRender<>("/listar",clientes);
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes",clientes);
        model.addAttribute("page",pageRender);
        return "listar";
    }

    @GetMapping(value = "/form")
    public String crear(Map<String,Object> model){

        Cliente cliente = new Cliente();
        model.put("cliente",cliente);
        model.put("titulo","Formulario de cliente");


        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status){
        if (result.hasErrors()){
            model.addAttribute("titulo","Formulario Cliente");
            return "form";
        }
        clienteService.save(cliente);
        status.setComplete();  // despues de guardar eliminar el objeto cliente de la session
        flash.addAttribute("succes", "Cliente creado con exito");

        return "redirect:listar";
    }

    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String,Object> model, RedirectAttributes flash){
        Cliente cliente = null;
        if(id>0){

            cliente = clienteService.findOne(id);
            if(cliente == null){
                flash.addAttribute("error","La ID  no existe");
                return "redirect:/listar";
            }
        }else {
            flash.addAttribute("error","El ID no puede ser 0");
            return "redirect:/listar";
        }
        model.put("cliente",cliente);
        model.put("titulo","Editar Cliente");
        return "form";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash){

        if (id>0){
            clienteService.delete(id);
            flash.addAttribute("success","Cliente eliminado con exito");
        }
        return "redirect:/listar";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id, Map<String,Object> model, RedirectAttributes flash){
        Cliente cliente = clienteService.findOne(id);

        if (cliente==null){
            flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
            return "redirect::/listar";
        }

        model.put("cliente",cliente);
        model.put("titulo","Detalle cliente: "+cliente.getNombre());
        return "ver";
    }
}
