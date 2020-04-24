package com.shzb.secondapp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "name canot be blank")
    private String name;
    private int age;
    private String height;


}
