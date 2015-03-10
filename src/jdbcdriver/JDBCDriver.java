/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jdbcdriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bilal
 */
public class JDBCDriver implements java.sql.Driver{

    static String PREFIX = "jdbc:JDBCDriver:\\";
    Connection DatabaseConnection;
    
    public static void main(String[] args) 
    {
        try 
        {
            Class.forName("jdbcdriver.JDBCDriver");
            
            Connection databaseConnection = DriverManager.getConnection(PREFIX);
            
            Statement statement = databaseConnection.createStatement();
            
            ResultSet result = statement.executeQuery("Select * from sakila.actor");
            
            while(result.next())
            {
                System.out.println(result.getString(2) + " " + result.getString(3));
            }
        } 
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static
    {
        try 
        {
            DriverManager.registerDriver(new JDBCDriver());
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public JDBCDriver() throws SQLException
    {
        try 
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } 
        catch (ClassNotFoundException ex) 
        {
            ex.printStackTrace();
        }
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException 
    {
        DatabaseConnection = DriverManager.getConnection("jdbc:odbc:MyDatasource", "root", "bilal");
        return DatabaseConnection;
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return url.startsWith(PREFIX);
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMajorVersion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMinorVersion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean jdbcCompliant() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
