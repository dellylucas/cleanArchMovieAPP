package com.dfl.cleanarchmovieapp

import com.dfl.cleanarchmovieapp.data.local.dbsqlite.MovieEntity
import com.dfl.cleanarchmovieapp.data.mapper.MovieMap
import com.dfl.cleanarchmovieapp.data.remote.MovieRemote
import com.dfl.cleanarchmovieapp.domain.model.Movie
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 *pruebas unitarias sobre mapeadores
 */
class MappersUnitTest {
    @Test
    fun testMapRemoteToModel() {
        val remote = MovieRemote(1, "nemo", true, "historia", listOf(1), "image")
        val expected = Movie(1, "nemo", "historia", "image", true, 2)
        val page = 2
        val sut = MovieMap.getItems(listOf(remote), page)

        assertEquals(expected, sut.first())
    }

    @Test
    fun testMapToDB() {
        val model = Movie(1, "nemo", "historia", "image", true, 2)
        val expected = MovieEntity(1, "nemo", "historia", "image", true, 2)
        val page = 2
        val sut = MovieMap.getMoviesEntity(listOf(model), page)

        assertEquals(expected, sut.first())
    }

    @Test
    fun testMapFromDBToModel() {
        val model = MovieEntity(1, "nemo", "historia", "image", true, 2)
        val expected = Movie(1, "nemo", "historia", "image", true, 2)

        val sut = MovieMap.getMovieFromEntity(model)

        assertEquals(expected, sut)
    }

    @Test
    fun other() {
        val movie = MovieRemote(1, "nemo", true, "historia", listOf(1), "image")
        val page = 2
        // val exa = mockk<MovieRemote>()
        //every { exa } returns "6"
        val sut = MovieMap.getItems(listOf(movie), page)

        assertEquals(11, movie)
    }
}