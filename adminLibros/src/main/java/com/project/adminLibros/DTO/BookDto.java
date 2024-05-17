package com.project.adminLibros.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {

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
    private Date releaseDate;

    private Date createdAt;
    private Date updatedAt;

}
