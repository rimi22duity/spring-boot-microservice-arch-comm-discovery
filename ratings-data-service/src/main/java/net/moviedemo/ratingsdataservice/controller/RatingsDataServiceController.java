package net.moviedemo.ratingsdataservice.controller;


import net.moviedemo.ratingsdataservice.db.Ratings;
import net.moviedemo.ratingsdataservice.responseData.UserRatings;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsDataServiceController {

    @RequestMapping("/{movieId}")
    public Ratings getRatingData(@PathVariable String movieId) {
        return new Ratings(movieId, 4);
    }

    @RequestMapping("users/{userId}")
    public UserRatings getUserRatings(@PathVariable("userId") String userId) {
        List<Ratings> ratings = Arrays.asList(
                new Ratings("1234", 4),
                new Ratings("5678", 3)
        );

        UserRatings userRatings = new UserRatings();
        userRatings.setUserRatings(ratings);

        return userRatings;
    }

}
