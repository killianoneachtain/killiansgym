package models;

import play.db.jpa.Model;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static models.Analytics.*;


@Entity
public class assessmentDetails extends Model
{
    public float weight;
    public float chest;
    public float thigh;
    public float upperArm;
    public float waist;
    public float hips;

    public String comment;

    public void setTrend(String trend)
    {
        this.trend = trend;
    }

    public String getTrend() { return this.trend; }

    public String trend;

    public LocalDateTime assessmentDateTime;
    public String formattedDate;


    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getComment() { return this.comment; }

    public void setComment(String comment) { this.comment = comment;  }

    public String getFormattedDate() { return formattedDate;  }

    public void setAssessmentTime(LocalDateTime assessmentDateTime)
    {
        this.assessmentDateTime = assessmentDateTime;
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss ");
        formattedDate = assessmentDateTime.format(myFormatObj);
    }

    public assessmentDetails()
    {

    }

    public assessmentDetails(float weight, float chest, float thigh, float upperArm,
                             float waist, float hips)
    {
        this.weight = toTwoDecimalPlaces(weight);
        this.chest = toTwoDecimalPlaces(chest);
        this.thigh = toTwoDecimalPlaces(thigh);
        this.upperArm = toTwoDecimalPlaces(upperArm);
        this.waist = toTwoDecimalPlaces(waist);
        this.hips = toTwoDecimalPlaces(hips);
    }


}