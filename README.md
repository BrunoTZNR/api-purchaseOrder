# PURCHASE JAVA WEB
Esta api propõe o mantimento de uma ordem de compra, tendo todo CRUD funcional e validações para a funcionamento da api, a iniciativa deste projeto teve com um trabalho de faculdade da matéria de Programação para web.

### Stack utilizada

**IDE:** Eclipse

**Linguagem:** Java 17 - Spring Boot 3.2.1

### Funcionalidades

- Manter entidades (client, product, purchaseOrder, productItem)
- Facil uso e entendimento
- Auto controle de quantidade de estoque de produtos (cadastrando-os em ordens ou excluindo ordens)

### Licença

[MIT](https://choosealicense.com/licenses/mit/)

## Documentação da Api

### Cliente
**Get** - GET ALL

    http://localhost:8080/v1/client

**Post** - POST

    http://localhost:8080/v1/client
 ```json
{
    "cpf": "01234567890",
    "name": "Bruno",
    "email": "admin@admin.com",
    "dtNasc": "2000-01-01"
}
```
**Get** - GET ONE

     http://localhost:8080/v1/client/01234567890
**Put** - PUT

    http://localhost:8080/v1/client/01234567890
 ```json
{
    "cpf": "01234567890",
    "name": "Bruno Marco",
    "email": "admin@admin.com",
    "dtNasc": "2000-01-01"
}
```
**Delete**

    http://localhost:8080/v1/client/01234567890
#
### Product
**Get** - GET ALL

    http://localhost:8080/v1/product

**Post** - POST

    http://localhost:8080/v1/product
 ```json
{
    "name": "Maçã",
    "description": "Fruta",
    "quantity": 20,
    "amount": 2.99
}
```
**Get** - GET ONE

     http://localhost:8080/v1/product/1
**Put** - PUT

    http://localhost:8080/v1/client/01234567890
 ```json
{
    "name": "Banana",
    "description": "Fruta",
    "quantity": 30,
    "amount": 2.99
}
```
**Delete**

    http://localhost:8080/v1/product/1
#
### PurchaseOrder
**Get** - GET ALL

    http://localhost:8080/v1/purchaseOrder

**Post** - POST

    http://localhost:8080/v1/purchaseOrder
 ```json
{
    "dataPedido": "2023-10-25",
    "clientCpf": "01234567890"
}
```
**Get** - GET ONE

     http://localhost:8080/v1/purchaseOrder/1
**Put** - PUT

    http://localhost:8080/v1/purchaseOrder/1
 ```json
{
    "dataPedido": "2023-10-23",
    "clientCpf": "01234567890"
}
```
**Delete**

    http://localhost:8080/v1/purchaseOrder/1
#
### ProductItem
   **Get** - GET ALL

    http://localhost:8080/v1/productItem

**Post** - POST

    http://localhost:8080/v1/purchaseOrder
 ```json
{
    "quantity": 2,
    "amount": 5.98,
    "productId": 1,
    "purchaseOrderId": 1
}
```
**Get** - GET ONE

     http://localhost:8080/v1/productItem/1
**Put** - PUT

    http://localhost:8080/v1/productItem/1
 ```json
{
    "quantity": 1,
    "amount": 2.99,
    "productId": 1,
    "purchaseOrderId": 1
}
```
**Delete**

    http://localhost:8080/v1/productItem/1
