package CiroVitiello.TeddyTestBE.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    private String name;
    private String surname;
    private String address;
    private String location;
    private String municipality;
    private String province;
    private String email;
    private String notes;

    public User(String name, String surname, String address, String location, String municipality, String province, String email, String notes) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.location = location;
        this.municipality = municipality;
        this.province = province;
        this.email = email;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", location='" + location + '\'' +
                ", municipity='" + municipality + '\'' +
                ", province='" + province + '\'' +
                ", email='" + email + '\'' +
                ", notes='" + notes + '\'' +
                ", id=" + id +
                '}';
    }
}
