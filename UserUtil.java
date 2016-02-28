import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;

import java.util.Scanner;

public class UserUtil {
	
	static String classMethod = "";
	static final String driver = "com.mysql.jdbc.Driver";
	static final String url = "jdbc:mysql://localhost/tweeter";
	static final String user = "tweet";
	static final String pwd = "tweet";
	String errorMsg = "Error has occurred";
	
	
	/**
	 * 
	 * @param userName
	 * @return User ID in the database (users table)
	 */
	static int returnUserID(String userName){
		int uid = 0;
		Connection conn = null;
		Statement stmt = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pwd);
			String SQL = "SELECT ID FROM users WHERE username=?";
			PreparedStatement preparedStmt = conn.prepareStatement(SQL);
			preparedStmt.setString(1, userName);
			ResultSet rs = preparedStmt.executeQuery();
			
			while(rs.next()){
				uid = rs.getInt("ID");
			}
		}catch(SQLException se){
			se.printStackTrace();
			
		}catch(Exception e){
			e.printStackTrace();

		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return uid;
	}
	
	
	/**
	 * 
	 * @param userName
	 * @return Displays the users tweets that are in the database and associated with the users UID(tweets table)
	 */
	static String displayTweets(String userName){

		Connection conn = null;
		Statement stmt = null;
		String tweet = "";
		List tweets = new LinkedList(); 

		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pwd);			
			String SQL = "SELECT content FROM tweets WHERE ID=?";		
			PreparedStatement preparedStmt = conn.prepareStatement(SQL);			
			int userID = returnUserID(userName);
			preparedStmt.setInt(1, userID);		
			ResultSet rs = preparedStmt.executeQuery();		
			while(rs.next()){
				tweet = rs.getString("content");
				tweets.add(tweet);
				}
		}catch(SQLException se){
			se.printStackTrace();
			
		}catch(Exception e){
			e.printStackTrace();

		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tweet;
	}

	/**
	 * 
	 * @param userName
	 * @param content
	 * @return Nothing. Sends tweet to database for user
	 */
	static void tweet(String userName, String content){
		classMethod = "tweetTweet";
		Connection conn = null;
		Statement stmt = null;
		try{
			System.out.println("Content set to " + content);
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pwd);			
			String SQL = "INSERT INTO tweets VALUES(?,?,NOW())";
			PreparedStatement preparedStmt = conn.prepareStatement(SQL);			
			int userID = returnUserID(userName);
			preparedStmt.setInt(1, userID);	
			preparedStmt.setString(2, content);
			preparedStmt.execute();		
		}catch(SQLException se){
			se.printStackTrace();
			
		}catch(Exception e){
			e.printStackTrace();

		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param userName
	 * @param userToFollow
	 * @return Adds a user to the requesting user's follower list
	 */
	static void followerUser(String userName, String userToFollow){	
	}
	
	/**
	 * 
	 * @param userName
	 * @param userToUnfollow
	 * @return Unfollows a user
	 */
	static void unfollowUser(String userName, String userToUnfollow){

	}
	
}
