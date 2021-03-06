-- clave: logicgame
CREATE ROLE lg LOGIN
  ENCRYPTED PASSWORD 'md54b42ae7558548a3743da7bd729dffe56'
  NOSUPERUSER NOINHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;

GRANT SELECT, INSERT, UPDATE ON idiomas TO lg;
GRANT SELECT, INSERT, UPDATE ON literales TO lg;
GRANT SELECT, INSERT, UPDATE, DELETE ON juegos TO lg;
GRANT SELECT, INSERT, UPDATE, DELETE ON juegosxidioma TO lg;
GRANT SELECT, INSERT, DELETE ON dimensiones TO lg;
GRANT SELECT, INSERT, DELETE ON valores TO lg;
GRANT SELECT, INSERT, DELETE ON pistas_juego TO lg;
GRANT SELECT, INSERT, DELETE ON pistas TO lg;
GRANT SELECT, INSERT, UPDATE, DELETE ON rutas TO lg;
GRANT SELECT, INSERT, UPDATE ON usuarios TO lg;

-- clave: logicgame
CREATE ROLE lgweb LOGIN
  ENCRYPTED PASSWORD 'md52857636bdbdf5b6dbee3da682c4e2b78'
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;
  
GRANT SELECT ON juegos TO lgweb;
GRANT SELECT ON juegosxidioma TO lgweb;
GRANT SELECT ON rutas TO lgweb;
GRANT SELECT, INSERT, UPDATE ON usuarios TO lgweb;
GRANT SELECT ON idiomas TO lgweb;