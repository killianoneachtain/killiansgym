package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Analytics extends Model
{
    @OneToMany(cascade = CascadeType.ALL)
    public List<Member> memberdetails = new ArrayList<Member>();

    public static float toTwoDecimalPlaces(double num)
    {
        return (float) ((int) (num *100 ) /100.0);
    }

    public static float calcIdealBodyWeight(int height, String gender)
    {
        if (gender.equals("Male"))
        {
            double idealBodyWeight;
            idealBodyWeight = 50.00 + (((height - 152.4) / 2.54) * 2.3);
            return toTwoDecimalPlaces(idealBodyWeight);
        }
        else if ((gender.equals("Female")) || (gender.equals("")))
        {
            double idealBodyWeight = 45.5 + (((height - 152.4) / 2.54) * 2.3);
            return toTwoDecimalPlaces(idealBodyWeight);
        }
        else
            return 0;
    }

    private static String iwiIconColour(float idealWeight, float currentWeight)
    {
        if((currentWeight >= (idealWeight-2)) && (currentWeight <= (idealWeight + 2)))
        {
            return "green";
        } else {
            return "red";
        }

    }

    public static String iconColour(String verdict)
    {
        if(verdict.equals("Severe Thinness"))
        {
            return "violet";
        }
        else
        if(verdict.equals("Moderate Thinness"))
        {
            return "blue";
        } else
        if(verdict.equals("Mild Thinness"))
        {
            return "teal";
        } else
        if(verdict.equals("Normal"))
        {
            return "green";
        } else
        if(verdict.equals("Overweight"))
        {
            return "pink";
        } else
        if(verdict.equals("Obese Class I"))
        {
            return "yellow";
        } else
        if(verdict.equals("Obese Class II"))
        {
            return "orange";
        } else
        if(verdict.equals("Obese Class III"))
        {
            return "red";
        } else return "olive";

    }

    public static float getBMI(float weight, int height)
    {
        float v = toTwoDecimalPlaces(((weight/height/height) * 10000));
        return v;
    }

    public static String assessBMI(float weight, int height)
    {
        float BMI = getBMI(weight, height);
        int BMIIndex = 0;
        String verdict="No Verdict";


        if((BMI >0) &&(BMI <= 16))
        {
            BMIIndex = 1;
        }

        if((BMI > 16) && (BMI <=17))
        {
            BMIIndex = 2;
        }

        if((BMI >17) && (BMI <=18.5))
        {
            BMIIndex = 3;
        }

        if((BMI > 18.5) && (BMI <=25))
        {
            BMIIndex = 4;
        }

        if((BMI > 25) && (BMI <=30))
        {
            BMIIndex = 5;
        }

        if((BMI > 30) && (BMI <=35))
        {
            BMIIndex = 6;
        }

        if((BMI > 35) && (BMI <=40))
        {
            BMIIndex = 7;
        }

        if(BMI > 40)
        {
            BMIIndex = 8;
        }

        switch (BMIIndex) {
            case 1:     verdict = "Severe Thinness";

                break;

            case 2:     verdict = "Moderate Thinness";
                break;

            case 3:     verdict="Mild Thinness";
                break;

            case 4:     verdict="Normal";
                break;

            case 5:     verdict="Overweight";
                break;

            case 6:     verdict="Obese Class I";
                break;

            case 7:     verdict="Obese Class II";
                //return verdict;
                break;

            case 8:     verdict="Obese Class III";
                break;

        }

        return verdict;
    }

    public static String checkGender(String gender)
    {
        if((gender.equals("Male")))
        {
            return "blue";
        } else if ((gender.equals("Female")))
        {
            return "pink";
        } else
        {
            return "olive";
        }
    }


}
