package com.ss.demo.repository

import com.ss.demo.model.WordEntity
import org.springframework.data.jpa.repository.JpaRepository

interface WordRepository : JpaRepository<WordEntity, Long>