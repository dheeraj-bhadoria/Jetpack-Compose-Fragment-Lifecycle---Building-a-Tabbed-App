import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.dheeraj.composefragmentlifecycle.R

@Composable
fun Screen2(lifecycle: Lifecycle) {

    val onResumeEventObserver = remember(lifecycle) {
        object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_RESUME) {
                    // Handle onResume event
                    // This code will be executed when the associated Android component resumes
                    // You can perform any necessary actions here
                    println("onResume Screen2")
                }else if(event== Lifecycle.Event.ON_PAUSE){
                    println("onPause Screen2")
                }else if(event== Lifecycle.Event.ON_CREATE){
                    println("onCreate Screen2")
                }else if(event== Lifecycle.Event.ON_START){
                    println("onStart Screen2")
                }else if(event== Lifecycle.Event.ON_STOP){
                    println("onStop Screen2")
                }else if(event== Lifecycle.Event.ON_DESTROY){
                    println("onDistory Screen2")
                }
            }
        }
    }

    DisposableEffect(lifecycle) {
        lifecycle.addObserver(onResumeEventObserver)
        onDispose {
            lifecycle.removeObserver(onResumeEventObserver)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PostList()
    }


}

@Composable
fun PostList() {
    val postList = generateDummyPosts(10) // Generate dummy post data

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(postList) { post ->
            PostItem(post = post)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
fun PostItem(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // User info section
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // User profile picture
                Image(
                    painter = painterResource(R.drawable.placeholder),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(shape = CircleShape)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // User name and timestamp
                Column {
                    Text(text = post.userName, style = MaterialTheme.typography.subtitle1)
                    Text(text = post.timestamp, style = MaterialTheme.typography.caption)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Post content
            Text(text = post.content, style = MaterialTheme.typography.body1)
        }
    }
}



data class Post(
    val userName: String,
    val timestamp: String,
    val content: String
)

fun generateDummyPosts(count: Int): List<Post> {
    val dummyPosts = mutableListOf<Post>()
    for (i in 1..count) {
        dummyPosts.add(
            Post(
                userName = "User $i",
                timestamp = "2 hours ago",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla facilisi. Donec dictum faucibus nisi sed congue. Aliquam commodo laoreet neque ut consectetur. In convallis nunc ac enim condimentum, non scelerisque arcu luctus. Integer efficitur vulputate nibh, ut cursus massa aliquam ut. Quisque consequat ligula ac nulla pellentesque, eget luctus nulla sollicitudin. Mauris commodo fringilla diam, ut aliquam metus faucibus in. Aenean vitae orci in elit dapibus auctor"
            )
        )
    }
    return dummyPosts
}