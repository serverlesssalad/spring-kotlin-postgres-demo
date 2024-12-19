package com.ss.demo.controller

import com.ss.demo.repository.WordRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import com.ss.demo.model.WordEntity

@RestController
@RequestMapping("/api/words")
class WordController(private val wordRepository: WordRepository) {

    // Read all words
    @GetMapping
    fun getAllWords(): List<WordEntity> {
        return wordRepository.findAll()
    }

    // Read a single word by ID
    @GetMapping("/{id}")
    fun getWordById(@PathVariable id: Long): ResponseEntity<WordEntity> {
        val word = wordRepository.findById(id)
        return if (word.isPresent) {
            ResponseEntity.ok(word.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // Create a new word
    @PostMapping
    fun createWord(@RequestBody word: WordEntity): ResponseEntity<WordEntity> {
        if (word.word.isBlank()) {
            return ResponseEntity.badRequest().build()
        }
        val savedWord = wordRepository.save(word)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWord)
    }

    // Update an existing word
    @PutMapping("/{id}")
    fun updateWord(@PathVariable id: Long, @RequestBody updatedWord: WordEntity): ResponseEntity<WordEntity> {
        val existingWord = wordRepository.findById(id)
        return if (existingWord.isPresent) {
            val wordToUpdate = existingWord.get().copy(word = updatedWord.word)
            ResponseEntity.ok(wordRepository.save(wordToUpdate))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // Delete a word
    @DeleteMapping("/{id}")
    fun deleteWord(@PathVariable id: Long): ResponseEntity<Void> {
        return if (wordRepository.existsById(id)) {
            wordRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
