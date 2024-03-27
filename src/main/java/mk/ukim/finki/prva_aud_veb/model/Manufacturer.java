package mk.ukim.finki.prva_aud_veb.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "manufacturers")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "manufacturer_address")
    private String address;
    private String name;

    public Manufacturer(String address, String name) {
        this.address = address;
        this.name = name;
    }

    public Manufacturer() {

    }
}
