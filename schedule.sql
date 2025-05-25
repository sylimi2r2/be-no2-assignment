USE schedule;
CREATE TABLE schedule
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '스케줄 식별자',
    writer VARCHAR(100) NOT NULL COMMENT '작성자',
    password VARCHAR(100) NOT NULL COMMENT '비밀번호',
    title VARCHAR(100) NOT NULL COMMENT '할일',
    contents VARCHAR(255) NOT NULL COMMENT '내용',
    createdTime DATETIME NOT NULL,
    updatedTime DATETIME NOT NULL
)