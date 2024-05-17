package com.project.adminLibros.models;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private Long id;

    private String name;

    private String email;

    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @JsonProperty("created_at")
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @JsonProperty("updated_at")
    private Date updatedAt;

    @JsonCreator
    public User(@JsonProperty("name") String name, @JsonProperty("email") String email,
            @JsonProperty("password") String password) {
        this.id = UUID.randomUUID().getMostSignificantBits();
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

}
