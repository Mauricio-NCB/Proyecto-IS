use e9clubshop;

-- Mostrar todos los productos y sus detalles
SELECT * FROM Producto;

-- Mostrar camisetas disponibles
SELECT * FROM Camiseta c JOIN Producto p ON c.id = p.id;

-- Mostrar empleados con sus roles
SELECT e.identificador, e.nombre, e.sueldo, d.cargo 
FROM Empleado e LEFT JOIN Director d ON e.identificador = d.id;

-- Mostrar facturas con información del cliente
SELECT f.codigo, f.fecha, f.importe, c.nombre AS cliente, d.nombre AS dependiente
FROM Factura f
JOIN Cliente c ON f.cliente = c.num_socio
JOIN Dependiente dp ON f.dependiente = dp.id
JOIN Empleado d ON dp.id = d.identificador;

-- Mostrar ventas con productos asociados
SELECT v.id AS venta_id, p.nombre AS producto, v.precio, v.cantidad
FROM Venta v
JOIN VentaProducto vp ON v.id = vp.idVenta
JOIN Producto p ON vp.idProducto = p.id;
