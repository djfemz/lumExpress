package africa.semicolon.lumexpress.data.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Admin extends LumExpressUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ElementCollection
    private Set<Authority> authorities;
}
