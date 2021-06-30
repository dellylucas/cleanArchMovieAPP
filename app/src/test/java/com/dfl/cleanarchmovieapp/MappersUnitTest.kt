package com.dfl.cleanarchmovieapp

import com.dfl.datamodule.dbsqlite.MovieEntity
import com.dfl.datamodule.mapper.MovieMap
import com.dfl.datamodule.remote.MovieRemote
import com.dfl.model.Movie
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

}