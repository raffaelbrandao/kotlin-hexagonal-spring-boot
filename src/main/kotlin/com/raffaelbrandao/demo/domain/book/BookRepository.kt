package com.raffaelbrandao.demo.domain.book

interface BookRepository {
    fun findBooks(): List<Book>
    fun findBookById(id: Long): Book
    fun saveBook(book: Book): Book
    fun updateBook(book: Book): Book
    fun deleteBook(id: Long)
}