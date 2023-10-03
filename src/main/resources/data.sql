INSERT INTO user_entity (user_id , user_nickname, user_email, user_password) VALUES (1, "진우석","jws@gmail.com","asdf1234");
INSERT INTO user_entity (user_id , user_nickname, user_email, user_password) VALUES (2, "문지혜","mjh@gmail.com","asdf1234");
INSERT INTO user_entity (user_id , user_nickname, user_email, user_password) VALUES (3, "박현수","phs@gmail.com","asdf1234");
INSERT INTO user_entity (user_id , user_nickname, user_email, user_password) VALUES (4, "김수민","ksm@gmail.com","asdf1234");
INSERT INTO user_entity (user_id , user_nickname, user_email, user_password) VALUES (5, "강성준","kky@gmail.com","asdf1234");
INSERT INTO user_entity (user_id , user_nickname, user_email, user_password) VALUES (6, "김가연","ksj@gmail.com","asdf1234");

INSERT INTO auth_info_entity (auth_info_id, auth_info_type, auth_info_token, created_date, updated_date)
            VALUES (1, "service", "token", '2023-09-25', '2023-09-25');
INSERT INTO auth_info_entity (auth_info_id, auth_info_type, auth_info_token, created_date, updated_date)
            VALUES (2, "service", "token", '2023-09-26', '2023-09-26');
INSERT INTO auth_info_entity (auth_info_id, auth_info_type, auth_info_token, created_date, updated_date)
            VALUES (3, "service", "token", '2023-09-26', '2023-09-26');
INSERT INTO auth_info_entity (auth_info_id, auth_info_type, auth_info_token, created_date, updated_date)
            VALUES (4, "service", "token", '2023-09-26', '2023-09-26');
INSERT INTO auth_info_entity (auth_info_id, auth_info_type, auth_info_token, created_date, updated_date)
            VALUES (5, "service", "token", '2023-09-26', '2023-09-26');
INSERT INTO auth_info_entity (auth_info_id, auth_info_type, auth_info_token, created_date, updated_date)
            VALUES (6, "service", "token", '2023-09-26', '2023-09-26');


INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type)
VALUES (1, 1,"product", "축제 때 어떤 옷 입고갈까?", "낼모레 축제인데 츄리닝은 오바지?",
        "2023-10-05","continue");

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (1, "캐쥬얼한 조끼니트",10,1);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (2, "힙합!",10,1);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (3, "츄리닝",10,1);

INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (1,1,2,"츄리닝을 고민하냐 이양반아",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (2,1,2,"아니 누구랑 가는데?",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (3,1,2,"부럽다.. 인싸구나",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (4,1,3,"축제는 무조건 힙합이긴함",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (5,1,3,"나도 츠리닝 입고 갔는데 다들 꾸미고 와서 부끄러웠음..",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (6,1,3,"추리닝이 선택지에 있는거 자체가 에바임ㅋㅋ",,);

INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type)
VALUES (2, 2,"video", "넷플릭스 뭐 볼지 추천 좀 ㅠㅠ", "재밌는게 너무 많앙",
        "2023-10-06","continue");

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (4,"무서운이야기",5,2);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (5,"오징어게임3",3,2);

INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (7,2,4,"아니 근데 오징어게임3 안나옴 아직",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (8,2,4,"띵작 좀 아네..무서운이야기 좋아하면 브리저튼도 봐바",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (9,2,4,"장르가 딴판이네..",,);

INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type)
VALUES (3, 2,"doing", "학교가기싫다", "ㅇㅈ?",
        "2023-10-06","continue");

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (6,"갈까?",0,3);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (7,"말까?",70,3);

INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (10,3,1,"나도...",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (11,3,1,"ㅋㅋ찔렸다ㅋㅋ",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (12,3,1,"좀 쳐가라 제발 나중에 후회함",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (13,3,1,"ㅋㅋ난 직장인이라 학교안감",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (14,3,1,"윗댓아 출근하자..",,);

INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type)
VALUES (4, 3,"food", "여자친구랑 데이트 뭐 먹을까요?", "넷 중에 하나로 갈 예정임",
        "2023-10-06","continue");

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (8,"짜이나궁",1,4);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (9,"라쿵푸마라탕",2,4);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (10,"로니로티",4,4);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (11,"상무초밥",5,4);

INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (15,4,1,"아니 ",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (16,4,1,"이걸 고민이라고 올리냐? 제발",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (17,4,1,"난 마라탕도 괜찮은거 같아",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (18,4,1,"선택지들이 너무 똥이라 그나마 냄새덜한 똥을 골라야하네;;;",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (19,4,1,"그..여친이 전대생이면 로니로티는 좀 질려할듯",,);

INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type)
VALUES (5, 1,"consult", "군대..가야하나?", "이제 대학교 2학년 올라가는데 군대를 가야할지 말아야할지 고민이에요.. 사실 얼마 안 된 여친이 있는데 헤어지기 너무 싫어요",
        "2023-9-27","finish");

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (12,"걍 가라",5,5);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (13,"가지마라",1,5);

INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (20,5,2,"여친을 두고가다니..너무하네요..헤어지고 가세요;;",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (21,5,2,"ㅋㅋ근데 늦게 가면 갔다올 때 친구 없는게 개 에바긴함",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (22,5,2,"일말상초각ㅋㅋ",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (23,5,2,"사귄지 얼마 안된거면 일말상초도 아님 요즘엔 훈말이초임",,);

INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type)
VALUES (6, 3,"consult", "대학원엘 갈지 취업을 해서 경험을 먼저 쌓을지가 고민입니다", "취업하기가 무섭다 애들아..",
        "2023-10-06","continue");

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (14,"대학원",0,6);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (15,"취업",10,6);

INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (24,6,3,"가.지.마",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (25,6,3,"그.냥.가.지.마",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (26,6,3,"ㅋㅋ대학원생들 왤케 많음 ㅋㅋ",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (27,6,3,"진지하게 이걸 고민중이면 취업을 하는게 맞음. 나도 대학원생인데 대학원은 저 마인드로 오면 못 버팀",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (28,6,3,"이 투표는 전국대학원생협회에서 점령했습니다",,);

INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type)
VALUES (7, 1,"doing", "과제를 지금 할까 말까", "오늘 12시까지인데 하기 너무 싫어 ㅠㅠ",
        "2023-09-26","finish");

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (16,"지금 하자",5,7);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (17,"좀만 놀다 하자",5,7);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (18,"한번은 제껴도 됨",5,7);

INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (29,7,4,"와 진짜 지금 나임 ㅋㅋ",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (30,7,4,"좋을때다..",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (31,7,4,"아니 얼마나 했는데?",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (32,7,4,"방금알았어요 ㅜㅜ",,);

INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type)
VALUES (8, 1,"consult", "제가 팔랑귀인지 고민이에요", "그 종종 다른 사람들이 이 수업 좋다좋다 하길래 다 하는데..지금 그래서 이번학기에 경제, 컴공, 심리 강의 다
듣게 됐거든..이거 팔랑귀야?",
        "2023-10-05","continue");

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (19,"맞다",0,8);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (20,"그럴 수 있다",0,8);

INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (33,8,2,"ㅋㅋㅋ팔랑귀로 헤르미온느 됐네 ㅋㅋ",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (34,8,2,"다 잘하면 됨 화이팅",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (35,8,2,"그래서 님드라..답변이나 해줘요..",,);


INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type)
VALUES (9, 2,"product", "컴활 딸까 말까?", "컴활 진짜 중요해? 따는거 추천함?",
        "2023-10-05","continue");

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (21,"딴다",4,9);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (22,"그걸 왜 함",4,9);

INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (36,9,3,"남들 다 따길래 땀",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (37,9,3,"걍 개쉬우니까 해봐",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (38,9,3,"공기업갈거 아니면 비추",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (39,9,3,"그걸 왜 함 까지는 아닌데 굳이싶기는 함",,);

INSERT INTO vote_tb (vote_id, user_id, vote_category, vote_title, vote_content,
                     vote_end_date, vote_active, vote_type)
VALUES (10, 3,"product", "어떤 이성이 더 좋은 이성인가..", "성격은 진짜 잘 맞는데 내 스탈은 아닌 동생이 있고 이번에 소개팅나갔는데 얼굴은 예쁘신데 난 조용한걸
별로 안좋아해서..",
        "2023-10-05","continue");

INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (23,"전자",6,10);
INSERT INTO option_tb (option_id , option_name, option_count, option_vote_id, option_image) VALUES (24,"후자",10,10);

INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (40,10,2,"조용한 여자면 너가 말 많으면 되지",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (41,10,3,"난 무조건 얼굴",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (42,10,4,"내 성격이 까다로운 건 아닌데 근데 나는 안맞는 사람은 별로 안끌림",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (43,10,1,"나는 중자",,);
INSERT INTO comment_tb (comment_id, comment_vote_id, comment_user_id, comment_content, created_date, updated_date) VALUES (44,10,2,"윗댓아 선택지 만들지 마셈..이미 투표도 한 사람이..",,);


