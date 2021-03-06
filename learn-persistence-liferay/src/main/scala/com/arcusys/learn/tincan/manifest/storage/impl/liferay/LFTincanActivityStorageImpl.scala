package com.arcusys.learn.tincan.manifest.storage.impl.liferay

import com.arcusys.learn.storage.impl.KeyedEntityStorage
import com.arcusys.learn.tincan.manifest.model.Activity
import com.arcusys.learn.persistence.liferay.service.LFTincanActivityLocalServiceUtil
import com.arcusys.learn.persistence.liferay.model.LFTincanActivity
import scala.collection.JavaConverters._


trait LFTincanActivityStorageImpl extends KeyedEntityStorage[Activity] {

  protected def doRenew() { LFTincanActivityLocalServiceUtil.removeAll()}

  def getOne(parameters: (String, Any)*): Option[Activity] = throw new UnsupportedOperationException

  private def getLong(value: Any): Long={
    value match {
      case i:Int => i.toLong
      case l: Long => l
      case _ => 0
    }
  }

  def getAll(parameters: (String, Any)*) = {
    parameters match {
      case Seq(("packageID", id: Any)) =>
        LFTincanActivityLocalServiceUtil.findByPackageID(getLong(id)).asScala.map(extract)
    }
  }

  private def extract(lfEntity: LFTincanActivity)={
    new Activity(
      lfEntity.getId.toInt,
      lfEntity.getTincanID,
      lfEntity.getPackageID.toInt,
      lfEntity.getActivityType,
      lfEntity.getName, lfEntity.getDescription, Option(lfEntity.getLaunch), Option(lfEntity.getResource))
  }

  def create(parameters: (String, Any)*) {throw new UnsupportedOperationException}
  def create(entity: Activity, parameters: (String, Any)*) {throw new UnsupportedOperationException}

  def delete(parameters: (String, Any)*) {
    parameters match {
      case Seq(("id", id: Any)) =>
        LFTincanActivityLocalServiceUtil.deleteLFTincanActivity(getLong(id))
      case Seq(("packageID", packageID: Any)) =>
        LFTincanActivityLocalServiceUtil.findByPackageID(getLong(packageID)).asScala.foreach(
          a=>LFTincanActivityLocalServiceUtil.deleteLFTincanActivity(getLong(a.getId)))
    }
  }

  def modify(parameters: (String, Any)*) {
    throw new UnsupportedOperationException()
//    val lfEntity = parameters match {
//      case Seq(("id", id: Int), ("title", title: String), ("summary", summary: String)) => {
//        val entity = LFTincanActivityLocalServiceUtil.findByPackageID(Array(id.toLong:java.lang.Long)).get(0)
//        entity.setTitle(title)
//        entity.setSummary(summary)
//        entity
//      }
//      case Seq(("id", id: Any), ("assetRefID", assetRefID: Any)) =>{
//        val entity = LFTincanActivityLocalServiceUtil.findByPackageID(Array(getLong(id).toLong:java.lang.Long)).get(0)
//        entity.setAssetRefID(getLong(assetRefID))
//        entity
//      }
//    }
//    LFTincanActivityLocalServiceUtil.updateLFTincanActivity(lfEntity)
  }

  def modify(entity: Activity, parameters: (String, Any)*) {throw new UnsupportedOperationException}
  def getByID(id: Int, parameters: (String, Any)*) = {
    Option(LFTincanActivityLocalServiceUtil.getLFTincanActivity(id)).map(extract)
  }

  def createAndGetID(entity: Activity, parameters: (String, Any)*) = {
    import com.arcusys.learn.storage.impl.liferay.LiferayCommon._
    val newEntity = LFTincanActivityLocalServiceUtil.createLFTincanActivity()

    newEntity.setActivityType(entity.activityType)
    newEntity.setDescription(entity.description)
    newEntity.setLaunch(entity.launch.getOrElse(""))
    newEntity.setName(entity.name)
    newEntity.setPackageID(entity.packageId)
    newEntity.setResource(entity.resource.getOrElse(""))
    newEntity.setTincanID(entity.tincanId)

    LFTincanActivityLocalServiceUtil.addLFTincanActivity(newEntity).getId.toInt
  }

  override def getAll(sql: String, parameters: (String, Any)*) = {
    throw new UnsupportedOperationException()
  }

  def createAndGetID(parameters: (String, Any)*) = {
    throw new UnsupportedOperationException
  }

  def execute(sqlKey: String, parameters: (String, Any)*) {throw new UnsupportedOperationException}

  def getOne(sqlKey: String, parameters: (String, Any)*): Option[Activity] = throw new UnsupportedOperationException

  def modify(sqlKey: String, parameters: (String, Any)*) {throw new UnsupportedOperationException}
}
