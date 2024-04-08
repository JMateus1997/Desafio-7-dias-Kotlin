import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.imageio.ImageIO
import Movie
import androidx.compose.foundation.lazy.items


@Composable
@Preview
fun App() {

    val url_1 = "https://upload.wikimedia.org/wikipedia/pt/a/a7/Toy_Story_1995.jpg"
    val url_2 = "https://pt.wikipedia.org/wiki/Ficheiro:Movie_poster_toy_story_2.jpg"
    val url_3 = "https://a-static.mlcdn.com.br/800x560/poster-cartaz-toy-story-3-c-pop-arte-poster/poparteskins2/15938539965/2439854928f001ae7c84aac7c40c9e83.jpeg"
    val url_4 = "https://pt.wikipedia.org/wiki/Ficheiro:Toy_Story_4_poster.jpg"
    val padding = 10.dp

    MaterialTheme {
        MaterialTheme(
            colors = darkColors()
        ) {
            Surface {
                Box(
                    modifier = Modifier.background(color = Color.DarkGray).wrapContentSize().padding(16.dp),
                ) {
                    val movies = listOf<Movie>(
                        Movie(
                            titulo = "Toy Story",
                            imagem = "https://upload.wikimedia.org/wikipedia/pt/a/a7/Toy_Story_1995.jpg",
                            nota = 10.0,
                            ano = 1995
                        ),Movie(
                            titulo = "Toy Story2",
                            imagem = "https://pt.wikipedia.org/wiki/Ficheiro:Movie_poster_toy_story_2.jpg",
                            nota = 10.0,
                            ano = 1999
                        ),/*Movie(
                            titulo = "Toy Story3",
                            imagem = url_3,
                            nota = 10.0,
                            ano = 2010
                        ),Movie(
                            titulo = "Toy Story4",
                            imagem = url_4,
                            nota = 10.0,
                            ano = 2019
                        ),*/
                    )
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        items(items = movies) { movie ->
                            MovieItem(
                                movie = movie
                            )
                    }
                    }
                }
            }
        }
    }

}

@Composable
fun MovieItem(movie: Movie){

    println(movie.imagem)
    Column {
        Image(
            bitmap = loadNetworkImage(movie.imagem),
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
                        text = movie.nota.toString(),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                        )
                    )
                }
            }
            Text(
                color = Color.White,
                text = movie.ano.toString(),
            )
        }
        Text(
            modifier = Modifier.padding(10.dp),
            text = movie.titulo,
            style = TextStyle(
                color = Color.White,
                fontFamily = FontFamily.Monospace,
                fontSize = 16.sp,
            )
        )
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
fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}



