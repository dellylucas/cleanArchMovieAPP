package com.dfl.datamodule

import com.dfl.model.Movie
import com.dfl.sharedmodule.DataResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ServiceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var expected: Movie
    private val serverFakeProvider = ServerFakeProvider()
    private var page: Int = 0

    @Before
    fun before() {
        mockWebServer = serverFakeProvider.mockWebServer
        page = 1
        expected = Movie(
            id = 508943,
            name = "Luca",
            description = "Ambientada en un precioso pueblo costero de la Riviera italiana, narra la historia sobre el paso a la edad adulta de un chico que vive un verano inolvidable lleno de helados, pasta e infinitos paseos en scooter. Luca comparte estas aventuras con su nuevo mejor amigo, pero toda la diversión se ve amenazada por un secreto muy profundo: él es un monstruo marino de un mundo que yace bajo la superficie del agua.",
            posterPath = "/pr06RihHOGE3waZQx5fs2WYUdwr.jpg",
            isAdult = false,
            page = page
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `debe traer las peliculas del servicio respuesta 200`() {

        val repo = MoviesRepository(serverFakeProvider, serverFakeProvider)
        mockWebServer.enqueueResponse("serviceFake.json", 200)

        runBlocking {
            val actual = repo.getRemoteMovies(page)

            val serviceValueMovie = (actual as? DataResult.Success)?.data
            assert(serviceValueMovie?.isNotEmpty() == true)
            assertEquals(expected, serviceValueMovie?.first())
        }
    }

    @Test
    fun `debe ser una clase de error con una ecxepcion por servicio respuesta 400`() {

        val repo = MoviesRepository(serverFakeProvider, serverFakeProvider)
        mockWebServer.enqueueResponse("serviceFake.json", 400)

        runBlocking {
            val actual = repo.getRemoteMovies(page)
            assert(actual is DataResult.Error)
        }
    }
}
