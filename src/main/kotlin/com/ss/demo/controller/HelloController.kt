package com.ss.demo.controller

import com.ss.demo.repository.WordRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(private val wordRepository: WordRepository) {

    @GetMapping("/hello")
    fun getHello(): String {
        val word = wordRepository.findAll().firstOrNull()?.word ?: "No word found"
        return "Hello, $word!"
    }
}