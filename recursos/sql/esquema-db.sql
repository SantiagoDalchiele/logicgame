-- @Deprecated: Se utiliza uy.com.uma.logicgame.persistencia.ManejadorEstructura para administrar la estructura de la base de datos

-- Internacionalización
CREATE TABLE IF NOT EXISTS idiomas (
	id CHAR(2) PRIMARY KEY,
	nombre VARCHAR(64) NOT NULL,
	icono VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS literales (
	id BIGINT,
	idioma CHAR(2) REFERENCES idiomas (id),
	texto TEXT NOT NULL,
	PRIMARY KEY (id, idioma)
);



-- Definición del juego
CREATE TABLE IF NOT EXISTS juegos (
	id INTEGER PRIMARY KEY,
	titulo BIGINT NOT NULL,
	texto BIGINT NOT NULL,
	costo INTEGER NOT NULL,
	cant_dims SMALLINT,
	cant_valores SMALLINT,
	solucion TEXT
);

CREATE TABLE IF NOT EXISTS juegosxidioma (
	id_juego INTEGER REFERENCES juegos (id),
	idioma CHAR(2) REFERENCES idiomas (id),
	def_juego TEXT,
	PRIMARY KEY (id_juego, idioma)
);
	
CREATE TABLE IF NOT EXISTS dimensiones (
	id_juego INTEGER REFERENCES juegos (id),
	sec SMALLINT,
	id BIGINT NOT NULL,
	PRIMARY KEY (id_juego, sec)
);
	
CREATE TABLE IF NOT EXISTS valores (
	id_juego INTEGER,
	sec_dim SMALLINT,
	sec SMALLINT,
	valor BIGINT NOT NULL,
	PRIMARY KEY (id_juego, sec_dim, sec),
	FOREIGN KEY (id_juego, sec_dim) REFERENCES dimensiones (id_juego, sec)
);

CREATE TABLE IF NOT EXISTS pistas_juego (
	id_juego INTEGER REFERENCES juegos (id),
	sec SMALLINT,
	texto BIGINT NOT NULL,
	PRIMARY KEY (id_juego, sec)
);

CREATE TABLE IF NOT EXISTS pistas (
	id_juego INTEGER,
	sec_pista SMALLINT,
	sec SMALLINT,
	dim1 SMALLINT,
	valor1 SMALLINT,
	dim2 SMALLINT,
	valor2 SMALLINT,
	afirma_niega BOOLEAN NOT NULL,
	PRIMARY KEY (id_juego, sec_pista, sec),
	FOREIGN KEY (id_juego, sec_pista) REFERENCES pistas_juego (id_juego, sec),
	FOREIGN KEY (id_juego, dim1, valor1) REFERENCES valores (id_juego, sec_dim, sec),
	FOREIGN KEY (id_juego, dim2, valor2) REFERENCES valores (id_juego, sec_dim, sec)	
);



-- Esquema de usuarios
CREATE TABLE IF NOT EXISTS rutas (
	id SMALLINT,
	nivel INTEGER,
	id_juego INTEGER REFERENCES juegos (id),
	hoja_estilo VARCHAR(256),
	PRIMARY KEY (id, nivel)
);

CREATE TABLE IF NOT EXISTS usuarios (
	id VARCHAR(64) PRIMARY KEY,
	alias VARCHAR(64) NOT NULL,
	correo VARCHAR(128) NOT NULL,
	clave CHAR(256) NOT NULL,
	logeado BOOLEAN NOT NULL,
	ruta SMALLINT,
	nivel INTEGER,
	estado TEXT,
	idioma CHAR(2) REFERENCES idiomas (id),
	FOREIGN KEY (ruta, nivel) REFERENCES rutas (id, nivel)	
);