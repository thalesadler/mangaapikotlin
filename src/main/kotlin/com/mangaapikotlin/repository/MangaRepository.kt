package com.mangaapikotlin.repository

import com.mangaapikotlin.model.EstadoManga
import com.mangaapikotlin.model.Manga
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface MangaRepository : JpaRepository<Manga, Long> {
    fun findByEstado(status: EstadoManga): List<Manga>
}