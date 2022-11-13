import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import java.sql.*;
import java.util.regex.Pattern;

public class MainMethod {

     static boolean ans=true;

    public static int displayMenu()
    {
        Scanner sc=new Scanner(System.in);

        System.out.println("press 1 for   listening song");
        System.out.println("press 2 for see all song ");
        System.out.println("press 3 for  Listening podcast");
        System.out.println("press 4 for  playlist");
        System.out.println("press 5 for Searching");
        System.out.println("press 6 for see all podcast");
        System.out.println("press 7 for LOG-OUT FROM THE APP ");
        System.out.println("Enter the choice");
        int c = sc.nextInt();
        return c ;
    }

    public static int  singin(Connection con,int user_id ) throws SQLException {
        Scanner sc=new Scanner(System.in);
        try {

            System.out.println("Are You an Existing User  [1 = yes] or [2 = No ]");
            int sa = sc.nextInt();
            if (sa == 1) {

                //for existing customer

                System.out.println("Please enter your credentials to login into Application. ");
                System.out.println("Enter email Address");
                String email = sc.next();
                System.out.println("Enter your password");
                String password = sc.next();
                String finalpassword = null;

                boolean ismatch = Pattern.matches("[a-z]+@[1-9]+", password);

                if (ismatch == false) {
                    System.out.println("wrong credentials");
                }

                if (ismatch) {
                    finalpassword = password;

                }

                Statement stmt = con.createStatement();
                String query1 = "select password from user where email= '" + email + "' ;";
                ResultSet rs1 = stmt.executeQuery(query1);
                String pswrd = null;
                while (rs1.next()) {
                    pswrd = rs1.getString(1);
                }

                if ( finalpassword!=null && finalpassword.equalsIgnoreCase(pswrd)) {
                    System.out.println("Welcome to your Account.");

                    String query3 = "select user_id from user where email= '" + email + "' ;";
                    ResultSet rs3 = stmt.executeQuery(query3);

                    int uId = 0;
                    while (rs3.next()) {
                        uId = rs3.getInt(1);
                    }
                    System.out.println("your user ID is :  " + uId);
                    System.out.println("-----------------------------------------------------------------------");
                    return uId;

                } else {
                    System.out.println("invalid credentials, try again later.");
                    ans = false;
                }
            } else if (sa == 2) {
                //for new customer
                System.out.println("Enter Some basic details : as You are a new Customer ");


                String q1 = "insert into user values(?,?,?,?);";
                PreparedStatement ps1 = con.prepareStatement(q1);
                ps1.setInt(1, user_id);
                System.out.println("Enter  name :");
                String name = sc.next();
                System.out.println("enter the email");
                String email = sc.next();
                ps1.setString(2, name);

                System.out.println("Enter the password");
                String password = sc.next();

                ps1.setString(3, password);

                ps1.setString(4, email);

                int res = ps1.executeUpdate();
                System.out.println(res);
                System.out.println("Your user_id id  :  " + user_id);

                System.out.println("**************************************************");
                return user_id;
            } else {
                System.out.println("Invalid Input");
                ans=false;
            }

        }
        catch (NullPointerException a)
        {
            a.printStackTrace();
        }

        return user_id;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    String url = "jdbc:mysql://localhost:3306/jukebox";
    Connection con = DriverManager.getConnection(url, "root", "root@123");

    System.out.println("Welcome to the JukeBox Music ");
    System.out.println();
    Random rm = new Random();
    int user_id = rm.nextInt(50);

    int userid= singin(con,user_id);
    boolean ans1=false;
     if(ans) {
     ans1 = true;
            }

    while(ans1){

        int c=displayMenu();
        Song s=new Song();
        Sorting srt=new Sorting();

    switch (c) {
        case 1:
            User us=new User();
            us.newcustomer(con, userid);
            break;

        case 2 :

            //displaying songs
            s.DisplayingSong(con,userid);
            break;
        case 5:
            System.out.println("What you want to Search :[1= Song]  or   [2=podcast] ");
            int choice1=sc.nextInt();
            if(choice1==1)
            {
                srt.searchingSong(con,userid);
            }
            else if(choice1==2)
            {
                srt.searchingPodcast(con,userid);
            }
            else
            {
                System.out.println("Invalid input");
            }
            break;

        case 3:

            Podcast p=new Podcast();
            p.newcustomer(con,user_id);
            break;

        case 4 :
            PlayList playList=new PlayList();
            playList.playlist(con,userid);
                    break;

        case 7:
            System.out.println("System is stopping");
            ans1=false;
            break;

        case 6:
            //displaying podcast
            s.displayingPodcast(con,userid);

        default :
            System.out.println("invalid input");
            break;

    }

    }
} catch (SQLException e) {
    e.printStackTrace();
} catch (ClassNotFoundException e) {
    e.printStackTrace();
}
    }

}
