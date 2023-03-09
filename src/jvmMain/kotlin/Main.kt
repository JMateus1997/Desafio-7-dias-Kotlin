import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.awaitAll
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.imageio.ImageIO
import javax.swing.Painter


@Composable
@Preview
fun App() {

    val image = "https://upload.wikimedia.org/wikipedia/pt/a/a7/Toy_Story_1995.jpg"
    val padding = 10.dp

    MaterialTheme {
        MaterialTheme(
            colors = darkColors()
        ) {
            Box(

                modifier = Modifier.background(color = Color.DarkGray).wrapContentSize().padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        bitmap = loadNetworkImage(image),
                        contentDescription = "Toy Story",
                    )
                    Row(
                        modifier = Modifier.padding(10.dp).requiredSize(height = 16.dp, width = 256.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Box() {
                            Row {
                                Icon(
                                    Icons.Rounded.Star,
                                    contentDescription = null,
                                    tint = Color.Yellow
                                )
                                Text(
                                    color = Color.White,
                                    text = "10",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                    )
                                )
                            }
                        }
                        Text(
                            color = Color.White,
                            text = "Ano: 1995"
                        )
                    }
                    Text(
                        modifier = Modifier.padding(padding),
                        text = "Toy Story",
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 16.sp,
                        )
                    )
                }
            }
        }
    }

}

fun loadNetworkImage(link: String): ImageBitmap {
    val url = URL(link)
    val connection = url.openConnection() as HttpURLConnection
    connection.connect()

    val inputStream = connection.inputStream
    val bufferedImage = ImageIO.read(inputStream)

    val stream = ByteArrayOutputStream()
    ImageIO.write(bufferedImage, "png", stream)
    val byteArray = stream.toByteArray()

    return org.jetbrains.skia.Image.makeFromEncoded(byteArray).toComposeImageBitmap()
}

/*fun loadImageBitmap(url: URL): ImageBitmap{
    val inputStream = url.openStream()
    val bufferedImage = inputStream.buffered()
    val stream = bufferedImage.readBytes()

    return org.jetbrains.skia.Image.makeFromEncoded(stream).toComposeImageBitmap()
}*/

/*fun loadImageBitmap(url: String): ImageBitmap =
    URL(url).openStream().buffered().use(::loadImageBitmap)
    //URL(url).openStream().buffered().use(::loadImageBitmap)*/





fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
