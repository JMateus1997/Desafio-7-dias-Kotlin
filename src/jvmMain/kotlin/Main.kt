import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.imageio.ImageIO
import javax.swing.Painter




@Composable
@Preview
fun App() {

    val image = "https://upload.wikimedia.org/wikipedia/pt/a/a7/Toy_Story_1995.jpg"
    val image2 = "https://prod-ripcut-delivery.disney-plus.net/v1/variant/disney/26E7C634EB29C137475AE05AC983080BAE7E2E3741C49987EEA2539A40A9262C/scale?width=1200&aspectRatio=1.78&format=jpeg"
    val image3 = "https://www.google.com/imgres?imgurl=https%3A%2F%2Fstatic.wikia.nocookie.net%2Fdublagem%2Fimages%2Fa%2Fa6%2FToy_Story_poster.png%2Frevision%2Flatest%3Fcb%3D20220909172215%26path-prefix%3Dpt-br&imgrefurl=https%3A%2F%2Fdublagem.fandom.com%2Fwiki%2FToy_Story&tbnid=WkkaqxSmaxM-VM&vet=12ahUKEwiGj-vwgc39AhVpHbkGHb8JDLQQMygmegUIARC7Ag..i&docid=JbQBJKXNufk5pM&w=1000&h=1500&q=toy%20story&ved=2ahUKEwiGj-vwgc39AhVpHbkGHb8JDLQQMygmegUIARC7Ag"

    MaterialTheme {
        MaterialTheme{
            Column {
                Text("Toy Story")
                Image(
                    bitmap = loadNetworkImage(image),
                    contentDescription = "Toy Story",
                )
                Text("Nota: 10 - Ano: 1995")
            }
        }
    }

}

fun loadNetworkImage(link: String): ImageBitmap{
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
