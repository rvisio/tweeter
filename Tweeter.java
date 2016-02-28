import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.EventQueue;
/**
 * 
 * @author robert jarvis
 * TODO List
 * 
 * 2) Registration functionality 
 * 3) GUI 
 * 
 *
 */
public class Tweeter{

	public static String userName; 
	public static boolean verified = false;
	public static int optionSelected;
	static AuthUtil auth = new AuthUtil();
	static UserInfo userInformation = new UserInfo();
	static UserUtil tweet = new UserUtil();
	public static String choice;

	public Tweeter(){

	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner reader = new Scanner(System.in);

		System.out.println("Hey there, please enter your Tweeter Name");
		userName = reader.next();
		verified = auth.checkUserName(userName);

		while(!verified){
			System.out.println("That user name is not registered. Would you like to register? (y/n)");
			reader = new Scanner(System.in);
			choice = reader.next();
			if(choice.equals("y")){
				System.out.println("Would you like your username to be " + userName + "?(y/n)");
				reader = new Scanner(System.in);
				choice = reader.next();
				if(choice.equals("y")){
					auth.registerUser(userName);
					verified = auth.checkUserName(userName);
				}
			}
			userName = reader.next();
			verified = auth.checkUserName(userName);



			//TODO 
			//Include registration functionality 
		}

		while(optionSelected != -999){
			System.out.println("Welcome to the tweeter " + userName + ". Please type in the number that you would like to do next.");
			System.out.println("1. View Tweets\n2. View Followers\n3. View Users You Are Following\n4.Send a Tweet");
			reader = new Scanner(System.in);

			optionSelected = reader.nextInt();
			if(optionSelected == 1){
				System.out.println(tweet.displayTweets(userName));
			}else if(optionSelected == 2){
				// do this
			}else if(optionSelected == 3){
				// do this
			}else if(optionSelected == 4){
				System.out.println("Type your tweet here");
				reader = new Scanner(System.in);
				String tweetContent = "";
				tweetContent = reader.nextLine();
				tweet.tweet(userName, tweetContent);
			}
		}




	}
}
