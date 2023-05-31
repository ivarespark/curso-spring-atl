package com.curso.curso.dao;

import com.curso.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id); // buscar usuario
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id); // para encryptar
        String hash = argon2.hash(1,1024,1,usuario.getPassword()); // (iteraciones,tamaño,hilos,contraseña)

        usuario.setPassword(hash);
        entityManager.merge(usuario);
    }

    @Override
    public boolean verificarCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> lista = entityManager.createQuery(query)
                                .setParameter("email",usuario.getEmail())
                                .getResultList();
        if (lista.isEmpty()) { return false; }
        String passwordHased = lista.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        return argon2.verify(passwordHased,usuario.getPassword()); // compara las pass de la base y la ingresada (preva encriptacion)
    }
}
