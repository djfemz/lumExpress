package africa.semicolon.lumexpress.data.models;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int buildingNumber;
    private String street;
    private String city;
    private String state;
    private String country;
}
