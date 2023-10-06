package com.kimi.qrisnew.components

import android.Manifest
import android.util.Log
import android.view.MotionEvent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.kimi.qrisnew.R
import com.kimi.qrisnew.navigation.QrisNewScreens
import com.kimi.qrisnew.viewModel.BannerViewModel
import com.kimi.qrisnew.viewModel.MainViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Dashboard(
    viewModel: BannerViewModel,
    navController: NavHostController
) {

    if (viewModel.DataBanner.value.isLoading == true) {
        LoadingAnimation()
    } else {
        val viewModelMain = MainViewModel()
        val cameraPermissionResultLauncher =
            rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                viewModelMain.onPermissionResult(
                    Manifest.permission.CAMERA,
                    isGranted
                )
            }
        LaunchedEffect(key1 = true, block = {
            cameraPermissionResultLauncher.launch(Manifest.permission.CAMERA)
        })
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            ImageBackground()
            Column {
                Column(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                ) {
                    LeftText(
                        text = "Current Balance",
                        modifier = Modifier.padding(top = 50.dp, start = 10.dp),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFCCCAFF)
                        )
                    )
                    LeftText(
                        text = "Rp. 500.000",
                        modifier = Modifier.padding(start = 10.dp),
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFCCCAFF),
                        )
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(135.dp)
                            .padding(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        ),
                    ) {
                        ButtonDashboard { MenuDashboard(navController) }
                    }
                    BannerPreview(viewModel)
                }
            }
        }
    }
}

@Composable
fun LoadingAnimation(
    indicatorSize: Dp = 100.dp,
    circleColors: List<Color> = listOf(
        Color(0xFF5851D8),
        Color(0xFF833AB4),
        Color(0xFFC13584),
        Color(0xFFE1306C),
        Color(0xFFFD1D1D),
        Color(0xFFF56040),
        Color(0xFFF77737),
        Color(0xFFFCAF45),
        Color(0xFFFFDC80),
        Color(0xFF5851D8)
    ),
    animationDuration: Int = 360
) {

    val infiniteTransition = rememberInfiniteTransition(label = "")

    val rotateAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration,
                easing = LinearEasing
            )
        ), label = ""
    )

    CircularProgressIndicator(
        modifier = Modifier
            .size(size = indicatorSize)
            .rotate(degrees = rotateAnimation)
            .border(
                width = 4.dp,
                brush = Brush.sweepGradient(circleColors),
                shape = CircleShape
            ),
        progress = 1f,
        strokeWidth = 1.dp,
        color = MaterialTheme.colorScheme.background // Set background color
    )
}

@Composable
fun ButtonDashboard(content: @Composable () -> Unit = {}) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE7E0EC),
        ),
    ) {
        content()
    }
}

@Composable
fun ImageBackground() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-330).dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.round_circle_24),
            contentDescription = "background",
            colorFilter = ColorFilter.tint(Color(0xFF29A2E2)),
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .width(600.dp)
                .height(600.dp)
                .align(Alignment.TopCenter)
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ColumnWithIcon(
    content: @Composable () -> Unit,
    destination: String? = null,
    navController: NavHostController
) {
    val isSelected = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (isSelected.value) 1.1f else 1f, label = "")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .scale(scale.value)
            .padding(3.dp)
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        isSelected.value = true
                        Log.d("ColumnWithIcon", "ColumnWithIcon: $isSelected")
                    }

                    MotionEvent.ACTION_UP -> {
                        isSelected.value = false
                        if (!isSelected.value) {
                            if (destination != null) {
                                navController.navigate(destination)
                                Log.d("ColumnWithIcon", "ColumnWithIcon: $isSelected")
                            }
                        }
                    }
                }
                true
            }
    ) {
        content()
    }
}

@Composable
fun MenuDashboard(
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ColumnWithIcon(
            destination = null,
            navController = navController,
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.round_person_24),
                    contentDescription = "Account"
                )
                Text(text = "Account")
            }
        )
        ColumnWithIcon(
            destination = QrisNewScreens.CameraScreen.name,
            navController = navController,
            content = {
                Icon(
                    modifier = Modifier
                        .clickable {
                            Log.d("ColumnWithIcon", "MenuDashboard: Clicked")
                        },
                    painter = painterResource(id = R.drawable.round_qr_code_24),
                    contentDescription = "QR Code"
                )
                Text(text = "QR Code")
            }
        )
        ColumnWithIcon(
            destination = null,
            navController = navController,
            content = {
                Icon(Icons.Filled.Menu, "Transaction")
                Text(text = "Transaction")
            }
        )
    }
}

@Composable
fun LeftText(text: String, modifier: Modifier = Modifier, style: TextStyle) {
    Text(
        text = text,
        style = style,
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerPreview(
    viewModel: BannerViewModel
) {
    val data = viewModel.DataBanner.value.data?.toMutableList()
    val images = data?.map { it.image } ?: listOf()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            AutoSlidingCarousel(
                itemsCount = images.size,
                itemContent = { index ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(images[index])
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.height(200.dp)
                    )
                }
            )
        }
    }
}


@Composable
fun IndicatorDot(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = Color.Yellow /* Color.Yellow */,
    unSelectedColor: Color = Color.Gray /* Color.Gray */,
    dotSize: Dp
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unSelectedColor,
                size = dotSize
            )

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    autoSlideDuration: Long = 3000,
    pagerState: PagerState = remember { PagerState() },
    itemsCount: Int,
    itemContent: @Composable (index: Int) -> Unit,
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    LaunchedEffect(pagerState.currentPage) {
        delay(autoSlideDuration)
        pagerState.animateScrollToPage((pagerState.currentPage + 1) % itemsCount)
    }

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        HorizontalPager(count = itemsCount, state = pagerState) { page ->
            itemContent(page)
        }
        Surface(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.BottomCenter),
            shape = CircleShape,
            color = Color.Black.copy(alpha = 0.5f)
        ) {
            DotsIndicator(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                totalDots = itemsCount,
                selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage,
                dotSize = 8.dp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@Composable
fun TransactionInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = onTextChange,
        maxLines = maxLine,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()

        }),
        modifier = modifier
    )

}

@Composable
fun TransactionButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text)
    }
}
