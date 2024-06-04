import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.chatgptappfinal.compose.ChatScreen
import com.example.chatgptappfinal.ui.theme.ChatGPTAppFinalTheme
import com.example.chatgptappfinal.viewmodel.ChatViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatGPTAppFinalTheme {
                ChatScreen(viewModel = ChatViewModel())
            }
        }
    }
}