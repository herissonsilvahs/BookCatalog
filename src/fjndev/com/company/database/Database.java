package fjndev.com.company.database;

import java.sql.*;

public class Database
{
    private String driver;
    private String url;
    private Connection conn;
    private Statement statement;

    public Statement getStatement()
    {
        return statement;
    }

    public ResultSet getResultSet()
    {
        return resultSet;
    }

    private ResultSet resultSet;

    public Database(final String driver, final String url)
    {
        this.driver = driver;
        this.url = url;
    }

    public boolean connect()
    {
        try
        {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            statement = conn.createStatement();
            return true;
        }catch(ClassNotFoundException ex)
        {
            ex.printStackTrace();
            return false;
        }catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    public void close(boolean connection, boolean statment, boolean resultset)
    {
        try
        {
            if(connection)
                conn.close();
            else if(statment)
                statement.close();
            else if(resultset)
                resultSet.close();
        }catch (SQLException ex)
        {
            ex.printStackTrace();
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void close()
    {
        try
        {
            conn.close();
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void setResultSet(String query)
    {
        try
        {
            resultSet = statement.executeQuery(query);
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public int executeQuery(String query)
    {
        int quant = 0;
        try
        {
            quant = statement.executeUpdate(query);
        } catch (SQLException erro)
        {
            erro.printStackTrace();
        }
        return quant;
    }
}