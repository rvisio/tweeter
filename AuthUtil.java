import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;





public class AuthUtil {
	String activeUsers = "rjarvis,ernie,swipht,khalifa";
	static final String driver = "com.mysql.jdbc.Driver";
	static final String url = "jdbc:mysql://localhost/tweeter";
	static final String user = "tweet";
	static final String pwd = "tweet";

	Connection conn = null;
	Statement stmt = null;
	
	/*boolean checkUserName(String userName){
		
		List<String> users = Arrays.asList(activeUsers.split(","));
		
		for(int i =0; i < users.size(); i++){
			if(userName.equals(users.get(i))){
				return true;
			}
		}
		return false;
	}*/
	
	boolean checkPassword(String userName){
		// check users password against datbase
		return false;
	}
	
	public void registerUser(String userName){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pwd);
			
			stmt=conn.createStatement();
			
			String SQL = "INSERT INTO users (username) VALUES (?)";
			PreparedStatement preparedStmt = conn.prepareStatement(SQL);			
			
			preparedStmt.setString(1, userName);
			
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
		System.out.println(userName + " has been created");
	}
	/**
	 * 
	 * @param userInputLogin
	 * @return
	 */
	boolean checkUserName(String userInputLogin){
		List users = new LinkedList(); 

		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pwd);
			
			stmt=conn.createStatement();
			
			String SQL = "SELECT * from users";
			
			ResultSet rs = stmt.executeQuery(SQL);
			
			String result = "";
			
			
			String user = "";
			while(rs.next()){
				
				user = rs.getString("username");
				users.add(user);
			}
			
			System.out.println(users);
			
			
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
		if(users.contains(userInputLogin)){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 
	 * @param userName
	 * @param password
	 * @return Syncs the user password with the database
	 */
	void syncPassword(String userName, String password){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pwd);
			
			stmt=conn.createStatement();
			
			String SQL = "INSERT INTO users (username) VALUES (?)";
			PreparedStatement preparedStmt = conn.prepareStatement(SQL);			
			
			preparedStmt.setString(1, userName);
			
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
	 * @param password
	 * @return Encrypted password that will be synced to database
	 */
	public String encryptPassword(String password){
		String cryptPassword = "";
		return cryptPassword;
		
	}
}

