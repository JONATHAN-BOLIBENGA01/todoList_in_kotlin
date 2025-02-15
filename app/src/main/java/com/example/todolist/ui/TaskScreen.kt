import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.todolist.ViewModel.TaskViewModel
import com.example.todolist.ViewModel.ViewModelFactory
import com.example.todolist.data.Task
import com.example.todolist.data.TaskRepository
import com.example.todolist.data.model.TaskDatabase
import com.example.todolist.ui.AddTaskDialog


@Composable
fun TaskItem(task: Task,onToggle:()->Unit,onClick:()->Unit) {
    Row(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        Checkbox(checked = task.isDone, onCheckedChange = { onToggle() })
        Column(modifier = Modifier
            .weight(1f)
            .padding(start = 8.dp)
            .align(Alignment.CenterVertically)
            .clickable { onClick() }) {

            Text(text = task.name, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "Début: ${task.dateDebut} - Fin: ${task.dateFin}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )


        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(navController: NavController) {
    val context = LocalContext.current
    val database = remember { TaskDatabase.getDatabase(context) }
    val repository = remember { TaskRepository(database.taskDao()) }
    val viewModel: TaskViewModel = viewModel(factory = ViewModelFactory(repository))
    var showDialog by remember { mutableStateOf(false) } // État pour afficher la boîte de dialogue
    val tasks by viewModel.tasks.collectAsState(initial = emptyList())
    Scaffold(
//centrer le texte
        topBar = {
            TopAppBar(title = { Text("Liste des taches") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Text("+") // Icône simple pour le bouton
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            LazyColumn {
                items(tasks) { task ->
                    TaskItem(task,
                        onToggle = {
                            viewModel.updateTask(task.copy(isDone = !task.isDone))
                        },
                        onClick = {navController.navigate("task_detail/${task.id}")}


                    )
                }
            }
        }
    }
    if (showDialog) {
        AddTaskDialog(
            context = context,
            onDismiss = { showDialog = false },
            onConfirm = { name, dateDebut, dateFin ->
                if (name.isNotBlank()) {
                    viewModel.addTask(name, dateDebut, dateFin)
                }
                showDialog = false
            }
        )
    }


}