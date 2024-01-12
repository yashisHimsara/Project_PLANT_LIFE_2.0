package lk.ijse.plantLife.utill;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.PlaybackListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlarmPlayer {

    public void playAlarmAtTime(String time) {
        try {
            // Validate the time format
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            sdf.setLenient(false);
            Date alarmTime = sdf.parse(time);

            // Get the current time
            Date currentTime = new Date();
            currentTime = sdf.parse(currentTime.getHours() +":"+currentTime.getMinutes());
            System.out.println(currentTime.getHours() +":"+currentTime.getMinutes());
            System.out.println(alarmTime.getHours() +" : "+alarmTime.getMinutes());
            //System.out.println(alarmTime.getTime());
            // Calculate the time difference in milliseconds
            long timeDifference = alarmTime.getTime() - currentTime.getTime() ;
            System.out.println(timeDifference);
            // Check if the alarm time is in the future
            if (timeDifference > 0) {
                // Wait until the specified time to play the alarm
                //Thread.sleep(timeDifference);
                Thread.sleep(timeDifference);

                // Specify the path to your sound file
                String soundFilePath = "C:\\Users\\User\\Desktop\\Project Plant Life\\src\\main\\resources\\audio\\archivo.mp3";
                //URL resource = getClass().getResource(soundFilePath);

//                System.out.println(resource.toExternalForm());
                playMp3(soundFilePath);
                // Get the audio input stream from the sound file


                // Close the clip after playing

            } else {
                // The specified time is in the past
                //JOptionPane.showMessageDialog(null, "Invalid time. Please enter a future time.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ParseException | InterruptedException e) {
            e.printStackTrace();
            System.out.println(e);
            // Handle parsing and interruption exceptions
            //JOptionPane.showMessageDialog(null, "Invalid time format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions
            //JOptionPane.showMessageDialog(null, "Error playing the alarm.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void playMp3(String mp3FilePath) throws JavaLayerException {
        try (FileInputStream fileInputStream = new FileInputStream(mp3FilePath)) {
            Bitstream bitstream = new Bitstream(fileInputStream);

            int duration = (int) bitstream.readFrame().max_number_of_frames(1);
            fileInputStream.close();

            FileInputStream fileInputStream2 = new FileInputStream(mp3FilePath);
            Player player = new Player(fileInputStream2);
            System.out.println(player.isComplete());
            player.play(800);
            System.out.println(player.getPosition());
            System.out.println(duration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
