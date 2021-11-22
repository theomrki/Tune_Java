package fr.theo.data.table;

public class Instrument {

  private int id;
  private String name;

  public Instrument(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {return this.id;}
  public String getName() {return this.name;}

  @Override public String toString() {return getName();}
}
