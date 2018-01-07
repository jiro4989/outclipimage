import java.awt.*
import java.awt.datatransfer.*
import java.awt.image.BufferedImage as BImg
import java.io.File
import java.text.SimpleDateFormat as SDF
import java.util.Date
import javax.imageio.*

// args[0] 画像の保存先
// args[1] 画像を保存する際の日付書式
fun main(args : Array<String>) {
  if (args.size < 2) {
    println("""
require arguments

args[0] export image directory
args[1] export filename format of datetime
    """)
    return
  }
  val pictDir     = args[0]
  val datetimeFmt = args[1]

  val clip = Toolkit.getDefaultToolkit().getSystemClipboard()
  try {
    // クリップボードから画像を取得
    val image = clip.getData(DataFlavor.imageFlavor) as BImg

    // 現在のタイムスタンプを取得
    val now = SDF(datetimeFmt).format(Date())

    // 画像の保存
    val imageFileName = "$pictDir/$now.png"
    val imageFile = File(imageFileName)
    ImageIO.write(image, "png", imageFile)
    println("[success] save $imageFileName")
  } catch (e: UnsupportedFlavorException) {
    // クリップボードから画像を取得しようとしてるのに
    // クリップボードに画像以外のデータが保存されてるときに吐くException
    e.printStackTrace()
  } catch (e: Exception) {
    // その他のなんらかのException
    e.printStackTrace()
  }
}
