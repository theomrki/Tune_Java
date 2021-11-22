
package fr.theo.util.sql.connection;

import fr.theo.util.sql.query.QueryBuilder;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class MySQLConnectionWrapper {
  
  private Connection connection;
  private String url;

  public MySQLConnectionWrapper(String host, String port, String database) {
    this.url = String.format("jdbc:mysql://%s:%s/%s", host, port, database);
  }

  public void open(String username, String password) {
    try {
      this.connection = DriverManager.getConnection(this.url, username, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void close() {
    try {
      this.connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public ArrayList<String> callProcedure(String procedure, String... args) {
    try {
      return getResult(
        this.connection.createStatement().executeQuery(
          (new QueryBuilder()).call(procedure, args).build()
        )
      );
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public int countRows(String table) {
    ResultSet resultSet = null;
    try {
      resultSet = this.connection.createStatement().executeQuery(
        (new QueryBuilder()).select().count("*").from(table).build()
      );
      resultSet.next();
      return resultSet.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  public int countColumns(String table) {
    ResultSet resultSet = null;
    int output = 0;
    try {
      resultSet = this.connection.createStatement().executeQuery(
        (new QueryBuilder()).select("*").from(table).build()
      );
      ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
      output =  resultSetMetaData.getColumnCount();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return output;
  }

  public String[] getColumnNames(String table) {
    ResultSet resultSet = null;
    int nbColumns = countColumns(table);
    String[] output = new String[nbColumns];
    try {
      resultSet = this.connection.createStatement().executeQuery(
        (new QueryBuilder()).select("*").from(table).build()
      );
      ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
      for (int index = 0; index < nbColumns; index++) {
        output[index] = resultSetMetaData.getColumnName(index+1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return output;
  }

  public void insert(String table, String[] fields_names, String[] fields_values) {
    try {
      this.connection.createStatement().execute(
        (new QueryBuilder())
          .insert()
          .into(table, fields_names)
          .values(fields_values)
          .build()
      );
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public String[] select(String table, String[] fields_names) {

    ResultSet resultSet;
    String[] result = new String[fields_names.length];
    try {
      resultSet = this.connection.createStatement().executeQuery(
        (new QueryBuilder())
          .select(fields_names)
          .from(table)
          .build()
      );
      int index = 0;
      while (resultSet.next()) {
        result[index] = "";
        result[index] += String.format("%s:%s,", fields_names[index], resultSet.getString(fields_names[index]));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public String[] SelectAll(String table) {
    String[] output = new String[countRows(table)];
    String[] names = getColumnNames(table);
    try {
      ResultSet resultSet = this.connection.createStatement().executeQuery(
        (new QueryBuilder()).select("*").from(table).build()
      );
      int index = 0;
      int nbColumns = countColumns(table);
      while (resultSet.next()) {
        output[index] = "";
        for (int columnIndex = 0; columnIndex < nbColumns - 1; columnIndex++) {
          output[index] += String.format("%s:%s,", names[columnIndex], resultSet.getString(columnIndex+1));
        }
        output[index] += String.format("%s:%s", names[nbColumns-1], resultSet.getString(nbColumns));
        index++;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return output;
  }

  private ArrayList<String> getResult(ResultSet pResultSet) {
    ArrayList<String> output = null;
    try {
      output = new ArrayList<>();
      ResultSetMetaData resultSetMetaData = pResultSet.getMetaData();
      int columnCount = resultSetMetaData.getColumnCount();
      String[] columnNames = new String[columnCount];
      for (int index = 0; index < columnCount; index++)
        columnNames[index] = resultSetMetaData.getColumnName(index+1);
      while (pResultSet.next()) {
        StringBuilder builder = new StringBuilder();
        for (int columnIndex = 0; columnIndex < columnCount - 1; columnIndex++)
          builder.append(String.format(
            "%s:%s,", 
            columnNames[columnIndex], 
            pResultSet.getString(columnIndex+1)
          ));
        builder.append(String.format(
          "%s:%s", 
          columnNames[columnCount-1], 
          pResultSet.getString(columnCount)
        ));
        output.add(builder.toString());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return output;
  }

  public void updateById(String table, int id, String[] names, String[] values) {
    try {
      this.connection.createStatement().execute(
        (new QueryBuilder())
          .update(table).set(names, values)
          .where(String.format("id=%d", id))
          .build()
      );
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteById(String table, int id) {
    try {
      this.connection.createStatement().execute(
        (new QueryBuilder())
          .delete().from(table)
          .where(String.format("id=%d", id))
          .build()
      );
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
