package controllers;

import java.util.Comparator;
import models.assessmentDetails;

public class DateSorter implements Comparator<assessmentDetails>
{
    @Override
    public int compare(assessmentDetails o1, assessmentDetails o2)
    {
        return o2.getFormattedDate().compareToIgnoreCase(o1.getFormattedDate());
    }

}

// https://howtodoinjava.com/java/collections/arraylist/arraylist-sort-objects-by-field/