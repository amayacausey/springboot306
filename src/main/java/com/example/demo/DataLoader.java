package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    MovieRepository movieRepository;


    @Override
    public void run(String... args) throws Exception {
        Director director = new Director();

        director.setName("Stephen Bullock");
        director.setGenre("SciFi");

        Movie movie= new Movie();
        movie.setTitle("Star Movie");
        movie.setYear(2017);
        movie.setDescription("About stars...");

        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);

        Movie movie2=new Movie();
        movie2.setTitle("Deathstar Ewoks");
        movie2.setYear(2011);
        movie2.setDescription("About Ewoks on the Deathstar...");
        movies.add(movie2);


        director.setMovies(movies);


        directorRepository.save(director);
        movie.setDirector(director);
        movie2.setDirector(director);

        movieRepository.save(movie);
        movieRepository.save(movie2);

    }
}
