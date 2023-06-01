insert into users(id, first_name, last_name, email, password, role)
values (1, 'Nurisa', 'Mamirayim kyzy', 'nurisa@gmail.com',
        '$2a$12$JP3lvcCNtY2OaJIIriLZ7.691ar/tfMMj.jyvBWEtmZ4GXeyZwy.S', 'ADMIN'),
       (2, 'Datka', 'Mamatzhanova', 'datka@gmail.com',
        '$2a$12$6rapFJQF5gVakbgbA1BGq.Ewfn4bxYAIR/IWtl1pUvmMqNsq/kqLG', 'USER'),
       (3, 'Ulan', 'Kubanychbekov', 'ulan@gmail.com',
        '$2a$12$laATqF5MJg6b1Kcs.GUtAOtEzpMsFfbaTxDnVPKXn0Tcr2aPKYVeC', 'USER'),
       (4, 'Syimyk', 'Ravshanbekov', 'syimyk@gmail.com',
        '$2a$12$COlPJLbhSwgPfG13oVZFc.fPqIs9vIUPRAPZZFNYMOj.DQkGaFvq6', 'USER'),
       (5, 'Aijamal', 'Asangazieva', 'aijamal@gmail.com',
        '$2a$12$lcZgimkUpCdBBJfMcaOzR.bdVUsE8y1vZOazXYyX8XMaEkm7VpWWm', 'USER');

insert into tests(id, description, duration, enable, title)
values (1, 'English Test for students-1', 30, false, 'Test-1'),
       (2, 'English Test for students-2', 40, true, 'Test-2');

insert into questions(id, duration, enable, count, passage, question_type, title, test_id,correct_answer)
values (1, 1, false, null, null, 'SELECT_ENGLISH_WORD',  'Select real English word', 1,null),
       (2, 15, false, null, null, 'LISTEN_AND_SELECT_ENGLISH_WORD', 'Listen and select English word', 2,null),
       (3, 15, false, 3, null, 'TYPE_WHAT_YOU_HEAR',  'Listen and type what you hear', 1,'Hello my friend'),
       (4, 15, false, null, null, 'DESCRIBE_IMAGE',  'Describe the image', 2,'Nell, a white woman with short black hair, is outside on a bright day. She wears thick, black-rimmed glasses and smiles at the camera. She also wears a white and dark-colored quatrefoil collared shirt and a black and gold diagonal stripe bow tie.'),
       (5, 15, false, null, null, 'RECORD_SAYING_STATEMENT',  'Record saying statement', 1,null),
       (6, 15, false, 3 , null, 'RESPOND_N_WORDS',  'Respond at least N words', 2,null),
       (7, 15, false, null, 'Passage-7', 'HIGHLIGHT_THE_ANSWER', 'Highlight the answer', 1,'A picture of a baby gorilla hugging its mother'),
       (8, 15, false, null, 'Passage-8', 'SELECT_THE_MAIN_IDEA',  'Select the main idea', 2,null),
       (9, 15, false, null, 'Passage-9', 'SELECT_BEST_TITLE',  'Select the best title', 1,null),
       (10, 15, false, null, 'Passage-10', 'SELECT_BEST_TITLE',  'Select the best title', 2,null);

insert into options(id, is_true, title, value, question_id)
values (1, false, 'Select real English word', null, 1),
       (2, true, 'Select real English word', null, 1),
       (3, false, 'Select real English word', null, 1),
       (4, false, 'LISTEN_AND_SELECT_ENGLISH_WORD', 'https://bilingualbackendbucket.s3.eu-central-1.amazonaws.com/gvrw_unit001_exercise_02.mp3', 2),
       (5, true, 'LISTEN_AND_SELECT_ENGLISH_WORD', 'https://bilingualbackendbucket.s3.eu-central-1.amazonaws.com/gvrw_unit001_exercise_02.mp3', 2),
       (6, false, 'LISTEN_AND_SELECT_ENGLISH_WORD', 'https://bilingualbackendbucket.s3.eu-central-1.amazonaws.com/gvrw_unit001_exercise_02.mp3', 2),
       (7, false, 'SELECT_THE_MAIN_IDEA', null, 8),
       (8, true, 'SELECT_THE_MAIN_IDEA', null, 8),
       (9, false, 'SELECT_THE_MAIN_IDEA', null, 8),
       (10, true, 'SELECT_BEST_TITLE', null, 9),
       (11, false, 'SELECT_BEST_TITLE', null, 9),
       (12, true, 'SELECT_BEST_TITLE', null, 9);

insert into results(id, date_of_submission, is_evaluated, is_seen, score, test_id, user_id)
values (1, now(), false, false, 0, 1, 2),
       (2, now(), true, true, 5, 2, 3),
       (3, now(), false, false, 0, 1, 4),
       (4, now(), true, false, 0, 2, 5);

insert into question_answers(id, duration, fixed_time, score, question_id, is_seen, is_evaluated)
values (1, 15, now(), 0, 1,true,true),
       (2, 15, now(), 0, 2 ,true,true),
       (3, 15, now(), 0, 3 ,true,true),
       (4, 15, now(), 0, 4 ,true,false),
       (5, 15, now(), 0, 5 ,true,false),
       (6, 15, now(), 0, 6 ,true,true),
       (7, 15, now(), 0, 7,false,false),
       (8, 15, now(), 0, 8,true,true),
       (9, 15, now(), 0, 9,false,true),
       (10, 15, now(), 0, 10,true,true);

insert into question_answers_selected_options(question_answer_id, selected_options_id)
values (1, 2),
       (2, 6),
       (8, 12),
       (9, 11);

insert into results_question_answers(result_id, question_answers_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8),
       (1, 9),
       (1, 10);

insert into question_value(question_id, value, value_key)
values (3, 'https://bilingualbackendbucket.s3.eu-central-1.amazonaws.com/gvrw_unit001_exercise_06.mp3', 'AUDIO'),
       (4,'https://images.unsplash.com/photo-1575936123452-b67c3203c357?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8fDA%3D&w=1000&q=80','IMAGE'),
       (5,'Information technology (IT) is the use of any computers, storage, networking and other physical devices','TEXT'),
       (6,'Text messaging, or texting, is the act of composing and sending electronic messages, typically consisting of alphabetic and numeric characters, between two or more users of mobile devices, desktops/laptops, or another type of compatible computer.','TEXT'),
       (7,'What does information technology encompass?','TEXT');