package net.moviedemo.moviecatalogservice.responseData;

import java.util.List;

public class UserRatings {

    private List<Ratings> userRatings;

    public List<Ratings> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(List<Ratings> userRatings) {
        this.userRatings = userRatings;
    }
}
