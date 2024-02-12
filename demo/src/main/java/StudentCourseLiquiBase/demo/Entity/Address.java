package StudentCourseLiquiBase.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "flat",nullable = true)
    private Integer flat;

    @Column(name = "area",nullable = false)
    private String area;

    @Column(name = "city",nullable = false)
    private String city;

    @Column(name = "pin",nullable = true)
    private Integer pin;

    @Column(name = "state",nullable = false)
    private String state;

    @Column(name = "country",nullable = false)
    private String country;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(table = "students",name = "sid")
    private Student student;
}
