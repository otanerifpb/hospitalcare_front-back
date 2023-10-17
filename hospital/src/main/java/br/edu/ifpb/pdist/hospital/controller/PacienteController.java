package br.edu.ifpb.pdist.hospital.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pdist.hospital.model.Medico;
import br.edu.ifpb.pdist.hospital.model.Paciente;
import br.edu.ifpb.pdist.hospital.repository.PacienteRepository;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    // Ativa o menu Paciente na barra de navegação
    @ModelAttribute("menu")
    public String activeMenu(){
        return "pacientes";
    }
    
    // Rota para acessar a lista pelo menu
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listAll(ModelAndView mav) {
        List<Paciente> opPacientes = pacienteRepository.findAll();
        mav.addObject("pacientes", opPacientes);
        mav.setViewName("pacientes/listPaciente");
        return mav;
    } 

    // Rota para acessar a lista ao usar o REDIRECT
    @RequestMapping()
    public String listAll(Model model) {
        model.addAttribute("pacientes", pacienteRepository.findAll());
        return "pacientes/listPaciente";
    }

    // Rota para acessar o formunário
    @RequestMapping("/formPaciente")
    public ModelAndView getFormEstu(Paciente paciente, ModelAndView mav) {
        mav.addObject("paciente", paciente);
        mav.setViewName("pacientes/formPaciente");
        return mav;
    }

    // Rota para cadastrar um Paciente no Sitema
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(Paciente paciente, ModelAndView mav, RedirectAttributes redAttrs) {
        Optional<Paciente> opCPF = pacienteRepository.findByCPF(paciente.getCPF());
        if (opCPF.isPresent()) {
            redAttrs.addFlashAttribute("errorMensagem", "Paciente já cadastrada no sistema!!");
            mav.setViewName("redirect:/pacientes");
        } else {
            pacienteRepository.save(paciente);
            mav.addObject("pacientes", pacienteRepository.findAll());
            mav.addObject("succesMensagem", "Paciente cadastrado com sucesso!");
            mav.setViewName("pacientes/listPaciente");
        }
        return mav;
    }

    // Rota para acessar o formunlário de atualização ou a lista se não for atualizar 
    @RequestMapping("/{id}")
    public ModelAndView getPacienteById(@PathVariable(value = "id") Integer id, ModelAndView mav) {
        Optional<Paciente> opPaciente = pacienteRepository.findById(id);
        if (opPaciente.isPresent()) {
            Paciente paciente = opPaciente.get();
            mav.addObject("paciente", paciente);
            mav.setViewName("pacientes/formUpPaciente");
        } else {
            mav.addObject("errorMensagem", "Paciente  não encontrado.");
            mav.setViewName("pacientes/listPaciente");
        }
        return mav;
    }

    // Rota para atualizar um Paciente na lista pelo formUpPaciente
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public ModelAndView updade(Paciente paciente, ModelAndView mav, RedirectAttributes redAttrs) {
        pacienteRepository.save(paciente);
        mav.addObject("pacientes", pacienteRepository.findAll());
        mav.addObject("succesMensagem", "Paciente "+paciente.getNome()+", atualizado com sucesso!");
        mav.setViewName("/pacientes/listPaciente"); 
        return mav;
    }

    // Rota para deletar um Paciente da lista
    @RequestMapping("{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Integer id, ModelAndView mav, RedirectAttributes redAttrs) {
        Optional<Paciente> opPaciente = pacienteRepository.findById(id);
        if (opPaciente.isPresent()) {
            Paciente paciente = opPaciente.get();
            pacienteRepository.deleteById(id);
            redAttrs.addFlashAttribute("succesMensagem", "Paciente "+paciente.getNome()+" deletado com sucesso!!");
        } else {
            redAttrs.addFlashAttribute("errorMensagem", "Paciente Não encontrado!!");
        }
        mav.setViewName("redirect:/pacientes");
        return mav;
    }
}
