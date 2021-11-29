package com.codelab.basicscodelab_week2

import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.view.RoundedCorner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.BuildCompat
import coil.compose.rememberImagePainter
import com.codelab.basicscodelab_week2.ui.theme.BasicsCodelab_Week2Theme
import kotlinx.coroutines.launch


//2주차 - 레이아웃 실습
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelab_Week2Theme {
                // A surface container using the 'background' color from the theme
                /*Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }*/
                LayoutsCodelab()
            }
        }
    }
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier){
    Row (
        modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .clickable(onClick = {/*클릭 메소드*/ })
            .padding(16.dp)//위치에 따라 결과 다름
        ){
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha=0.2f)
        ){
            //이미지 넣는 곳
        }
        Column() {
            Text("Alfred Sisly", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Composable
fun LayoutsCodelab() {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ){innerPadding ->
        BodyContent(
            Modifier
                .padding(innerPadding)
                .padding(8.dp))
        //Text(text = "Hi there!", modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier){
    /*Column(modifier = modifier.padding(8.dp)){
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
    }*/
    /*MyOwnColumn(modifier.padding(8.dp)) {
        Text("MyOwnColumn")
        Text("places items")
        Text("vertically.")
        Text("We've done it by hand!")
    }*/
    /*StaggeredGrid(modifier = modifier.horizontalScroll(rememberScrollState())) {
        for(topic in topics){
            Chip(modifier = Modifier.padding(8.dp), text = topic)
        }
    }*/
    Row(modifier = modifier
        .background(color = Color.LightGray, shape = RectangleShape)
        .size(200.dp)
        .padding(16.dp)
        .horizontalScroll(rememberScrollState())
    ){
        StaggeredGrid() {
            for(topic in topics){
                Chip(modifier = Modifier.padding(8.dp), text = topic)
            }
        }
    }
}

@Composable
fun SimpleList(){
    val scrollState = rememberScrollState()

    Column(Modifier.verticalScroll(scrollState)){
        repeat(100){
            Text("Item #$it")
        }
    }
}

@Composable
fun LazyList(){
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState){
        items(100){
            Text("Item #$it")
        }
    }
}

@Composable
fun ImageListItem(index: Int){
    Row(verticalAlignment = Alignment.CenterVertically){
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android-Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun ImageList(){
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState){
        items(100){
            ImageListItem(it)
        }
    }
}

@Composable
fun ScrollingList(){
    val listSize = 100
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    Column {
        Row{
            Button(onClick = {
                coroutineScope.launch{
                    scrollState.animateScrollToItem(0)
                }
            }){
                Text("Scroll to the top")
            }
            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(listSize-1)
                }
            }){
                Text("Scroll to the end")
            }
        }
        LazyColumn(state = scrollState){
            items(listSize){
                ImageListItem(it)
            }
        }
    }
}

fun Modifier.firstBaselineTotop(
    firstBaselineToTop: Dp
) = this.then(
    layout{measurable, constraints ->
        val placeable = measurable.measure(constraints)
        check(placeable[FirstBaseline]!= AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline]
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height+placeableY
        layout(placeable.width, height){
            placeable.placeRelative(0,placeableY)
        }
    }
)

@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeables = measurables.map { measurable ->
            // Measure each child
            measurable.measure(constraints)
        }

        // Track the y co-ord we have placed children up to
        var yPosition = 0

        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Place children in the parent layout
            placeables.forEach { placeable ->
                // Position item on the screen
                placeable.placeRelative(x = 0, y = yPosition)

                // Record the y co-ord placed up to
                yPosition += placeable.height
            }
        }
    }
}

@Composable
fun StaggeredGrid(
    modifier: Modifier = Modifier,
    rows: Int = 3,
    content: @Composable () -> Unit
){
    Layout(
        modifier = modifier,
        content = content
    ){measurables, constraints ->
        val rowWidths = IntArray(rows){0}
        val rowHeights = IntArray(rows){0}
        val placeables = measurables.mapIndexed{index, measurable ->
            val placeable = measurable.measure(constraints)
            val row = index % rows
            rowWidths[row] += placeable.width
            rowHeights[row] = Math.max(rowHeights[row], placeable.height)
            placeable
        }
        val width = rowWidths.maxOrNull()
            ?.coerceIn(constraints.minWidth.rangeTo(constraints.maxWidth))?:constraints.minWidth
        val height = rowHeights.sumOf{it}
            .coerceIn(constraints.minHeight.rangeTo(constraints.maxHeight))
        val rowY = IntArray(rows){0}
        for(i in 1 until rows){
            rowY[i] = rowY[i-1]+rowHeights[i-1]
        }
        layout(width, height){
            val rowX = IntArray(rows){0}
            placeables.forEachIndexed{index, placeable ->
                val row = index % rows
                placeable.placeRelative(
                    x = rowX[row],
                    y = rowY[row]
                )
                rowX[row]+=placeable.width
            }
        }
    }
}

@Composable
fun Chip(modifier: Modifier = Modifier, text: String){
    Card(
        modifier = modifier,
        border = BorderStroke(color = Color.Black, width = Dp.Hairline),
        shape = RoundedCornerShape(8.dp)
    ){
        Row(
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .size(16.dp, 16.dp)
                    .background(color = MaterialTheme.colors.secondary)
            )
            Spacer(Modifier.width(4.dp))
            Text(text = text)
        }
    }
}

val topics = listOf(
    "Arts & Crafts", "Beauty", "Books", "Business", "Comics", "Culinary",
    "Design", "Fashion", "Film", "History", "Maths", "Music", "People", "Philosophy",
    "Religion", "Social sciences", "Technology", "TV", "Writing"
)

//create modifier'
@Stable
fun Modifier.padding(all:Dp) = (
    this.then(
        PaddingModifier(start = all, top = all, end = all, bottom = all, rtlAware = true)
    )
)

private class PaddingModifier(
    val start: Dp = 0.dp,
    val top: Dp = 0.dp,
    val end: Dp = 0.dp,
    val bottom: Dp = 0.dp,
    val rtlAware: Boolean,
): LayoutModifier {
    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult{
        val horizontal = start.roundToPx()+end.roundToPx()
        val vertical = top.roundToPx()+bottom.roundToPx()

        val placeable = measurable.measure(constraints.offset(-horizontal, -vertical))
        val width = constraints.constrainWidth(placeable.width+horizontal)
        val height = constraints.constrainHeight(placeable.height+vertical)
        return layout(width, height){
            if(rtlAware){
                placeable.placeRelative(start.roundToPx(), top.roundToPx())
            }else{
                placeable.place(start.roundToPx(), top.roundToPx())
            }
        }
    }
}

@Composable
fun ConstraintLayoutContent(){
    ConstraintLayout() {
        val (button1, button2, text) = createRefs()

        Button(
            onClick = {},
            modifier = Modifier.constrainAs(button1){
                top.linkTo(parent.top, margin = 16.dp)
            }
        ){
            Text("Button1")
        }
        Text("Text", Modifier.constrainAs(text){
            top.linkTo(button1.bottom, margin = 16.dp)
           // centerHorizontallyTo(parent)
            centerAround(button1.end)
        })
        val barrier = createEndBarrier(button1, text)
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(button2){
                top.linkTo(parent.top, margin=16.dp)
                start.linkTo(barrier)
            }
        ){
            Text("Button2")
        }
    }
}

@Composable
fun LargeConstraintLayout(){
    ConstraintLayout() {
        val text = createRef()
        val guideline = createGuidelineFromStart(0.5f)
        Text(
            "This is a very very very very very very long text",
            Modifier.constrainAs(text){
                linkTo(start = guideline, end = parent.end)
                width = Dimension.preferredWrapContent
            }
        )
    }
}

@Composable
fun DecoupledConstraintLayout(){
    BoxWithConstraints() {
        val constraints = if(maxWidth<maxHeight){
            decoupledConstraints(margin = 16.dp)
        }else{
            decoupledConstraints(margin = 32.dp)
        }
        ConstraintLayout(constraints) {
            Button(
                onClick = {},
                modifier = Modifier.layoutId("button")
            ){
                Text("Button")
            }
            Text("Text", Modifier.layoutId("text"))
        }
    }
}
private fun decoupledConstraints(margin: Dp): androidx.constraintlayout.compose.ConstraintSet {
    return ConstraintSet{
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button){
            top.linkTo(parent.top, margin = margin)
        }
        constrain(text){
            top.linkTo(button.bottom, margin)
        }
    }
}

@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1: String, text2: String){
    Row(modifier = modifier){
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start),
            text = text1
        )
        Divider(color = Color.Black, modifier = Modifier
            .fillMaxHeight()
            .width(1.dp))
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End),
            text = text2
        )
    }
}

@Preview
@Composable
fun TwoTextsPreview(){
    BasicsCodelab_Week2Theme() {
        Surface{
            TwoTexts(text1 = "hi", text2="there")
        }
    }
}

@Preview
@Composable
fun LargeConstraintLayoutPreview(){
    BasicsCodelab_Week2Theme {
        LargeConstraintLayout()
    }
}

@Preview
@Composable
fun ConstraintLayoutPreview(){
    BasicsCodelab_Week2Theme {
        ConstraintLayoutContent()
    }
}

@Preview
@Composable
fun ChipPreview()  {
    BasicsCodelab_Week2Theme() {
        Chip(text = "Hi there")
        
    }
}

@Preview
@Composable
fun TextWithPaddingToBaselinePreview(){
    BasicsCodelab_Week2Theme {
        Text("Hi there!", Modifier.firstBaselineTotop(32.dp))
    }
}

@Preview
@Composable
fun TextWithNormalPaddingPreview(){
    BasicsCodelab_Week2Theme {
        Text("Hi there!", Modifier.padding(top=32.dp))
    }
}


@Preview
@Composable
fun show(){
    //SimpleList()
    ScrollingList()
}

@Preview
@Composable
fun PhotographerCardPreview(){
    BasicsCodelab_Week2Theme {
        PhotographerCard()
    }
}

@Preview
@Composable
fun LayoutsCodelabPreview() {
    BasicsCodelab_Week2Theme {
        LayoutsCodelab()
    }
}