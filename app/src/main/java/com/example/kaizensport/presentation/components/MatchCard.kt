package com.example.kaizensport.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kaizensport.domain.model.MatchEvent
import com.example.kaizensport.R
import com.example.kaizensport.util.DateConverter
import kotlinx.coroutines.delay


@Composable
fun MatchCard(
    matchEvent: MatchEvent,
    updateFavouriteMatch: () -> Unit,
    updateCountDown: (matchEvent: MatchEvent) -> Unit
) {

    val opponents = matchEvent.eventName.split("-")

    val timeToStart = remember {
        mutableStateOf(matchEvent.eventStartTime.toLong())
    }

    LaunchedEffect(key1 = timeToStart.value){

        if(timeToStart.value != 0L){
            delay(1000L)
            timeToStart.value -= 1000L
            updateCountDown(matchEvent)
        }

    }



    Column(
        modifier = Modifier
            .width(100.dp)
            .height(120.dp)
            .background(color = Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White
                )
                .padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 2.dp)
        ) {

            Text(
                text = DateConverter.untilEvent(matchEvent.eventStartTime),
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )
        }

        if (matchEvent.isEventFavourite) {
            Image(
                painter = painterResource(id = R.drawable.ic_filled_star),
                contentDescription = "Favourite Icon",
                modifier = Modifier
                    .padding(4.dp)
                    .clickable { updateFavouriteMatch() }
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_empty_star),
                contentDescription = "Not Favourite Icon",
                modifier = Modifier
                    .padding(4.dp)
                    .clickable { updateFavouriteMatch() }
            )
        }

        Text(
            text = opponents[0],
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = opponents[1],
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            color = Color.White
        )


    }

}

@Preview
@Composable
private fun PreviewComp() {
    MatchCard(
        matchEvent = MatchEvent(
            sportId = "",
            eventId = "",
            eventName = "Wigan U23 - Sunderland AFC U23",
            eventStartTime = "12:00:32",
            isEventFavourite = true
        ), {}, {}
    )
}