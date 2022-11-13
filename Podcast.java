import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Podcast
{
    public void newcustomer(Connection con,int user_id)
    {
        Scanner sc=new Scanner(System.in);

        try {

            Statement stmt = con.createStatement();
            String query = "SELECT * FROM jukebox.podcast order by name ;";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("Podcast's Id :  "+rs.getInt(1)+"    "+"Podcast Name  : "+rs.getString(2)+"    "+"Song  Duration  : "+rs.getString(3)+
                        "   podcaster Name   :  "+rs.getString(5));
            }

            System.out.println("Which Podcast you want to play  Enter it's id :  ");

            int podcast_id = sc.nextInt();

            String q2 = "Select * from podcast where podcast_id=?;";
            PreparedStatement ps2 = con.prepareStatement(q2);
            ps2.setInt(1, podcast_id);

            ResultSet res3 = ps2.executeQuery();
            //System.out.println(res);
            String filepath="";
            while(res3.next()) {
                filepath = res3.getString(6);
            }

            Play p=new Play(filepath);

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
