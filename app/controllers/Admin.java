package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Member;
import models.assessmentDetails;

import play.Logger;
import play.mvc.Controller;

public class Admin extends Controller
{
    public static void index()
    {
        Logger.info("Rendering Admin");
        List<assessmentDetails> assessmentdetails = assessmentDetails.findAll();
        Member member = Accounts.getLoggedInMember();
        Logger.info(String.valueOf(member.firstname));
        List<Member> memberDetails = member.findAll();


        render ("admin.html", assessmentdetails, memberDetails);
    }
}