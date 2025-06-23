use e9clubshop;


-- Vacia las tablas antes de insertar datos
SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE Producto;
TRUNCATE TABLE Juguete;
TRUNCATE TABLE Camiseta;
TRUNCATE TABLE Poster;
TRUNCATE TABLE Entrada;
TRUNCATE TABLE Empleado;
TRUNCATE TABLE Director;
TRUNCATE TABLE Dependiente;
TRUNCATE TABLE Cliente;
TRUNCATE TABLE Factura;
TRUNCATE TABLE Venta;
TRUNCATE TABLE Linea_venta;
SET FOREIGN_KEY_CHECKS=1;

-- Productos
INSERT INTO Producto (nombre, precio, stock) VALUES 
('Camiseta Oficial', 79.99, 100), 
('Balón Oficial', 29.99, 50), 
('Póster del equipo', 9.99, 200), 
('Entrada VIP', 150.00, 30);

-- Juguetes
INSERT INTO Juguete (id, tipo, tamano) VALUES (2, 'Balón', 'Mediano');

-- Camisetas
INSERT INTO Camiseta (id, talla, dorsal, numero) VALUES (1, 43, 'Messi', 10);

-- Pósters
INSERT INTO Poster (id, tamano) VALUES (3, 'A2');

-- Entradas
INSERT INTO Entrada (id, fecha, hora, ubicacion, numero_asiento, partido) VALUES 
(4, '2025-04-15', '20:00:00', 'Tribuna', 'A10', 'Final Copa');

-- Empleados
INSERT INTO Empleado (identificador, nombre, sueldo, contrasena) VALUES 
-- contrasena: contrasena1
('EMP001', 'Juan Pérez', 3000, 'd48b165d1e5a63b56c7601e4269642e6a71fa90b2178a0212a1da5f7ee54255f'), 
-- contrasena: contrasena2
('EMP002', 'María López', 2500, '6086d0c00085495558ee2dc7ba5a136de0a0c28ed46e4a957f0ec741e8d98966');

-- Directores
INSERT INTO Director (id, cargo) VALUES ('EMP001', 'DIRECTOR GENERAL');

-- Dependientes
INSERT INTO Dependiente (id, sum_ventas) VALUES ('EMP002', 5000);

-- Clientes
INSERT INTO Cliente (nombre, direccion, correo) VALUES 
('Carlos Sánchez', 'Calle Fútbol 10', 'carlos@email.com'), 
('Ana Torres', 'Avenida Gol 20', 'ana@email.com');

-- Ventas
INSERT INTO Venta (codigo, fecha, hora, cliente, dependiente, factura) VALUES 
('VEN001', '2025-03-25', '15:30:00', 1, 'EMP002', 'FAC001');

--Lineas de venta
INSERT INTO Linea_venta (venta, producto, cantidad, precio_unitario) VALUES 
('VEN001', 1, 2, 79.99),
('VEN001', 2, 1, 29.99);

-- Facturas
INSERT INTO Factura (codigo, fecha, hora, importe) VALUES 
('FAC001', '2025-03-25', '15:30:00', 159.99);