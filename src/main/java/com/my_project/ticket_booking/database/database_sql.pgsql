INSERT INTO event_001(id, tittle, category, thumbnail, address, is_deleted)
SELECT
  gen_random_uuid(),
  'tittle_' || g,
  'category_' || (random()*10)::int,
  'https://picsum.photos/200/300?random=' || g,
  'address_' || g,
    false
FROM generate_series(1,10000) AS g;

INSERT INTO event_detail_001(organizer, description, map, banner, start_date, end_date, created_at)
SELECT
    (SELECT id FROM event_001 ORDER BY id DESC LIMIT 1 OFFSET (g - 1)),
    'organizer_' || g,
    'description_' || g,
    jsonb_build_object(
  'name', 'User ' || g,
  'contact', jsonb_build_object(
    'email', 'user' || g || '@example.com',
    'phone', '+84' || (100000000 + (random() * 99999999)::int)
  ),
    'https://picsum.photos/200/300?random=' || g,
    NOW() + (g || ' days')::interval,
    NOW() + ((g + 1) || ' days')::interval
FROM generate_series(1,10000) AS g;

INSERT INTO event_tag_name_001(tag_name)
SELECT
    'tagName_' || g
FROM generate_series(1,100) AS g;

INSERT INTO event_tag_relation_001(event_id, tag_id)
SELECT
    SELECT id FROM event_001 ORDER BY id DESC LIMIT 1 OFFSET ( (random()*10000)::int ),
    SELECT id FROM event_tag_name_001 ORDER BY id DESC LIMIT 1 OFFSET ( (random()*100)::int )
FROM generate_series(1,20000) AS g;

INSERT INTO event_store_001(event_id)
SELECT
    'tagName_' || g
FROM generate_series(1,100) AS g;

INSERT INTO ticket_001(id, purchaser, tittle, description, img, policy, price, is_deleted, created_at, store_id )
SELECT
  gen_random_uuid(),
    'purchaser_' || g,
    'tittle_' || g,
    'description_' || g,
    'https://picsum.photos/200/300?random=' || g,
    'policy_' || g,
    (random()*10000)::int,
    false,
    NOW(),
    (SELECT es.event_id FROM event_store_001 es ORDER BY id DESC LIMIT 1 OFFSET ( (random()*10000)::int ))
FROM generate_series(1,100000) AS g;

