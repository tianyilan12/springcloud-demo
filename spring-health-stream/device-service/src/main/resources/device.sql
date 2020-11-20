DROP TABLE IF EXISTS `device`;

CREATE TABLE `device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `device_code` varchar(20) DEFAULT NULL,
  `device_name` varchar(20) DEFAULT NULL,
  `health_data` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `device` VALUES ('1', '便携式血压计', 'device1', 'Sphygmomanometer', '100');