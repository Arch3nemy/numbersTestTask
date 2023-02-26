package com.alacrity.numbersTestTask.ui.main.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.alacrity.numbersTestTask.entity.NumberWithFact
import com.alacrity.numbersTestTask.theme.*
import com.alacrity.numbersTestTask.util.getScreenSize

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TopSection(
    modifier: Modifier,
    onGetFactClicked: (Int) -> Unit,
    onGetRandomFactClicked: () -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                keyboardController?.hide()
                focusManager.clearFocus(true)
            }, verticalArrangement = Arrangement.SpaceAround
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Center) {
            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = TextColor,
                    disabledTextColor = TextColor,
                    leadingIconColor = Purple,
                    cursorColor = Purple,
                    unfocusedIndicatorColor = Purple,
                    focusedIndicatorColor = Purple,
                    unfocusedLabelColor = Purple,
                    focusedLabelColor = Purple
                ),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }),
                label = { Text("Enter number") }
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Purple),
                onClick = {
                    if (text.isNotEmpty()) onGetFactClicked(text.toInt())
                }) {
                Text(text = "Get fact")
            }
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Purple),
                onClick = { onGetRandomFactClicked() }) {
                Text(text = "Get random fact")
            }
        }
    }
}

@Composable
fun BottomSection(
    modifier: Modifier,
    list: List<NumberWithFact>,
    onItemClick: (NumberWithFact) -> Unit,
    onLongItemClick: (NumberWithFact) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn {
            items(list) { item ->
                RowItem(item = item,
                    onItemClick = { onItemClick(item) },
                    onLongItemClick = { onLongItemClick(item) })
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowItem(item: NumberWithFact, onItemClick: () -> Unit, onLongItemClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxSize()
                .combinedClickable(
                    onClick = { onItemClick() },
                    onLongClick = { onLongItemClick() },
                ),
            shape = RoundedCornerShape(10.dp),
            elevation = 2.dp,
        ) {
            Text(
                text = item.fact,
                style = NumberTestTaskTypography.h3,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkerGray)
                    .align(CenterVertically)
                    .padding(start = 10.dp, end = 10.dp)
            )
        }
    }
}

@Composable
fun RowWithBackButton(onBackPressed: () -> Unit) {
    val sectionHeight = getScreenSize<Float>().first / 5
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(sectionHeight.dp), verticalAlignment = Alignment.Top
    ) {
        IconButton(onClick = { onBackPressed() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "null"
            )
        }
    }
}

@Composable
fun TopSection(number: Int) {
    val sectionHeight = getScreenSize<Float>().first / 5
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(sectionHeight.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(), contentAlignment = Center
        ) {
            Text(text = number.toString(), style = NumberTestTaskTypography.h2)
        }
    }

}

@Composable
fun BottomSection(fact: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        contentAlignment = TopCenter
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(1.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp,
            backgroundColor = DarkerGray
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp),
                text = fact,
                style = NumberTestTaskTypography.h1
            )
        }
    }
}