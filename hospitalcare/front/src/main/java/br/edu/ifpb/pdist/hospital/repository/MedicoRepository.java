package br.edu.ifpb.pdist.hospital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pdist.hospital.model.Medico;

public interface MedicoRepository  extends JpaRepository<Medico, Integer> {

    Optional<Medico> findByCRM(String string);

}
