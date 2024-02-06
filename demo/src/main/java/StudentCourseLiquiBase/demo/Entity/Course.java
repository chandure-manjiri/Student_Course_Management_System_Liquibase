package StudentCourseLiquiBase.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "courses")

public class Course {
    @Id
//    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "name",nullable = false, unique = true)
    private String Name;

//
//<<<<<<< HEAD
//    @ManyToMany(mappedBy = "course", fetch = FetchType.LAZY)
//    @JsonIgnore
//=======

    @ManyToMany(mappedBy = "course", fetch = FetchType.LAZY)

    private Set<Student> student;


    public Course(){
        super();
    }

    public Course(String Name, Set<Student> student){
        this.Name = Name;
        this.student = student;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return this.Name;
    }

    public void setName(String name){
        this.Name = name;
    }

    public Set<Student> getStudent(){ return  this.student;}

    public void setStudent(Set<Student> student){
        this.student = student;
    }


}
