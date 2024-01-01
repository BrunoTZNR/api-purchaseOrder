# PURCHASE JAVA WEB
## Cliente
**Get** - GET ALL

    http://localhost:8080/v1/client
**Get** - GET ONE

     http://localhost:8080/v1/client/01234567890
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
## Product
**Get** - GET ALL

    http://localhost:8080/v1/product
**Get** - GET ONE

     http://localhost:8080/v1/product/1
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
## PurchaseOrder
**Get** - GET ALL

    http://localhost:8080/v1/purchaseOrder
**Get** - GET ONE

     http://localhost:8080/v1/purchaseOrder/1
**Post** - POST

    http://localhost:8080/v1/purchaseOrder
 ```json
{
    "dataPedido": "2023-10-25",
    "clientCpf": "01234567890"
}
```
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
   ## ProductItem
   **Get** - GET ALL

    http://localhost:8080/v1/productItem
**Get** - GET ONE

     http://localhost:8080/v1/productItem/1
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
