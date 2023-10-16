package com.example.timerpicker
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.timerpicker.ui.theme.TimerPickerTheme
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerPickerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var isTimePickerVisible by remember { mutableStateOf(false) }
                        val selectedTime = remember { LocalDateTime.now() }

                        Button(
                            onClick = { isTimePickerVisible = true },
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                        ) {
                            Text(text = "Select Time")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(text = "Selected Time: ${selectedTime.hour}:${selectedTime.minute}")

                        if (isTimePickerVisible) {
                            val timePicker = remember {
                                TimePickerState(
                                    initialHour = selectedTime.hour,
                                    initialMinute = selectedTime.minute,
                                    is24Hour = false
                                )
                            }
                            TimePicker(state = timePicker)

                            Button(
                                onClick = {
                                    isTimePickerVisible = false
                                    // Atualize selectedTime com o valor selecionado no TimePicker
                                },
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(text = "OK")
                            }
                        }
                    }
                }
            }
        }
    }
}
