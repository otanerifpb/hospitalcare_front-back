package br.edu.ifpb.pdist.hospital.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pdist.hospital.model.Medico;
import br.edu.ifpb.pdist.hospital.repository.MedicoRepository;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    // Ativa o menu Médico na barra de navegação
    @ModelAttribute("menu")
    public String activeMenu(){
        return "medicos";
    }

    // Rota para acessar a lista pelo menu
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listAll(ModelAndView mav) {
        List<Medico> opMedicos = medicoRepository.findAll();
        mav.addObject("medicos", opMedicos);
        mav.setViewName("medicos/listMedico");
        return mav;
    } 

    // Rota para acessar a lista ao usar o REDIRECT
    @RequestMapping()
    public String listAll(Model model) {
        model.addAttribute("medicos", medicoRepository.findAll());
        return "medicoes/listMedico";
    }

    // Rota para acessar o formunário
    @RequestMapping("/formMedico")
    public ModelAndView getFormEstu(Medico medico, ModelAndView mav) {
        mav.addObject("medico", medico);
        mav.setViewName("medicos/formMedico");
        return mav;
    }

    // Rota para cadastrar um Médico no Sitema
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(Medico medico, ModelAndView mav, RedirectAttributes redAttrs) {
        Optional<Medico> opCRM = medicoRepository.findByCRM(medico.getCRM());
        if (opCRM.isPresent()) {
            redAttrs.addFlashAttribute("errorMensagem", "Médico já cadastrada no sistema!!");
            mav.setViewName("redirect:/medicos");
        } else {
            medicoRepository.save(medico);
            mav.addObject("medicos", medicoRepository.findAll());
            mav.addObject("succesMensagem", "Médico cadastrado com sucesso!");
            mav.setViewName("medicos/listMedico");
        }
        return mav;
    }
}
