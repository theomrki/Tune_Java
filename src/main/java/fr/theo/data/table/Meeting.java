package fr.theo.data.table;

public class Meeting {

  private int id;
  private String name;
  private int visitors;

  public Meeting(int id, String name, int visitors) {
    this.id = id;
    this.name = name;
    this.visitors = visitors;
  }

  public int getId() {return this.id;}
  public String getName() {return this.name;}
  public int getVisitors() {return this.visitors;}
  
}
