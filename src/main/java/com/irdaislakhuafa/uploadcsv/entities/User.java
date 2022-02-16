package com.irdaislakhuafa.uploadcsv.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.irdaislakhuafa.uploadcsv.entities.utils.GenderConverter;
import com.irdaislakhuafa.uploadcsv.entities.utils.GenderUtils;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    // @Enumerated(value = EnumType.STRING)
    @NotNull(message = "gender cannot be null")
    @Column(length = 10, nullable = false)
    @CsvCustomBindByName(column = "jk", converter = GenderConverter.class)
    private GenderUtils gender;

    // my custom constructor
    public User(
            @NotNull(message = "name cannot be null!") String name,
            @Email(message = "email format not valid!") @NotNull(message = "email cannot be null!") String email,
            @NotNull(message = "city cannot be null") String city,
            @NotNull(message = "gender cannot be null") GenderUtils gender) {
        this.name = name;
        this.email = email;
        this.city = city;
        this.gender = gender;
    }

}
