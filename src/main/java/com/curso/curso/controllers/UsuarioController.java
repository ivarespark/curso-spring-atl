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

    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
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
        if (!validarToken(token)){ return null; }
        return usuarioDao.getUsuarios();
    }

    @RequestMapping(value = "api/usuario/{id}",method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value = "Authorization") String token,@PathVariable Long id){
        if (!validarToken(token)){ return; }
        usuarioDao.eliminar(id);
    }

    @RequestMapping(value = "api/usuarios",method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){
        usuarioDao.registrar(usuario);
    }
}
