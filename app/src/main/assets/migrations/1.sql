CREATE TABLE Notes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title varchar(200) NOT NULL,
    body TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL
);