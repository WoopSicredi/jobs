CREATE TABLE voto (
  id BIGINT NOT NULL AUTO_INCREMENT,
  pauta_id BIGINT NOT NULL,
  usuario_id BIGINT NOT NULL,
  decisao BOOLEAN NOT NULL COMMENT '1(Verdadeiro) para SIM e 0(Falso) para NÃO',
  PRIMARY KEY pk__voto (id),
  FOREIGN KEY fk__pauta (pauta_id) REFERENCES pauta(id),
  UNIQUE KEY uk__usuario_pauta (pauta_id, usuario_id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;