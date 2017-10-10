import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterDao {

	public static int save(String name, String email, String password, String gender, String dob, String address,
			String city, String state, String country, String contact) {
		try
		{
			Connection con=ConProvider.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into register(name,email,password,gender,dob,address,city,state,country,contact,registereddate,authorized) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, gender);
			java.sql.Date dd=Formatter.getSqlDate(dob);
			ps.setDate(5, dd);
			ps.setString(6, address);
			ps.setString(7, city);
			ps.setString(8, state);
			ps.setString(9, country);
			ps.setString(10,contact);
			ps.setDate(11,Formatter.getCurrentDate());
			
			ps.setString(12, "yes");
			return ps.executeUpdate();
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		return 0;
		}
		
	}

}
