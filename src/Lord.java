import java.util.LinkedList;
import java.util.*;
/**
 * Created by Me on 3/17/2017.
 */
public class Lord implements Comparable<Lord>{

    static final double TAX_FACTOR = .9;
    static final double DEMENSE_SIZE = 5;

    double planets = 0;
    double ships = 0;
    double income = 0;
    int rank;
    boolean isLoyal = true;

    double fleet = 0;
    Lord liege;
    LinkedList<Lord> court = new LinkedList<Lord>();




    public Lord(Lord owner, double startP, int myRank){
        liege = owner;
        planets = startP;
        rank = myRank;
    }

    public void spendIncome(){
        planets += income/2;
        ships += income/2;
        if (planets > DEMENSE_SIZE){
            court.add(new Lord(this,(planets-DEMENSE_SIZE),rank+1));
            planets = DEMENSE_SIZE;
        }

        for (Lord l: court){
            l.spendIncome();
        }
    }

    public double getIncome(){

        for (Lord l: court){
                l.getIncome();
        }
        income = planets;

        for (Lord l: court){
            if(isLoyal){
                income += ((1-TAX_FACTOR)*l.income);
                l.reduceIncome();
            }
        }

        return income;
    }

    private void reduceIncome(){
        income = TAX_FACTOR*income;
    }

    public double  updateLoyalty(){

        for (Lord l: court){
            l.updateLoyalty();
        }

        Collections.sort(court,new LordCompare());

        for(Lord l: court){
            if(this.compareTo(l) > ){

            }
        }

        return income;
    }

    @Override public int compareTo(Lord other){
        if(other.fleet == this.fleet){
            return 0;
        }else if(this.fleet>other.fleet){
            return 1;
        }else{
            return -1;
        }
    }

    @Override public String toString(){
        String output = "\n";

        for(int i = 0; i<rank; i++){
            output += "\t";
        }
        output +=  "Planets: " + this.planets + " Income: " + this.income + " Ships: " + this.ships + " Rank: " + this.rank;
        for (Lord l: court){
            output += l.toString();
        }
        return (output);
    }

}
//spend
/*loyalty & fleet
0. let my court get their fleet#
1. list all of your vassals from smallest fleet to largest
2. go through each member left to right. see if they are loyal, then add thier troops to your fleet
*/
//tax