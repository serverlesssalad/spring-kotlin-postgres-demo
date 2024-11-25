package com.ss.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import com.ss.demo.model.WordEntity
import com.ss.demo.repository.WordRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringKotlinPostgresDemoApplication {

    @Bean
    fun initializeDatabase(wordRepository: WordRepository): CommandLineRunner {
        return CommandLineRunner {
            wordRepository.save(WordEntity(word = "Serverless Salad Infrapal World"))
        }
    }
}

fun main(args: Array<String>) {
	runApplication<SpringKotlinPostgresDemoApplication>(*args)
}