package com.curso.curso.controllers;

import com.curso.curso.dao.UsuarioDao;
import com.curso.curso.models.Usuario;
import com.curso.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "prueba")
    public List<String> prueba(){
        return List.of("Manzana","Kiwi","Banana");
    }

    @RequestMapping(value = "api/usuario/{id}",method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Ivan");
        usuario.setApellido("Espinosa");
        usuario.setEmail("espinosa@correo.com");
        usuario.setTelefono("985484584");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios")
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token){
        String usuarioId = jwtUtil.getKey(token);
        if (usuarioId == null){
            return new ArrayList<>();
        }
        return usuarioDao.getUsuarios();
    }

    @RequestMapping(value = "api/usuario/{id}",method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id){
        usuarioDao.eliminar(id);
    }

    @RequestMapping(value = "api/usuarios",method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){
        usuarioDao.registrar(usuario);
    }
}
