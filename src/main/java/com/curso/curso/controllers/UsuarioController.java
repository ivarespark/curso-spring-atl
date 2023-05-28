package com.curso.curso.controllers;

import com.curso.curso.models.Usuario;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @RequestMapping(value = "prueba")
    public List<String> prueba(){
        return List.of("Manzana","Kiwi","Banana");
    }

    @RequestMapping(value = "usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Ivan");
        usuario.setApellido("Espinosa");
        usuario.setEmail("espinosa@correo.com");
        usuario.setTelefono("985484584");
        return usuario;
    }

    @RequestMapping(value = "usuarios")
    public List<Usuario> getUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario();
        usuario.setId(123L);    // L para castear el numero a long
        usuario.setNombre("Ivan");
        usuario.setApellido("Espinosa");
        usuario.setEmail("espinosa@correo.com");
        usuario.setTelefono("985484584");

        Usuario usuario2 = new Usuario();
        usuario2.setId(345L);
        usuario2.setNombre("Juan");
        usuario2.setApellido("Perico");
        usuario2.setEmail("perico@correo.com");
        usuario2.setTelefono("987844584");

        Usuario usuario3 = new Usuario();
        usuario3.setId(456L);
        usuario3.setNombre("Miguel");
        usuario3.setApellido("Puchungo");
        usuario3.setEmail("pucho@correo.com");
        usuario3.setTelefono("945184584");

        usuarios.add(usuario);
        usuarios.add(usuario2);
        usuarios.add(usuario3);
        return usuarios;
    }
}
