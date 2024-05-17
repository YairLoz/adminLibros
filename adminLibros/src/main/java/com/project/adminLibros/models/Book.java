package com.project.adminLibros.models;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 75)
    @Pattern(regexp = "^[\\p{L}ÁÉÍÓÚáéíóúñÑ ]*$", message = "Book name must not contain numbers, dots or at signs")
    @JsonProperty("book_name")
    private String bookName;

    @NotBlank
    @Size(min = 3, max = 75)
    @Pattern(regexp = "^[\\p{L}ÁÉÍÓÚáéíóúñÑ ]*$", message = "Book name must not contain numbers, dots or at signs")
    private String author;

    @NotNull
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
        this.id = UUID.randomUUID().getMostSignificantBits(); // System.currentTimeMillis();
        this.bookName = bookName;
        this.author = author;
        this.releaseDate = releaseDate;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
