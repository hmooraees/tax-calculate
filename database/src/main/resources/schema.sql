CREATE TABLE IF NOT EXISTS CATEGORY (
  ID INT NOT NULL COMMENT 'ID da Categoria',
  NAME VARCHAR NOT NULL COMMENT 'Nome da Categotia'
);

CREATE TABLE IF NOT EXISTS PRODUCT (
  ID VARCHAR NOT NULL COMMENT 'ID do Produto',
  NAME VARCHAR NOT NULL COMMENT 'Nome do Produto',
  CATEGORY_ID INT NOT NULL COMMENT 'ID da Categoria',
  BASE_PRICE DOUBLE PRECISION NOT NULL DEFAULT 0.0 COMMENT 'Preco base do Produto',
  TAX_PRICE DOUBLE PRECISION NOT NULL DEFAULT 0.0 COMMENT 'Preco tarifado do Produto'
);