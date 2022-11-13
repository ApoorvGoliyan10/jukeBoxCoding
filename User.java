import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class User {

    public void newcustomer(Connection con , int user_id)
    {
        try {
            Scanner sc=new Scanner(System.in);
            //displaying song

            Statement stmt = con.createStatement();

            String query = "SELECT * FROM songs join artist on songs.artist_id=artist.artist_id order by songs.name;";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("Song's Id :  "+rs.getInt(1)+"    "+"Song Name  : "+rs.getString(2)+"    "+"Song  Duration  : "+rs.getString(3)+"   Artist Name  :  "
                        +rs.getString(9));
            }

            //playing song

            System.out.println("Which Song you want to play  Enter it's id :  ");

            int song_id = sc.nextInt();

            String q2 = "Select * from songs where song_id=?;";
            PreparedStatement ps2 = con.prepareStatement(q2);
            ps2.setInt(1, song_id);

            ResultSet res1 = ps2.executeQuery();
            //System.out.println(res);
            String filepath="";
            while(res1.next()) {
                filepath = res1.getString(6);
            }
            Play p=new Play(filepath);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(NullPointerException ne)
        {
            ne.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
