package fr.univtln.bruno.demos.jpa.hello.samples.ex_associations.ex_onetomany_b;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.*;

@Data
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Table(name = "LINE", schema = "EX_ONE_TO_MANY_B")
public class Line {
    @NonNull
    private String product;
    @NonNull
    private double price;
}
