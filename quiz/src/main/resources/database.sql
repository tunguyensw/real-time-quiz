-- user
INSERT INTO portal.e_user(user_id, user_name) VALUES(1, 'tunv');
INSERT INTO portal.e_user(user_id, user_name) VALUES(2, 'david');
INSERT INTO portal.e_user(user_id, user_name) VALUES(3, 'johnny');

-- quiz
INSERT INTO portal.quiz(id, title, status, meta, created_date, updated_date, created_by, quiz_no, quiz_type)
VALUES(1, 'ELSA - TOEIC Test', 'DRAFT', '{"quizTime":3600000,"maximumQuestion":2}', now()::timestamp , null, null, '22355', 'QUIZ');

INSERT INTO portal.quiz(id, title, status, meta, created_date, updated_date, created_by, quiz_no, quiz_type)
VALUES(2, 'ELSA - IELTS Test', 'ACTIVE', '{"quizTime":3600000,"maximumQuestion":3}', now()::timestamp , null, null, '22354', 'QUIZ');

-- question
INSERT INTO portal.question(id, title, meta, question_type)
VALUES(1, '______ are my trousers.', '{"options":["This","These"],"correctOptions":["These"],"config":{"type":"SINGLE"}}', 'QUIZ');

INSERT INTO portal.question(id, title, meta, question_type)
VALUES(2, 'Is ______ hotel nice?', '{"options":["this","that"],"correctOptions":["that"],"config":{"type":"SINGLE"}}', 'QUIZ');

INSERT INTO portal.question(id, title, meta, question_type)
VALUES(3, 'Are ______ your friends?', '{"options":["these","those"],"correctOptions":["these"],"config":{"type":"SINGLE"}}', 'QUIZ');

INSERT INTO portal.question(id, title, meta, question_type)
VALUES(4,'______ ask you a question?', '{"options":["I can","Do I can","Can I"],"correctOptions":["Can I"],"config":{"type":"SINGLE"}}', 'QUIZ');

INSERT INTO portal.question(id, title, meta, question_type)
VALUES(5,'We love ______ little dog.', '{"options":["We","Our"],"correctOptions":["Our"],"config":{"type":"SINGLE"}}', 'QUIZ');


-- quiz_question
INSERT INTO portal.quiz_question(quiz_id, question_id) VALUES(1, 1);
INSERT INTO portal.quiz_question(quiz_id, question_id) VALUES(1, 2);
INSERT INTO portal.quiz_question(quiz_id, question_id) VALUES(1, 3);
INSERT INTO portal.quiz_question(quiz_id, question_id) VALUES(2, 4);
INSERT INTO portal.quiz_question(quiz_id, question_id) VALUES(2, 5);