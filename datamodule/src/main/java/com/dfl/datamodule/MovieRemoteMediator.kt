package com.dfl.datamodule

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.dfl.datamodule.dbsqlite.MovieEntity
import com.dfl.datamodule.mapper.MovieMap
import com.dfl.sharedmodule.DataResult
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val service: IDataSourceRemote,
    private val repoDatabase: IDataSourceLocal
) : RemoteMediator<Int, MovieEntity>() {

    private val GITHUB_STARTING_PAGE_INDEX = 1

    override suspend fun initialize(): InitializeAction {
        // Launch remote refresh as soon as paging starts and do not trigger remote prepend or
        // append until refresh has succeeded. In cases where we don't mind showing out-of-date,
        // cached offline data, we can return SKIP_INITIAL_REFRESH instead to prevent paging
        // triggering remote refresh.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.page ?: GITHUB_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                // If remoteKeys is null, that means the refresh result is not in the database yet.
                // We can return Success with `endOfPaginationReached = false` because Paging
                // will call this method again if RemoteKeys becomes non-null.
                // If remoteKeys is NOT NULL but its prevKey is null, that means we've reached
                // the end of pagination for prepend.
                val prevKey = remoteKeys?.page?.minus(1) ?: return MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null
                )
                if (prevKey < GITHUB_STARTING_PAGE_INDEX) GITHUB_STARTING_PAGE_INDEX else prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                // If remoteKeys is null, that means the refresh result is not in the database yet.
                // We can return Success with `endOfPaginationReached = false` because Paging
                // will call this method again if RemoteKeys becomes non-null.
                // If remoteKeys is NOT NULL but its prevKey is null, that means we've reached
                // the end of pagination for append.
                val nextKey = remoteKeys?.page?.plus(1)
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        try {
            val apiResponse = service.getMovies(page)//, state.config.pageSize)

            if (apiResponse is DataResult.Error)
                return MediatorResult.Error(apiResponse.exception)

            val movies = (apiResponse as DataResult.Success).data
            val endOfPaginationReached = movies.isEmpty()
            // clear all tables in the database
            /*if (loadType == LoadType.REFRESH) {
                repoDatabase.remoteKeysDao().clearRemoteKeys()
                repoDatabase.reposDao().clearRepos()
            }*/
            repoDatabase.saveMovies(MovieMap.getMoviesEntity(movies, page))
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private fun getRemoteKeyForLastItem(state: PagingState<Int, MovieEntity>): MovieEntity? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
    }

    private fun getRemoteKeyForFirstItem(state: PagingState<Int, MovieEntity>): MovieEntity? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, MovieEntity>
    ): MovieEntity? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { movieId ->
                repoDatabase.getMovieById(movieId)
            }
        }
    }
}
