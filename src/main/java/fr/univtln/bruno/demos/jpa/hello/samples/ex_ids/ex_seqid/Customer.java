package fr.univtln.bruno.demos.jpa.hello.samples.ex_ids.ex_seqid;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "CustomerSeqId")
@Table(name="CUSTOMER", schema = "EX_IDSEQ")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_customer")
    @SequenceGenerator(name = "seq_customer", sequenceName = "CUSTOMER_SEQUENCE", allocationSize = 10)
    private Long id;

    private String name;
}
