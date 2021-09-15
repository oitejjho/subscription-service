CREATE TABLE if not exists `subscribed_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `subscription_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL UNIQUE,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL UNIQUE,
  `first_name` varchar(100) COLLATE utf8_unicode_ci NULL,
  `gender` varchar(55) COLLATE utf8_unicode_ci NULL,
  `date_of_birth` char(10) NOT NULL,
  `consent_flag` boolean NOT NULL,
  `newsletter_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `active_flag` boolean NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;