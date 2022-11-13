import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class PlayList
{
    public void playlist(Connection con,int user_id)
    {
        Scanner sc=new Scanner(System.in);
        try {

            System.out.println();
            System.out.println(" playlists for  [1=song   ,  2 = podcast] ");
            System.out.println("please enter  : ");
            int a = sc.nextInt();
            if(a==1) {

                System.out.println("want to make new playlist ");
                String choice = sc.next();

                if (choice.equalsIgnoreCase("y")) {

                    //displaying Songs  ******************************************

                    Statement stmt = con.createStatement();
                    String query = "SELECT * FROM jukebox.songs order by name;";

                    ResultSet rs = stmt.executeQuery(query);

                    while (rs.next()) {
                        System.out.println("Song's Id :  " + rs.getInt(1) + "    " + "Song Name  : " + rs.getString(2) + "    " + "Song  Duration  : " + rs.getString(3));
                    }

                    System.out.println("********************************************************************************************************************");

                    //making playlist ********************************************

                    Random rm = new Random();
                    int playlist_id = rm.nextInt(50);

                    System.out.println("GIVE A PLAYLIST NAME :  ");
                    String play_name = sc.next();
                    System.out.println("Which song you want to add to yourplaylist enter it's id : ");
                    int song_id = sc.nextInt();

                    String q2 = "insert into  playlist values(?,?,?,?);";
                    PreparedStatement ps2 = con.prepareStatement(q2);

                    ps2.setInt(1, playlist_id);
                    ps2.setInt(2, song_id);
                    ps2.setString(3, play_name);
                    ps2.setInt(4, user_id);


                    int res = ps2.executeUpdate();
                    System.out.println("Song is added successfully  into your playlist  :" + play_name);
                    boolean zoom=true;

                    while(zoom){
                    System.out.println("Want to add more Songs into the playlist  :  "+play_name);
                    String addmore=sc.next();
                    if(addmore.equalsIgnoreCase("y")) {
                        System.out.println("Enter the song's id which you want to add into playlist :  " + play_name);
                        int vid = sc.nextInt();

                        String query6 = "insert into playlist values(?,?,?,?);";


                        PreparedStatement ps6 = con.prepareStatement(query6);

                        ps6.setInt(1, playlist_id);
                        ps6.setInt(2, vid);
                        ps6.setString(3, play_name);
                        ps6.setInt(4, user_id);

                        int res32 = ps6.executeUpdate();
                        System.out.println(" Another Song is added successfully  into your playlist  :" + play_name);
                    }else{
                            zoom=false;
                        }

                    }

                } else if (choice.equalsIgnoreCase("n")) {
                    System.out.println("checking your playlist");
                    // System.out.println("here is your play lists");
                    System.out.println();

                    //displaying songs from playlist

                    Statement stmt4 = con.createStatement();
                    String query4 = " SELECT * FROM playlist join songs on playlist.song_id=songs.song_id  where playlist.user_id=" + user_id + " order by songs.name ;\n ;";

                    ResultSet rs4 = stmt4.executeQuery(query4);

                    while (rs4.next()) {
                        System.out.println("PlayList's Id :  " + rs4.getInt(1) + "    " + " Playlist Name  : " + rs4.getString(3) + "    Song's id  : " + rs4.getInt(2) + "    " +
                                " song's name  : " + rs4.getString(6) + "     User's Id :  " + rs4.getInt(4));
                    }

                    //playing song from the playlist

                        System.out.println("wanted to play Songs ");
                        String holla = sc.next();

                        if (holla.equalsIgnoreCase("y")) {

                            System.out.println("To play the Song Enter Song's Id ");
                            System.out.println();

                            int songid = sc.nextInt();
                            String q2 = "Select * from songs where song_id=?;";
                            PreparedStatement ps2 = con.prepareStatement(q2);
                            ps2.setInt(1, songid);

                            ResultSet res = ps2.executeQuery();

                            String filepath = "";
                            while (res.next()) {
                                filepath = res.getString(6);
                            }

                            Play p = new Play(filepath);
                        } else {
                            System.out.println("Invalid Input");
                        }
                }
            }
            else if(a==2){
                playlist_podcast(con,user_id);
            }
            else{
                System.out.println("Invalid Inout");
            }

            } catch(SQLException e){
                e.printStackTrace();
            } catch(UnsupportedAudioFileException e){
                e.printStackTrace();
            } catch(LineUnavailableException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }


    }
        public void playlist_podcast(Connection con,int user_id ) throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException
        {


                Scanner sc = new Scanner(System.in);
                //displaying user
                System.out.println("want to make new playlist ");
                String choice = sc.next();

                if (choice.equalsIgnoreCase("y")) {

                    //displaying podcast  ******************************************

                    Statement stmt = con.createStatement();
                    String query = "SELECT * FROM jukebox.podcast order by name ;";

                    ResultSet rs = stmt.executeQuery(query);

                    while (rs.next()) {
                        System.out.println("Podcast's Id :  " + rs.getInt(1) + "      " + "Podcast Name  : " + rs.getString(2) + "    " + "Song  Duration  : " + rs.getString(3) +
                                "   podcaster Name   :  " + rs.getString(5));
                    }
                    //making playlist ********************************************

                    Random rm = new Random();
                    int playlist_id = rm.nextInt(50);

                    System.out.println("GIVE A PLAYLIST NAME :  ");
                    String play_name = sc.next();
                    System.out.println("Which Podcast you want to add to yourplaylist enter it's id : ");
                    int podcast_id = sc.nextInt();

                    String q2 = "insert into  playlist_podcast values(?,?,?,?);";
                    PreparedStatement ps2 = con.prepareStatement(q2);

                    ps2.setInt(1, playlist_id);
                    ps2.setInt(2, podcast_id);
                    ps2.setString(3, play_name);
                    ps2.setInt(4, user_id);


                    int res = ps2.executeUpdate();
                    System.out.println("Podcast is added successfully  into your playlist  :" + play_name);

                    boolean zoom=true;

                    while(zoom){
                    System.out.println("Want to add more Podcast into the playlist  :  "+play_name);
                    String addmore=sc.next();
                    if(addmore.equalsIgnoreCase("y")) {
                        System.out.println("Enter the Podcast's id which you want to add into playlist :  " + play_name);
                        int vid = sc.nextInt();

                        String query6 = "insert into playlist_podcast values(?,?,?,?);";


                        PreparedStatement ps6 = con.prepareStatement(query6);

                        ps6.setInt(1, playlist_id);
                        ps6.setInt(2, vid);
                        ps6.setString(3, play_name);
                        ps6.setInt(4, user_id);

                        int res32 = ps6.executeUpdate();
                        System.out.println(" Another Podcast is added successfully  into your playlist  :" + play_name);
                    }
                    else
                    {
                        zoom=false;
                    }
                    }
                } else if (choice.equalsIgnoreCase("n")) {
                    System.out.println("here is your play lists");
                    System.out.println();

                    Statement stmt4 = con.createStatement();
                    String query4 = "SELECT * FROM playlist_podcast join podcast on podcast.podcast_id=playlist_podcast.podcast_id where playlist_podcast.user_id="+user_id+" ;";

                    ResultSet rs4 = stmt4.executeQuery(query4);

                    while (rs4.next()) {
                        System.out.println("PlayList's Id :  " + rs4.getInt(1) + "    " + " Playlist Name  : " + rs4.getString(3) + "    Podcast's id  : " + rs4.getInt(2) + "    " +
                                "     User's Id :  " + rs4.getInt(4) + "   Podcast's Name " + rs4.getString(6));
                    }

                    System.out.println("wanted to play any  podcast");
                    String holla=sc.next();
                    if(holla.equalsIgnoreCase("y")) {

                        System.out.println("To play the podcast Enter Podcast's Id ");
                        System.out.println();
                        int podcast_id = sc.nextInt();
                        String q2 = "Select * from podcast where podcast_id=?;";
                        PreparedStatement ps2 = con.prepareStatement(q2);
                        ps2.setInt(1, podcast_id);

                        ResultSet res = ps2.executeQuery();
                        //System.out.println(res);
                        String filepath = "";
                        while (res.next()) {
                            filepath = res.getString(6);
                        }

                        Play p = new Play(filepath);
                    }

                } else {
                    System.out.println("Invalid Input");
                }
            }

}

