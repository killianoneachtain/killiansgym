package controllers;

import models.Member;
import models.assessmentDetails;

import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class TrainerCtrl extends Controller
{
    public static void index() {
        Logger.info("Rendering Trainer Dashboard");

        List<Member> memberList = Member.findAll();

        render("trainer.html", memberList);
    }

    public static void displayMembers(Long id)
    {
        Logger.info("Rendering Member Details for Trainer");
        Member member = Member.findById(id);
        List<assessmentDetails> assessmentDetailsList = member.assessmentdetailslist;
        assessmentDetailsList.sort(new DateSorter());

        render("trainer-member.html", member, assessmentDetailsList);
    }

    public static void addComment(long id,String comment)
    {

        Logger.info("Adding comment : " + comment);
        assessmentDetails assessment = assessmentDetails.findById(id);
        assessment.setComment(comment);
        assessment.save();


        redirect("/trainer" );
    }

    public static void deleteMember(Long id)
    {
        Member member = Member.findById(id);
        Logger.info("Deleting Member : " + member.id);
        List<Member> memberList = Member.findAll();

        memberList.remove(member);
        member.delete();
        render("trainer.html", memberList);
    }

}