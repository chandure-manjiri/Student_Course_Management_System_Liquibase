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
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressSequence")
//    @SequenceGenerator(name = "addressSequence", sequenceName = "addressSequenceTable", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "area")
    private String area;

    @Column(name = "city")
    private String city;

    @Column(name = "pin",nullable = true)
    private Integer pin;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "sid")
    private Student student;
}