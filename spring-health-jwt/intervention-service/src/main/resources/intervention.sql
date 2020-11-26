DROP TABLE IF EXISTS `intervention`;

CREATE TABLE `intervention`
(
  id              BIGINT(20) PRIMARY KEY NOT NULL,
  create_time      TIMESTAMP NOT NULL,
  user_id   BIGINT(20) NOT NULL,
  device_id          BIGINT(20) NOT NULL,
  `health_data` float DEFAULT NULL,
  intervention varchar(255) DEFAULT NULL,
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
