# INSERT INTO eventsagregator.role (id, roleName, description) VALUES (0, 'ADMIN', 'Admin');
# INSERT INTO eventsagregator.role (id, roleName, description) VALUES (1, 'USER', 'User');
#
# INSERT INTO eventsagregator.role (id, roleName, description) VALUES (0, 'ACTIVE', 'Active');
# INSERT INTO eventsagregator.role (id, roleName, description) VALUES (1, 'INACTIVE', 'Inactive');
# INSERT INTO eventsagregator.role (id, roleName, description) VALUES (2, 'BLOCKED', 'Blocked');
#
# INSERT INTO eventsagregator.hibernate_sequence (next_val) VALUES (1), (1), (1), (1), (1), (1), (1), (1)
# WHERE NOT EXISTS (Select next_val From eventsagregator.hibernate_sequence);