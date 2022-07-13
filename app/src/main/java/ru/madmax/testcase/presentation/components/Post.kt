package ru.madmax.testcase.presentation.components

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.startActivity
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.skydoves.landscapist.glide.GlideImage
import ru.madmax.testcase.R
import ru.madmax.testcase.domain.models.*
import ru.madmax.testcase.ui.theme.Black
import ru.madmax.testcase.util.Constants.BASE_MEDIA_URL
import ru.madmax.testcase.util.toDp
import java.util.*
import java.util.concurrent.TimeUnit

@Composable
fun Post(
    postData: PostData,
    isPlayed: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        Column(Modifier.fillMaxWidth()) {
            Header(
                postData.date,
                postData.author,
                postData.subsite
            )
            if (postData.title != "")
                when (postData.blocks.size) {
                    2 -> {
                        Column(Modifier.fillMaxSize()) {
                            Title(postData.title)
                            Description(postData.blocks[0].data.text)
                            Content(postData.blocks[1].data, isPlayed)
                        }
                    }
                    else -> {
                        Column(Modifier.fillMaxSize()) {
                            Title(postData.title)
                            Content(postData.blocks[0].data, isPlayed)
                        }
                    }
                }
            else {
                when (postData.blocks.size) {
                    2 -> {
                        Column(Modifier.fillMaxSize()) {
                            Description(postData.blocks[0].data.text)
                            Content(postData.blocks[1].data, isPlayed)
                        }
                    }
                    else -> {
                        Column(Modifier.fillMaxSize()) {
                            Content(postData.blocks[0].data, isPlayed)
                        }
                    }
                }
            }
            Futter(
                postData.counters,
                postData.likes
            )
        }

    }
}

@Composable
fun Futter(counters: Counters, likes: Likes) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_comment),
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
                .padding(
                    start = 16.dp,
                )
                .clip(RoundedCornerShape(6.dp)),
            tint = Color.Gray
        )
        Text(
            text = counters.comments.toString(),
            modifier = Modifier
                .padding(
                    start = 8.dp
                ),
            color = Color.Gray,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Normal
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = likes.counter.toString(),
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 25.dp
                ),
            color = Color(0xFF00AA11),
            style = TextStyle(
                fontSize = 16.sp
            )
        )
    }
}

@Composable
fun Content(media: BlockData, isPlayed: Boolean) {
    if (media.items.isNotEmpty()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            VideoPlayer(
                uri = BASE_MEDIA_URL + media.items[0].image.data.uuid,
                isPlayed = isPlayed,
                modifier = if (media.items[0].image.data.width != 0 || media.items[0].image.data.height != 0)
                    Modifier
                        .padding(top = 11.dp)
                        .width(media.items[0].image.data.width.toDp())
                        .height(media.items[0].image.data.height.toDp())
                else
                    Modifier
                        .padding(top = 11.dp)
            )
        }
    } else {
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val intent = Intent(
                        ACTION_VIEW,
                        Uri.parse("https://${media.video.data.external_service.name}.com/watch?v=${media.video.data.external_service.id}/")
                    )
                    startActivity(context, intent, Bundle.EMPTY)
                },
            contentAlignment = Alignment.Center
        ) {
            GlideImage(
                modifier = if (media.video.data.thumbnail.data.width != 0 || media.video.data.thumbnail.data.height != 0)
                    Modifier
                        .padding(top = 11.dp)
                        .width(media.video.data.thumbnail.data.width.toDp())
                        .height(media.video.data.thumbnail.data.height.toDp())
                else
                    Modifier
                        .padding(top = 11.dp),
                imageModel = BASE_MEDIA_URL + media.video.data.thumbnail.data.uuid
            )
            Box(
                modifier = Modifier
                    .size(100.dp, 80.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0x99000000)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = "",
                    tint = Color(0xAAFFFFFF)
                )
            }
        }
    }
}

@Composable
fun VideoPlayer(
    uri: String,
    isPlayed: Boolean,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .build()
            .apply {
                val defaultDataSourceFactory = DefaultDataSource.Factory(context)
                val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
                    context,
                    defaultDataSourceFactory
                )
                val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(uri))

                setMediaSource(source)
                prepare()
            }
    }
    exoPlayer.playWhenReady = isPlayed
    exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
    exoPlayer.repeatMode = Player.REPEAT_MODE_ALL

    DisposableEffect(
        AndroidView(modifier = modifier,
            factory = {
                PlayerView(context).apply {
                    hideController()
                    useController = false
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    player = exoPlayer
                    layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                }
            })
    ) {
        onDispose { exoPlayer.release() }
    }
}

@Composable
fun Description(description: String) {
    Text(
        text = description,
        color = Black,
        style = TextStyle(
            fontSize = 15.sp
        ),
        lineHeight = 24.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 11.dp,
                start = 16.dp,
                end = 16.dp
            )
    )
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        color = Black,
        style = TextStyle(
            fontSize = 20.sp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 11.dp,
                start = 16.dp,
                end = 16.dp
            )
    )
}

@Composable
fun Header(
    time: Long,
    author: Author,
    subsite: Subsite
) {
    val date = Date()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            imageModel = BASE_MEDIA_URL + subsite.avatar.data.uuid,
            modifier = Modifier
                .size(40.dp)
                .padding(
                    top = 17.dp,
                    start = 16.dp,
                )
                .clip(RoundedCornerShape(6.dp))
        )
        Text(
            text = subsite.name,
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    start = 8.dp
                ),
            color = Black,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Normal
            )
        )
        Text(
            text = author.name,
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    start = 12.dp
                ),
            color = Black,
            style = TextStyle(
                fontSize = 15.sp
            )
        )
        Text(
            text = TimeUnit.MILLISECONDS.toHours((date.time - time * 1000)).toString() + " часов",
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    start = 12.dp
                ),
            color = Black,
            style = TextStyle(
                fontSize = 15.sp
            )
        )
    }
}
