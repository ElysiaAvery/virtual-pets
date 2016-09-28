--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: communities; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE communities (
    id integer NOT NULL,
    name character varying,
    description character varying
);


ALTER TABLE communities OWNER TO "Guest";

--
-- Name: communities_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE communities_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE communities_id_seq OWNER TO "Guest";

--
-- Name: communities_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE communities_id_seq OWNED BY communities.id;


--
-- Name: monsters; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE monsters (
    id integer NOT NULL,
    name character varying,
    type character varying,
    personid integer,
    foodlevel integer,
    sleeplevel integer,
    playlevel integer,
    birthday timestamp without time zone,
    lastslept timestamp without time zone,
    lastate timestamp without time zone,
    lastplayed timestamp without time zone,
    lastkindling timestamp without time zone,
    lastwater timestamp without time zone
);


ALTER TABLE monsters OWNER TO "Guest";

--
-- Name: monsters_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE monsters_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE monsters_id_seq OWNER TO "Guest";

--
-- Name: monsters_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE monsters_id_seq OWNED BY monsters.id;


--
-- Name: persons; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE persons (
    id integer NOT NULL,
    name character varying,
    email character varying
);


ALTER TABLE persons OWNER TO "Guest";

--
-- Name: persons_communities; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE persons_communities (
    id integer NOT NULL,
    person_id integer,
    community_id integer
);


ALTER TABLE persons_communities OWNER TO "Guest";

--
-- Name: persons_communties_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE persons_communties_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE persons_communties_id_seq OWNER TO "Guest";

--
-- Name: persons_communties_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE persons_communties_id_seq OWNED BY persons_communities.id;


--
-- Name: persons_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE persons_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE persons_id_seq OWNER TO "Guest";

--
-- Name: persons_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE persons_id_seq OWNED BY persons.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY communities ALTER COLUMN id SET DEFAULT nextval('communities_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY monsters ALTER COLUMN id SET DEFAULT nextval('monsters_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY persons ALTER COLUMN id SET DEFAULT nextval('persons_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY persons_communities ALTER COLUMN id SET DEFAULT nextval('persons_communties_id_seq'::regclass);


--
-- Data for Name: communities; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY communities (id, name, description) FROM stdin;
\.


--
-- Name: communities_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('communities_id_seq', 1, false);


--
-- Data for Name: monsters; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY monsters (id, name, type, personid, foodlevel, sleeplevel, playlevel, birthday, lastslept, lastate, lastplayed, lastkindling, lastwater) FROM stdin;
\.


--
-- Name: monsters_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('monsters_id_seq', 1, false);


--
-- Data for Name: persons; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY persons (id, name, email) FROM stdin;
\.


--
-- Data for Name: persons_communities; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY persons_communities (id, person_id, community_id) FROM stdin;
\.


--
-- Name: persons_communties_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('persons_communties_id_seq', 1, false);


--
-- Name: persons_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('persons_id_seq', 1, false);


--
-- Name: communities_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY communities
    ADD CONSTRAINT communities_pkey PRIMARY KEY (id);


--
-- Name: monsters_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY monsters
    ADD CONSTRAINT monsters_pkey PRIMARY KEY (id);


--
-- Name: persons_communties_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY persons_communities
    ADD CONSTRAINT persons_communties_pkey PRIMARY KEY (id);


--
-- Name: persons_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY persons
    ADD CONSTRAINT persons_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

