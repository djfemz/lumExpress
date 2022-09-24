package africa.semicolon.lumexpress.data.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer extends LumExpressUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Cart cart;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Address> addresses;
}
