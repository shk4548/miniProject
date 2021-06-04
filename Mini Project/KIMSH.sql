CREATE TABLE PHONE_BOOK(
    id NUMBER(10),
    name VARCHAR2(20) NOT NULL,
    hp VARCHAR2(30) NOT NULL,
    tel VARCHAR2(30) NOT NULL,
    PRIMARY KEY (id)
);


CREATE SEQUENCE SEQ_PHONE_BOOK_PK
    start with 1
    increment by 1
    maxvalue 10000
    NOCACHE;
    
select id,name,hp,tel from PHONE_BOOK;
select id, name ,hp ,tel from PHONE_BOOK where PHONE_BOOK_name LIKE?;
select * from user_sequences;