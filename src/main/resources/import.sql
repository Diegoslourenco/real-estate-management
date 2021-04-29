# categorias
INSERT INTO categorias (`name`) VALUES ('Apartamento')
INSERT INTO categorias (`name`) VALUES ('Casa')
INSERT INTO categorias (`name`) VALUES ('Chácara')

# negocios
INSERT INTO negocios (`name`) VALUES ('Alugar')
INSERT INTO negocios (`name`) VALUES ('Comprar')

# estados
INSERT INTO estados (`uf`, `name`) VALUES ('BA', 'Bahia')
INSERT INTO estados (`uf`, `name`) VALUES ('CE', 'Ceará')
INSERT INTO estados (`uf`, `name`) VALUES ('RJ', 'Rio de Janeiro')
INSERT INTO estados (`uf`, `name`) VALUES ('SP', 'São Paulo')

# municipios
INSERT INTO municipios (`name`, `state_id`) VALUES ('Salvador', 1)
INSERT INTO municipios (`name`, `state_id`) VALUES ('Fortaleza', 2)
INSERT INTO municipios (`name`, `state_id`) VALUES ('Petrópolis', 3)
INSERT INTO municipios (`name`, `state_id`) VALUES ('Sorocaba', 4)

# bairros
INSERT INTO bairros (`name`, `city_id`, `state_id`) VALUES ('Pilar', 1, 1)
INSERT INTO bairros (`name`, `city_id`, `state_id`) VALUES ('Fátima', 2, 2)
INSERT INTO bairros (`name`, `city_id`, `state_id`) VALUES ('Alto da Serra', 3, 3)
INSERT INTO bairros (`name`, `city_id`, `state_id`) VALUES ('Santa Rosália', 4, 4)
