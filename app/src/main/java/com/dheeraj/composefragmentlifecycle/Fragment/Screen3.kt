import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun Screen3(lifecycle: Lifecycle) {

    val showDialog = remember { mutableStateOf(false) }


    val onResumeEventObserver = remember(lifecycle) {
        object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_RESUME) {
                    // Handle onResume event
                    // This code will be executed when the associated Android component resumes
                    // You can perform any necessary actions here
                    println("onResume Screen3")
                }else if(event== Lifecycle.Event.ON_PAUSE){
                    println("onPause Screen3")
                }else if(event== Lifecycle.Event.ON_CREATE){
                    println("onCreate Screen3")
                }else if(event== Lifecycle.Event.ON_START){
                    println("onStart Screen3")
                }else if(event== Lifecycle.Event.ON_STOP){
                    println("onStop Screen3")
                }else if(event== Lifecycle.Event.ON_DESTROY){
                    println("onDistory Screen3")
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
        Text(text = "Setting Screen", style = MaterialTheme.typography.h5)
        Button(
            onClick = { showDialog.value = true },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Open Dialog")
        }
    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "Screen3 Dialog") },
            text = { Text(text = "I am in screen3") },
            confirmButton = {
                Button(
                    onClick = { showDialog.value = false },
                ) {
                    Text(text = "OK")
                }
            }
        )
    }
}
