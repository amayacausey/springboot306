package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {


    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("directors",directorRepository.findAll());
        return "index";
    }
    @RequestMapping("/addDirectors")
    public String addDirector(Model model) {
        model.addAttribute("director",new Director());
        return "addDirectors";
    }


    @RequestMapping("/updateDirector/{id}")
    public  String updateDirector(Model model , @PathVariable("id") long id){
        model.addAttribute("director" ,directorRepository.findById(id).get());
        return "addDirectors";
    }

    @PostMapping("/processDirector")
    public String processDirector(@ModelAttribute Director director){
        directorRepository.save(director);
        return "redirect:/";
    }
    @RequestMapping("/addMovies")
    public String addMovies(Model model){
        model.addAttribute("movie", new Movie());
        model.addAttribute("directors",directorRepository.findAllByIdGreaterThanOrderByName(0));
        return "addMovies";
    }
    @PostMapping("/processMovie")
    public String proccessMovie(@ModelAttribute Movie movie,@RequestParam("directorid")long directorid){
        Director director= directorRepository.findById(directorid).get();
        movie.setDirector(director);
        movieRepository.save(movie);
        return "redirect:/";
    }
    @RequestMapping("/deleteMovie/{id}")
    public String deleteMovie (@PathVariable("id") long id){
    movieRepository.deleteById(id);
    return "redirect:/";
    }
    @RequestMapping("/updateMovie/{id}")
    public String updateMovie (@PathVariable("id") long id,Model model){
        model.addAttribute("movie",movieRepository.findById(id).get());
        model.addAttribute("directors",directorRepository.findAll());
        return "addMovies";
    }




    @RequestMapping("/detailsDirectors")
    public String detailsDirectors(Model model) {
        model.addAttribute("directors",directorRepository.findAll());
        return "detailsDirectors";
    }
    @RequestMapping("/detailsMovies")
    public String detailsMovies(Model model) {
        model.addAttribute("directors",directorRepository.findAll());
        return "detailsMovies";

        }
    @RequestMapping("/deleteDirectors/{id}")
    public String deleteDirectors(@PathVariable("id")long id)
    {
        directorRepository.deleteById(id);
        return "redirect:/";
    }

}
