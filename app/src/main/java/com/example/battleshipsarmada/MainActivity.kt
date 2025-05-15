package com.example.battleshipsarmada

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.estimateAnimationDurationMillis
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.battleshipsarmada.ui.theme.BattleshipsArmadaTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.Composable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.fadeIn
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                navigation()
            }
        }
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter", "UnrememberedMutableState")
@Composable
fun navigation() {
    var screen by remember { mutableStateOf(Screen.homepage.route) }
    var gameKey by remember { mutableStateOf(0) }

    Crossfade(
        targetState   = screen,
        animationSpec = tween(durationMillis = 1000)
    ) { route ->
        when (route) {
            Screen.homepage.route -> homepage(
                onStartGame = { screen = Screen.GamePage.route }
            )

            Screen.GamePage.route -> {
                key(gameKey) {
                    lista2 = mutableStateListOf(
                        mutableListOf(0, 0, 0, 0),
                        mutableListOf(0, 0, 0, 0),
                        mutableListOf(0, 0, 0, 0),
                        mutableListOf(0, 0, 0, 0)
                    )
                    lista1 = mutableStateListOf(
                        mutableListOf(0, 0, 0, 0),
                        mutableListOf(0, 0, 0, 0),
                        mutableListOf(0, 0, 0, 0),
                        mutableListOf(0, 0, 0, 0)
                    )
                    GamePage(
                        playfinal = { gameKey++ },
                        homefinal = { screen = Screen.homepage.route }
                    )
                }
            }

            else -> {
                homepage(onStartGame = { screen = Screen.GamePage.route })
            }


        }
    }
}
var p1=0
var p2=0

@Composable
fun homepage(onStartGame: () -> Unit) {
    var showHelp by remember { mutableStateOf(false) }
    var fok = remember { mutableStateOf(0) }
    var size = 150.dp
    val sizeup by animateDpAsState(targetValue = if (fok.value > 0) 480.dp else size, tween(2000))
    var showgame by remember { mutableStateOf(false) }
    val scope     = rememberCoroutineScope()
    val scaleAnim = remember { Animatable(1f) }
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF7D7D7D)), contentAlignment = Alignment.BottomCenter) {
//        Row {
//            Image(
//                painter = painterResource(id = R.drawable.homepaggset),
//                contentDescription = "Icon 1",
//                modifier = Modifier
//                    .size(100.dp)
//                    .offset(y = -30.dp)
//                    .clip(CircleShape)
//                    .clickable { }
//            )
//            Spacer(modifier = Modifier.width(50.dp))
        Image(
            painter = painterResource(id = R.drawable.hompagqu),
            contentDescription = "Icon 1",
            modifier = Modifier
                .size(100.dp)
                .offset(y = -30.dp)
                .clip(CircleShape)
                .clickable { showHelp = true}
        )
        // }
        Spacer(modifier = Modifier.height(80.dp))
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.hompagg11),
            contentDescription = "Icon 1",
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxSize()
        )
        Image(
            painter = painterResource(id = R.drawable.hompag2),
            contentDescription = "Icon 1",
            modifier = Modifier
                .size(sizeup)
                .graphicsLayer {
                    scaleX = scaleAnim.value
                    scaleY = scaleAnim.value
                }
                .clip(CircleShape)
                .shadow(20.dp)
                .clickable {
                    scope.launch {
                        scaleAnim.animateTo(
                            targetValue = 30f,
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = FastOutSlowInEasing
                            )
                        )
                        onStartGame()
                    }
                }
        )
    }
    LaunchedEffect(fok.value) {
        if (fok.value > 0) {
            delay(300)
            showgame = true
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                "BATTLESHIPS",
                fontSize = 40.sp,
                fontFamily = bowlbyfontfamily,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                "ARMADA",
                fontSize = 40.sp,
                fontFamily = bowlbyfontfamily,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
    if (showHelp) {
        AlertDialog(
            onDismissRequest = { showHelp = false },
            containerColor = Color(0xFF47E2D0),
            title = { Text("How to Play Battleships Armada", fontFamily = bowlbyfontfamily, fontWeight = FontWeight.Bold) },
            text = {
                Column(
                    Modifier
                        .heightIn(max = 300.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text("""
                        OBJECTIVE 
                        Be the first to sink all of your opponent’s ships.

                        SETUP 
                        • Each player has a 5×5 grid.  
                        • Fleet: one 3-cell ship, one 2-cell ship, and two 1-cell ships.  
                        • Place ships secretly, horizontally or vertically, no overlap.

                        TURN ACTIONS
                        On your turn you choose one:
                        1. Attack
                           • Select a tile on your opponent’s grid.  
                           • Hit if a ship is there (damage it), otherwise miss.  
                        2. Fortify/Defend 
                           • Move any of your undamaged ships to a valid, non-overlapping new spot.  
                           • Slide or rotate to reposition.

                        TURN INDICATOR
                        Background color shows whose turn:  
                        • Blue = Current Player  
                        • Black = Other Player

                        END GAME 
                        First to have all ships sunk loses.

                        STRATEGY TIPS  
                        • Balance offense and defense.  
                        • Watch your opponent’s pattern of misses.  
                        • Fortify just when you need it most.
                    """.trimIndent(), fontFamily = bowlbyfontfamily)
                }
            },
            confirmButton = {
                TextButton(onClick = { showHelp = false }) {
                    Text("Got it", fontFamily = bowlbyfontfamily)
                }
            }
        )
    }

    Crossfade(targetState = showgame) { show ->
        if (show) {
            onStartGame()
        }
    }
}

var a2 = listOf(
    mutableMapOf(Pair(0,0) to true),
    mutableMapOf(Pair(2,1) to true),
    mutableMapOf(Pair(0,2) to true,Pair(0,3) to true),
    mutableMapOf(Pair(2,4) to true,Pair(3,4) to true,Pair(4,4) to true)
)
var a1 = listOf(
    mutableMapOf(Pair(0,1) to true),
    mutableMapOf(Pair(2,4) to true),
    mutableMapOf(Pair(3,2) to true,Pair(3,3) to true),
    mutableMapOf(Pair(4,1) to true,Pair(4,2) to true,Pair(4,3) to true)
)
var scorea = 0
var scoreb = 0
var lista2 = mutableStateListOf(mutableListOf(0, 0, 0, 0), mutableListOf(0, 0, 0, 0), mutableListOf(0, 0, 0, 0), mutableListOf(0, 0, 0, 0))
var lista1 = mutableStateListOf(mutableListOf(0, 0, 0, 0), mutableListOf(0, 0, 0, 0), mutableListOf(0, 0, 0, 0), mutableListOf(0, 0, 0, 0))
var sizep1 = mutableStateListOf(0.dp,0.dp,0.dp,0.dp)
var sizep2 = mutableStateListOf(0.dp,0.dp,0.dp,0.dp)

fun shipforda(
    listab: SnapshotStateList<MutableList<Int>>,
    previous: List<MutableMap<Pair<Int,Int>,Boolean>>
): MutableList<MutableMap<Pair<Int,Int>,Boolean>> {
    val na = mutableListOf<MutableMap<Pair<Int,Int>,Boolean>>()

    listab.forEachIndexed { idx, i ->
        val angla = i[3] % 360
        if (i[2] == 0) {
            na += mutableMapOf()
            return@forEachIndexed
        }

        val maps = mutableMapOf<Pair<Int,Int>,Boolean>()

        fun putCell(x: Int, y: Int) {
            val key = Pair(x, y)
            val wasAlive = previous.getOrNull(idx)?.get(key) ?: true
            maps[key] = wasAlive
        }

        when (i[2]) {
            1 -> putCell(i[1], i[0])
            2 -> when (angla) {
                0   -> for (k in 0..1) putCell(i[1] + k, i[0])
                90  -> for (k in 0..1) putCell(i[1],     i[0] + k)
                180 -> for (k in 0..1) putCell(i[1] - k, i[0])
                270 -> for (k in 0..1) putCell(i[1],     i[0] - k)
            }
            3 -> when (angla) {
                0   -> for (k in 0..2) putCell(i[1] + k, i[0])
                90  -> for (k in 0..2) putCell(i[1],     i[0] + k)
                180 -> for (k in 0..2) putCell(i[1] - k, i[0])
                270 -> for (k in 0..2) putCell(i[1],     i[0] - k)
            }
        }

        na += maps
    }

    return na
}

@Composable
fun GamePage(playfinal:()->Unit,homefinal:()->Unit) {

    //  var winner by remember { mutableStateOf(0) }
    val cellsizep1 = remember {
        List(5) {
            List(5) { Animatable(50f) }
        }
    }
    val cellsizep2 = remember {
        List(5) {
            List(5) { Animatable(50f) }
        }
    }
    var finalscore1 by remember { mutableStateOf(0) }
    var finalscore2 by remember { mutableStateOf(0) }


    val boxpos = remember { Animatable(33f) }
    var licked by remember { mutableStateOf(0) }
    var mode by remember { mutableStateOf(0) }
    var toggled by remember{ mutableStateOf(false) }
    Box(modifier=Modifier.fillMaxSize()){
        if(mode==2 || mode==0){toggled=true}
        if(mode==1){toggled=false}
        LaunchedEffect(licked) {
            if (licked > 1) {
                boxpos.snapTo(-6f)
                mode = 1
                toggled = false
            }
        }

        Crossfade(targetState = mode) { mod ->
            var idate by remember { mutableStateOf(0) }
            key(licked) {
                LaunchedEffect(licked) {
                    idate=0
                }
            }
            var lickedd2 by remember { mutableStateOf(licked ) }
            LaunchedEffect(licked) {
                delay(1000)
                lickedd2 = licked
            }
            if (mod == 0 || mod == 2) {
                var lickedd1 by remember { mutableStateOf(licked % 2) }
                LaunchedEffect(licked) {
                    delay(600)
                    lickedd1 = licked % 2
                }
                Crossfade(targetState = lickedd1) { turn ->
                    Column(modifier = Modifier.graphicsLayer {
                        if (turn == 1) rotationZ = 180f
                    }) {
                        Box(modifier = Modifier
                            .weight(1f)
                            .graphicsLayer {
                                rotationZ = 180f
                            }) {
                            if(turn==1) blackgrid(mode,cellsizep2,a2)
                            if(turn==0) blackgrid(mode,cellsizep1,a1)
                            Box(contentAlignment = Alignment.BottomCenter) {
                                fortify(toggled,lickedd2,onUpdate = {licked =it},idate, onIdate = {idate=it})
                            }
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            if(turn==1){bluegrid(finalscore1, onscore1 = {finalscore1=it},lickedd2, onUpdate = { licked = it },cellsizep1,idate,mode,a1,lista1, playagain1 = {playfinal()}, homeagain1 = {homefinal()})
                            }
                            if(turn==0) {bluegrid(finalscore2, onscore1 = {finalscore2=it},lickedd2, onUpdate = {licked = it},cellsizep2,idate,mode,a2,lista2, playagain1 = {playfinal()}, homeagain1 = {homefinal()})
                            }
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .height(20.dp)
                                .graphicsLayer {}, contentAlignment = Alignment.BottomCenter) {
                                tab(boxpos, nmode = { mode = it },mod,lickedd2)
                            }
                        }
                    }
                }
            }
            if (mod == 1) {
                var lickedd1 by remember { mutableStateOf(licked % 2) }
                LaunchedEffect(licked) {
                    delay(1000)
                    lickedd1 = licked % 2
                }
                Crossfade(targetState = lickedd1) { turn ->

                    Column(modifier = Modifier.graphicsLayer {
                        if (turn == 1) rotationZ = 180f
                    }) {
                        Box(modifier = Modifier
                            .weight(1f)
                            .graphicsLayer {
                                rotationZ = 180f
                            }) {
                            if(turn==0) {bluegrid(finalscore1, onscore1 = {finalscore1=it},lickedd2, onUpdate = {licked = it},cellsizep1,idate,mode,a1,lista1, playagain1 = {playfinal()}, homeagain1 = {homefinal()})
                            }
                            if(turn==1) {bluegrid(finalscore2, onscore1 = {finalscore2=it},lickedd2, onUpdate = {licked =it},cellsizep2,idate,mode,a2,lista2, playagain1 = {playfinal()}, homeagain1 = {homefinal()})
                            }
                        }
                        Box(modifier = Modifier
                            .weight(1f)
                            .graphicsLayer {
                            }) {
                            if(turn==0) {blackgrid(mode,cellsizep2,a2)
                                Box(modifier=Modifier
                                    .fillMaxSize()
                                    .graphicsLayer { rotationZ = 180f }, contentAlignment = Alignment.Center) {
                                    Image(
                                        painter = painterResource(id = R.drawable.cannon),
                                        contentDescription = "Icon 1",
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(200.dp)
                                    )
                                }
                            }
                            if(turn==1) {
                                blackgrid(mode, cellsizep1,a1)
                                Box(modifier=Modifier
                                    .fillMaxSize()
                                    .graphicsLayer { rotationZ = 180f }, contentAlignment = Alignment.Center) {
                                    Image(
                                        painter = painterResource(id = R.drawable.cannon),
                                        contentDescription = "Icon 1",
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(200.dp)
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .height(20.dp),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                tab(boxpos, nmode = { mode = it },mod,lickedd2)
                            }
                        }
                    }
                }
            }
        }
        var k1=0
        for(k in a1) {
            for(l in k.values){
                if (l==true){
                    k1++
                }
            }
        }
        var k2=0
        for(k in a2) {
            for(l in k.values){
                if (l==true){
                    k2++
                }
            }
        }
        if(k1==0 && licked>2){
            p1++
            win(
                highScore = p1,
                score = p2,
                player      = 1,
                onPlayAgain = { playfinal() },
                onHome      = { homefinal() }
            )
        }
        if(k2==0 && licked>2){
            p2++
            win(
                highScore = p1,
                score = p2,
                player = 2,
                onPlayAgain = { playfinal() },
                onHome = { homefinal() }
            )
        }
    }
}

val bowlbyfontfamily= FontFamily(
    Font(R.font.bowlbyone)
)
val ubuntufontfamily= FontFamily(
    Font(R.font.ubuntmued)
)

@Composable
fun blackgrid(n:Int,cellsize: List<List<Animatable<Float, AnimationVector1D>>>,a: List<MutableMap<Pair<Int, Int>, Boolean>> ){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D0D0D)),
        contentAlignment = Alignment.Center
    ) {
        Column {
            for (i in 1..5) {
                Row {
                    for (j in 1..5) {
                        var boxsize=cellsize[i-1][j-1]
                        Box(
                            modifier = Modifier
                                .size(56.dp) // Base size for cell
                                .background(Color(0xFF0D0D0D)),
                            contentAlignment = Alignment.Center
                        ) {
                            var match=false
                            for(k1 in a) {
                                for (k2 in k1) {
                                    if(j-1==k2.key.first && i-1==k2.key.second) {
                                        match=true
                                    }
                                }
                            }
                            if(match){
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.redcircl),
                                        contentDescription = "Icon 1",
                                        modifier = Modifier
                                            .size(32.dp)
                                            .alpha(0.2f)
                                    )
                                }
                            }
                            else {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.whitecrosbl),
                                        contentDescription = "Icon 1",
                                        modifier = Modifier
                                            .size(32.dp)
                                            .alpha(0.2f)
                                    )
                                }
                            }
                            Surface(
                                modifier = Modifier
                                    .width(boxsize.value.dp)
                                    .height(boxsize.value.dp),
                                shape = RoundedCornerShape(3.dp),
                                color = Color(0xFF1C1C1C),
                                shadowElevation = 4.dp
                            ) {}
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun fortify(toggled:Boolean, licked:Int, onUpdate: (Int) -> Unit,idate:Int, onIdate: (Int) -> Unit){
    val aheight = remember { Animatable(10f) }
    var itext="FORTIFY"
    var jtext="Click and place"
    var ktext="undamaged ships to safety"
    var isize=0.dp
    var jsize=0.dp
    if (licked==0 || licked==1) {
        itext="DEPLOY"
        jtext="Click and place ships for"
        ktext="the best strategy"
        isize=50.dp
        jsize=120.dp
    }
    LaunchedEffect(toggled) {
        aheight.animateTo(
            targetValue = if (toggled) 270f else 0f,
            tween(durationMillis = 500)
        )
    }
    Surface(
        shape = RoundedCornerShape(
            topStart = 25.dp,
            topEnd = 25.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ),
        color= Color(0xFF4A4A4A),
        modifier = Modifier
            .fillMaxWidth()
            .height(aheight.value.dp)
            .graphicsLayer { rotationZ = 180f }
    ) {
        Column {
            Spacer(modifier = Modifier.height(9.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text=itext,
                    fontSize = 50.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = bowlbyfontfamily,
                    color = Color.White
                )
            }
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = jtext,
                    fontSize = 12.5.sp,
                    color = Color(0xFFE9E6E4),
                    textAlign = TextAlign.Center,
                    fontFamily = bowlbyfontfamily
                )
            }
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = ktext,
                    fontSize = 12.5.sp,
                    color = Color(0xFFE9E6E4),
                    textAlign = TextAlign.Center,
                    fontFamily = bowlbyfontfamily
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Row {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = Color(0xFF29DBFF).copy(alpha = if(idate==3)1f else 0.5f),
                        modifier = Modifier
                            .height(50.dp)
                            .width(120.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable(enabled=idate==3) {
                                    onUpdate(licked + 1)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "SAVE",
                                fontSize = 35.sp,
                                color = Color.White.copy(alpha = if(idate==3)1f else 0.5f),
                                textAlign = TextAlign.Center,
                                fontFamily = bowlbyfontfamily,
                            )
                        }
                    }
                    Spacer(modifier=Modifier.width(20.dp))
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = Color(0xFFFF00FF),
                        modifier = Modifier
                            .height(isize)
                            .width(jsize),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {},
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "AUTO",
                                fontSize = 35.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontFamily = bowlbyfontfamily,
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Row {
                    Box(modifier = Modifier
                        .size(42.dp)
                        .clickable {}) {
                        Image(
                            painter = painterResource(id = R.drawable.binner),
                            contentDescription = "Icon 1",
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(42.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(32.dp))
                    Box(modifier = Modifier
                        .size(42.dp)
                        .clickable(enabled = idate<3) { onIdate(idate + 1) }) {
                        Image(
                            painter = painterResource(id = R.drawable.tickker),
                            contentDescription = "Icon 1",
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(42.dp)
                                .alpha(if(idate<3) 1f else 0.5f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun bluegrid(score1: Int,onscore1: (Int) -> Unit,n: Int, onUpdate: (Int) -> Unit, cellsize: List<List<Animatable<Float, AnimationVector1D>>>, idate:Int, mode:Int, a: List<MutableMap<Pair<Int, Int>, Boolean>>, lista: SnapshotStateList<MutableList<Int>>,playagain1:()->Unit,homeagain1:()->Unit) {
    var shipRevealTrigger by remember { mutableStateOf(0) }
    var clickcount by remember { mutableStateOf(0) }
    var awin by remember { mutableStateOf(0) }
    var visibility by remember { mutableStateOf(1) }
    val clicklist = remember { mutableStateListOf(0, 0, 0, 0) }
    var clicktrig by remember { mutableStateOf(0) }
    var curship by remember { mutableStateOf(0) }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF47E2D0))
            .height(20.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.ship2act),
                contentDescription = "Icon 1",
                modifier = Modifier
                    .size(80.dp)
                    .offset(y = (-3).dp)
                    .alpha(if (visibility == 1) 1f else 0.5f)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Image(
                painter = painterResource(id = R.drawable.ship1),
                contentDescription = "Icon 1",
                modifier = Modifier
                    .size(70.dp)
                    .alpha(if (visibility == 2) 1f else 0.5f)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Image(
                painter = painterResource(id = R.drawable.ship3),
                contentDescription = "Icon 1",
                modifier = Modifier
                    .size(38.dp)
                    .offset(y = (17).dp)
                    .alpha(if (visibility == 3) 1f else 0.5f)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Image(
                painter = painterResource(id = R.drawable.ship3),
                contentDescription = "Icon 1",
                modifier = Modifier
                    .size(38.dp)
                    .offset(y = (17).dp)
                    .alpha(if (visibility == 4) 1f else 0.5f)
            )
        }
        if (n == 0 || n == 1 || mode==2 || mode==0) {
            LaunchedEffect(clicktrig, curship) {
                clicklist[curship] = clicktrig
            }

            LaunchedEffect(curship) {
                clicktrig = 0
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    for (i in 1..5) {
                        Row() {
                            for (j in 1..5) {
                                Box(
                                    modifier = Modifier
                                        .size(56.dp)
                                        .clickable {
                                            val tempa = lista[idate][0]
                                            val tempb = lista[idate][1]
                                            val templ = lista[idate][2]

                                            lista[idate][2] = shipOrder[idate]

                                            lista[idate][0] = i - 1
                                            lista[idate][1] = j - 1

                                            if (isNotCompliant(lista)) {
                                                lista[idate][0] = tempa
                                                lista[idate][1] = tempb
                                                lista[idate][2] = templ
                                            } else {
                                                clicktrig++
                                            }
                                        }
                                        .border(
                                            width = 1.dp,
                                            color = Color(0xFF008B8B),
                                            shape = RoundedCornerShape(4.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {}
                            }
                        }
                    }
                }
                curship=if(idate>curship) idate else curship
                a2= shipforda(lista2,a2)
                a1= shipforda(lista1,a1)
                key(clicktrig) {
                    if(n==0 || n==1)
                        shipreveldefend(lista, curship, clicklist ,n<2)
                    if(n>1)
                        ship22reveldefend(lista, curship, clicklist ,n<2)
                }
//                key(curship){
//                    clicktrig=0
//                }
            }
            visibility=curship+1
        }
        if (mode == 1) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    for (i in 1..5) {
                        Row() {
                            for (j in 1..5) {
                                Box(
                                    modifier = Modifier
                                        .size(56.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    var isship = false
                                    var shipalive = false
                                    if (a==a1) {
                                        a1.forEach { k ->
                                            if (k.keys.any { it.first == j - 1 && it.second == i - 1 }) {
                                                isship = true
                                                if (k.values.any { it }) {
                                                    shipalive = true
                                                }
                                            }
                                        }
                                    }
                                    if (a==a2) {
                                        a2.forEach { k ->
                                            if (k.keys.any { it.first == j - 1 && it.second == i - 1 }) {
                                                isship = true
                                                if (k.values.any { it }) {
                                                    shipalive = true
                                                }
                                            }
                                        }
                                    }

                                    if (isship && shipalive) {
                                        Image(
                                            painter = painterResource(id = R.drawable.redcircl),
                                            contentDescription = "hit",
                                            modifier = Modifier.size(32.dp)
                                        )
                                    }
                                    if (!isship) {
                                        Image(
                                            painter = painterResource(id = R.drawable.whitecros),
                                            contentDescription = "water",
                                            modifier = Modifier.size(32.dp)
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .size(56.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        val boxsize = cellsize[i - 1][j - 1]
                                        val scope = rememberCoroutineScope()
                                        Surface(
                                            modifier = Modifier
                                                .clickable(enabled = clickcount < 3){
                                                    if(a==a1) {
                                                        for (k in a1) {
                                                            for (l in k) {
                                                                if (i - 1 == l.key.second && j - 1 == l.key.first) {
                                                                    k[l.key] = false
                                                                }
                                                            }
                                                        }
                                                    }
                                                    if(a==a2) {
                                                        for (k in a2) {
                                                            for (l in k) {
                                                                if (i - 1 == l.key.second && j - 1 == l.key.first) {
                                                                    k[l.key] = false
                                                                }
                                                            }
                                                        }
                                                    }
                                                    scope.launch {
                                                        boxsize.animateTo(
                                                            0f,
                                                            tween(300)
                                                        )
                                                    }
                                                    shipRevealTrigger++
                                                    clickcount++
                                                }
                                                .width(boxsize.value.dp)
                                                .height(boxsize.value.dp),
                                            shape = RoundedCornerShape(3.dp),
                                            color = Color(0xFFD8D9D8),
                                            shadowElevation = 4.dp,
                                        ) {}
                                    }
                                }
                            }
                        }
                    }
                }
            }
            var score2=0
            key(shipRevealTrigger) {
                if(a==a1){ ship1revel(score1, onscore = {score2=it},a1,sizep1,lista,onWin = {awin=it},playagain={playagain1()},homeagain={homeagain1()})}
                if(a==a2){ ship1revel(score1, onscore = {score2=it},a2,sizep2,lista,onWin = {awin=it},playagain={playagain1()},homeagain={homeagain1()})}
                LaunchedEffect(clickcount, awin) {
                    if (clickcount > 0 && clickcount % 3 == 0 && awin == 0) {
                        onUpdate(n + 1)
                    }
                }
            }

//            onscore1(score2)
        }
    }
//    Row{
//        Box{
//            Column{
//        for(i in a1){
//            Row {
//                for(j in i){
//                    Text("${j.key.first} , ${j.key.second} -- ${j.value}       ")
//                }
//
//            }
//        }}}
//        Box{
//            Column{
//                for(i in a2){
//                    Row {
//                        for(j in i){
//                            Text("${j.key.first} , ${j.key.second} -- ${j.value}       ")
//                        }
//
//                    }
//                }}}
//
//    }

}

fun isNotCompliant(lista: SnapshotStateList<MutableList<Int>>): Boolean {
    var fa = shipforda(lista,a2)
    if(lista==lista1){
        fa = shipforda(lista,a1)}

    val occ= mutableSetOf<Pair<Int,Int>>()

    for (ship in fa) {
        for ((x, y) in ship.keys) {
            if (x < 0 || x > 4 || y < 0 || y > 4) {
                return true
            }
            if (!occ.add(x to y)) {
                return true
            }
        }
    }
    return false
}

val shipOrder = arrayOf(3,2,1,1)

@Composable
fun ship22reveldefend( lista: SnapshotStateList<MutableList<Int>>, currentShip: Int,summa: SnapshotStateList<Int>, isDeploy: Boolean){
    val tr = rememberInfiniteTransition()
    val apuls by tr.animateFloat(
        initialValue = 0.6f,
        targetValue  = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 400, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    for(ij in 0..3) {
        if(ij > currentShip && isDeploy) continue
        Spacer(modifier = Modifier.height(5.dp))
        if (ij==0) {
            lista[0][2] = shipOrder[ij]
            var j = lista[0][0]
            var i = lista[0][1]
            var angle by remember { mutableStateOf(lista[0][3].toFloat()) }
            val anglo by animateFloatAsState(angle, tween(1000))
            lista[0][3]= (angle%360).toInt()
            var pointx = (i - 1) * 56
            var pointy = (j - 2) * 56 +10
            Box(
                modifier = Modifier
                    .width(170.dp)
                    .height(50.dp)
                    .offset(x = pointx.dp, y = (pointy).dp)
                    .graphicsLayer {
                        rotationZ = anglo
                        transformOrigin = TransformOrigin(0.18f, 0.35f)
                    }
                , contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.shiparrow3),
                    contentDescription = "Icon 1",
                    modifier = Modifier
                        .size(170.dp)
                        .alpha(if (ij==currentShip) apuls else 1f)
                        .offset(y = -10.dp)
                        .clickable(enabled = ij == currentShip) {
                            var temp = lista.toMutableStateList()
                            temp[0][3] = (temp[0][3] + 90) % 360
                            if (isNotCompliant(temp) == false) {
                                angle += 90
                            }
                            temp[0][3] = (temp[0][3] - 90) % 360
                        }
                )
            }
        }
        if (ij == 1) {
            lista[1][2] = shipOrder[ij]
            var j = lista[1][0]
            var i = lista[1][1]
            var angle2 by remember { mutableStateOf(lista[1][3].toFloat()) }
            val anglo2 by animateFloatAsState(angle2, tween(1000))
            lista[1][3]= (angle2%360).toInt()
            var pointx = (i - 1.5) * 56
            var pointy = (j - 2) * 56
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(60.dp)
                    .offset(x = pointx.dp, y = (pointy).dp)
                    .graphicsLayer {
                        rotationZ = anglo2
                        transformOrigin = TransformOrigin(0.2f, 0.5f)
                    }
                , contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ship1),
                    contentDescription = "Icon 1",
                    modifier = Modifier
                        .size(100.dp)
                        .alpha(if (ij==currentShip) apuls else 1f)
                        .clickable(enabled = ij == currentShip) {
                            var temp = lista.toMutableStateList()
                            temp[1][3] = (temp[1][3] + 90) % 360
                            if (isNotCompliant(temp) == false) {
                                angle2 += 90
                            }
                            temp[1][3] = (temp[1][3] - 90) % 360
                        }
                )
            }
        }
        if (ij == 2) {
            lista[2][2] = shipOrder[ij]
            var j = lista[2][0]
            var i = lista[2][1]
            lista[2][3] = 0
            var pointx = (i - 2) * 56
            var pointy = (j - 2) * 56
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .offset(x = pointx.dp, y = (pointy).dp), contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ship3),
                    contentDescription = "Icon 1",
                    modifier = Modifier
                        .clickable {}
                        .size(50.dp)
                        .alpha(if (ij==currentShip) apuls else 1f)
                )
            }
        }
        if (ij == 3) {
            lista[3][2] = shipOrder[ij]
            var j = lista[3][0]
            var i = lista[3][1]
            lista[3][3] = 0
            var pointx = (i - 2) * 56
            var pointy = (j - 2) * 56
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .offset(x = pointx.dp, y = (pointy).dp)
                , contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ship3),
                    contentDescription = "Icon 1",
                    modifier = Modifier
                        .clickable {}
                        .size(50.dp)
                        .alpha(if (ij==currentShip) apuls else 1f)
                )
            }
        }

    }
}

@Composable
fun shipreveldefend( lista: SnapshotStateList<MutableList<Int>>, currentShip: Int, clicklist: List<Int> ,isDeploy: Boolean) {

    val tr = rememberInfiniteTransition()
    val apuls by tr.animateFloat(
        initialValue = 0.6f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 400, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    for (ij in 0..3) {
        if (ij > currentShip && isDeploy) continue
        // Spacer(modifier = Modifier.height(5.dp))
        if (clicklist[ij] > 0) {

            AnimatedVisibility(
                visible = clicklist[ij] > 0,
                enter = fadeIn(animationSpec = tween(durationMillis = 800)),
                exit = fadeOut(animationSpec = tween(durationMillis = 400)),

                ) {
                Spacer(Modifier.height(5.dp))
                when (ij) {
                    0 -> {
                        lista[0][2] = shipOrder[ij]
                        var j = lista[0][0]
                        var i = lista[0][1]
                        var angle by remember { mutableStateOf(lista[0][3].toFloat()) }
                        val anglo by animateFloatAsState(angle, tween(1000))
                        lista[0][3] = (angle % 360).toInt()
                        var pointx = (i - 1) * 56
                        var pointy = (j - 2) * 56 + 10
                        Box(
                            modifier = Modifier
                                .width(170.dp)
                                .height(50.dp)
                                .offset(x = pointx.dp, y = (pointy).dp)
                                .graphicsLayer {
                                    rotationZ = anglo
                                    transformOrigin = TransformOrigin(0.18f, 0.35f)
                                }, contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.shiparrow3),
                                contentDescription = "Icon 1",
                                modifier = Modifier
                                    .size(170.dp)
                                    .alpha(if (ij == currentShip) apuls else 1f)
                                    .offset(y = -10.dp)
                                    .clickable(enabled = ij == currentShip) {
                                        var temp = lista.toMutableStateList()
                                        temp[0][3] = (temp[0][3] + 90) % 360
                                        if (isNotCompliant(temp) == false) {
                                            angle += 90
                                        }
                                        temp[0][3] = (temp[0][3] - 90) % 360
                                    }
                            )
                        }
                    }


                    1 -> {
                        lista[1][2] = shipOrder[ij]
                        var j = lista[1][0]
                        var i = lista[1][1]
                        var angle2 by remember { mutableStateOf(lista[1][3].toFloat()) }
                        val anglo2 by animateFloatAsState(angle2, tween(1000))
                        lista[1][3] = (angle2 % 360).toInt()
                        var pointx = (i - 1.5) * 56
                        var pointy = (j - 2) * 56
                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .height(60.dp)
                                .offset(x = pointx.dp, y = (pointy).dp)
                                .graphicsLayer {
                                    rotationZ = anglo2
                                    transformOrigin = TransformOrigin(0.2f, 0.5f)
                                }, contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ship1),
                                contentDescription = "Icon 1",
                                modifier = Modifier
                                    .size(100.dp)
                                    .alpha(if (ij == currentShip) apuls else 1f)
                                    .clickable(enabled = ij == currentShip) {
                                        var temp = lista.toMutableStateList()
                                        temp[1][3] = (temp[1][3] + 90) % 360
                                        if (isNotCompliant(temp) == false) {
                                            angle2 += 90
                                        }
                                        temp[1][3] = (temp[1][3] - 90) % 360
                                    }
                            )
                        }
                    }

                    2 -> {
                        lista[2][2] = shipOrder[ij]
                        var j = lista[2][0]
                        var i = lista[2][1]
                        lista[2][3] = 0
                        var pointx = (i - 2) * 56
                        var pointy = (j - 2) * 56
                        Box(
                            modifier = Modifier
                                .size(150.dp)
                                .offset(x = pointx.dp, y = (pointy).dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ship3),
                                contentDescription = "Icon 1",
                                modifier = Modifier
                                    .clickable {}
                                    .size(50.dp)
                                    .alpha(if (ij == currentShip) apuls else 1f)
                            )
                        }
                    }

                    3 -> {
                        lista[3][2] = shipOrder[ij]
                        var j = lista[3][0]
                        var i = lista[3][1]
                        lista[3][3] = 0
                        var pointx = (i - 2) * 56
                        var pointy = (j - 2) * 56
                        Box(
                            modifier = Modifier
                                .size(150.dp)
                                .offset(x = pointx.dp, y = (pointy).dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ship3),
                                contentDescription = "Icon 1",
                                modifier = Modifier
                                    .clickable {}
                                    .size(50.dp)
                                    .alpha(if (ij == currentShip) apuls else 1f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ship1revel(score1:Int,onscore:(Int)->Unit,a: List<MutableMap<Pair<Int, Int>, Boolean>>, sizep: SnapshotStateList<Dp>, lista: SnapshotStateList<MutableList<Int>>,onWin:(Int)->Unit,playagain:()->Unit,homeagain:()->Unit){

//    LaunchedEffect (a){score =0}
    var c1=0
    for (k in a) {
        if (k.size == 1) {
            if (k.values.first() == false) {
                c1++
                val sie1 by animateDpAsState(sizep[c1-1], tween(1000))
                var angles = 0
                var j = lista[3][0]
                var i = lista[3][1]
                for (g in lista) {
                    if (g[1] == k.keys.first().first && g[0] == k.keys.first().second) {
                        angles = g[3]
                        j = g[0]
                        i = g[1]
                    }
                }
                LaunchedEffect(key1 = c1) {
                    sizep[c1-1] = 50.dp
                }
                var pointx = (i - 2) * 56
                var pointy = (j - 2) * 56
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .offset(x = pointx.dp, y = pointy.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ship3),
                            contentDescription = "Icon 1",
                            modifier = Modifier
                                .graphicsLayer { rotationZ = angles.toFloat() }
                                .size(sie1)

                        )
                        if(a==a1) {scorea+=1}
                        else scoreb+=1
                    }
                }
            }

        }
        if (k.size == 2) {
            var c2 = 0
            for (l in k) {
                if (k[l.key] == true) {
                    c2++
                }
            }
            val sie2 by animateDpAsState(sizep[2], tween(1000))
            if (c2 == 0) {
                var angles = 0
                var j = lista[1][0]
                var i = lista[1][1]
                var pointx = (i - 1.5) * 56
                var pointy = (j - 2) * 56
                for (g in lista) {
                    if (g[1] == k.keys.first().first && g[0] == k.keys.first().second) {
                        angles = g[3]
                    }
                }
                LaunchedEffect(key1 = true) {
                    sizep[2] = 100.dp
                }
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(60.dp)
                            .offset(x = pointx.dp, y = pointy.dp)
                            .graphicsLayer {
                                rotationZ = angles.toFloat()
                                transformOrigin = TransformOrigin(0.2f, 0.5f)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ship1),
                            contentDescription = "Icon 1",
                            modifier = Modifier
                                .size(sie2)
                        )
                        if(a==a1) {scorea+=2}
                        else scoreb+=2
                    }
                }
            }
        }
        if (k.size == 3) {
            var c3 = 0
            for (l in k) {
                if (k[l.key] == true) {
                    c3++
                }
            }
            val sie3 by animateDpAsState(sizep[3], tween(1000))
            if (c3 == 0) {
                var angles = 0
                var j = lista[0][0]
                var i = lista[0][1]

                var pointx = (i - 1) * 56
                var pointy = (j - 2) * 56 +10
                for (g in lista) {
                    if (g[1] == k.keys.first().first && g[0] == k.keys.first().second) {
                        angles = g[3]
                    }
                }
                LaunchedEffect(key1 = true) {
                    sizep[3] = 170.dp
                }
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier
                            .width(170.dp)
                            .height(50.dp)
                            .offset(x = pointx.dp, y = (pointy).dp)
                            .graphicsLayer {
                                rotationZ = angles.toFloat()
                                transformOrigin = TransformOrigin(0.18f, 0.35f)
                            }, contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.shiparrow3),
                            contentDescription = "Icon 1",
                            modifier = Modifier
                                .size(sie3)
                                .offset(y = -10.dp)
                        )
                        if(a==a1) {scorea+=3}
                        else scoreb+=3
                    }
                }
            }
        }
    }


}

@Composable
fun tab(boxpos: Animatable<Float, *>, nmode: (Int) -> Unit,mode:Int, licked:Int){
    val s = rememberCoroutineScope()
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        var jk=Color(0xFFD96E5B)
        var sk=Color(0xFFFF6243)
        var tes="ATTACK"
        var tescolor=Color.White
        if(boxpos.value == -6f) {
            jk=Color(0xFFBF5A47)
        }
        else if(boxpos.value == 33f){
            jk=Color(0xFF4A4A4A)
            if(licked==0 || licked==1) tes="DEPLOY"
            else tes="DEFEND"
            sk=Color.White
            tescolor=Color(0xFF2C2B2C)}


        Surface(
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp
            ),
            color = sk,
            modifier = Modifier
                .fillMaxWidth()
                .height(41.5.dp)
        ) {
            Box {
                Surface(modifier = Modifier.align(Alignment.CenterStart),color=sk) {
                    Row {
                        Spacer(modifier = Modifier.width(20.dp))
                        for (i in 1..3) {
                            Surface(
                                shape = CircleShape,
                                modifier = Modifier.size(23.dp),
                                color = Color(0xFF2C2B2C)
                            ) {}
                            Spacer(modifier = Modifier.width(6.5.dp))
                        }
                    }
                }
                Surface(modifier = Modifier.align(Alignment.Center),color=sk) {
                    Text(
                        text = tes,
                        color = tescolor,
                        fontSize = 27.sp,
                        fontFamily = ubuntufontfamily,
                        textAlign = TextAlign.Center
                    )
                }
                Surface(modifier = Modifier.align(Alignment.CenterEnd),color=sk) {
                    Row {
                        Box(
                            contentAlignment = Alignment.CenterEnd
                        ) {

                            Surface(
                                modifier = Modifier
                                    .height(30.dp)
                                    .width(65.dp),
                                shape = RoundedCornerShape(30.dp),
                                color = jk
                            ) {
                                Box(
                                    modifier = Modifier
                                        .offset(x = boxpos.value.dp)
                                        .height(30.dp)
                                        .width(65.dp)
                                        .clickable {
                                            s.launch {
                                                if (boxpos.value == -6f) {
                                                    boxpos.animateTo(33f)
                                                    nmode(2)
                                                } else {
                                                    boxpos.animateTo(-6f)
                                                    nmode(1)
                                                }
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (boxpos.value==-6f){
                                        Image(
                                            painter = painterResource(id = R.drawable.attac),
                                            contentDescription = "Icon 1",
                                            modifier = Modifier
                                                .clip(CircleShape)
                                                .size(38.dp)
                                                .align(Alignment.CenterStart)
                                        )
                                    }
                                    else{
                                        Image(
                                            painter = painterResource(id = R.drawable.dener3),
                                            contentDescription = "Icon 1",
                                            modifier = Modifier
                                                .clip(CircleShape)
                                                .size(38.dp)
                                                .align(Alignment.CenterStart)
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(5.dp))
                                }
                            }
                            if(licked==0 || licked==1) {
                                Surface(
                                    modifier = Modifier
                                        .height(30.dp)
                                        .width(65.dp),
                                    shape = RoundedCornerShape(30.dp),
                                    color = Color(0xFFBEBEBE)
                                ) { Box(
                                    modifier = Modifier
                                        .offset(x = 33.dp)
                                        .height(30.dp)
                                        .width(65.dp),
                                    contentAlignment = Alignment.Center
                                ){
                                    Image(
                                        painter = painterResource(id = R.drawable.dener4),
                                        contentDescription = "Icon 1",
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(38.dp)
                                            .align(Alignment.CenterStart)
                                    )
                                }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }
            }
        }
    }

}

@Composable
fun qwin(highScore: Int = 500, score: Int = 750, player: Int, onPlayAgain: () -> Unit = {}, onHome: () -> Unit = {}) {
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = .70f)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(320.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF47C4F3))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    "PLAYER $player WINS",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFF2C2B2C),
                    modifier = Modifier
                        .background(Color(0xFFD2B429), RoundedCornerShape(4.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF2C2B2C))
                            .clickable {
                                scope.launch {
                                    delay(1000)
                                    onPlayAgain()
                                }
                            }
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        Text(
                            "PLAY AGAIN",
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                            color = Color.White,
                            fontSize = 15.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .border(2.dp, Color(0xFF2C2B2C), RoundedCornerShape(8.dp))
                            .clickable {
                                scope.launch {
                                    delay(1000)
                                    onHome()
                                }
                            }
                            .padding(horizontal = 32.dp, vertical = 10.dp)
                    ) {
                        Text(
                            "HOME",
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                            color = Color(0xFF2C2B2C),
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun win(highScore: Int = 0, score: Int = 0, player:Int, onPlayAgain: () -> Unit = {}, onHome: () -> Unit = {}) {
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = .70f)),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .width(320.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF47C4F3))
        ) {
            Column {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White),

                    ) {
                    Spacer(modifier=Modifier.height(10.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Box(
                            modifier = Modifier
                                .width(240.dp)
                                .height(42.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0xFFD2B429)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "PLAYER $player WINS",
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color(0xFF2C2B2C)
                            )
                        }

                        Spacer(Modifier.height(6.dp))

                        Box(
                            modifier = Modifier
                                .width(240.dp)
                                .height(10.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0xFF2C2B2C))
                        )
                    }


                    Spacer(modifier=Modifier.height(20.dp))
                    Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 90.dp, start = 32.dp, end = 32.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {


                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    "PLAYER 1",
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.SansSerif,
                                    color = Color(0xFFD2B429),
                                    fontSize = 20.sp
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    highScore.toString(),
                                    fontWeight = FontWeight.ExtraBold,
                                    fontFamily = FontFamily.SansSerif,
                                    color = Color.Black,
                                    fontSize = 50.sp
                                )

                                Text(
                                    "WINS",
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.SansSerif,
                                    color = Color.Black,
                                    fontSize = 20.sp
                                )
                            }

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    "PLAYER 2",
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.SansSerif,
                                    color = Color(0xFFD2B429),
                                    fontSize = 20.sp
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    score.toString(),
                                    fontWeight = FontWeight.ExtraBold,
                                    fontFamily = FontFamily.SansSerif,
                                    color = Color.Black,
                                    fontSize = 50.sp
                                )

                                Text(
                                    "WINS",
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.SansSerif,
                                    color = Color.Black,
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }
                }


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF2C2B2C))
                                .clickable {
                                    scope.launch {
                                        delay(1000)
                                        onPlayAgain() }
                                }
                                .padding(horizontal = 20.dp, vertical = 10.dp)
                        ) {
                            Text(
                                "PLAY AGAIN",
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif,
                                color = Color.White,
                                fontSize = 15.sp
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.White)
                                .border(
                                    2.dp,
                                    Color(0xFF2C2B2C),
                                    RoundedCornerShape(8.dp)
                                )
                                .clickable {
                                    scope.launch {
                                        delay(1000)
                                        onHome() }
                                }
                                .padding(horizontal = 32.dp, vertical = 10.dp)
                        ) {
                            Text(
                                "HOME",
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif,
                                color = Color(0xFF2C2B2C),
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
