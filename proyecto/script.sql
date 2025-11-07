CREATE DATABASE IF NOT EXISTS gestion_compras;
USE gestion_compras;
CREATE OR REPLACE TABLE proveedores (
	id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(50) NOT NULL,
	contacto VARCHAR(9) NOT NULL,
	email VARCHAR(30) NOT NULL,
	direccion VARCHAR(50) NOT NULL
);

CREATE OR REPLACE TABLE compras (
	id_compra INT AUTO_INCREMENT PRIMARY KEY,
	id_proveedor INT,
	fecha DATE NOT NULL,
	
	CONSTRAINT fk_id_pro FOREIGN KEY (id_proveedor) REFERENCES proveedores(id_proveedor)
);

CREATE OR REPLACE TABLE productos (
	id_producto INT AUTO_INCREMENT PRIMARY KEY,
	id_proveedor INT,
	nombre VARCHAR(50) NOT NULL,
	precio_unitario DECIMAL(10,2) NOT NULL,
	stock_actual INT NOT NULL,
	
	CONSTRAINT fk_id_pro_p FOREIGN KEY (id_proveedor) REFERENCES proveedores(id_proveedor)
);

CREATE OR REPLACE TABLE detalle_compra (
	id_detalle INT AUTO_INCREMENT PRIMARY KEY,
	id_compra INT,
	id_producto INT,
	id_proveedor INT,
	cantidad INT NOT NULL,
	precio DECIMAL(10,2),
	
	CONSTRAINT fk_id_com FOREIGN KEY (id_compra) REFERENCES compras(id_compra),
	CONSTRAINT fk_id_prod FOREIGN KEY (id_producto) REFERENCES productos(id_producto),
	CONSTRAINT fk_id_pro_d FOREIGN KEY (id_proveedor) REFERENCES proveedores(id_proveedor),
	
	CONSTRAINT ck_cantidad CHECK (cantidad>0)
);

CREATE TABLE proveedores_historico (
id_historico INT AUTO_INCREMENT PRIMARY KEY,
id_proveedor INT,
nombre VARCHAR(100),
email VARCHAR(100),
direccion VARCHAR(150),
contacto INT,
fecha_eliminacion DATETIME DEFAULT CURRENT_TIMESTAMP
);



INSERT INTO proveedores (nombre, contacto, email, direccion) VALUES
('Tech Solutions S.A.', 912345678, 'contacto@techsolutions.com', 'Av. Siempre Viva 123'),
('Distribuidora Global', 987654321, 'ventas@distribuidoraglobal.com', 'Calle Central 45'),
('Suministros del Norte', 934567890, 'info@suministrosnorte.com', 'Av. Libertad 77'),
('Proveedora Industrial', 923456789, 'contacto@proveedoraind.com', 'Parque Industrial 22'),
('Papelería Total', 976543210, 'ventas@papeleriastotal.com', 'Calle Comercio 10'),
('OfiCenter S.A.', 965432187, 'info@oficenter.com', 'Avenida Las Flores 89'),
('Electronix Import', 978654321, 'ventas@electronix.com', 'Calle 9 de Octubre 55');

INSERT INTO productos (id_proveedor, nombre, precio_unitario, stock_actual) VALUES
(1, 'Laptop HP Pavilion', 2500.00, 15),
(1, 'Mouse Inalámbrico', 80.00, 100),
(2, 'Monitor Samsung 24"', 950.00, 20),
(3, 'Teclado Mecánico RGB', 150.00, 50),
(4, 'Taladro Industrial', 1200.00, 10),
(5, 'Resma A4', 25.00, 500),
(6, 'Silla Ergonómica', 650.00, 30);

INSERT INTO compras (id_proveedor, fecha) VALUES
(1, '2025-11-01'),
(2, '2025-11-01'),
(3, '2025-11-03'),
(4, '2025-11-03'),
(5, '2025-11-05'),
(6, '2025-11-05'),
(7, '2025-11-07');

INSERT INTO detalle_compra (id_compra, id_producto, id_proveedor, cantidad, precio) VALUES
(1, 1, 1, 5, 2500.00),
(1, 2, 1, 10, 80.00),
(2, 3, 2, 4, 950.00),
(3, 4, 3, 6, 150.00),
(4, 5, 4, 3, 1200.00),
(5, 6, 5, 20, 25.00),
(6, 7, 6, 2, 650.00);


DELIMITER $$

CREATE OR REPLACE PROCEDURE eliminar_proveedor(IN p_id INT)
BEGIN
    DECLARE v_nombre VARCHAR(100);
    DECLARE v_email VARCHAR(100);
    DECLARE v_direccion VARCHAR(150);
    DECLARE v_contacto INT;

    -- Obtener datos del proveedor antes de eliminarlo
    SELECT nombre, email, direccion, contacto
    INTO v_nombre, v_email, v_direccion, v_contacto
    FROM proveedores
    WHERE id_proveedor = p_id;

    -- Insertar en tabla histórica
    INSERT INTO proveedores_historico (id_proveedor, nombre, email, direccion, contacto)
    VALUES (p_id, v_nombre, v_email, v_direccion, v_contacto);

    -- Eliminar del original
    DELETE FROM proveedores WHERE id_proveedor = p_id;
END$$

DELIMITER ;








