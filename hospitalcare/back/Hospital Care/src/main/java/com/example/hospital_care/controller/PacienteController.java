package com.example.hospital_care.controller;

import com.example.hospital_care.model.Medico;
import com.example.hospital_care.model.Paciente;
import com.example.hospital_care.repository.MedicoRepository;
import com.example.hospital_care.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @GetMapping("/medico")
    public List<Medico> getMedicos(){
        return medicoRepository.findAll();
    }

    @GetMapping
    public List<Paciente> getPacientes(){
        return pacienteRepository.findAll();
    }

    @PostMapping
    public Paciente postMedico(@RequestBody Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @GetMapping("/{id}")
    public Paciente getPacienteById(@PathVariable int id) {
        return pacienteRepository.findById(id).get();
    }

    @PutMapping("/{id}")
    public Paciente putPaciente(@PathVariable int id, @RequestBody Paciente paciente) {
        Paciente pacienteDoBanco = pacienteRepository.findById(id).get();
        pacienteDoBanco.setName(paciente.getName());
        pacienteDoBanco.setAnamnese(paciente.getAnamnese());
        pacienteDoBanco.setCpf(paciente.getCpf());
        pacienteDoBanco.setData_de_nascimento(paciente.getData_de_nascimento());
        pacienteDoBanco.setEmail(paciente.getEmail());
        pacienteDoBanco.setProntuario(paciente.getProntuario());
        pacienteDoBanco.setSexo(paciente.getSexo());
        pacienteDoBanco.setTelefone(paciente.getTelefone());
        return pacienteRepository.save(pacienteDoBanco);
    }

    @DeleteMapping("/{id}")
    public void deletePaciente(@PathVariable int id) {
        pacienteRepository.deleteById(id);
    }

}
