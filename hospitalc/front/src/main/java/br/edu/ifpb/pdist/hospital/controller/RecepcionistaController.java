package br.edu.ifpb.pdist.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/recepcionista")
public class RecepcionistaController {
    
    // Ativa o menu Recepcionista na barra de navegação
    @ModelAttribute("menu")
    public String activeMenu(){
        return "recepcionista";
    }

    // Rota para acessar a lista pelo menu
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listAll(ModelAndView mav) {
        mav.setViewName("erros/404");
        return mav;
    }
}
