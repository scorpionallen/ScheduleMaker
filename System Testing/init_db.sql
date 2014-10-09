SET FOREIGN_KEY_CHECKS=0;

create database if not exists `schedule_maker`;

USE `schedule_maker`;

drop table if exists `schedule`;

CREATE TABLE `schedule` (
  `id` int(11) NOT NULL default '0',
  `classNbr` varchar(100) NOT NULL default '',
  `courseId` varchar(6) NOT NULL default '',
  PRIMARY KEY  (`id`,`classNbr`,`courseId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

drop table if exists `student`;

CREATE TABLE `student` (
  `ssn` varchar(9) NOT NULL default '',
  `pantherID` varchar(7) NOT NULL default '',
  `firstname` varchar(100) default NULL,
  `lastname` varchar(100) default NULL,
  PRIMARY KEY  (`ssn`),
  UNIQUE KEY `PK_Student` (`ssn`),
  KEY `pantherID` (`pantherID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

drop table if exists `student_schedule`;

CREATE TABLE `student_schedule` (
  `id` int(11) NOT NULL default '0',
  `pantherID` varchar(7) NOT NULL default '',
  PRIMARY KEY  (`id`,`pantherID`),
  KEY `pantherID` (`pantherID`),
  CONSTRAINT `student_schedule_ibfk_2` FOREIGN KEY (`pantherID`) REFERENCES `student` (`pantherID`) ON UPDATE CASCADE,
  CONSTRAINT `student_schedule_ibfk_1` FOREIGN KEY (`id`) REFERENCES `schedule` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create database if not exists `univdb`;

USE `univdb`;

drop table if exists `advising`;

CREATE TABLE `advising` (
  `studentSSN` varchar(9) NOT NULL default '',
  `facultySSN` varchar(9) NOT NULL default '',
  PRIMARY KEY  (`studentSSN`,`facultySSN`),
  UNIQUE KEY `PK_Advising` (`studentSSN`,`facultySSN`),
  KEY `FK_Advising_Faculty` (`facultySSN`),
  CONSTRAINT `FK_Advising_Faculty` FOREIGN KEY (`facultySSN`) REFERENCES `faculty` (`ssn`),
  CONSTRAINT `FK_Advising_Student` FOREIGN KEY (`studentSSN`) REFERENCES `student` (`ssn`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

drop table if exists `classes`;

CREATE TABLE `classes` (
  `courseid` varchar(20) NOT NULL default '',
  `classNbr` varchar(30) NOT NULL default '',
  `term` enum('Spring 2007') NOT NULL default 'Spring 2007',
  `campus` varchar(100) NOT NULL default '',
  `sect` varchar(10) NOT NULL default '',
  `from` time NOT NULL default '00:00:00',
  `to` time NOT NULL default '00:00:00',
  `days` varchar(7) NOT NULL default '',
  PRIMARY KEY  (`classNbr`),
  KEY `courseid` (`courseid`),
  CONSTRAINT `classes_ibfk_1` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseid`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into `classes` values ('1','10772','Spring 2007','Biscayne','B51','11:00:00','12:15:00','1010000');
insert into `classes` values ('3','10777','Spring 2007','University','U01','18:25:00','19:40:00','1010000');
insert into `classes` values ('3','10778','Spring 2007','University','U02','09:30:00','10:45:00','0101000');
insert into `classes` values ('2','13136','Spring 2007','University','U02','15:30:00','16:45:00','1010100');
insert into `classes` values ('2','14210','Spring 2007','University','U01','11:00:00','12:15:00','0101000');
insert into `classes` values ('2','14211','Spring 2007','Biscayne','B51','15:30:00','16:45:00','0101010');
insert into `classes` values ('1','17736','Spring 2007','Biscayne','B52','07:50:00','09:05:00','1010101');
insert into `classes` values ('5','24536','Spring 2007','University','U01','08:00:00','09:15:00','1010000');
insert into `classes` values ('5','24537','Spring 2007','University','U02','18:25:00','19:40:00','0101000');
insert into `classes` values ('7','26543','Spring 2007','University','U01','11:00:00','12:15:00','1010100');
insert into `classes` values ('7','26544','Spring 2007','Biscayne','B01','14:00:00','15:15:00','1010100');
insert into `classes` values ('7','26545','Spring 2007','University','U02','09:30:00','10:45:00','0101000');
insert into `classes` values ('7','26546','Spring 2007','Biscayne','B02','17:00:00','18:15:00','0101000');
insert into `classes` values ('4','34567','Spring 2007','University','U01','14:00:00','15:15:00','1010100');
insert into `classes` values ('4','34568','Spring 2007','Biscayne','B01','09:30:00','10:45:00','1010100');
insert into `classes` values ('4','34569','Spring 2007','University','U02','15:30:00','16:45:00','0101000');
insert into `classes` values ('8','36543','Spring 2007','University','U01','15:30:00','16:45:00','1010000');
insert into `classes` values ('8','36544','Spring 2007','University','U02','09:30:00','10:45:00','1010000');
insert into `classes` values ('8','36545','Spring 2007','Biscayne','B01','19:50:00','21:15:00','0101000');
insert into `classes` values ('6','65476','Spring 2007','University','U01','17:00:00','18:15:00','1010000');
insert into `classes` values ('9','76546','Spring 2007','University','U01','17:00:00','18:15:00','1010100');
insert into `classes` values ('9','76547','Spring 2007','Biscayne','B01','09:30:00','10:45:00','0101000');

drop table if exists `classroom`;

CREATE TABLE `classroom` (
  `cid` varchar(10) NOT NULL default '',
  `capacity` int(10) unsigned default NULL,
  PRIMARY KEY  (`cid`),
  UNIQUE KEY `PK_Classroom` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

drop table if exists `course`;

CREATE TABLE `course` (
  `courseid` varchar(20) NOT NULL default '',
  `description` varchar(250) default NULL,
  `level` varchar(50) default NULL,
  `deptID` varchar(50) NOT NULL default '',
  `semesterID` varchar(10) NOT NULL default '',
  `classroomID` varchar(10) default NULL,
  `facultySSN` varchar(9) default NULL,
  `subject` char(3) NOT NULL default '',
  `catlgNbr` varchar(4) NOT NULL default '',
  `units` int(11) default NULL,
  PRIMARY KEY  (`courseid`,`semesterID`),
  UNIQUE KEY `PK_Course` (`courseid`,`semesterID`),
  KEY `FK_Course_Classroom` (`classroomID`),
  KEY `FK_Course_Department` (`deptID`),
  KEY `FK_Course_Semester` (`semesterID`),
  CONSTRAINT `FK_Course_Classroom` FOREIGN KEY (`classroomID`) REFERENCES `classroom` (`cid`),
  CONSTRAINT `FK_Course_Department` FOREIGN KEY (`deptID`) REFERENCES `department` (`deptid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Course_Semester` FOREIGN KEY (`semesterID`) REFERENCES `semester` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into `course` values ('1',NULL,NULL,'1','1',NULL,NULL,'COP','2210',4);
insert into `course` values ('2',NULL,NULL,'1','1',NULL,NULL,'COP','2250',3);
insert into `course` values ('3',NULL,NULL,'1','1',NULL,NULL,'COP','3175',3);
insert into `course` values ('4',NULL,NULL,'1','1',NULL,NULL,'COP','3145',3);
insert into `course` values ('5',NULL,NULL,'1','1',NULL,NULL,'HIS','1010',3);
insert into `course` values ('6',NULL,NULL,'1','1',NULL,NULL,'STA','3510',3);
insert into `course` values ('7',NULL,NULL,'1','1',NULL,NULL,'PHY','2048',4);
insert into `course` values ('8',NULL,NULL,'1','1',NULL,NULL,'PHY','2049',4);
insert into `course` values ('9',NULL,NULL,'1','1',NULL,NULL,'COP','4338',3);

drop table if exists `department`;

CREATE TABLE `department` (
  `deptid` varchar(50) NOT NULL default '',
  `dname` varchar(50) default NULL,
  PRIMARY KEY  (`deptid`),
  UNIQUE KEY `PK_Department` (`deptid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into `department` values ('1','Database');

drop table if exists `faculty`;

CREATE TABLE `faculty` (
  `ssn` varchar(9) NOT NULL default '',
  `level` varchar(50) default NULL,
  `deptID` varchar(50) NOT NULL default '',
  PRIMARY KEY  (`ssn`),
  UNIQUE KEY `PK_Faculty` (`ssn`),
  KEY `FK_Faculty_Department` (`deptID`),
  CONSTRAINT `FK_Faculty_Department` FOREIGN KEY (`deptID`) REFERENCES `department` (`deptid`),
  CONSTRAINT `FK_Faculty_Member` FOREIGN KEY (`ssn`) REFERENCES `member` (`ssn`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

drop table if exists `grade`;

CREATE TABLE `grade` (
  `studentSSN` varchar(9) NOT NULL default '',
  `courseID` varchar(20) NOT NULL default '',
  `grade` char(2) default NULL,
  `semesterID` varchar(10) NOT NULL default '',
  `gradeID` int(10) NOT NULL default '0',
  PRIMARY KEY  (`gradeID`),
  UNIQUE KEY `PK_Grade` (`gradeID`),
  KEY `FK_Grade_Course` (`courseID`,`semesterID`),
  KEY `FK_Grade_Student` (`studentSSN`),
  CONSTRAINT `FK_Grade_Course` FOREIGN KEY (`courseID`, `semesterID`) REFERENCES `course` (`courseid`, `semesterID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Grade_Student` FOREIGN KEY (`studentSSN`) REFERENCES `student` (`ssn`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

drop table if exists `member`;

CREATE TABLE `member` (
  `ssn` varchar(9) NOT NULL default '',
  `firstname` varchar(50) NOT NULL default '',
  `lastname` varchar(50) NOT NULL default '',
  `dob` timestamp NULL default NULL,
  `email` varchar(100) default NULL,
  `address` varchar(250) default NULL,
  PRIMARY KEY  (`ssn`),
  UNIQUE KEY `PK_Member` (`ssn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into `member` values ('123437295','John','Smith',NULL,NULL,NULL);
insert into `member` values ('123456789','Pepe','Lopez',NULL,NULL,NULL);
insert into `member` values ('134523135','Abraham','Lou',NULL,NULL,NULL);
insert into `member` values ('154345234','Yaima','Perez',NULL,NULL,NULL);
insert into `member` values ('2352352','Alfred','White',NULL,NULL,NULL);
insert into `member` values ('2523452','Gina','Studd',NULL,NULL,NULL);

drop table if exists `minor`;

CREATE TABLE `minor` (
  `studentSSN` varchar(9) NOT NULL default '',
  `programID` varchar(50) NOT NULL default '',
  PRIMARY KEY  (`studentSSN`,`programID`),
  UNIQUE KEY `PK_Minor` (`studentSSN`,`programID`),
  KEY `FK_Minor_Program` (`programID`),
  CONSTRAINT `FK_Minor_Program` FOREIGN KEY (`programID`) REFERENCES `program` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Minor_Student` FOREIGN KEY (`studentSSN`) REFERENCES `student` (`ssn`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

drop table if exists `payroll`;

CREATE TABLE `payroll` (
  `facultySSN` varchar(9) default NULL,
  `semesterID` varchar(10) default NULL,
  `salary` decimal(19,4) unsigned default NULL,
  `paymentID` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`paymentID`),
  UNIQUE KEY `PK_Payroll` (`paymentID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

drop table if exists `program`;

CREATE TABLE `program` (
  `pid` varchar(50) NOT NULL default '',
  `pname` varchar(100) default NULL,
  `deptID` varchar(50) NOT NULL default '',
  PRIMARY KEY  (`pid`),
  UNIQUE KEY `PK_Program` (`pid`),
  KEY `FK_Program_Department` (`deptID`),
  CONSTRAINT `FK_Program_Department` FOREIGN KEY (`deptID`) REFERENCES `department` (`deptid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

drop table if exists `semester`;

CREATE TABLE `semester` (
  `sid` varchar(10) NOT NULL default '',
  `start` timestamp NULL default NULL,
  `end` timestamp NULL default NULL,
  `sname` varchar(50) default NULL,
  PRIMARY KEY  (`sid`),
  UNIQUE KEY `PK_Semester` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into `semester` values ('1','0000-00-00 00:00:00','0000-00-00 00:00:00','Spring 2007');

drop table if exists `student`;

CREATE TABLE `student` (
  `ssn` varchar(9) NOT NULL default '',
  `pantherID` varchar(7) NOT NULL default '',
  `password` varchar(50) NOT NULL default '',
  PRIMARY KEY  (`ssn`),
  UNIQUE KEY `PK_Student` (`ssn`),
  CONSTRAINT `FK_Student_Member` FOREIGN KEY (`ssn`) REFERENCES `member` (`ssn`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into `student` values ('123456789','1412412','abc123');
insert into `student` values ('134523135','2354235','abc234');
insert into `student` values ('154345234','2234523','abc345');

drop table if exists `tuition`;

CREATE TABLE `tuition` (
  `studentSSN` varchar(9) default NULL,
  `semesterID` varchar(10) default NULL,
  `balance` decimal(19,4) unsigned default NULL,
  `tuition` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`tuition`),
  UNIQUE KEY `PK_Tuition` (`tuition`),
  KEY `FK_Tuition_Semester` (`semesterID`),
  KEY `FK_Tuition_Student` (`studentSSN`),
  CONSTRAINT `FK_Tuition_Semester` FOREIGN KEY (`semesterID`) REFERENCES `semester` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Tuition_Student` FOREIGN KEY (`studentSSN`) REFERENCES `student` (`ssn`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS=1;
