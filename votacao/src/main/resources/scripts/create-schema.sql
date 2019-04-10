-- DROP SCHEMA votacao;

CREATE SCHEMA votacao
  AUTHORIZATION user_votacao;


-- Table: votacao.tb_associado

-- DROP TABLE votacao.tb_associado;

CREATE TABLE votacao.tb_associado
(
  id_associado serial NOT NULL,
  nome character varying(100) NOT NULL,
  cpf character varying(11) NOT NULL,
  CONSTRAINT pk_associado PRIMARY KEY (id_associado)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE votacao.tb_associado
  OWNER TO user_votacao;

  
 -- Table: votacao.tb_pauta

-- DROP TABLE votacao.tb_pauta;

CREATE TABLE votacao.tb_pauta
(
  id_pauta serial NOT NULL,
  nome character varying(100) NOT NULL,
  pergunta character varying(1000) NOT NULL,
  CONSTRAINT pk_pauta PRIMARY KEY (id_pauta)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE votacao.tb_pauta
  OWNER TO user_votacao;

  
-- Table: votacao.tb_sessao

-- DROP TABLE votacao.tb_sessao;

CREATE TABLE votacao.tb_sessao
(
  id_sessao serial NOT NULL,
  id_pauta integer NOT NULL,
  dt_inicio timestamp with time zone NOT NULL,
  dt_fim timestamp with time zone NOT NULL,
  CONSTRAINT pk_sessao PRIMARY KEY (id_sessao),
  CONSTRAINT fk_sessao_pauta FOREIGN KEY (id_pauta)
      REFERENCES votacao.tb_pauta (id_pauta) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE votacao.tb_sessao
  OWNER TO user_votacao;

  
-- Table: votacao.tb_voto

-- DROP TABLE votacao.tb_voto;

CREATE TABLE votacao.tb_voto
(
  id_voto serial NOT NULL,
  id_sessao integer NOT NULL,
  id_associado integer NOT NULL,
  valor character varying(3) NOT NULL,
  CONSTRAINT pk_voto PRIMARY KEY (id_voto),
  CONSTRAINT fk_voto_associado FOREIGN KEY (id_associado)
      REFERENCES votacao.tb_associado (id_associado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_voto_sessao FOREIGN KEY (id_sessao)
      REFERENCES votacao.tb_sessao (id_sessao) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE votacao.tb_voto
  OWNER TO user_votacao;
