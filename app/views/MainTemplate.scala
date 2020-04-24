package views

import play.api.i18n.Messages
import scalatags.Text
import scalatags.Text.TypedTag
import scalatags.Text.all._
import scalatags.Text.tags2.title

trait MainTemplate {

  def mainTemplate(pageTitle: String)(content: Text.Modifier*)(implicit messages: Messages): TypedTag[String] = {
    html(
      head(
        title(cls := "bold")(
          messages(pageTitle)
        ),
        link(rel := "stylesheet", `type` := "text/css", href := controllers.routes.Assets.versioned("stylesheets/custom.css").url)
      ),
      body(
        content: _*
      )
    )
  }

}
