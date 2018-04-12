
package drone_mini;

import static drone_mini.Drone_Mini.loc_arr;
import static drone_mini.Drone_Mini.transport_name;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tourist_Details implements Transport {

    int t_id = ++Drone_Mini.id;
    String tname, phno;
    ArrayList<String> loc = new ArrayList<>();
    ArrayList<String> transport_choice = new ArrayList<>();
    
    int spot_no_old;
    String inputString;
    boolean check = true;
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public void add() throws IOException {
        System.out.println("Enter Name: ");
        inputString = input.readLine();
        tname = inputString;

        System.out.println("Enter Phone No.: ");
        inputString = input.readLine();
        phno = inputString;

        System.out.println("Welcome to Cyber City!");
    }

    public void Choose_Spot() throws IOException {
        //System.out.println(move);
        //System.out.println(spot_no_old);
        check = true;
        while (check) {
            System.out.println("Choose your preferred Location Type:");

            for (int i = 0; i < loc_arr.size(); i++) {
                System.out.println((i + 1) + ". " + loc_arr.get(i).name);
            }
            inputString = input.readLine();
            int choice1 = Integer.parseInt(inputString);
            choice1--;
            System.out.println("The city offers:");
            for (int i = 0; i < loc_arr.get(choice1).spot.size(); i++) {
                System.out.println((i + 1) + ". " + loc_arr.get(choice1).spot.get(i).name+
                        " ("+loc_arr.get(choice1).spot.get(i).opening_time+" - "+
                        loc_arr.get(choice1).spot.get(i).closing_time+")");
            }
            System.out.println("Choose your preffered location: ");
            inputString = input.readLine();
            int choice2 = Integer.parseInt(inputString);
            choice2--;

            int spot_no_new = loc_arr.get(choice1).spot.get(choice2).spot_no;
            //System.out.println(num);

            //int choice11 = 0, choice22 = 0;
            if (loc.isEmpty()) {
                spot_no_old = 0;
                Drone_Mini.distance_webcam = Drone_Mini.distance[spot_no_old][spot_no_new];
            } else {

                Drone_Mini.distance_webcam = Drone_Mini.distance[spot_no_old][spot_no_new];
                //System.out.println(spot_no_old);
                //System.out.println(Drone_Mini.distance_webcam);
            }
            if (Drone_Mini.distance_webcam == 0) {
                System.out.println("You Choosed your current location! Find a new place :D");
            } else {
                required();
                Drone_Mini.count_webcam = 0;
                MotionDetector.mainone();//Check
                String current_Location = loc_arr.get(choice1).spot.get(choice2).name;
                loc.add(current_Location);
                spot_no_old = spot_no_new;
                System.out.println("Do you wish to visit another Location?(Y/N):");
                inputString = input.readLine();
                inputString = inputString.toUpperCase();
                if (inputString.equals("N")) {
                    check = false;
                }
            }

        }
    }

    @Override
    public void required() {
        try {
            System.out.println("Choose your Transport Mode: ");
            for (int i = 0; i < transport_name.length; i++) {
                System.out.println((i + 1) + ". " + transport_name[i]);
            }
            inputString = input.readLine();
            int choice = Integer.parseInt(inputString);
            transport_choice.add(transport_name[choice - 1]);
            
            System.out.println("Your Transport mode has been Chosen!\nYou can start moving!");
        } catch (IOException ex) {
            Logger.getLogger(Tourist_Details.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
