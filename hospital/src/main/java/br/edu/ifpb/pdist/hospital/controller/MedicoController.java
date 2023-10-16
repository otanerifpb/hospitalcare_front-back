package br.edu.ifpb.pdist.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Controller;
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
        mav.setViewName("medicos/listMedico");
        return mav;
    } 

    // Rota para acessar o formunário
    @RequestMapping("/formMedico")
    public ModelAndView getFormEstu(Medico medico, ModelAndView mav) {
        mav.addObject("medico", medico);
        mav.setViewName("medicos/formMedico");
        return mav;
    }

    // Rota para cadastrar um Estudante no Sitema
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(Medico medico, ModelAndView mav, RedirectAttributes redAttrs) {
        
        medicoRepository.save(medico);

        mav.addObject("medicos", medicoRepository.findAll());
        mav.addObject("succesMensagem", "Médico cadastrado com sucesso!");
        mav.setViewName("medicos/listMedico");

        return mav;
    }
}
