CREATE TABLE senhanormal
(
  idsenha integer NOT NULL,
  descricao text NOT NULL,
  CONSTRAINT senhanormal_pkey PRIMARY KEY (idsenha)
);



CREATE TABLE senhapref
(
  idsenha integer NOT NULL,
  descricao text NOT NULL,
  CONSTRAINT senhapref_pkey PRIMARY KEY (idsenha)
);

CREATE SEQUENCE "seq_senhanormal" START 1;
CREATE SEQUENCE "seq_senhapref" START 1;