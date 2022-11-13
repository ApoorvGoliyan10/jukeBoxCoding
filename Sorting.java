import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Sorting {

    public void searchingSong(Connection con,int user_id )
    {
        Scanner sc=new Scanner(System.in);
        try {
            System.out.println("Enter the song Name ");
            String song_name = sc.nextLine();


            String q2 = "SELECT * FROM songs where name=? ;";
            PreparedStatement ps2 = con.prepareStatement(q2);
            ps2.setString(1, song_name);
            ResultSet res = ps2.executeQuery();
            while(res.next()) {

                System.out.println("Song's Id  :  "+res.getInt(1)+"     "+"Song Name  : "+res.getString(2)+
                        "     "+" Song  Duration  : "+res.getString(3));
            }
            //Asking for playing

            System.out.println("want to play this song ");
            String ans=sc.next();
            if(ans.equalsIgnoreCase("y")) {

                String q3 = "select filepath from songs where name='" + song_name + "';";
                Statement stmt = con.createStatement();
                ResultSet rs1 = stmt.executeQuery(q3);
                String filepath = null;
                while (rs1.next()) {
                    filepath = rs1.getString(1);
                }

                Play pl = new Play(filepath);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void searchingPodcast(Connection con ,int user_id)
    {
        Scanner sc=new Scanner(System.in);
        try {
            System.out.println("Enter the Podcast Name ");
            String podcast_name = sc.nextLine();


            String q2 = "SELECT * FROM podcast where name=? ;";
            PreparedStatement ps2 = con.prepareStatement(q2);
            ps2.setString(1, podcast_name);
            ResultSet res = ps2.executeQuery();
            while(res.next()) {

                System.out.println("Podcast's Id :  "+res.getInt(1)+"     "+"podcast Name  : "+res.getString(2)+
                        "     "+"podcast  Duration  : "+res.getString(3));
            }
               //Asking for play
            System.out.println("want to play this podcast :  ");
            String ans=sc.next();
            if(ans.equalsIgnoreCase("y")) {

                String q3 = "select filepath from podcast where name='" + podcast_name + "';";
                Statement stmt = con.createStatement();
                ResultSet rs1 = stmt.executeQuery(q3);
                String filepath = null;
                while (rs1.next()) {
                    filepath = rs1.getString(1);
                }

                Play pl = new Play(filepath);
            }





        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

