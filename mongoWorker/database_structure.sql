create database group_test;

//No banco criado acima
create schema group_schema;

CREATE TABLE group_schema.groups (
	id uuid NOT NULL DEFAULT gen_random_uuid(),
	external_id text NOT NULL,
	version numeric NOT NULL,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT NULL,
	CONSTRAINT groups_pkey PRIMARY KEY (id)
);

CREATE TABLE group_schema.people (
	id uuid NOT NULL DEFAULT gen_random_uuid(),
	cpf text NOT NULL,
	group_id uuid NOT NULL,
	group_version numeric NOT NULL,
	active bool NOT NULL default false,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT NULL,
	CONSTRAINT people_pkey PRIMARY KEY (id),
	CONSTRAINT people_group_id_fkey FOREIGN KEY (group_id) REFERENCES group_schema.groups(id),
	CONSTRAINT people_table_cpf_group_id_unique UNIQUE (cpf,group_id)
);
CREATE UNIQUE INDEX people_table_cpf_group_id_idx ON group_schema.people (cpf,group_id);
CREATE UNIQUE INDEX people_table_update_active_status_idx ON group_schema.people (cpf,group_id,group_version,active);


insert into groups values ()