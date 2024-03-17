package com.raffaelbrandao.demo.application.web.resource

import com.raffaelbrandao.demo.application.web.resource.requests.BookRequest
import com.raffaelbrandao.demo.application.web.resource.responses.BookResponse
import com.raffaelbrandao.demo.domain.book.BookRepository
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/books")
class BookResource(@Autowired private val bookRepository: BookRepository) {
    @GetMapping("/")
    fun findBooks() = bookRepository
        .findBooks()
        .map { BookResponse.from(it) }
        .let {
            ResponseEntity.ok().body(emptyList<BookResponse>())
        }

    @GetMapping("/{id}")
    fun findBookById(@PathVariable("id") id: Long) = bookRepository
        .findBookById(id)
        .let { ResponseEntity.ok().body(BookResponse.from(it)) }

    @PostMapping("/")
    fun saveBook(@Valid @RequestBody bookRequest: BookRequest) = bookRequest
        .toBook()
        .run { bookRepository.saveBook(this) }
        .let {
            ResponseEntity
                .created(URI("/books/${it.id}"))
                .body(BookResponse.from(it))
        }

    @PutMapping("/{id}")
    fun updateBook(@PathVariable("id") id: Long, @Valid @RequestBody bookRequest: BookRequest) = bookRepository
        .findBookById(id)
        .let {
            BookRequest.to(it.id, bookRequest)
                .apply { bookRepository.updateBook(this) }
                .let { book -> ResponseEntity.accepted().body(BookResponse.from(book)) }
        }

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable("id") id: Long) =
        bookRepository.deleteBook(id).let { ResponseEntity.accepted().build<Void>() }
}