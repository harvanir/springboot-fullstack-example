CREATE TABLE PERSON
  (
    PERSON_ID bigserial NOT NULL,
    PERSON_NAME VARCHAR(200) NULL,
    DATE_OF_BIRTH TIMESTAMP NULL,
    PRIMARY KEY (PERSON_ID)
);

CREATE UNIQUE INDEX IDX_PERSON_ID ON PERSON(PERSON_ID);
CREATE INDEX IDX_PERSON_NAME ON PERSON(PERSON_NAME DESC);
CREATE INDEX IDX_DATE_OF_BIRTH ON PERSON(DATE_OF_BIRTH DESC);