package com.api.consumindoApi.controller;

import com.api.consumindoApi.models.Cep;
import com.api.consumindoApi.models.dto.CepDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ControllerCep {


    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/cep")
    public ModelAndView consultaCep(CepDto cepDto, RedirectAttributes redirectAttributes) throws Exception{
        ModelAndView mv = new ModelAndView("exibirCep");

        RestTemplate restTemplate = new RestTemplate();
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplate = restTemplateBuilder.build();

        try {

            Cep cep1 = restTemplate.getForObject("https://viacep.com.br/ws/" + cepDto.cep() + "/json/", Cep.class);
            mv.addObject("cep", cep1);
            return mv;
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("msgErro", "Cep não existe");
            return new ModelAndView("redirect:/");
        }
    }


}
