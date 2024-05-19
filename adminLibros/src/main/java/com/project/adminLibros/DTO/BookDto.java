package com.project.adminLibros.DTO;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 75)
    @JsonProperty("book_name")
    @Pattern(regexp = "^[\\p{L}ÁÉÍÓÚáéíóúñÑ ]*$", message = "Book name must not contain numbers or special characters")
    private String bookName;

    @NotBlank
    @Size(min = 3, max = 75)
    @Pattern(regexp = "^[\\p{L}ÁÉÍÓÚáéíóúñÑ ]*$", message = "Book name must not contain numbers or special characters")
    private String author;

    @NotNull
    @JsonProperty("release_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date releaseDate;

    private Date createdAt;
    private Date updatedAt;

    @JsonCreator
    public BookDto(@JsonProperty("book_name") String bookName,
            @JsonProperty("author") String author,
            @JsonProperty("release_date") Date releaseDate) {
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.bookName = bookName;
        this.author = author;
        this.releaseDate = releaseDate;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

}
