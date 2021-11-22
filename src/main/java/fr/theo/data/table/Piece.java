package fr.theo.data.table;

public class Piece {

  private int id;
  private String title;
  private String author;
  private String duration;

  public Piece(int id, String title, String author, String duration) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.duration = duration;
  }

  public int getId() {return this.id;}
  public String getTitle() {return this.title;}
  public String getAuthor() {return this.author;}
  public String getDuration() {return this.duration;}
}
