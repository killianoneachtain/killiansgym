package controllers;

import models.Member;
import models.assessmentDetails;
import play.Logger;
import play.mvc.Controller;

import java.time.LocalDateTime;
import java.util.List;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<assessmentDetails> assessmentDetailsList = member.assessmentdetailslist;
    assessmentDetailsList.sort(new DateSorter());

    //Here is where we read the weights by index into and set the trend by a for loop through
    // the member.assessmentdetailslist
    if(member.assessmentdetailslist.size() >1)
    {
      for (int i =0;i< (member.assessmentdetailslist.size()) - 1; i++)
      {
        if (member.assessmentdetailslist.get(i).getWeight() > member.assessmentdetailslist.get(i + 1).getWeight())
        {
          member.assessmentdetailslist.get(i).setTrend("red");
          member.assessmentdetailslist.get(i).save();
        } else
        if (member.assessmentdetailslist.get(i).getWeight() <= member.assessmentdetailslist.get(i + 1).getWeight())
        {
          member.assessmentdetailslist.get(i).setTrend("olive");
          member.assessmentdetailslist.get(i).save();
        }
          else
        {
          member.assessmentdetailslist.get(i).setTrend("blue");
        }

      }
      // This sets the oldest(last) assessment in the list to blue
      member.assessmentdetailslist.get((member.assessmentdetailslist.size())-1).setTrend("blue");
      member.assessmentdetailslist.get((member.assessmentdetailslist.size())-1).save();
    }
    if(member.assessmentdetailslist.size() == 1)
    {
        member.assessmentdetailslist.get(0).setTrend("blue");
        member.assessmentdetailslist.get(0).save();
    }

    render ("dashboard.html", member, assessmentDetailsList);
  }

  public static void addAssessmentDetails(float weight, float chest, float thigh,
                                          float upperArm, float waist, float hips)
  {
    Member member = Accounts.getLoggedInMember();
    assessmentDetails assessmentdetails = new assessmentDetails(weight,chest,thigh,upperArm,waist,hips);
    member.assessmentdetailslist.add(assessmentdetails);
    assessmentdetails.setAssessmentTime(LocalDateTime.now());
    member.addToAssessmentsCount();
    member.setCurrentWeight(weight);
    member.save();

    Logger.info("Adding Assessment Details: " + weight +", " + chest +", "
    + thigh +", " + upperArm +", " + waist +", " + hips +"." + "Making Current Weight " + weight);
    redirect("/dashboard");
  }

  public static void viewProfile()
  {
    Member member = Accounts.getLoggedInMember();
    render("profile.html", member);
  }

  public static void editProfile()
  {
    Member member = Accounts.getLoggedInMember();
    render("editprofile.html",member);
  }

  public static void deleteAssessment(Long id)
  {
    Member member = Accounts.getLoggedInMember();
    List<assessmentDetails> assessmentDetailsList = member.assessmentdetailslist;

    assessmentDetails assessment = assessmentDetails.findById(id);

    member.assessmentdetailslist.remove(assessment);
    member.subtractFromAssessmentsCount();
    member.save();
    assessment.delete();
    member.setCurrentWeight(member.assessmentdetailslist.get(0).getWeight());


    redirect ("/dashboard");
  }

  public static void saveProfile(String firstname, String lastname, String gender,
                                 String email, String address, String password,
                                 int height, float currentWeight)
  {
    Member member = Accounts.getLoggedInMember();
    member.setFirstname(firstname);
    member.setLastname(lastname);
    member.setGender(gender);
    member.setEmail(email);
    member.setPassword(password);
    member.setAddress(address);
    member.setHeight(height);
    member.setCurrentWeight(currentWeight);
    member.save();

    render("profile.html", member);

  }
}
