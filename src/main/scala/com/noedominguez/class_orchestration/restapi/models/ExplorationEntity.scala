package com.noedominguez.class_orchestration.restapi.models

case class ExplorationEntity(id: Option[Long],
                             teacherId: Option[Long] = None,
                             explorationObjectId: Option[Long] = None,
                             explorationMode: Option[Int] = None) {
}

case class ExplorationEntityUpdate(teacherId: Option[Long] = None,
                                   explorationObjectId: Option[Long] = None,
                                   explorationMode: Option[Int] = None) {

  def merge(exploration: ExplorationEntity): ExplorationEntity = {
    ExplorationEntity(exploration.id,
      teacherId.orElse(exploration.teacherId),
      explorationObjectId.orElse(exploration.explorationObjectId),
      explorationMode.orElse(exploration.explorationMode)
    )
  }
}

