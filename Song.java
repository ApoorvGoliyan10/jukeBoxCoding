import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Song {

public void DisplayingSong(Connection con,int user_id )
{
        User us=new User();
        us.newcustomer(con,user_id);

}

public void displayingPodcast(Connection con,int user_id)
{
    Podcast pd=new Podcast();
    pd.newcustomer(con,user_id);

}
}
