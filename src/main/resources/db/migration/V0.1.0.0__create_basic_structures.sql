
CREATE TABLE topic (
 id BIGSERIAL PRIMARY KEY,
 description VARCHAR(500) NOT NULL
);

CREATE TABLE poll (
 id BIGSERIAL PRIMARY KEY,
 topic_id BIGINT NOT NULL,
 description VARCHAR(500) NOT NULL,
 end_date TIMESTAMP NOT NULL,
 FOREIGN KEY(topic_id) REFERENCES topic(id)
);

CREATE TABLE poll_option (
 id BIGSERIAL PRIMARY KEY,
 poll_id BIGINT NOT NULL,
 description VARCHAR(500) NOT NULL,
 FOREIGN KEY(poll_id) REFERENCES poll(id),
 UNIQUE(id, poll_id)
);

CREATE TABLE vote (
 voter_id BIGINT NOT NULL,
 poll_id BIGINT NOT NULL,
 poll_option_id BIGINT NOT NULL,
 PRIMARY KEY(voter_id, poll_id),
 FOREIGN KEY(poll_id, poll_option_id) REFERENCES poll_option(id, poll_id)
);

CREATE INDEX poll_option_fk_index ON poll_option(id, poll_id);
