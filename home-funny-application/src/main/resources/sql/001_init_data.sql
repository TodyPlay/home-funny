insert into home_funny_media_tag (id, name) value (1, '血腥'), (2, '暴力'), (3, '文艺'), (4, '青春');

insert into home_funny_multi_media
    (name, cover, type, description) VALUE
    ('宝贝计划1', '封面', 'VIDEO', '描述'),
    ('宝贝计划2', '封面', 'VIDEO', '描述'),
    ('宝贝计划3', '封面', 'VIDEO', '描述'),
    ('宝贝计划4', '封面', 'VIDEO', '描述'),
    ('宝贝计划5', '封面', 'VIDEO', '描述'),
    ('宝贝计划6', '封面', 'VIDEO', '描述'),
    ('宝贝计划7', '封面', 'VIDEO', '描述'),
    ('宝贝计划8', '封面', 'VIDEO', '描述'),
    ('宝贝计划9', '封面', 'VIDEO', '描述'),
    ('宝贝计划10', '封面', 'VIDEO', '描述'),
    ('宝贝计划11', '封面', 'VIDEO', '描述');

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
    (5, 3)
;