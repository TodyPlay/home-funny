insert into home_funny_media_tag (id, name) value (1, '血腥'), (2, '暴力'), (3, '文艺'), (4, '青春');

insert into home_funny_multi_media
    (name, cover, media_type, description, create_date) VALUE
    ('宝贝计划1', '封面', 'VIDEO', '描述', '2022-12-17'),
    ('宝贝计划2', '封面', 'VIDEO', '描述', '2022-12-17'),
    ('宝贝计划3', '封面', 'VIDEO', '描述', '2022-12-17'),
    ('宝贝计划4', '封面', 'VIDEO', '描述', '2022-12-17'),
    ('宝贝计划5', '封面', 'VIDEO', '描述', '2022-12-17'),
    ('宝贝计划6', '封面', 'VIDEO', '描述', '2022-12-17'),
    ('宝贝计划7', '封面', 'VIDEO', '描述', '2022-12-17'),
    ('宝贝计划8', '封面', 'VIDEO', '描述', '2022-12-17'),
    ('宝贝计划9', '封面', 'VIDEO', '描述', '2022-12-17'),
    ('宝贝计划10', '封面', 'VIDEO', '描述', '2022-12-17'),
    ('宝贝计划11', '封面', 'VIDEO', '描述', '2022-12-17');

insert into home_funny_media_tag_mapping
    (media_id, tag_id) VALUE
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 1),
    (2, 2),
    (2, 3),
    (3, 1),
    (3, 2),
    (3, 3),
    (4, 1),
    (4, 2),
    (4, 3),
    (5, 1),
    (5, 2),
    (5, 3);

insert into home_funny_file (file_group, file_name, file_path) VALUE
    ('video', '计划.zip', '计划.zip'),
    ('video', '计划2.zip', '计划2.zip')
