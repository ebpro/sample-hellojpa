package fr.univtln.bruno.demos.jpa.hello.samples.ex_ids.ex_tableid;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "CustomerTableId")
@Table(name="CUSTOMER", schema = "EX_IDTABLE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "customer_table_generator")
    @TableGenerator(name = "customer_table_generator", schema = "EX_IDTABLE", table = "ID_GENERATOR_TABLE")
    private Long id;

    private String name;
}
