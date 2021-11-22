package fr.theo.data.table;

public class Member {

  private int id;
  private String name;
  private String instrument;

  public Member(int id, String name, String instrument) {
    this.id = id;
    this.name = name;
    this.instrument = instrument;
  }

  public int getId() {return this.id;}
  public String getName() {return this.name;}
  public String getInstrument() {return this.instrument;}
}
