package com.tech.peachmedia.feature_media_posts.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.peachmedia.feature_media_posts.data.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import com.tech.peachmedia.feature_media_posts.data.datasource.remote.model.Result
import com.tech.peachmedia.feature_media_posts.presentation.model.PostView
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class PostsViewModel(private val postsRepository: PostsRepository) :
    ViewModel() {
    private val _state = MutableStateFlow(PostsState())
    val state: StateFlow<PostsState> = _state

    init {
        fetchRemoteUsers()
        fetchRemotePost()
        getAllPosts()
    }

    fun fetchRemotePost() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = postsRepository.fetchRemoteMediaPost()) {
                is Result.Loading -> {
                    _state.value = state.value.copy(
                        isRefreshingPosts = true
                    )
                }
                is Result.Success -> {
                    _state.value = state.value.copy(
                        isSuccessFetchPosts = true
                    )
                }
                is Result.Error -> {
                    _state.value = state.value.copy(
                        isErrorFetchingPosts = true,
                        errorMessagePosts = result.exception.message.toString()
                    )
                }
            }
        }
    }

    fun fetchRemoteUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = postsRepository.fetchRemoteUsers()) {
                is Result.Loading -> {
                    _state.value = state.value.copy(
                        isRefreshingUsers = true
                    )
                }
                is Result.Success -> {
                    _state.value = state.value.copy(
                        isSuccessFetchUsers = true
                    )
                }
                is Result.Error -> {
                    _state.value = state.value.copy(
                        isErrorFetchingUsers = true,
                        errorMessageUsers = result.exception.message.toString()
                    )
                }
            }
        }
    }

    fun filterPostByMediaType(mediaType: String) {
        postsRepository.filterPostByMediaType(mediaType).onEach { items ->
            _state.value = state.value.copy(
                posts = items
            )
            Timber.d("PostItems.."+mediaType+items.count())
        }.launchIn(viewModelScope)
    }

    fun getAllPosts() {
        postsRepository.getAllPosts().onEach { items ->
            _state.value = state.value.copy(
                posts = items
            )
            Timber.d("PostItems.."+items.count())
        }.launchIn(viewModelScope)
    }


    fun getPostById(documentId: String?) {
        postsRepository.getPostById(documentId).onEach { item ->
            _state.value = state.value.copy(
                post = item
            )
        }.launchIn(viewModelScope)
    }

}

data class PostsState(
    val posts: List<PostView> = emptyList(),
    val post: PostView? = null,
    val isRefreshingPosts: Boolean = false,
    val isSuccessFetchPosts: Boolean = false,
    val isErrorFetchingPosts: Boolean = false,
    val isRefreshingUsers: Boolean = false,
    val isSuccessFetchUsers: Boolean = false,
    val isErrorFetchingUsers: Boolean = false,
    val errorMessageUsers: String = "",
    val errorMessagePosts: String = ""
)
