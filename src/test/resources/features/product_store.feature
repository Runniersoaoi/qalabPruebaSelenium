# language: es

Característica: Product - Store
  Como automatizador de pruebas de regresión
  Quiero validar el flujo de compra de la tienda en línea
  Para detectar fallas de disponibilidad, autenticación o listado de productos

  Esquema del escenario: Validación del precio de un producto

    Dado estoy en la página de la tienda
    Y me logueo con mi usuario "<usuario>" y clave "<clave>"
    Entonces valido que el usuario se encuentre autenticado
    Cuando navego a la categoria "<categoria>" y subcategoria "<subcategoria>"
    Y agrego <cantidad> unidades del primer producto al carrito
    Entonces valido en el popup la confirmación del producto agregado
    Y valido en el popup que el monto total sea calculado correctamente
    Cuando finalizo la compra
    Entonces valido el titulo de la pagina del carrito
    Y vuelvo a validar el calculo de precios en el carrito

    Ejemplos:
      | usuario                   | clave           | categoria | subcategoria | cantidad |
      | matiasaquinozc@gmail.com  | Paasw0rd@123    | Clothes   | Men          | 2        |
      | usuario_invalido@mail.com | ClaveIncorrecta | Clothes   | Men          | 2        |
      | matiasaquinozc@gmail.com  | Paasw0rd@123    | Autos     | Men          | 2        |
