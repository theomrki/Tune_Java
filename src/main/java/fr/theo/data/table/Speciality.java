
package fr.theo.data.table;

public class Speciality {

  private int id;
  private String label;

  public Speciality(int id, String label) {
    this.id = id;
    this.label = label;
  }

  public int getId() {return this.id;}
  public String getLabel() {return this.label;}

  @Override public String toString() {return getLabel();}
}
