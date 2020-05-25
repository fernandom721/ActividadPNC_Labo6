package com.uca.capas.controller;

import com.uca.capas.dao.EstudianteDAO;
import com.uca.capas.domain.Estudiante;
import com.uca.capas.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private EstudianteDAO estudianteDAO;
    @Autowired
    private EstudianteService estudianteService;


    @RequestMapping("/estudiante")
    public ModelAndView initMain(){
        ModelAndView mav = new ModelAndView();
        List<Estudiante>estudiantes = null;
        try {
            estudiantes = estudianteService.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        mav.addObject("estudiantes",estudiantes);
        mav.setViewName("main");
        return mav;
    }

    @RequestMapping(value = "/mostrarEstudiante", method = RequestMethod.POST)
    public ModelAndView findOne (@RequestParam(value = "codigo") int id){
        ModelAndView mav = new ModelAndView();
        Estudiante estudiante = null;
        try {
            estudiante = estudianteService.findOne(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        mav.addObject("estudiante",estudiante);
        mav.setViewName("estudiante");
        return mav;
    }

    @PostMapping("/save")
    public ModelAndView guardar(@Valid @ModelAttribute Estudiante estudiante, BindingResult result){
        ModelAndView mav = new ModelAndView();
        if(result.hasErrors()){
            mav.setViewName("agregarEstudiante");
        }else {
            estudianteService.save(estudiante);
            List<Estudiante> estudiantes=null;
            try {
                estudiantes = estudianteService.findAll();
            }catch (Exception e){
                e.printStackTrace();
            }
            mav.addObject("estudiante",estudiantes);
            mav.setViewName("estudiante");
        }
        return mav;
    }

    @RequestMapping(value = "/borrarEstudiante", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "codigo")int id){
        ModelAndView mav = new ModelAndView();
        List<Estudiante>estudiantes=null;
        try {
            estudiantes = estudianteService.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        mav.addObject("estudiante",estudiantes);
        mav.setViewName("estudiante");
        return mav;
    }

    @GetMapping("/insertarEstudiante")
    public ModelAndView inicio(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("estudiante",new Estudiante());
        mav.setViewName("agregarEstudiante");
        return mav;
    }


    @RequestMapping("listado")
    public ModelAndView Listado() {
        ModelAndView mav = new ModelAndView();
        List<Estudiante> estudiantes = null;
        try {
            estudiantes = estudianteDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("estudiantes", estudiantes);
        mav.setViewName("listado");
        return mav;
    }

    @RequestMapping("ingresarEstudiante")
    public ModelAndView findOne(@Valid() @ModelAttribute() Estudiante estudiante, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            mav.setViewName("index");
            return mav;
        }
        try {
            estudianteDAO.insert(estudiante);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("estudiante", new Estudiante());
        mav.setViewName("index");
        return mav;
    }
}