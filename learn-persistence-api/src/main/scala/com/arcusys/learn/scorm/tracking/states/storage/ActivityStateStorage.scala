package com.arcusys.learn.scorm.tracking.states.storage

import com.arcusys.learn.scorm.tracking.model.ActivityState

trait ActivityStateStorage {
  def getCurrentActivityStateForAttempt(attemptID: Int): Option[ActivityState]

  //TODO: do we really need this?
  //def createOrganization(treeID: Int, state: ActivityState)
  //def getOrganization(treeID: Int): Option[ActivityState]

  def createNodeItem(nodeID: Int, state: ActivityState)

  def getNodeItem(nodeID: Int): Option[ActivityState]

  def modify(attemptID: Int, state: ActivityState)
  def renew()
}
