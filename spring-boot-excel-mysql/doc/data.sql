DROP TABLE IF EXISTS `dictum_base_info`;
CREATE TABLE `dictum_base_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `showed` bit(1) NOT NULL,
  `addTime` datetime DEFAULT NULL,
  `addMan` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
