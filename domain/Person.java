
package domain;


public class Person {
    
    private String id;
    private String name;
    private String lastName1;
    private String lastName2;
    private String date_birth;
    private String country;
    private String parent_id;

    public Person(String id, String name, String lastName1, String lastName2, String date_birth, String country, String parent_id) {
        this.id = id;
        this.name = name;
        this.lastName1 = lastName1;
        this.lastName2 = lastName2;
        this.date_birth = date_birth;
        this.country = country;
        this.parent_id = parent_id;
    }

    public Person() {
        this.id = "";
        this.name = "";
        this.lastName1 = "";
        this.lastName2 = "";
        this.date_birth = "";
        this.country = "";
        this.parent_id = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) {
        this.lastName1 = lastName1;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public String getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(String date_birth) {
        this.date_birth = date_birth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", lastName1=" + lastName1 + ", lastName2=" + lastName2 + ", date_birth=" + date_birth + ", country=" + country + ", parent_id=" + parent_id + "}";
    }
    
    
    
    

}
