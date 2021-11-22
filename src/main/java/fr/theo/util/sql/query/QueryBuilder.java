
package fr.theo.util.sql.query;

public class QueryBuilder {

  private StringBuilder builder;

  public QueryBuilder() {this.builder = new StringBuilder();}
  
  public String build() {return this.builder.toString();}

  public QueryBuilder call(String procedure, String... parameters) {
    this.builder.append(String.format("%s %s(", "CALL", procedure));
    if (parameters.length > 0) {
      for (String parameter: parameters)
        this.builder.append(String.format("%s,", parameter));
      this.builder.setLength(this.builder.length() - 1);
    }
    this.builder.append(");");
    System.out.println(this.builder.toString());
    return this;
  }

  public QueryBuilder select() {
    this.builder.append("SELECT ");
    return this;
  }

  public QueryBuilder select(String name) {
    this.select();
    this.builder.append(String.format("%s ", name));
    return this;
  }

  public QueryBuilder select(String[] names) {

    this.select();
    for (int index = 0; index < names.length - 1; index++) {
      this.builder.append(String.format("%s, ", names[index]));
    }
    this.builder.append(String.format("%s ", names[names.length - 1]));
    return this;
  }
    
  public QueryBuilder from(String table) {
    this.builder.append(String.format("FROM %s", table)); 
    return this;
  }

  public QueryBuilder where(String condition) {
    this.builder.append("WHERE " + condition + " ");
    return this;
  }

  public QueryBuilder groupBy(String expression) {
    this.builder.append("GROUP BY " + expression + " ");
    return this;
  }

  public QueryBuilder notNull() {
    this.builder.append("NOT NULL ");
    return this;
  }

  public QueryBuilder autoIncrement() {
    this.builder.append("AUTO INCREMENT ");
    return this;
  }

  public QueryBuilder primaryKey(String key) {
    this.builder.append(String.format("PRIMARY KEY (%s)", key));
    return this;
  } 

  public QueryBuilder insert() {
    this.builder.append("INSERT ");
    return this;
  }

  public QueryBuilder delete() {
    this.builder.append("DELETE ");
    return this;
  }

  public QueryBuilder create() {
    this.builder.append("CREATE ");
    return this;
  }

  public QueryBuilder database(String database) {
    this.builder.append(String.format("DATABASE %s", database));
    return this;
  }

  public QueryBuilder table(String table) {
    this.builder.append(String.format("TABLE %s ", table));
    return this;
  }

  public QueryBuilder createTable(
    String table, 
    String[] field_declarations
  ) {
    this.create();
    this.table(table);
    this.builder.append("(");
    for (int index = 0; index < field_declarations.length - 1; index++)
      this.builder.append(String.format("%s, ", 
        field_declarations[index])
      );
    this.builder.append(String.format("%s);", 
      field_declarations[field_declarations.length - 1])
    );
    return this;
  }
  
  public QueryBuilder update(String table) {
    this.builder.append(String.format("UPDATE %s ", table));
    return this;
  }

  public QueryBuilder values(String[] values) {
    this.builder.append("VALUES (");
    for (int index = 0; index < values.length - 1; index++) {
      if (values[index] == "NULL") this.builder.append(String.format("%s,", values[index]));
      else this.builder.append(String.format("'%s', ", values[index]));
    }
    this.builder.append(String.format("'%s') ", values[values.length - 1]));
    return this;
  }

  public QueryBuilder set(String[] names, String[] values) {
    this.builder.append("SET ");
    for (int index = 0; index < names.length - 1; index++) {
      this.builder.append(String.format("`%s` = '%s', ", names[index], values[index]));
    }
    this.builder.append(String.format("`%s` = '%s' ", names[names.length - 1], values[values.length - 1]));
    return this;
  }

  public QueryBuilder into(String table, String[] names) {
    this.builder.append(String.format("INTO `%s` (", table));
    for (int index = 0; index < names.length - 1; index++) {
      this.builder.append(String.format("`%s`, ", names[index]));
    }
    this.builder.append(String.format("`%s`) ", names[names.length - 1]));
    return this;
  }

  public QueryBuilder count(String name) {
    this.builder.append(String.format("COUNT(%s) ", name));
    return this;
  }

}








