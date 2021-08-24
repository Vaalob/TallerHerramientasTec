-- MySQL Script generated by MySQL Workbench
-- Mon Aug 23 00:56:18 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema baseday
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema baseday
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `baseday` DEFAULT CHARACTER SET utf8 ;
USE `baseday` ;

-- -----------------------------------------------------
-- Table `baseday`.`servicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baseday`.`servicio` (
  `idservicio` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(255) NULL,
  `precio` DECIMAL(7,2) NOT NULL,
  `serviciocol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idservicio`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseday`.`persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baseday`.`persona` (
  `idpersona` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `documento` VARCHAR(45) NOT NULL,
  `ndocumento` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`idpersona`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `telefono_UNIQUE` (`telefono` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseday`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baseday`.`cliente` (
  `idpersona` INT NOT NULL,
  `codigo_cliente` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idpersona`),
  UNIQUE INDEX `codigo_cliente_UNIQUE` (`codigo_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_persona_cliente`
    FOREIGN KEY (`idpersona`)
    REFERENCES `baseday`.`persona` (`idpersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseday`.`trabajador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baseday`.`trabajador` (
  `idpersona` INT NOT NULL,
  `acceso` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idpersona`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  CONSTRAINT `fk_persona_trabajador`
    FOREIGN KEY (`idpersona`)
    REFERENCES `baseday`.`persona` (`idpersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseday`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baseday`.`producto` (
  `idproducto` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(255) NULL,
  `precio` DECIMAL(7,2) NOT NULL,
  PRIMARY KEY (`idproducto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseday`.`cita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baseday`.`cita` (
  `idcita` INT NOT NULL AUTO_INCREMENT,
  `idservicio` INT NOT NULL,
  `idcliente` INT NOT NULL,
  `idtrabajador` INT NOT NULL,
  `fecha_cita` DATE NOT NULL,
  `costo_total` DECIMAL(7,2) NOT NULL,
  PRIMARY KEY (`idcita`),
  INDEX `fk_cita_servicio_idx` (`idservicio` ASC) VISIBLE,
  INDEX `fk_cita_cliente_idx` (`idcliente` ASC) VISIBLE,
  INDEX `fk_cita_trabajador_idx` (`idtrabajador` ASC) VISIBLE,
  CONSTRAINT `fk_cita_servicio`
    FOREIGN KEY (`idservicio`)
    REFERENCES `baseday`.`servicio` (`idservicio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cita_cliente`
    FOREIGN KEY (`idcliente`)
    REFERENCES `baseday`.`cliente` (`idpersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cita_trabajador`
    FOREIGN KEY (`idtrabajador`)
    REFERENCES `baseday`.`trabajador` (`idpersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseday`.`consumo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baseday`.`consumo` (
  `idconsumo` INT NOT NULL AUTO_INCREMENT,
  `idcita` INT NOT NULL,
  `idproducto` INT NOT NULL,
  PRIMARY KEY (`idconsumo`),
  INDEX `fk_consumo_producto_idx` (`idproducto` ASC) VISIBLE,
  INDEX `fk_consumo_cita_idx` (`idcita` ASC) VISIBLE,
  CONSTRAINT `fk_consumo_producto`
    FOREIGN KEY (`idproducto`)
    REFERENCES `baseday`.`producto` (`idproducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_consumo_cita`
    FOREIGN KEY (`idcita`)
    REFERENCES `baseday`.`cita` (`idcita`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseday`.`pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baseday`.`pago` (
  `idpago` INT NOT NULL AUTO_INCREMENT,
  `idcita` INT NOT NULL,
  `comprobante` VARCHAR(45) NOT NULL,
  `ncomprobante` VARCHAR(45) NOT NULL,
  `total_pago` DECIMAL(7,2) NOT NULL,
  PRIMARY KEY (`idpago`),
  INDEX `fk_pago_cita_idx` (`idcita` ASC) VISIBLE,
  CONSTRAINT `fk_pago_cita`
    FOREIGN KEY (`idcita`)
    REFERENCES `baseday`.`cita` (`idcita`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
