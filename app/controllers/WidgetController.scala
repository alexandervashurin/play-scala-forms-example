package controllers

import controllers.WidgetForm.Rectangle.area

import javax.inject.Inject
import models.RectangleModel
import play.api.data._
import play.api.i18n._
import play.api.mvc._

import scala.collection._

/**
 * The classic WidgetController using MessagesAbstractController.
 *
 * Instead of MessagesAbstractController, you can use the I18nSupport trait,
 * which provides implicits that create a Messages instance from a request
 * using implicit conversion.
 *
 * See https://www.playframework.com/documentation/2.8.x/ScalaForms#passing-messagesprovider-to-form-helpers
 * for details.
 */
class WidgetController @Inject()(cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {
  import WidgetForm._

  private val widgets = mutable.ArrayBuffer(
    RectangleModel(1, 123),//тестовые данные, для проверки работы отображения
    RectangleModel(2, 456),
    RectangleModel(44, 789)
  )

  // The URL to the widget.  You can call this directly from the template, but it
  // can be more convenient to leave the template completely stateless i.e. all
  // of the "WidgetController" references are inside the .scala file.
  private val postUrl = routes.WidgetController.createWidget

  def index: Action[AnyContent] = Action {
    Ok(views.html.index())
  }

  def listWidgets: Action[AnyContent] = Action { implicit request: MessagesRequest[AnyContent] =>
    // Pass an unpopulated form to the template
    Ok(views.html.listWidgets(widgets.toSeq, form, postUrl))
  }

  // This will be the action that handles our form post
  def createWidget: Action[AnyContent] = Action { implicit request: MessagesRequest[AnyContent] =>
    val errorFunction = { formWithErrors: Form[Rectangle] =>
      // This is the bad case, where the form had validation errors.
      // Let's show the user the form again, with the errors highlighted.
      // Note how we pass the form with errors to the template.
      BadRequest(views.html.listWidgets(widgets.toSeq, formWithErrors, postUrl))
    }

    val successFunction = { data: Rectangle =>
      // This is the good case, where the form was successfully parsed as a Data object.
      val widget = RectangleModel(length= data.length, width = data.width)
      widgets += widget
      Redirect(routes.WidgetController.listWidgets).flashing("info" -> "Прямоугольник добавлен!")
    }

    val formValidationResult = form.bindFromRequest()
    formValidationResult.fold(errorFunction, successFunction)
  }
}
