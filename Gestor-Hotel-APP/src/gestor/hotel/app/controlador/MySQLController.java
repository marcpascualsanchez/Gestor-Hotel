package gestor.hotel.app.controlador;
import java.sql.*;
import java.util.*;

public class MySQLController
{
  private String dbUrl;
  private String dbUser;
  private String dbPass;
  private Connection conn;
  private Statement currentStatement;
  
  public MySQLController(String dbUrl, String dbUser, String dbPass){
      this.dbUrl = dbUrl;
      this.dbUser = dbUser;
      this.dbPass = dbPass;
  }
  
  public void connect(){
      try
        {
        String myDriver = "org.gjt.mm.mysql.Driver";
        Class.forName(myDriver);
        this.conn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPass);
        }
      catch (Exception e)
        {
          System.err.println("Got an exception! ");
          System.err.println(e.getMessage());
        }
  }
  
  public void insert(String table, Map<String, String> values){
      int i = 0;
      String query = "INSERT INTO `" + table + "` VALUES (";
      
        for (Map.Entry<String, String> pair : values.entrySet()) {
            if(i == values.size() - 1){
                query += "'" + pair.getValue() + "');";//controlar la ultima coma i parentesi
            }else{
                query += "'" + pair.getValue() + "', ";
            }
            i++;
        }
      
      this.commit(query);
  }
  
  public void delete(String table, int id){
      String query = "DELETE FROM `" + table + "` WHERE `" + table + "`.`id` = " + id;
      this.commit(query);
  }
  
  public void update(String table, Map<String, String> values, int id){
      int i = 0;
      String query = "UPDATE `" + table + "` SET ";
      
        for (Map.Entry<String, String> pair : values.entrySet()) {
            if(i == values.size() - 1){
                query += pair.getKey() + "='" + pair.getValue() + "'";//controlar la ultima coma i parentesi
            }else{
                query += pair.getKey() + "='" + pair.getValue() + "', ";
            }
            i++;
        }
        query += " WHERE `"+ table + "`.`id` = " + id + ";";
        
        this.commit(query);

  }
  
  private void commit(String query){
    if(this.conn != null){
        try
        {     
          Statement st = this.conn.createStatement();
          // execute the query, and get a java resultset
          int rs = st.executeUpdate(query);
          System.out.println(rs);
          st.close();
        }
        catch (Exception e)
        {
          System.err.println("Got an exception in commitment.");
          System.err.println(e.getMessage());
        }
    }else{
        System.err.println("Connection object is null");
    }
  }
          
  public ResultSet select(String fields, String table, String conditions)
  {
    ResultSet rs = null;
    
    if(this.conn != null){
        try
        {     
          // our SQL SELECT query. 
          // if you only need a few columns, specify them by name instead of using "*"
          String query = "SELECT " + fields + " FROM `" + table + "`";
          if(!conditions.equals("")){
              query += " WHERE " + conditions;
          }
          query += ";";
          System.out.println(query);

          // create the java statement
          this.currentStatement = this.conn.createStatement();

          // execute the query, and get a java resultset
          rs = this.currentStatement.executeQuery(query);
          
          //this.currentStatement.close();
        }
        catch (SQLException e){
          System.err.println("Got an exception with a SELECT query in class " + this.getClass().getName());
          System.err.println(e.getMessage());
        }
    }else{
        System.err.println("Connection object is null");
    }
    
    return rs;
  }
  
  public void closeStatement(){
    try
    { 
      this.currentStatement.close();
    }
    catch (Exception e){
        System.err.println("Got an exception closing a query statement in class " + this.getClass().getName());
        System.err.println(e.getMessage());
    }
  }
}