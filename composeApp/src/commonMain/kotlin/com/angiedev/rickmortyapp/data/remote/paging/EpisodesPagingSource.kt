package com.angiedev.rickmortyapp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.angiedev.rickmortyapp.data.remote.ApiService
import com.angiedev.rickmortyapp.data.remote.responses.episodes.EpisodesWrapperResponse
import com.angiedev.rickmortyapp.domain.model.EpisodeModel

class EpisodesPagingSource(
    private val api: ApiService
) : PagingSource<Int, EpisodeModel>() {

    // Responsible for knowing when we need to load more data
    override fun getRefreshKey(state: PagingState<Int, EpisodeModel>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeModel> =
        try {
            val page = params.key ?: 1
            val response: EpisodesWrapperResponse = api.getAllEpisodes(page)
            val episodes = response.results
            val prev = if (page > 0) -1 else null
            val nextPage = if (response.info.next != null) page + 1 else null

            LoadResult.Page(
                data = episodes.map { it.toDomain() },
                prevKey = prev,
                nextKey = nextPage
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}