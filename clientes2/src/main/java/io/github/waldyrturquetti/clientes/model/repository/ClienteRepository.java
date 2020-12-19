package io.github.waldyrturquetti.clientes.model.repository;

import io.github.waldyrturquetti.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
