package ru.madmax.testcase.domain.models

import com.google.gson.annotations.SerializedName

data class PostData(
    @SerializedName("audioUrl")
    val audioUrl: String = "",
    @SerializedName("author")
    val author: Author = Author(),
    @SerializedName("badges")
    val badges: Badges = Badges(),
    @SerializedName("blocks")
    val blocks: List<Block> = emptyList(),
    @SerializedName("canEdit")
    val canEdit: Boolean = false,
    @SerializedName("coAuthor")
    val coAuthor: Any = Any(),
    @SerializedName("commentEditor")
    val commentEditor: CommentEditor = CommentEditor(),
    @SerializedName("commentsSeenCount")
    val commentsSeenCount: Any = Any(),
    @SerializedName("counters")
    val counters: Counters = Counters(),
    @SerializedName("date")
    val date: Long = 0,
    @SerializedName("dateFavorite")
    val dateFavorite: Long = 0,
    @SerializedName("hitsCount")
    val hitsCount: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("isBlur")
    val isBlur: Boolean = false,
    @SerializedName("isCommentsEnabled")
    val isCommentsEnabled: Boolean = false,
    @SerializedName("isEditorial")
    val isEditorial: Boolean = false,
    @SerializedName("isFavorited")
    val isFavorited: Boolean = false,
    @SerializedName("isFilledByEditors")
    val isFilledByEditors: Boolean = false,
    @SerializedName("isLikesEnabled")
    val isLikesEnabled: Boolean = false,
    @SerializedName("isPinned")
    val isPinned: Boolean = false,
    @SerializedName("isPromoted")
    val isPromoted: Boolean = false,
    @SerializedName("isRepost")
    val isRepost: Boolean = false,
    @SerializedName("isRepostedByMe")
    val isRepostedByMe: Boolean = false,
    @SerializedName("isShowThanks")
    val isShowThanks: Boolean = false,
    @SerializedName("isStillUpdating")
    val isStillUpdating: Boolean = false,
    @SerializedName("likes")
    val likes: Likes = Likes(),
    @SerializedName("recommendedType")
    val recommendedType: String = "",
    @SerializedName("repost")
    val repost: Any = Any(),
    @SerializedName("stackedRepostsAuthors")
    val stackedRepostsAuthors: List<Any> = emptyList(),
    @SerializedName("subscribedToTreads")
    val subscribedToTreads: Boolean = false,
    @SerializedName("subsite")
    val subsite: Subsite = Subsite(),
    @SerializedName("subsiteId")
    val subsiteId: Int =0 ,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("type")
    val type: Int = 0
)