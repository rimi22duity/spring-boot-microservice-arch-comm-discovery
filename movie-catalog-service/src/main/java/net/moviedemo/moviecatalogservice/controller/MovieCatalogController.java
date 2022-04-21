package net.moviedemo.moviecatalogservice.controller;


import net.moviedemo.moviecatalogservice.db.CatalogItem;
import net.moviedemo.moviecatalogservice.responseData.Movie;
import net.moviedemo.moviecatalogservice.responseData.UserRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalogItems(@PathVariable("userId") String userId ) {

        // get all rated movie IDs
        UserRatings ratings = restTemplate.getForObject("http://ratings-data-service/ratings/users/" + userId, UserRatings.class);

        return ratings.getUserRatings().stream().map(rating -> {
            //API calling by RestTemplate Library.
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

            //API calling by WebClient Library.
            /**
                Movie movie = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8082/movies/" + rating.getMovieId())
                        .retrieve()
                        .bodyToMono(Movie.class)
                        .block();
            **/
            return new CatalogItem(movie.getName(), "test Desc", rating.getRating());
        }).collect(Collectors.toList());

    }
}
