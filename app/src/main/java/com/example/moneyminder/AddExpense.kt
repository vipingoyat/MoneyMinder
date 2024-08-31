package com.example.moneyminder

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun AddExpense() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (nameRow, list, card, topBar) = createRefs()
            Image(painter = painterResource(id = R.drawable.background_top),
                contentDescription = null,
                modifier = Modifier.constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp, start = 16.dp, end = 16.dp)
                    .constrainAs(nameRow) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Text(
                    text = "Add Expense",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
//                Button(onClick = { /*TODO*/ }) {
//                    Icon(painter = painterResource(id = R.drawable.icon), contentDescription = null)
//                }
                Image(
                    painter = painterResource(id = R.drawable.icon), contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterStart)
                )

                Image(
                    painter = painterResource(id = R.drawable.dots_menu), contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 4.dp)
                )
            }
            DataForm(modifier = Modifier
                .padding(top = 50.dp)
                .constrainAs(card) {
                    top.linkTo(nameRow.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        }
    }
}

@Composable
fun DataForm(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .shadow(15.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.size(6.dp))
        Text(text = "Type", color = Color.DarkGray, fontSize = 14.sp)
        OutlinedTextField(
            value = "", onValueChange = {}, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "Name", color = Color.DarkGray, fontSize = 14.sp)
        OutlinedTextField(
            value = "", onValueChange = {}, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "Category", color = Color.DarkGray, fontSize = 14.sp)
        OutlinedTextField(
            value = "", onValueChange = {}, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "Amount", color = Color.DarkGray, fontSize = 14.sp)
        OutlinedTextField(
            value = "", onValueChange = {}, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "Date", color = Color.DarkGray, fontSize = 14.sp)
        OutlinedTextField(
            value = "", onValueChange = {}, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
//        Text(text = "Invoice", color = Color.Black, fontSize = 14.sp)
//        OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 10.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .clip(RoundedCornerShape(2.dp))
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Text(text = "Add Expense", fontSize = 14.sp, color = Color.White)
        }
    }
}


@Composable
@Preview(showBackground = true)
fun AddExpensePreview() {
    AddExpense()
}