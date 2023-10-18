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
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public List<Medico> getMedicos(){
        return medicoRepository.findAll();
    }

    @GetMapping("/pacientes")
    public List<Paciente> getPacientes(){
        return pacienteRepository.findAll();
    }

    @PostMapping
    public Medico postMedico(@RequestBody Medico medico) {
        return medicoRepository.save(medico);
    }

    @GetMapping("/{id}")
    public Medico getMedicoById(@PathVariable int id) {
        return medicoRepository.findById(id).get();
    }

    @PutMapping("/{id}")
    public Medico putMedico(@PathVariable int id, @RequestBody Medico medico) {
        Medico medicoDoBanco = medicoRepository.findById(id).get();
        medicoDoBanco.setName(medico.getName());
        medicoDoBanco.setTelefone(medico.getTelefone());
        medicoDoBanco.setEspecialidade(medico.getEspecialidade());
        medicoDoBanco.setSexo(medico.getSexo());
        medicoDoBanco.setCrm(medico.getCrm());
        return medicoRepository.save(medicoDoBanco);
    }

    @DeleteMapping("/{id}")
    public void deleteMedico(@PathVariable int id) {
        medicoRepository.deleteById(id);
    }
}

