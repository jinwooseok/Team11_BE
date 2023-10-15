use golajuma;
INSERT INTO user (user_id, user_nickname, user_email, user_password, created_date, updated_date, deleted)
VALUES
    (1, "진우석","jws@gmail.com","$2a$10$7eEORrZ6", "2023-10-15 14:43:01.696000", "2023-10-15 14:43:01.696000",false);


INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type, created_date, updated_date, deleted, vote_total_count)
VALUES (1, 1,"product", "축제 때 어떤 옷 입고갈까?", "낼모레 축제인데 츄리닝은 오바지?",
        "2023-10-20","continue","기본",NOW(), NOW(),false, 20);

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (1, "캐쥬얼한 조끼니트",5,1,"default",NOW(), NOW(),false);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (2, "힙합!",10,1,"default",NOW(), NOW(),false);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (3, "츄리닝",5,1,"default",NOW(), NOW(),false);

INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (1,1,2,"츄리닝을 고민하냐 이양반아",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (2,1,2,"아니 누구랑 가는데?",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (3,1,2,"부럽다.. 인싸구나",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (4,1,3,"축제는 무조건 힙합이긴함",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (5,1,3,"나도 츠리닝 입고 갔는데 다들 꾸미고 와서 부끄러웠음..",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (6,1,3,"추리닝이 선택지에 있는거 자체가 에바임ㅋㅋ",NOW(), NOW(),false);

INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 11, 1);
INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 12, 1);
INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 13, 1);
INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 14, 1);
INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 15, 1);

INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 1, 2),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 2, 2),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 3, 2),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 4, 2),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 5, 2),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 6, 2),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 7, 2),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 8, 2),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 9, 2),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 10, 2);

INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 16, 3);
INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 17, 3);
INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 18, 3);
INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 19, 3);
INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 20, 3);

INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type, created_date, updated_date, deleted, vote_total_count)
VALUES (2, 2,"video", "넷플릭스 뭐 볼지 추천 좀 ㅠㅠ", "재밌는게 너무 많앙",
        "2023-10-20","continue","기본",NOW(), NOW(),false,8);

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (4,"무서운이야기",5,2,"default",NOW(), NOW(),false);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (5,"오징어게임3",3,2,"default",NOW(), NOW(),false);

INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (7,2,4,"아니 근데 오징어게임3 안나옴 아직",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (8,2,4,"띵작 좀 아네..무서운이야기 좋아하면 브리저튼도 봐바",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (9,2,4,"장르가 딴판이네..",NOW(), NOW(),false);

INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 1, 4),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 2, 4),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 3, 4),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 4, 4),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 5, 4),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 6, 5),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 7, 5),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 8, 5);


INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type, created_date, updated_date, deleted, vote_total_count)
VALUES (3, 2,"doing", "학교가기싫다", "ㅇㅈ?",
        "2023-10-20","continue","기본",NOW(), NOW(),false,10);

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (6,"갈까?",0,3,"default",NOW(), NOW(),false);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (7,"말까?",10,3,"default",NOW(), NOW(),false);

INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 1, 7),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 2, 7),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 3, 7),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 4, 7),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 5, 7),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 6, 7),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 7, 7),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 8, 7),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 9, 7),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 10, 7);

INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (10,3,1,"나도...",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (11,3,1,"ㅋㅋ찔렸다ㅋㅋ",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (12,3,1,"좀 쳐가라 제발 나중에 후회함",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (13,3,1,"ㅋㅋ난 직장인이라 학교안감",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (14,3,1,"윗댓아 출근하자..",NOW(), NOW(),false);

INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type, created_date, updated_date, deleted,vote_total_count)
VALUES (4, 3,"food", "여자친구랑 데이트 뭐 먹을까요?", "넷 중에 하나로 갈 예정임",
        "2023-10-20","continue","기본",NOW(), NOW(),false, 12);

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (8,"짜이나궁",1,4,"default",NOW(), NOW(),false);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (9,"라쿵푸마라탕",2,4,"default",NOW(), NOW(),false);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (10,"로니로티",4,4,"default",NOW(), NOW(),false);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (11,"상무초밥",5,4,"default",NOW(), NOW(),false);

INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 11, 8);
INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 12, 9);
INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) VALUES ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 13, 9);

INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 1, 10),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 2, 10),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 3, 10),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 4, 10),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 5, 11),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 6, 11),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 7, 11),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 8, 11),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 9, 11);

INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (15,4,1,"아니 ",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (16,4,1,"이걸 고민이라고 올리냐? 제발",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (17,4,1,"난 마라탕도 괜찮은거 같아",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (18,4,1,"선택지들이 너무 똥이라 그나마 냄새덜한 똥을 골라야하네;;;",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (19,4,1,"그..여친이 전대생이면 로니로티는 좀 질려할듯",NOW(), NOW(),false);

INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type, created_date, updated_date, deleted, vote_total_count)
VALUES (5, 1,"consult", "군대..가야하나?", "이제 대학교 2학년 올라가는데 군대를 가야할지 말아야할지 고민이에요.. 사실 얼마 안 된 여친이 있는데 헤어지기 너무 싫어요",
        "2023-9-27","finish","기본",NOW(), NOW(),false, 6);

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (12,"걍 가라",5,5,"default",NOW(), NOW(),false);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (13,"가지마라",1,5,"default",NOW(), NOW(),false);

INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 4, 12),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 5, 12),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 6, 12),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 7, 12),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 8, 12),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 9, 13);

INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (20,5,2,"여친을 두고가다니..너무하네요..헤어지고 가세요;;",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (21,5,2,"ㅋㅋ근데 늦게 가면 갔다올 때 친구 없는게 개 에바긴함",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (22,5,2,"일말상초각ㅋㅋ",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (23,5,2,"사귄지 얼마 안된거면 일말상초도 아님 요즘엔 훈말이초임",NOW(), NOW(),false);

INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type, created_date, updated_date, deleted, vote_total_count)
VALUES (6, 3,"consult", "대학원엘 갈지 취업을 해서 경험을 먼저 쌓을지가 고민입니다", "취업하기가 무섭다 애들아..",
        "2023-10-20","continue","기본",NOW(), NOW(),false, 10);

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (14,"대학원",0,6,"default",NOW(), NOW(),false);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (15,"취업",10,6,"default",NOW(), NOW(),false);

INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 1, 15),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 2, 15),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 3, 15),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 4, 15),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 5, 15),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 6, 15),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 7, 15),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 8, 15),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 10, 15),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 9, 15);

INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (24,6,3,"가.지.마",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (25,6,3,"그.냥.가.지.마",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (26,6,3,"ㅋㅋ대학원생들 왤케 많음 ㅋㅋ",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (27,6,3,"진지하게 이걸 고민중이면 취업을 하는게 맞음. 나도 대학원생인데 대학원은 저 마인드로 오면 못 버팀",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (28,6,3,"이 투표는 전국대학원생협회에서 점령했습니다",NOW(), NOW(),false);

INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type, created_date, updated_date, deleted, vote_total_count)
VALUES (7, 1,"doing", "과제를 지금 할까 말까", "오늘 12시까지인데 하기 너무 싫어 ㅠㅠ",
        "2023-09-26","finish","기본",NOW(), NOW(),false,6);

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (16,"지금 하자",3,7,"default",NOW(), NOW(),false);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (17,"좀만 놀다 하자",3,7,"default",NOW(), NOW(),false);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (18,"한번은 제껴도 됨",4,7,"default",NOW(), NOW(),false);

INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 1, 16),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 2, 16),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 3, 16),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 4, 17),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 5, 17),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 6, 17),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 7, 18),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 8, 18),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 10, 18),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 9, 18);

INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (29,7,4,"와 진짜 지금 나임 ㅋㅋ",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (30,7,4,"좋을때다..",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (31,7,4,"아니 얼마나 했는데?",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (32,7,4,"방금알았어요 ㅜㅜ",NOW(), NOW(),false);

INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type, created_date, updated_date, deleted, vote_total_count)
VALUES (8, 1,"consult", "제가 팔랑귀인지 고민이에요", "그 종종 다른 사람들이 이 수업 좋다좋다 하길래 다 하는데..지금 그래서 이번학기에 경제, 컴공, 심리 강의 다
듣게 됐거든..이거 팔랑귀야?",
        "2023-10-20","continue","기본",NOW(), NOW(),false, 0);

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (19,"맞다",0,8,"default",NOW(), NOW(),false);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (20,"그럴 수 있다",0,8,"default",NOW(), NOW(),false);


INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (33,8,2,"ㅋㅋㅋ팔랑귀로 헤르미온느 됐네 ㅋㅋ",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (34,8,2,"다 잘하면 됨 화이팅",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (35,8,2,"그래서 님드라..답변이나 해줘요..",NOW(), NOW(),false);


INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type, created_date, updated_date, deleted, vote_total_count)
VALUES (9, 2,"product", "컴활 딸까 말까?", "컴활 진짜 중요해? 따는거 추천함?",
        "2023-10-20","continue","기본",NOW(), NOW(),false, 8);

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (21,"딴다",4,9,"default",NOW(), NOW(),false);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (22,"그걸 왜 함",4,9,"default",NOW(), NOW(),false);

INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 1, 21),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 2, 21),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 3, 21),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 4, 21),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 5, 22),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 6, 22),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 7, 22),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 8, 22);

INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (36,9,3,"남들 다 따길래 땀",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (37,9,3,"걍 개쉬우니까 해봐",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (38,9,3,"공기업갈거 아니면 비추",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (39,9,3,"그걸 왜 함 까지는 아닌데 굳이싶기는 함",NOW(), NOW(),false);

INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type, created_date, updated_date, deleted, vote_total_count)
VALUES (10, 3,"product", "어떤 이성이 더 좋은 이성인가..", "성격은 진짜 잘 맞는데 내 스탈은 아닌 동생이 있고 이번에 소개팅나갔는데 얼굴은 예쁘신데 난 조용한걸
별로 안좋아해서..",
        "2023-10-20","continue","기본",NOW(), NOW(),false,11);

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (23,"전자",3,10,"default",NOW(), NOW(),false);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image, created_date, updated_date, deleted) VALUES (24,"후자",8,10,"default",NOW(), NOW(),false);

INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 11, 23),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 22, 23),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 23, 23);

INSERT INTO decision_tb (created_date, deleted, updated_date, decision_user_id, decision_option_id) values
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 1, 24),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 2, 24),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 3, 24),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 4, 24),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 5, 24),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 6, 24),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 7, 24),
                                                                                                        ('2023-10-11 12:34:56.789', false, '2023-10-11 12:34:56.789', 8, 24);

INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (40,10,2,"조용한 여자면 너가 말 많으면 되지",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (41,10,3,"난 무조건 얼굴",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (42,10,4,"내 성격이 까다로운 건 아닌데 근데 나는 안맞는 사람은 별로 안끌림",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (43,10,1,"나는 중자",NOW(), NOW(),false);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date, deleted) VALUES (44,10,2,"윗댓아 선택지 만들지 마셈..이미 투표도 한 사람이..",NOW(), NOW(),false);
