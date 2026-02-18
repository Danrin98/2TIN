-- Insert ~10 sample events for the application
-- Columns: id, name, event_date, venue_area, internal_code, ticket_price, ticket_capacity

INSERT INTO events (id, name, event_date, venue_area, internal_code, ticket_price, ticket_capacity) VALUES
(1, 'Java Day', TIMESTAMP '2026-01-10 10:00:00', 'DOWNTOWN', 'EVT-JAVA-001', 19.99, 10),
(2, 'Spring Boot Summit', TIMESTAMP '2026-02-15 09:30:00', 'RIVERSIDE', 'EVT-SB-002', 29.50, 50),
(3, 'Cloud Native Conf', TIMESTAMP '2026-03-20 13:00:00', 'CAMPUS', 'EVT-CLOUD-003', 39.00, 200),
(4, 'Microservices Meetup', TIMESTAMP '2026-04-05 18:30:00', 'INDUSTRIAL', 'EVT-MICRO-004', 9.99, 25),
(5, 'Frontend & Friends', TIMESTAMP '2026-05-12 11:00:00', 'SUBURBAN', 'EVT-FE-005', 14.50, 75),
(6, 'Data Engineering Day', TIMESTAMP '2026-06-01 10:00:00', 'DOWNTOWN', 'EVT-DED-006', 49.99, 150),
(7, 'AI & ML Workshop', TIMESTAMP '2026-07-22 09:00:00', 'RIVERSIDE', 'EVT-AI-007', 99.00, 120),
(8, 'DevOps Intensive', TIMESTAMP '2026-08-18 09:30:00', 'CAMPUS', 'EVT-DO-008', 59.00, 80),
(9, 'Security Essentials', TIMESTAMP '2026-09-14 14:00:00', 'INDUSTRIAL', 'EVT-SEC-009', 24.00, 40),
(10, 'Tech Careers Fair', TIMESTAMP '2026-10-05 10:00:00', 'SUBURBAN', 'EVT-CAREER-010', 0.00, 300);

-- End of data.sql

