package controllers

import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._

case class TriangleForm(al: Double, bl: Double, cl: Double)

object TriangleForm {
  val triangleForm: Form[TriangleForm] = Form(
    mapping(
      "A" -> of(doubleFormat),
      "B" -> of(doubleFormat),
      "C" -> of(doubleFormat)
    )(TriangleForm.apply)(TriangleForm.unapply)
  )
}
