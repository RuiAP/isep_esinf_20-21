import java.util.Objects;

public class Country {
    private String countryName;
    private String continent;
    private double population;
    private String capital;
    private double latitude;
    private double longitude;

    public Country(String countryName, String continent, double population, String capital, double latitude, double longitude) {
        this.countryName = countryName;
        this.continent = continent;
        this.population = population;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Double.compare(country.getPopulation(), getPopulation()) == 0 &&
                Double.compare(country.getLatitude(), getLatitude()) == 0 &&
                Double.compare(country.getLongitude(), getLongitude()) == 0 &&
                Objects.equals(getCountryName(), country.getCountryName()) &&
                Objects.equals(getContinent(), country.getContinent()) &&
                Objects.equals(getCapital(), country.getCapital());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountryName(), getContinent(), getPopulation(), getCapital(), getLatitude(), getLongitude());
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryName='" + countryName + '\'' +
                ", continent='" + continent + '\'' +
                ", population=" + population +
                ", capital='" + capital + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
