package fr.univtln.bruno.demos.jpa.hello.samples.ex_ids.ex_seqid;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter

@Entity(name = "CustomerSeqId")
@Table(name="CUSTOMER", schema = "EX_IDSEQ")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_customer")
    @SequenceGenerator(name = "seq_customer", sequenceName = "CUSTOMER_SEQUENCE", allocationSize = 10)
    private Long id;

    private String name;
}
