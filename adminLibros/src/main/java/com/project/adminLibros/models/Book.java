package com.project.adminLibros.models;

import com.fasterxml.jackson.annotation.*;

import java.util.Date;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    private Long id;

    @JsonProperty("book_name")
    private String bookName;

    private String author;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @JsonProperty("release_date")
    private Date releaseDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @JsonProperty("created_at")
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @JsonProperty("updated_at")
    private Date updatedAt;

    @JsonCreator
    public Book(@JsonProperty("book_name") String bookName, @JsonProperty("author") String author,
            @JsonProperty("release_date") Date releaseDate) {
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.bookName = bookName;
        this.author = author;
        this.releaseDate = releaseDate;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
