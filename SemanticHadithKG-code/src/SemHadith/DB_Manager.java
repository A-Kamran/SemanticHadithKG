package SemHadith;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dataAccess.connectionFactory;

public class DB_Manager {
	
	public static Connection conn = null ;
	public static Statement st = null;
	
	// ******************* Database Connection*****************
		public static void createConnection(String dbName)
		{

			try {
				conn = connectionFactory.createConnection(dbName);
				st = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void closeConnection()
		{
			try 
			{
				if (conn != null)
				{
					conn.close();
				}
				if (st != null)
				{
					st.close();
				}
			} 
			catch (SQLException sqlee)
			{
				sqlee.printStackTrace();
			}
		}
		// ******************* Helping function: to get number of rows in a sql table *****************
		public static int rowCount(String tableName)
		{
			int count =0;
			try 
			{

				ResultSet r = st.executeQuery("SELECT COUNT(*) AS rowcount FROM "+tableName);
				r.next();
				count = r.getInt("rowcount");
				r.close();		
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			return count;
		}
}
