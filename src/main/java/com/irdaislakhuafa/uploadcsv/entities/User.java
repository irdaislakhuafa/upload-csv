package com.irdaislakhuafa.uploadcsv.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.irdaislakhuafa.uploadcsv.entities.utils.GenderUtils;
import com.opencsv.bean.CsvBindByName;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(length = 100, nullable = false)
    @NotNull(message = "name cannot be null!")
    @CsvBindByName(column = "nama")
    private String name;

    @Column(length = 100, unique = true, nullable = false)
    @Email(message = "email format not valid!")
    @NotNull(message = "email cannot be null!")
    @CsvBindByName
    private String email;

    @Column(length = 50, nullable = false)
    @NotNull(message = "city cannot be null")
    @CsvBindByName(column = "kota")
    private String city;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    @Min(value = 1, message = "minimal value is 1 character")
    @Column(length = 10, nullable = false)
    @CsvBindByName(column = "jk")
    private GenderUtils gender;
}
