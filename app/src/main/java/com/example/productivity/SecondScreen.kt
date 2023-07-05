package com.example.productivity

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(vm:Todoviewmodel){
    val listflow= vm.noteListFlow.collectAsState(initial = listOf())
    val txtState= rememberSaveable {
        mutableStateOf("")
    }
    val txtid:MutableState<Long?> = rememberSaveable {
        mutableStateOf(null)
    }
    val diag= rememberSaveable {
        mutableStateOf(false)
    }
    val upd= rememberSaveable {
        mutableStateOf(false)
    }
    Scaffold (topBar = {
        TopAppBar(title = {
            Text("Things to do today", color = Color(0xFF703900))
        }, colors = TopAppBarDefaults.largeTopAppBarColors
            (MaterialTheme.colorScheme.primary))
    },floatingActionButton = {
        FloatingActionButton(onClick = {
            diag.value=true
        }, modifier = Modifier
            .padding(bottom =50.dp, start = 180.dp)
            .size(80.dp)
            .clip(CircleShape)) {
            Icon(
                Icons.Default.Add,
                contentDescription = "",
                Modifier.size(40.dp)

            )
        }
    }){
        LazyColumn{
            items(listflow.value.size){idx->
                val note= listflow.value[idx]
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        60
                            .dp
                    )
                    .clickable {
                        txtid.value = note.roomId
                        txtState.value = note.text
                        upd.value = true
                    }){

                    Box(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = note.text,
                                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }

                            IconButton(
                                onClick = { vm.deleteNote(note) },
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(20.dp)
                            ) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "",
                                    Modifier.fillMaxSize()

                                )
                            }
                        }
                    }


                    Spacer(modifier = Modifier
                        .height(0.5.dp)
                        .background(color = Color.Gray.copy(alpha = 0.4f))
                        .fillMaxWidth())
                }

            }

        }
        when(diag.value){
            true -> {
                UserEntry(OnClickSave ={vm.addNote(note =
                Todoentity(text = it))
                                       diag.value=false} ,
                    OnClickDismiss =
                {diag.value=false})
            }
            false -> {}
        }
        if(upd.value){

            UserEntry(
                txtState.value,
            OnClickSave={vm.updateNote(note =
            Todoentity(txtid.value,text = it))
                upd.value=false},
            OnClickDismiss={upd.value=false},
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserEntry(
    text:String="",
    OnClickSave:(String)->Unit,
    OnClickDismiss: ()->Unit,
){
    var textState= rememberSaveable() {
        mutableStateOf(text)
    }
    Dialog(onDismissRequest = OnClickDismiss) {
        Column(
            Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(
                    color = MaterialTheme.colorScheme
                        .background
                )
                .heightIn(min = 200.dp, max = 400.dp)
                .padding(16.dp)) {
            TextField(value = textState.value, onValueChange
            ={txt-> textState.value=txt},
                Modifier
                    .weight(1f)
                    .fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Button(onClick = OnClickDismiss ) {
                    Text(text = "Dismiss")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { OnClickSave(textState.value) }) {
                    Text(text = "Save")
                }
            }
        }
    }
}
