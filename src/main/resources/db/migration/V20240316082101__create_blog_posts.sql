CREATE TABLE blog_posts (
                            id varchar(255) NOT NULL,
                            author varchar(255) NOT NULL,
                            content varchar(10000),
                            created_at datetime(6),
                            title varchar(255),
                            PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;