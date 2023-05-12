insert into home_funny_media_tag (id, name) value (1, '血腥'), (2, '暴力'), (3, '文艺'), (4, '青春');

insert into home_funny_storage
    (id, storage_group, storage_name, storage_path) value
    (1, 'video', 'favicon.ico', 'favicon.ico'),
    (2, 'video', 'favicon2.ico', 'favicon2.ico');

insert into home_funny_multi_media
    (id, name, media_type, description, cover_storage_id, create_date) VALUE
    (1, '宝贝计划1', 'VIDEO', '描述', 1, curdate()),
    (2, '宝贝计划2', 'VIDEO', '描述', 1, curdate()),
    (3, '宝贝计划3', 'VIDEO', '描述', 1, curdate()),
    (4, '宝贝计划4', 'VIDEO', '描述', 1, curdate()),
    (5, '宝贝计划5', 'VIDEO', '描述', 1, curdate()),
    (6, '宝贝计划6', 'VIDEO', '描述', 1, curdate()),
    (7, '宝贝计划7', 'VIDEO', '描述', 1, curdate()),
    (8, '宝贝计划8', 'VIDEO', '描述', 1, curdate()),
    (9, '宝贝计划9', 'VIDEO', '描述', 1, curdate()),
    (10, '宝贝计划10', 'VIDEO', '描述', 1, curdate()),
    (11, '宝贝计划11', 'VIDEO', '描述', 1, curdate());

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

insert into home_funny_media_detail
    (id, media_id, detail_name, sorter, storage_id) value
    (1, 1, 'detail_name', 1, 1),
    (2, 1, 'detail_name', 2, 1),
    (3, 2, 'detail_name', 1, 1),
    (4, 2, 'detail_name', 1, 1),
    (5, 3, 'detail_name', 2, 1),
    (6, 3, 'detail_name', 1, 1),
    (7, 4, 'detail_name', 2, 1),
    (8, 4, 'detail_name', 1, 1),
    (9, 5, 'detail_name', 2, 1),
    (10, 5, 'detail_name', 1, 1),
    (11, 6, 'detail_name', 2, 1),
    (12, 6, 'detail_name', 1, 1),
    (13, 7, 'detail_name', 2, 1),
    (14, 7, 'detail_name', 1, 1),
    (15, 8, 'detail_name', 2, 1),
    (16, 8, 'detail_name', 1, 1),
    (17, 9, 'detail_name', 2, 1),
    (18, 9, 'detail_name', 1, 1),
    (19, 10, 'detail_name', 1, 1),
    (20, 10, 'detail_name', 2, 1),
    (21, 11, 'detail_name', 1, 1),
    (22, 11, 'detail_name', 2, 1);


