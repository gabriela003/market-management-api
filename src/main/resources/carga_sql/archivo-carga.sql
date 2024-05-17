INSERT INTO `COMITENTES` (`descripcion`)
VALUES
    ('comitente 1A'),
    ('comitente 2B'),
    ('comitente 3C'),
    ('comitente 4D'),
    ('comitente 5E'),
    ('comitente 6F'),
    ('comitente 7G'),
    ('comitente 8H'),
    ('comitente 9I'),
    ('comitente 10J'),
    ('comitente 11K'),
    ('comitente 12L'),
    ('comitente 13M'),
    ('comitente 14N'),
    ('comitente 15O'),
    ('comitente 16P'),
    ('comitente 17Q'),
    ('comitente 18R'),
    ('comitente 19S'),
    ('comitente 20T'),
    ('comitente 21U'),
    ('comitente 22V');

INSERT INTO `MERCADOS` (`codigo`,`descripcion`, `pais`)
VALUES
    ('MAE','mercado abierto electronico','Argentina'),
    ('ROFEX','mercado de futuros', 'Argentina'),
    ('UFEX', 'bolsa de Valores y futuros','Uruguay');


INSERT INTO `COMITENTES_X_MERCADOS` (`comitente_id`,`mercado_id`)
VALUES
    (1,1), (2,1), (3,1), (4,1), (5,1), (6,1), (7,1), (8,1), (9,1), (10,1), (11,1), (12,1),
    (13,2),
    (14,3),(15,3),(15,3);

