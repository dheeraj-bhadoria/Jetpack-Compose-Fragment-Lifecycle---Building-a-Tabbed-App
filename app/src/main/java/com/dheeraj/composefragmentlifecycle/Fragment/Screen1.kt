import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.dheeraj.composefragmentlifecycle.R

@Composable
fun Screen1(lifecycle: Lifecycle) {

    val onResumeEventObserver = remember(lifecycle) {
        object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_RESUME) {
                    // Handle onResume event
                    // This code will be executed when the associated Android component resumes
                    // You can perform any necessary actions here
                    println("onResume Screen1")
                }else if(event== Lifecycle.Event.ON_PAUSE){
                    println("onPause Screen1")
                }else if(event== Lifecycle.Event.ON_CREATE){
                    println("onCreate Screen1")
                }else if(event== Lifecycle.Event.ON_START){
                    println("onStart Screen1")
                }else if(event== Lifecycle.Event.ON_STOP){
                    println("onStop Screen1")
                }else if(event== Lifecycle.Event.ON_DESTROY){
                    println("onDistory Screen1")
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
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Rounded image
        Image(
            painter = painterResource(R.drawable.placeholder),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(shape = CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Name
        Text(
            text = "John Doe",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Designation
        Text(
            text = "Software Engineer",
            style = MaterialTheme.typography.subtitle1
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mobile
        Text(
            text = "Mobile: 5455454545",
            style = MaterialTheme.typography.body1
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Address
        Text(
            text = "Address:\n123 Main Street\nCity, State, Country",
            style = MaterialTheme.typography.body1
        )
    }



}