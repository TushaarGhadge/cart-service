# cart-service
* This is the  main service for adding products into the cart and then performing checkout at the end.
* Customer interacts with this service for purchasing from grocery store
* This service is dependent on product catalog service and order service 
* It commumnicates with these two services using JWT authorization
* Implicit authorization is used to commumnicate between two services to implement security

## Endpoints
1. http://localhost:9090/cart/{cartId}                                    GET
2. http://localhost:9090/cart/{cartId}/item/{itemId}/quantity/{quantity}  POST
3. http://localhost:9090/cart/{cartId}/item/{itemId}/quantity/{quantity}  DELETE
4. http://localhost:9090/checkout/{cartId}                                POST
