package com.noedominguez.class_orchestration.restapi.models

case class UserEntity(id: Option[Long],
                      name: String,
                      password: String,
                      isAdmin: Boolean,
                      teamId: Option[Long]) {

  require(!name.isEmpty, "name.empty")
  require(!password.isEmpty, "password.empty")
}

case class UserEntityUpdate(name: Option[String] = None,
                            password: Option[String] = None,
                            isAdmin: Option[Boolean] = None,
                            teamId: Option[Long] = None) {

  def merge(user: UserEntity): UserEntity = {
    UserEntity(user.id,
      name.getOrElse(user.name),
      password.getOrElse(user.password),
      isAdmin.getOrElse(user.isAdmin),
      Some(teamId).getOrElse(user.teamId)
    )
  }

}