
-- ----------------------------------------------------------------------------------------------------------
-- INITIALIZATION --
-- ----------------------------------------------------------------------------------------------------------

DROP DATABASE IF EXISTS tupperware;
Create Database tupperware;
Use tupperware;

-- ----------------------------------------------------------------------------------------------------------
-- CREATION OF RECORDS --
-- ----------------------------------------------------------------------------------------------------------

Create Table cliente
(
	idCliente Int NOT NULL AUTO_INCREMENT,
    nombre varchar(30),
    apellido varchar(30),
    direccion varchar(30),
    celular varchar(30),
    email varchar(30),
    PRIMARY KEY (idCliente)
);
      
Create Table tipoDeProducto
(
	idTipoDeProducto Int NOT NULL AUTO_INCREMENT,
    nombre varchar(30),
    porcentajeDeGanancia Int NOT NULL,
    PRIMARY KEY (idTipoDeProducto)
);

Create Table estadoDeCompra
(
	idEstadoDeCompra Int NOT NULL AUTO_INCREMENT,
    nombre varchar(30),
    PRIMARY KEY (idEstadoDeCompra)
);

Create Table producto
(
	idProducto Int NOT NULL AUTO_INCREMENT,
    codigo varchar(30),
    nombre varchar(30),
    descripcion varchar(100),
    idTipoDeProducto Int NOT NULL,
	FOREIGN KEY (idTipoDeProducto) REFERENCES tipoDeProducto (idTipoDeProducto),
    PRIMARY KEY (idProducto)
);

Create Table imagenDeProducto
(
	idImagenDeProducto Int NOT NULL AUTO_INCREMENT,
    imagen blob,
    idProducto Int NOT NULL,
	FOREIGN KEY (idProducto) REFERENCES Producto (idProducto),
	PRIMARY KEY (idImagenDeProducto)
);

Create Table campaña
(
	idCampaña Int NOT NULL AUTO_INCREMENT,
    numero varchar(30),
    año varchar(30),
    cierre date,
    PRIMARY KEY (idCampaña)
);

Create Table premio
(
	idPremio Int NOT NULL AUTO_INCREMENT,
    nombre varchar(30),
    descripcion varchar(100),
    unidadesMinimas Int NOT NULL,
    recibido boolean,
    idCampaña Int NOT NULL,
    FOREIGN KEY (idCampaña) REFERENCES campaña (idCampaña),
    PRIMARY KEY (idPremio)
);

Create Table contacto
(
	idContacto Int NOT NULL AUTO_INCREMENT,
	nombre varchar(30),
    apellido varchar(30),
    direccion varchar(30),
    celular varchar(30),
    email varchar(30),
    tipoDeContacto varchar(30),
    PRIMARY KEY (idContacto)
);

Create Table promocion
(
	idPromocion Int NOT NULL AUTO_INCREMENT,
    nombre varchar(30),
    descripcion varchar(100),
    idCampaña Int NOT NULL,
    FOREIGN KEY (idCampaña) REFERENCES campaña (idCampaña),
    PRIMARY KEY (idPromocion)
);

Create Table promocionProducto
(
	idPromocionProducto Int NOT NULL AUTO_INCREMENT,
    idPromocion Int NOT NULL,
    idProducto Int NOT NULL,
    FOREIGN KEY (idPromocion) REFERENCES promocion (idPromocion),
    FOREIGN KEY (idProducto) REFERENCES producto (idProducto),
    PRIMARY KEY (idPromocionProducto)
);

Create Table compraPromocion
(
	idCompraPromocion Int NOT NULL AUTO_INCREMENT,
    idPromocion Int NOT NULL,
    FOREIGN KEY (idPromocion) REFERENCES promocion (idPromocion),
    PRIMARY KEY (idCompraPromocion)
);

Create Table compraPromocionProducto
(
	idCompraPromocionProducto Int NOT NULL AUTO_INCREMENT,
    idCompraPromocion Int NOT NULL,
    idProducto Int NOT NULL,
    FOREIGN KEY (idCompraPromocion) REFERENCES compraPromocion (idCompraPromocion),
    FOREIGN KEY (idProducto) REFERENCES producto (idProducto),
    PRIMARY KEY (idCompraPromocionProducto)
);

Create Table compra
(
	idCompra Int NOT NULL AUTO_INCREMENT,
    pagina varchar(5),
    precio Int NOT NULL,
    unidades Int NOT NULL,
    montoPagado Int NOT NULL,
    porcentajeDeGanancia Int NOT NULL,
    idCliente Int NOT NULL,
    idProducto Int,
    idCompraPromocion Int,
    idCampaña Int NOT NULL,
    idEstadoDeCompra Int NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES cliente (idCliente),
    FOREIGN KEY (idProducto) REFERENCES producto (idProducto),
    FOREIGN KEY (idCompraPromocion) REFERENCES compraPromocion (idCompraPromocion),
    FOREIGN KEY (idCampaña) REFERENCES campaña (idCampaña),
    FOREIGN KEY (idEstadoDeCompra) REFERENCES estadoDeCompra (idEstadoDeCompra),
    PRIMARY KEY (idCompra)
);

-- ----------------------------------------------------------------------------------------------------------
-- INSERTION OF RECORDS --
-- ----------------------------------------------------------------------------------------------------------

INSERT INTO estadoDeCompra (nombre)
VALUES("Pagado"),
	  ("Mora"),
      ("Entregado");

INSERT INTO tipoDeProducto (nombre, porcentajeDeGanancia)
VALUES("Tupperware", 33),
	  ("Homeware", 15),
      ("Fullercosmetics", 25);

-- ----------------------------------------------------------------------------------------------------------
