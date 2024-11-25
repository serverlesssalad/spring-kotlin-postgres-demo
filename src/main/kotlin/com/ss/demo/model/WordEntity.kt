package com.ss.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "random_table")
data class WordEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "word")
    val word: String
)