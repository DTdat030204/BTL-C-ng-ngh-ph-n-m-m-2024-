package com.se.ssps.server.entity.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "users") // Chuyển từ JPA Entity sang MongoDB Document
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    private Long id; // MongoDB sẽ tự động tạo id nếu không chỉ định

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Boolean isAdmin;

    // Thay quan hệ OneToOne với Student và Admin bằng cách nhúng hoặc tham chiếu
    @DBRef // DBRef để tham chiếu đối tượng Student trong MongoDB
    @JsonIgnore
    private Student student;

    @DBRef // DBRef để tham chiếu đối tượng Admin trong MongoDB
    @JsonIgnore
    private Admin admin;

    public User(String firstName, String lastName, String username, String password, Boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }
}
