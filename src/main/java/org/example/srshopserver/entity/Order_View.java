package org.example.srshopserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Setter
public class Order_View {
    @Id
    private int odseq;
    private int oseq;
    private String userid;
    @CreationTimestamp
    private Timestamp indate;
    private int pseq;
    private int quantity;
    private String mname;
    private String zip_num;
    private String address1;
    private String address2;
    private String phone;
    private String pname;
    private int price2;
    private String result;
}
