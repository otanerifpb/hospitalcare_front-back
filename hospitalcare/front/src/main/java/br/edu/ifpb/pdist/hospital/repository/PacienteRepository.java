package br.edu.ifpb.pdist.hospital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pdist.hospital.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer>{

    Optional<Paciente> findByCPF(String string);
    
}
