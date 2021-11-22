package fr.theo.data.table;

public class Band {

  private int id;
  private String name;
  private String correspondent;

  public Band(int id, String name, String correspondant) {
    this.id = id;
    this.name = name;
    this.correspondent = correspondant;
  }

  public int getId() {return this.id;}
  public String getName() {return this.name;}
  public String getCorrespondent() {return this.correspondent;}
}
