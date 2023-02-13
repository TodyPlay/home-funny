create table home_funny_user_details
(
    id                      bigint auto_increment
        primary key,
    username                varchar(64) null,
    password                varchar(128) null,
    account_non_expired     tinyint(1) null,
    account_non_locked      tinyint(1) null,
    credentials_non_expired tinyint(1) null,
    enabled                 tinyint(1) null,
    authorities             varchar(64) null
);

