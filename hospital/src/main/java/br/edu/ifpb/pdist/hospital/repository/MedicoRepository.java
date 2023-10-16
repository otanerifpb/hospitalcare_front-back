package br.edu.ifpb.pdist.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pdist.hospital.model.Medico;

public interface MedicoRepository  extends JpaRepository<Medico, Integer> {

}
