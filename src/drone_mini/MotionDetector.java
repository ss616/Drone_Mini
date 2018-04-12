/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drone_mini;

import java.io.IOException;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamMotionEvent;
import com.github.sarxos.webcam.WebcamMotionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MotionDetector implements WebcamMotionListener {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static String inputString;
    WebcamMotionDetector detector = new WebcamMotionDetector(Webcam.getDefault());
	public MotionDetector() {
		//System.out.println("In Constructor");
		detector.setInterval(1000); // one check per 1000 ms
		detector.addMotionListener(this);
		detector.start();
        }
	@Override
	public void motionDetected(WebcamMotionEvent wme) {
		System.out.println("||||");
                Drone_Mini.count_webcam++;
                if(Drone_Mini.count_webcam==Drone_Mini.distance_webcam){
                    detector.stop();
                    try {
                        MotionDetector.mainone();//check
                    } catch (IOException ex) {
                        Logger.getLogger(MotionDetector.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
	}

	   public static void mainone() throws IOException {
        //System.out.println("Reached in main");
        //System.out.println(Drone_Mini.count_webcam==Drone_Mini.distance_webcam);
        if(Drone_Mini.count_webcam==Drone_Mini.distance_webcam){
            //System.out.println("Reached closing");
            System.out.println("You have reached!");
            System.out.print("Rate our transport service: ");
            //input.close();
            return;
        }
        else{
            new MotionDetector();
            //System.in.read(); // keep program open
            inputString=input.readLine();
           }
     }
}


