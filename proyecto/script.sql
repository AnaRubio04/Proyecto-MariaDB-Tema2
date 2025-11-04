CREATE DATABASE IF NOT EXISTS gestion_compras;
USE gestion_compras;
CREATE OR REPLACE TABLE proveedores (
	id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(50) NOT NULL,
	contacto INT(9) NOT NULL,
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
	
	CONSTRAINT fk_id_com FOREIGN KEY (id_compra) REFERENCES compras(id_compra)
	CONSTRAINT fk_id_prod FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
	CONSTRAINT fk_id_pro_d FOREIGN KEY (id_proveedor) REFERENCES proveedores(id_proveedor)
	
	CONSTRAINT ck_cantidad CHECK (cantidad>0)
);