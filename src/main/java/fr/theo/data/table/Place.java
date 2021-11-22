package fr.theo.data.table;

public class Place {

  private int city_id;
  private int country_id;
  private String cityName;
  private String countryName;
  

  public Place(int city_id, int country_id, String cityName, String countryName) {
    this.city_id = city_id;
    this.country_id = country_id;
    this.cityName = cityName;
    this.countryName = countryName;
  }

  public int getCityId() {return this.city_id;}
  public int getCountryId() {return this.country_id;}
  public String getCityName() {return this.cityName;}
  public String getCountryName() {return this.countryName;}

}
