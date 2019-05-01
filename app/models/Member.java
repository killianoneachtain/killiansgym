package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static models.Analytics.*;
import static play.templates.JavaExtensions.capFirst;

@Entity
public class Member extends Model
{
    public String firstname;
    public String lastname;
    public String gender;
    public String email;
    public String password;
    public String address;
    public int height;
    public float startingWeight;

    public int numberOfAssessments;

    public float currentWeight;

    public String trend;


    public String getTrend() { return this.trend; }

    public void setTrend(String trend) {this.trend = trend;}



    public String getFirstname() {
        return capFirst(firstname);
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() { return capFirst(lastname); }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) { this.gender = capFirst(gender); }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getStartingWeight() {
        return this.startingWeight;
    }

    public void setStartingWeight(float startWeight) {
        this.startingWeight = toTwoDecimalPlaces(startWeight);
    }

    public int getNumberOfAssessments() { return numberOfAssessments;  }

    public float getCurrentWeight() { return currentWeight; }

    public void setCurrentWeight(float currentWeight) { this.currentWeight = currentWeight; }

    public void addToAssessmentsCount()
    {
        this.numberOfAssessments++;
    }

    public void subtractFromAssessmentsCount() { this.numberOfAssessments--;}

    public List<assessmentDetails> getAssessmentdetailslist() {
        return assessmentdetailslist;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<assessmentDetails> assessmentdetailslist = new ArrayList<assessmentDetails>();



    public Member(String firstname, String lastname, String gender, String email, String password,
                  String address, int height, float startingWeight)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.address = address;
        this.height = height;
        this.startingWeight = toTwoDecimalPlaces(startingWeight);
    }



    private float checkWeight(ArrayList<assessmentDetails> assessments, Member member)
    {
        if(assessments.isEmpty())
        {
            return member.getStartingWeight() ;
        }
        else
            return (assessments.get(0)).getWeight();
    }



    public static Member findByEmail(String email)
    {
        return find("email", email).first();
    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }


}