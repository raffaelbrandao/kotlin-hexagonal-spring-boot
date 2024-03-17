package com.raffaelbrandao.demo.application.web.resource.requests

import com.raffaelbrandao.demo.domain.book.Book
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class BookRequest(
    @field:NotNull
    @field:NotBlank
    @field:NotEmpty
    val title: String
) {
    fun toBook() = Book(title = title)

    companion object {
        fun to(id: Long?, bookRequest: BookRequest) = Book(
            id = id,
            title = bookRequest.title
        )
    }
}
