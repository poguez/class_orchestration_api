CREATE TABLE "teams" (
  "id"      BIGSERIAL PRIMARY KEY,
  "name" VARCHAR,
  "rights"   VARCHAR,
  "objectId"   BIGINT,
  "explorationId" BIGINT
);