package com.raffaelbrandao.demo.application.web.resource.responses

import com.raffaelbrandao.demo.domain.book.Book

data class BookResponse(
    val id: Long?,
    val title: String
) {
    companion object {
        fun from(book: Book) = BookResponse(
            id = book.id,
            title = book.title
        )
    }
}
