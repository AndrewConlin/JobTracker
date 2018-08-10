-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema job_tracker
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `job_tracker` ;

-- -----------------------------------------------------
-- Schema job_tracker
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `job_tracker` DEFAULT CHARACTER SET utf8 ;
USE `job_tracker` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(200) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `role` VARCHAR(50) NOT NULL DEFAULT 'standard',
  `enabled` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `board` ;

CREATE TABLE IF NOT EXISTS `board` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` TEXT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_board_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_board_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `country` ;

CREATE TABLE IF NOT EXISTS `country` (
  `country_code` CHAR(2) NOT NULL,
  `country_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`country_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(55) NOT NULL,
  `street2` VARCHAR(55) NULL,
  `city` VARCHAR(50) NOT NULL,
  `state_province` VARCHAR(50) NOT NULL,
  `zip_code` VARCHAR(10) NOT NULL,
  `country_code` CHAR(2) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_address_country_idx` (`country_code` ASC),
  CONSTRAINT `fk_address_country`
    FOREIGN KEY (`country_code`)
    REFERENCES `country` (`country_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `company` ;

CREATE TABLE IF NOT EXISTS `company` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `website_url` VARCHAR(255) NULL,
  `address_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_company_address_idx` (`address_id` ASC),
  CONSTRAINT `fk_company_address`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_status` ;

CREATE TABLE IF NOT EXISTS `job_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job` ;

CREATE TABLE IF NOT EXISTS `job` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `salary` DECIMAL(10,2) NULL,
  `post_url` VARCHAR(255) NULL,
  `description` TEXT NULL,
  `board_id` INT NOT NULL,
  `company_id` INT NULL,
  `status_id` INT NOT NULL,
  `application_date` DATETIME NULL,
  `offer_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_job_board_idx` (`board_id` ASC),
  INDEX `fk_job_company_idx` (`company_id` ASC),
  INDEX `fk_job_job_status_idx` (`status_id` ASC),
  CONSTRAINT `fk_job_board`
    FOREIGN KEY (`board_id`)
    REFERENCES `board` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_company`
    FOREIGN KEY (`company_id`)
    REFERENCES `company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_job_status`
    FOREIGN KEY (`status_id`)
    REFERENCES `job_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contact` ;

CREATE TABLE IF NOT EXISTS `contact` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(60) NOT NULL,
  `last_name` VARCHAR(60) NOT NULL,
  `phone_number` VARCHAR(45) NULL,
  `email` VARCHAR(100) NULL,
  `position` VARCHAR(50) NULL,
  `job_id` INT NOT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_contact_job_idx` (`job_id` ASC),
  CONSTRAINT `fk_contact_job`
    FOREIGN KEY (`job_id`)
    REFERENCES `job` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `note`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `note` ;

CREATE TABLE IF NOT EXISTS `note` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(45) NOT NULL,
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `job_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_note_job_idx` (`job_id` ASC),
  CONSTRAINT `fk_note_job`
    FOREIGN KEY (`job_id`)
    REFERENCES `job` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `file_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `file_location` ;

CREATE TABLE IF NOT EXISTS `file_location` (
  `id` INT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `s3Url` TEXT NOT NULL,
  `upload_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` INT NOT NULL,
  `job_id` INT NULL,
  `s3Key` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_file_user_idx` (`user_id` ASC),
  INDEX `fk_file_job_idx` (`job_id` ASC),
  CONSTRAINT `fk_file_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_file_job`
    FOREIGN KEY (`job_id`)
    REFERENCES `job` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `todo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `todo` ;

CREATE TABLE IF NOT EXISTS `todo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `task` VARCHAR(50) NOT NULL,
  `description` TEXT NULL,
  `completed` TINYINT NOT NULL DEFAULT 0,
  `due_date` DATETIME NULL,
  `complete_date` VARCHAR(45) NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` INT NOT NULL,
  `job_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_todo_user_idx` (`user_id` ASC),
  INDEX `fk_job_todo_idx` (`job_id` ASC),
  CONSTRAINT `fk_todo_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_todo`
    FOREIGN KEY (`job_id`)
    REFERENCES `job` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO tracker@localhost;
 DROP USER tracker@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'tracker'@'localhost' IDENTIFIED BY 'tracker';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'tracker'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `job_tracker`;
INSERT INTO `user` (`id`, `username`, `email`, `password`, `role`, `enabled`) VALUES (1, 'andrew', 'andrew@sd.com', 'wombat1', 'standard', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `board`
-- -----------------------------------------------------
START TRANSACTION;
USE `job_tracker`;
INSERT INTO `board` (`id`, `title`, `create_date`, `description`, `user_id`) VALUES (1, 'Job Search (7/31/18)', DEFAULT, 'Finding a dev job', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `country`
-- -----------------------------------------------------
START TRANSACTION;
USE `job_tracker`;
INSERT INTO `country` (`country_code`, `country_name`) VALUES ('US', 'United States');

COMMIT;


-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `job_tracker`;
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state_province`, `zip_code`, `country_code`) VALUES (1, '2025 S. Gilpin St.', NULL, 'Denver', 'CO', '80210', 'US');
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state_province`, `zip_code`, `country_code`) VALUES (2, '1400 E. Orchard', 'Suit 1400 N', 'Greenwood Village', 'CO', '80111', 'US');

COMMIT;


-- -----------------------------------------------------
-- Data for table `company`
-- -----------------------------------------------------
START TRANSACTION;
USE `job_tracker`;
INSERT INTO `company` (`id`, `name`, `website_url`, `address_id`) VALUES (1, 'Skill Distillery', 'http://www.skilldistillery.com', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `job_tracker`;
INSERT INTO `job_status` (`id`, `status`) VALUES (1, 'Interested');
INSERT INTO `job_status` (`id`, `status`) VALUES (2, 'Applied');
INSERT INTO `job_status` (`id`, `status`) VALUES (3, 'Interviewing');
INSERT INTO `job_status` (`id`, `status`) VALUES (4, 'Offered');
INSERT INTO `job_status` (`id`, `status`) VALUES (5, 'Rejected');
INSERT INTO `job_status` (`id`, `status`) VALUES (6, 'No Reply');

COMMIT;


-- -----------------------------------------------------
-- Data for table `job`
-- -----------------------------------------------------
START TRANSACTION;
USE `job_tracker`;
INSERT INTO `job` (`id`, `title`, `salary`, `post_url`, `description`, `board_id`, `company_id`, `status_id`, `application_date`, `offer_date`) VALUES (1, 'Instructor', 85000.00, 'http://www.indeed.com', 'Job teaching Java', 1, 1, 1, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `contact`
-- -----------------------------------------------------
START TRANSACTION;
USE `job_tracker`;
INSERT INTO `contact` (`id`, `first_name`, `last_name`, `phone_number`, `email`, `position`, `job_id`, `description`) VALUES (1, 'Kris', 'Kane', '30311112434', 'kkane106@gmail.com', 'Owner', 1, 'guy I know');

COMMIT;


-- -----------------------------------------------------
-- Data for table `note`
-- -----------------------------------------------------
START TRANSACTION;
USE `job_tracker`;
INSERT INTO `note` (`id`, `content`, `create_date`, `job_id`) VALUES (1, 'this job is decent', DEFAULT, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `file_location`
-- -----------------------------------------------------
START TRANSACTION;
USE `job_tracker`;
INSERT INTO `file_location` (`id`, `name`, `s3Url`, `upload_date`, `user_id`, `job_id`, `s3Key`) VALUES (1, 'resume', 'https://www.livecareer.com/wp-content/uploads/images/uploaded/resume-example-home/web-developer-resume-example-emphasis-2-expanded-2.png', DEFAULT, 1, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `todo`
-- -----------------------------------------------------
START TRANSACTION;
USE `job_tracker`;
INSERT INTO `todo` (`id`, `task`, `description`, `completed`, `due_date`, `complete_date`, `created_at`, `user_id`, `job_id`) VALUES (1, 'Go Round Mums', NULL, 0, NULL, NULL, DEFAULT, 1, NULL);
INSERT INTO `todo` (`id`, `task`, `description`, `completed`, `due_date`, `complete_date`, `created_at`, `user_id`, `job_id`) VALUES (2, 'Sort Life Out', NULL, 0, NULL, NULL, DEFAULT, 1, NULL);

COMMIT;

