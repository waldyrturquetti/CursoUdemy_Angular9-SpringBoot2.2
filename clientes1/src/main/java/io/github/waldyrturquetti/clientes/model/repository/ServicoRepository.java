package io.github.waldyrturquetti.clientes.model.repository;

import io.github.waldyrturquetti.clientes.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
}
