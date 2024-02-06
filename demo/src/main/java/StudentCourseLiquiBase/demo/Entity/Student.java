package StudentCourseLiquiBase.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "students")

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

    @Column(name = "phonenumber",nullable = true, length = 10)
    private String phone;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "students_courses",
        joinColumns = @JoinColumn(name = "sid"),
        inverseJoinColumns = @JoinColumn(name = "cid"))
    @JsonBackReference
    private Set<Course> course;



    public Student(){
        super();
    }

    public Student(String firstName, String lastName, String gender, int age, String phone, Set<Course> cource){
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.course = cource;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }
    //lastname
    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    //firstname
    public String getLastname(){
        return this.lastName;
    }

    //gender
    public String getGender(){
        return this.gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    //age
    public Integer getAge(){
        return this.age;
    }

    public void setAge(Integer age){
        this.age = age;
    }


    //phone number
    public String getPhoneNumber(){
        return this.phone;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phone = phoneNumber;
    }

    // course set

    public Set<Course> getCourse(){
        return this.course;
    }

    public void  setCourse(Set<Course> course){
        this.course = course;
    }

}
