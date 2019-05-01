package controllers;

import models.Member;

import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Profile extends Controller
{
    public static void index(Long id)
    {
        Member member = Member.findById(id);
        Logger.info("Rendering Profile");

        render ("profile.html",member);
    }

}

