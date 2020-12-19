package io.github.waldyrturquetti.clientes.service;

import io.github.waldyrturquetti.clientes.exception.UsuarioCadastradoException;
import io.github.waldyrturquetti.clientes.model.entity.Usuario;
import io.github.waldyrturquetti.clientes.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(Usuario usuario){
        boolean exists = repository.existsByUsername(usuario.getUsername());
        if (exists){
            throw new UsuarioCadastradoException(usuario.getUsername());
        }
        return repository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {

        Usuario usuario = repository
                    .findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado.") );

        return User                 //É equivalente ao usar o UserDetails como implementação da classe Usuário
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build()
                ;
    }
}
