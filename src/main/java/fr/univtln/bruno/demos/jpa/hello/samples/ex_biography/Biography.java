package fr.univtln.bruno.demos.jpa.hello.samples.ex_biography;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Biography {
    @Column(name = "BIO_BRIEF")
    private String brief;

    @Lob
    @Column(name = "BIO_EXTENDED")
    private String extended;
}
