package drone_mini;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Drone_Mini {

    static int id = 0;
    static ArrayList<Tourist_Details> tour = new ArrayList<>();
    static ArrayList<loc_type> loc_arr = new ArrayList<>();
    static String transport_name[] = {"Auto", "Bus", "Cab"};
    static int distance[][] = new int[9][9];
    static int count_webcam = 0, distance_webcam = 0;

    public static void main(String[] args) throws IOException, Exception {
        input_spots();
        input_distance();

        while (true) {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Welcome!");
            System.out.println("Choose your action:\n1.New Tourist Login\n2.Registered Tourist\n3.Cop\n4.Agent\n5.Drone Rest");
            String inputString = input.readLine();
            int choice = Integer.parseInt(inputString);
            switch (choice) {
                case 1:
                    Tourist_Details t = new Tourist_Details();
                    t.add();
                    t.Choose_Spot();
                    tour.add(t);
                    //System.out.println(tour.get(0).loc.get(0));

                    break;
                case 2:
                    System.out.println("Enter your Tourist ID");
                    inputString = input.readLine();
                    int id_value = Integer.parseInt(inputString);
                    id_value--;
                    //System.out.println(id_value);
                    //System.out.println(tour.get(0).t_id);
                    tour.get(id_value).Choose_Spot();
                    //System.out.println(tour.get(0).loc.get(0));
                    //System.out.println(tour.get(0).loc.get(1));
                    break;
                case 3:
                    while(true){
                    System.out.println("Cop!");
                    System.out.print("Enter Date in yyyy-mm-dd: ");
                    inputString = input.readLine();
                    inputString=inputString.concat("_");
                    System.out.print("Enter Time in HH-MM: ");
                    inputString = inputString.concat(input.readLine());
                    inputString=inputString.concat("_");
                    System.out.print("Enter SuspectID: ");
                    int id=Integer.parseInt(input.readLine());
                    inputString=inputString.concat(""+id);
                    //System.out.println(inputString);
                    new ShowImage(inputString);
                    System.out.print("View Another suspect?(Y/N): ");
                    String suspect=input.readLine();
                    suspect=suspect.toUpperCase();
                    if(suspect.equals("N")){
                        break;
                    }
                    }
                    while(true){
                        System.out.print("\nView a Suspect's Location History?(Y/N): ");
                        inputString = input.readLine();
                    inputString = inputString.toUpperCase();
                    if(inputString.equals("Y")){
                        int val=display_tourist();
                        if(val!=0){
                            System.out.print("Enter tourist ID: ");
                            inputString = input.readLine();
                    id = Integer.parseInt(inputString);
                    id--;
                    location_history(id);
                        }
                    System.out.print("\nFind Suspect's Exact Location?(Y/N): ");
                    inputString = input.readLine();
                    inputString = inputString.toUpperCase();
                    if(inputString.equals("Y")){
                        for(int i=0;i<loc_arr.size();i++){
                            for(int j=0;j<loc_arr.get(i).spot.size();j++){
                                System.out.println(loc_arr.get(i).spot.get(j).name+" (Pincode) "+loc_arr.get(i).spot.get(j).pincode);
                            }
                        }
                        System.out.println("Enter City with PinCode: ");
                        inputString = input.readLine();
                        geo.mainone(inputString);
                    }
                    }
                    else
                        break;
                    }
                    break;
                case 4:
                    System.out.println("Take Snap of any suspected activity.");
                    System.out.println("Get Started!");
                    TakeSnapshot.mainone();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter Correct Choice!");

            }
        }
    }

    private static void input_spots() {
        loc_type x = new loc_type("Adventure");
        loc_arr.add(x);
        Spots a = new Spots("Amarpali", "10:30 AM", "07:00 PM", 1,226005);
        loc_arr.get(0).spot.add(a);
        a = new Spots("Dream World", "11:00 AM", "07:30 PM", 2,226012);
        loc_arr.get(0).spot.add(a);

        x = new loc_type("Tourist Spots");
        loc_arr.add(x);
        a = new Spots("Bada Imambara", "06:00 AM", "07:00 PM", 3,226003);
        loc_arr.get(1).spot.add(a);
        a = new Spots("Regional Science City", "09:30 AM", "5:30 PM", 4,226024);
        loc_arr.get(1).spot.add(a);

        x = new loc_type("Restaurants");
        loc_arr.add(x);
        a = new Spots("Dastarkhwan(Mughlai)", "04:00 PM", "10:00 PM", 5,226001);
        loc_arr.get(2).spot.add(a);
        a = new Spots("Barbeque Nation(Chinese)", "12:00 PM", "10:30 PM", 6,226016);
        loc_arr.get(2).spot.add(a);

        x = new loc_type("Supermarts");
        loc_arr.add(x);
        a = new Spots("Spencer's", "11:00 AM", "11:00 PM", 7,226010);
        loc_arr.get(3).spot.add(a);
        a = new Spots("Big Bazaar", "11:30 AM", "10:00 PM", 8,226008);
        loc_arr.get(3).spot.add(a);
    }

    private static void input_distance() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(i==j){
                    distance[i][i] = 0;
                }
                else
                    distance[i][j] = i + j;
            }
        }
    }

    private static int display_tourist() {
        if(!tour.isEmpty()){
            for(int i=0;i<tour.size();i++){
            System.out.println(tour.get(i).t_id+" "+tour.get(i).tname+" "+tour.get(i).phno);
        }
            return 1;
        }
        else{
            System.out.println("There are no Tourist Logins yet!");
            return 0;
        }
    }

    private static void location_history(int id) {
        System.out.println("Starting");
        for(int i=0;i<tour.get(id).loc.size();i++){
            System.out.print("--> "+tour.get(id).loc.get(i));
        }
        System.out.println();
        System.out.println("Transport Mode Used: ");
        System.out.println("Entered Cyber City! Then: ");
        for(int i=0;i<tour.get(id).transport_choice.size();i++){
            System.out.print("--- By "+tour.get(id).transport_choice.get(i));
        }
    }

}
