
CREATE TABLE topic (
 id BIGSERIAL PRIMARY KEY,
 description VARCHAR(500) NOT NULL
);

CREATE TABLE poll (
 id BIGSERIAL PRIMARY KEY,
 topic_id BIGINT NOT NULL,
 description VARCHAR(500) NOT NULL,
 end_date TIMESTAMPTZ NOT NULL, -- TIMESTAMP with Time Zone
 FOREIGN KEY(topic_id) REFERENCES topic(id)
);

CREATE TABLE vote (
 voter_id BIGINT NOT NULL,
 poll_id BIGINT NOT NULL,
 poll_option CHAR(3) NOT NULL,
 PRIMARY KEY(voter_id, poll_id),
 FOREIGN KEY(poll_id) REFERENCES poll(id)
);
