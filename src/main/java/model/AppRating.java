package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "[pizzeria].[dbo].[app_rating]")
public class AppRating extends AbstractModel {
    @Column(nullable = false)
    private double rating;

    @Column(nullable = false)
    private long rateTimestamp;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public long getRateTimestamp() {
        return rateTimestamp;
    }

    public void setRateTimestamp(long rateTimestamp) {
        this.rateTimestamp = rateTimestamp;
    }
}
