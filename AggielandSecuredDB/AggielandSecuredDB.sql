-- -----------------------------------------------------
-- CONFIG
-- -----------------------------------------------------
SET SQL_SAFE_UPDATES = 0;
-- SET system_time_zone = 'US/Eastern';


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user`;

SHOW WARNINGS;

CREATE TABLE IF NOT EXISTS `user` (
	`user_id` INT NOT NULL AUTO_INCREMENT,
	`first_name`VARCHAR(50) NOT NULL,
	`last_name` VARCHAR(50) NOT NULL,
    `user_name` VARCHAR(50) NOT NULL,
	`email` VARCHAR(100) NOT NULL,
	`password` VARCHAR(400) NOT NULL,
    `password_salt` VARCHAR(200) NOT NULL,
    `date_added` DATE NOT NULL DEFAULT (CURRENT_DATE),
	`profile_picture` BLOB,
	`user_info` TEXT,
	`major` VARCHAR(100) NOT NULL,
	`classification` VARCHAR(30) NOT NULL,
	 PRIMARY KEY (`user_id`),
     UNIQUE INDEX `user_name` (`user_name`)
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `message` ;

SHOW WARNINGS;

CREATE TABLE IF NOT EXISTS `message` (
	`message_id` INT NOT NULL AUTO_INCREMENT,
	`sender_id`  INT NOT NULL,
	`conversation_id` INT NOT NULL,
	`message_data` VARCHAR(255) NOT NULL DEFAULT '',
    `time_sent` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	 PRIMARY KEY (`message_id`)
)ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `conversation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conversation` ;

SHOW WARNINGS;

CREATE TABLE IF NOT EXISTS `conversation` (
	`conversation_id` INT NOT NULL AUTO_INCREMENT,
	`conversation_title` VARCHAR(255) NOT NULL,
	`creator_id` INT NOT NULL,
	 PRIMARY KEY (`conversation_id`)
)ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `friendship`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `friendship` ;

SHOW WARNINGS;

CREATE TABLE IF NOT EXISTS `friendship` (
	`user_one_id` INT NOT NULL,
	`user_two_id` INT NOT NULL,
	`recent_user_id` INT NOT NULL,
    `status` INT NOT NULL DEFAULT '0',
	 FOREIGN KEY (`user_one_id`) REFERENCES user(`user_id`),
  	 FOREIGN KEY (`user_two_id`) REFERENCES user(`user_id`),
  	 FOREIGN KEY (`recent_user_id`) REFERENCES user(`user_id`)
)ENGINE = InnoDB;

ALTER TABLE `friendship` ADD UNIQUE KEY `unique_users_id` (`user_one_id`,`user_two_id`);
