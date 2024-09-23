# assessment
This is a pilot project of Spring boot 3  with in memory DB

<br/>

The application will start on <b>http://localhost:8080</b>
<br/><br/>
Access in memory H2 database at <b>http://localhost:8080/h2</b>
<br/>
This application has 2  entities named CUSTOMER and ORDER.
<br/><br/>
AspectJ logs included for request/response and unit test for each service class. <br/>
<br/>
API endpoints for POSTMAN <br/>


<ul><li>Customer:</li></ul>


<ul>
<li>POST /customers - Create a new Customer</li>
<li>GET /customers - Get all Customers</li>
<li>GET /customers/{id} - Get Customer by ID</li>
<li>PUT /customers/{id} - Update Customer</li>
<li>DELETE /customers/{id} - Delete Customer</li>
</ul>

<br/>
<ul><li>Order:</li></ul>

<ul>
<li>POST /orders - Create a new order</li>
<li>GET /orders - Get all orders with pagination (page, size as query params)</li>
<li>GET /orders/{id} - Get order by ID</li>
<li>PUT /orders/{id} - Update order</li>
<li>DELETE /orders/{id} - Delete order</li>

<li>GET /orders/by-customer-email?email={email}&page={page}&size={size} - Get orders by customers email with pagination</li>
</ul>
<br/>
<ul><li>External API:</li></ul>
<ul>
<li>GET external/api - Fetch API e.g. http://numbersapi.com/10</li>
</ul>
