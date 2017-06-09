package com.noedominguez.class_orchestration.restapi.models

import com.github.tminglei.slickpg.JsonString
import io.circe.generic.auto._

case class ExplorationObjectEntity(id: Option[Long],
                                   objectState: Option[String] = None,
                                   modelName: Option[String] = None) {
}

case class ExplorationObjectEntityUpdate(objectState: Option[String] = None,
                                         modelName: Option[String] = None) {

  def merge(explorationObject: ExplorationObjectEntity): ExplorationObjectEntity = {
    ExplorationObjectEntity(explorationObject.id,
      objectState.orElse(explorationObject.objectState),
      modelName.orElse(explorationObject.modelName)
    )
  }
}

