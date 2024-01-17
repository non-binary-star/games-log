insert into "user"(id, name) values ('0db4c521-8554-4917-8289-c6ee67985ac4', 'Binary-Star');

insert into "game"(id, name) values ('217a1895-3f2b-4e19-9579-12cd7917eb18', 'Last of Us');
insert into "game"(id, name) values ('0ba6307f-7c7d-4e47-8195-8bcfdea222a0', 'Days Gone');
insert into "game"(id, name) values ('008cc69e-e237-4e39-bfcb-1302aca793ef', 'Death Stranding');
insert into "game"(id, name) values ('b7834983-ec2a-4d8a-90d3-84c5bcaf85a8', 'Firewatch');

insert into user_game(user_id, game_id, label)
select u.id, g.id, 'Completed'
from "user" u cross join game g
where u.name = 'Binary-Star' and g.name = 'Last of Us'
UNION
select u.id, g.id, 'Completed'
from "user" u cross join game g
where u.name = 'Binary-Star' and g.name = 'Days Gone'
UNION
select u.id, g.id, 'Playing'
from "user" u cross join game g
where u.name = 'Binary-Star' and g.name = 'Death Stranding'
UNION
select u.id, g.id, 'Planning'
from "user" u cross join game g
where u.name = 'Binary-Star' and g.name = 'Firewatch';