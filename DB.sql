drop database if exists AM_DB_25_03;
create database AM_DB_25_03;
use AM_DB_25_03;

create table article(
                        id int(10) unsigned not null primary key auto_increment,
                        regDate datetime not null,
                        updateDate datetime not null,
                        title char(100) not null,
                        `body` text not null
);

INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = '제목1',
    `body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = '제목2',
    `body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = '제목3',
    `body` = '내용3';

select *
from article
order by id desc;

CREATE TABLE `member`(
                         id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         regDate DATETIME NOT NULL,
                         updateDate DATETIME NOT NULL,
                         loginId CHAR(100) NOT NULL,
                         loginPw CHAR(100) NOT NULL,
                         `name` TEXT NOT NULL
);

INSERT INTO `member`
SET regDate = NOW(),
    updateDate = NOW(),
    loginId = 'test1',
    loginPw = 'test1',
    `name` = '김철수';

INSERT INTO `member`
SET regDate = NOW(),
    updateDate = NOW(),
    loginId = 'test2',
    loginPw = 'test2',
    `name` = '김이슬';

INSERT INTO `member`
SET regDate = NOW(),
    updateDate = NOW(),
    loginId = 'test3',
    loginPw = 'test3',
    `name` = '김미영';

select *
from member