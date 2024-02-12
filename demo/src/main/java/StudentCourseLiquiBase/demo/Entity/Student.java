package StudentCourseLiquiBase.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or GenerationType.AUTO
//    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "firstname",nullable = true)
    private String firstName;
    @Column(name = "lastname",nullable = true)
    private String lastName;
    @Column(name = "gender",nullable = true)
    private String gender;
    @Column(name = "age",nullable = true)
    private Integer age;
//    , unique = true
    @Column(name = "phonenumber",nullable = true, length = 10)
    private String phone;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "students_courses",
        joinColumns = @JoinColumn(name = "sid"),
        inverseJoinColumns = @JoinColumn(name = "cid"))
    private Set<Course> course;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Address> addressList;

    public Student(){
        super();
    }

    public Student(String firstName, String lastName, String gender, int age, String phone, Set<Course> cource,  List<Address> addressList){
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.course = cource;
        this.addressList = addressList;
    }

    //phone number
    public String getPhoneNumber(){
        return this.phone;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phone = phoneNumber;
    }

}
