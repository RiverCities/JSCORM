package com.arcusys.learn.tincan.storage.impl.liferay

import com.arcusys.learn.storage.impl.EntityStorage
import com.arcusys.learn.tincan.model._
import com.arcusys.learn.persistence.liferay.service.LFTincanLrsEndpointLocalServiceUtil
import com.arcusys.learn.persistence.liferay.model.LFTincanLrsEndpoint

trait LFLrsEndpointStorageImpl extends EntityStorage[LrsEndpointSettings] {

  def renew() { LFTincanLrsEndpointLocalServiceUtil.removeAll()}

  def getOne(parameters: (String, Any)*): Option[LrsEndpointSettings] = {
    extract(LFTincanLrsEndpointLocalServiceUtil.getEndpoint)
  }

  def modify(entity: LrsEndpointSettings, parameters: (String, Any)*) = {
    entity match {
      case LrsEndpointSettings(endpoint, OAuthAuthorization(key, secret)) => LFTincanLrsEndpointLocalServiceUtil.setEndpoint(endpoint, "OAuth", key, secret)
      case LrsEndpointSettings(endpoint, UserBasicAuthorization) => LFTincanLrsEndpointLocalServiceUtil.setEndpoint(endpoint, "BasicUser", null, null)
      case LrsEndpointSettings(endpoint, CommonBasicAuthorization(key, secret)) => LFTincanLrsEndpointLocalServiceUtil.setEndpoint(endpoint, "BasicCommon", key, secret)
      case _ =>
    }
  }

  private def extract(entity: LFTincanLrsEndpoint): Option[LrsEndpointSettings] = {
    if (entity == null) None
    else {
      entity.getAuthType match {
        case "BasicCommon" => Some(LrsEndpointSettings(entity.getEndpoint, CommonBasicAuthorization(entity.getKey, entity.getSecret)))
        case "BasicUser" => Some(LrsEndpointSettings(entity.getEndpoint, UserBasicAuthorization))
        case "OAuth" => Some(LrsEndpointSettings(entity.getEndpoint, OAuthAuthorization(entity.getKey, entity.getSecret)))
        case _ => throw new UnsupportedOperationException("unsupported auth type : " + entity.getAuthType)
      }
    }
  }

  def getAll(parameters: (String, Any)*): Seq[LrsEndpointSettings] = throw new UnsupportedOperationException()

  def create(parameters: (String, Any)*) = throw new UnsupportedOperationException()

  def create(entity: LrsEndpointSettings, parameters: (String, Any)*) = throw new UnsupportedOperationException()

  def delete(parameters: (String, Any)*) = throw new UnsupportedOperationException()

  def modify(parameters: (String, Any)*) = throw new UnsupportedOperationException()

  def execute(sqlKey: String, parameters: (String, Any)*) = throw new UnsupportedOperationException()

  def getAll(sqlKey: String, parameters: (String, Any)*): Seq[LrsEndpointSettings] = throw new UnsupportedOperationException()

  def getOne(sqlKey: String, parameters: (String, Any)*): Option[LrsEndpointSettings] = throw new UnsupportedOperationException()

  def modify(sqlKey: String, parameters: (String, Any)*) = throw new UnsupportedOperationException()
}
