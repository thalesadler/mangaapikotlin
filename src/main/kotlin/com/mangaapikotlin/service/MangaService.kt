package com.mangaapikotlin.service

import com.mangaapikotlin.model.EstadoManga
import com.mangaapikotlin.model.Manga
import com.mangaapikotlin.repository.MangaRepository
import org.springframework.stereotype.Service

@Service
class MangaService(private val mangaRepository: MangaRepository) {

    fun List(): List<Manga>{
        return mangaRepository.findAll()
    }

    fun List(status: String): List<Manga>{
        return mangaRepository.findByEstado(EstadoManga.valueOf(status))
    }

    fun Post(form: Manga): Manga {
        var result: Manga = mangaRepository.save(form)
        return result
    }

    fun Put(form: Manga): Manga? {
        var manga: Manga? = form.id?.let { mangaRepository.getById(it) }

        if (manga != null) {
            manga.nome = form.nome
            manga.capituloatual = form.capituloatual
            manga.estado = form.estado

            return manga
        }

        return null
    }

    fun Delete(id: Long) {
        mangaRepository.deleteById(id)
    }


}