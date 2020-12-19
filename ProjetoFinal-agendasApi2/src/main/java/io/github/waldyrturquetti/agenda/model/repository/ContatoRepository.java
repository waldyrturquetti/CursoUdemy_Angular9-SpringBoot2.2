package io.github.waldyrturquetti.agenda.model.repository;

import io.github.waldyrturquetti.agenda.model.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {
}
