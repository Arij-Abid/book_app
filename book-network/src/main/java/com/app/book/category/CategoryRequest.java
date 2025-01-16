package com.app.book.category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(

        Integer id,
        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String  name,
        @NotNull(message = "204")
        Integer bookId

)
{
}
