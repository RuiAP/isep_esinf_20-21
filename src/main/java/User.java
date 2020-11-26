public class User {

    private String userId;
    private int age;
    private String city;

    public User(String user_name, int age, String city) {
        this.userId = user_name;
        this.age = age;
        this.city = city;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_name='" + userId + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }
}


