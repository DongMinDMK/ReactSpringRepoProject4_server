package org.example.srshopserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Cart_View {
    @Id
    private Integer cseq;
    private String userid;
    private String mname;
    private Integer pseq;
    private String pname;
    private Integer quantity;
    private Integer price2;
    @CreationTimestamp
    private Timestamp indate;
}
