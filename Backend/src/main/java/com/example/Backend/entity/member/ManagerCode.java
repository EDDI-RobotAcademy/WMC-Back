package com.example.Backend.entity.member;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManagerCode {

    @Id
    @Getter
    @Column(name = "managerCode_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(nullable = false)
    private String code;

    public ManagerCode(String code){
        this.code = code;
    }

    public static ManagerCode of(String code) {
        return new ManagerCode(code);
    }
}
