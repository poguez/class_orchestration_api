CREATE TABLE "users" (
  "id"       BIGSERIAL PRIMARY KEY,
  "name" VARCHAR NOT NULL,
  "password" VARCHAR NOT NULL,
  "isAdmin" BOOLEAN DEFAULT FALSE,
  "teamId" BIGINT
);