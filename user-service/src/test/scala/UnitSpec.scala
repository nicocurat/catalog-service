import org.scalatest._
import org.scalatestplus.play.WsScalaTestClient


trait UnitSpec extends FlatSpec with Matchers with
  OptionValues with Inside with Inspectors with WsScalaTestClient
