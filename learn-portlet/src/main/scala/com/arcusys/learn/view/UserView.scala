package com.arcusys.learn.view

import com.arcusys.scala.scalatra.mustache.MustacheSupport
import javax.portlet._
import org.scalatra.ScalatraFilter
import com.arcusys.learn.view.liferay.LiferayHelpers
import com.arcusys.learn.scorm.tracking.model.User
import java.io.FileNotFoundException
import com.liferay.portal.util.PortalUtil
import com.arcusys.learn.scorm.manifest.model.ScopeType
import com.arcusys.learn.service.util.SessionHandler
import javax.servlet.http.Cookie

class UserView extends GenericPortlet with ScalatraFilter with MustacheSupport with i18nSupport with ConfigurableView {
  override def destroy() {}

  override def doView(request: RenderRequest, response: RenderResponse) {
    val userUID = if (request.getRemoteUser != null) request.getRemoteUser.toInt else null.asInstanceOf[Int]
    if (userUID != null && userStorage.getByID(userUID).isEmpty) {
      userStorage.createAndGetID(User(userUID, LiferayHelpers.getUserName(request)))
    }
    val themeDisplay = LiferayHelpers.getThemeDisplay(request)
    val courseID = themeDisplay.getLayout.getGroupId

    val sessionID = SessionHandler.getSessionID(request.getRemoteUser)
    val cookie = new Cookie("valamisSessionID", sessionID)
    cookie.setMaxAge(-1)
    cookie.setPath("/")
    response.addProperty(cookie)
    SessionHandler.setAttribute(sessionID, "userID", request.getRemoteUser)
    SessionHandler.setAttribute(sessionID, "isAdmin", userManagement.isAdmin(userUID, courseID))
    SessionHandler.setAttribute(sessionID, "hasTeacherPermissions", userManagement.hasTeacherPermissions(userUID, courseID))

    val httpServletRequest = PortalUtil.getHttpServletRequest(request)
    httpServletRequest.getSession.setAttribute("userID", userUID)

    val out = response.getWriter
    val language = LiferayHelpers.getLanguage(request)

    val sessionPackageId = if (httpServletRequest.getSession.getAttribute("playerID") == request.getWindowID) httpServletRequest.getSession.getAttribute("packageId") else null
    var isComplete = false

    val packageToStart = if (sessionPackageId != null) sessionPackageId
    else {
      val packID = packageService.getDefaultPackageID(themeDisplay.getLayout.getGroupId.toString, themeDisplay.getLayout.getPrimaryKey.toString, request.getWindowID)
      isComplete = packageService.checkIfCompleteByUser(packID, userUID)
      if (!isComplete) packID else None
    }
    val defaultPackageID = if (sessionPackageId != null) None else packageToStart

    val sessionPackageTitle = httpServletRequest.getSession.getAttribute("packageTitle")
    val packageTitle = if (sessionPackageId != null) sessionPackageTitle
    else packageService.getPackageTitle(packageToStart match {
      case e: Option[Int] => e.getOrElse(0)
      case _ => 0
    })

    val sessionPackageType = httpServletRequest.getSession.getAttribute("packageType")
    val packageType = sessionPackageType

    val data = Map("contextPath" -> request.getContextPath,
      "entryID" -> request.getParameter("entryID"),
      "userID" -> userUID,
      "userName" -> LiferayHelpers.getUserName(request),
      "userEmail" -> LiferayHelpers.getUserEmail(request),
      "isAdmin" -> request.isUserInRole("administrator"),
      "language" -> language,
      "packageId" -> packageToStart,
      "packageTitle" -> packageTitle,
      "packageType" -> packageType,
      "isCompleteByUser" -> isComplete,
      "defaultPackageID" -> defaultPackageID,
      "isPortlet" -> true,
      "courseID" -> themeDisplay.getLayout.getGroupId,
      "pageID" -> themeDisplay.getLayout.getPrimaryKey,
      "playerID" -> request.getWindowID
    ) ++ getPlayerTranslations(language)
    out.println(mustache(data, "player.html"))
  }

  def getPlayerTranslations(language: String) = {
    try {
      getTranslation("/i18n/player_" + language)
    } catch {
      case e: FileNotFoundException => getTranslation("/i18n/player_en")
      case _ => Map[String, String]()
    }
  }

  override def doEdit(request: RenderRequest, response: RenderResponse) {
    val out = response.getWriter
    val language = LiferayHelpers.getLanguage(request)
    val themeDisplay = LiferayHelpers.getThemeDisplay(request)
    val rule = storageFactory.playerScopeRuleStorage.get(request.getWindowID)
    val scope = if (rule == None) ScopeType.Site else rule.get.scope

    val data = Map("contextPath" -> request.getContextPath,
      "courseID" -> themeDisplay.getLayout.getGroupId,
      "pageID" -> themeDisplay.getLayout.getPrimaryKey,
      "language" -> language,
      "selectedScope" -> scope,
      "playerID" -> request.getWindowID) ++ getPlayerTranslations(language)
    out.println(mustache(data, "player_settings.html"))
  }
}