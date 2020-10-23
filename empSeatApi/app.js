const express = require('express');
const app = express();
const routes = require('./api/emp/seatAllocation/seatAllocation.router');
const config = require('./config');
const cors = require('cors');
const mongoose = require('mongoose');

// const swaggerUi = require('swagger-ui-express');
// const swaggerDocument = require('swagger-jsdoc'); //./swagger.json //swagger-jsdoc

swaggerJsdoc = require("swagger-jsdoc"),
swaggerUi = require("swagger-ui-express");

const bodyParser = require('body-parser');

app.use(bodyParser.json());

mongoose.connect(config.DB_URL, { useNewUrlParser: true, useUnifiedTopology: true }, (err) => {
    if (!err) {
        console.log('Database Connected...');
    } else {
        console.log(err);
    }
});
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(cors());


const options = {
    definition: {
      openapi: "3.0.0",
      info: {
        title: "Employee seat Management",
        version: "0.1.0",
        description:
          "This is a simple CRUD API application made with Express and documented with Swagger",
        
        license: {
          name: "MIT",
          url: "https://vvvvvv",
        },
        contact: {
          name: "vinoth",
          url: "https://vincssin.com",
          email: "vincssin@email.com",
        },
        basePath:'/'
      },
      servers: [
        {
          url: "http://localhost:5002/seats",
        },
      ],
    },
    securityDefinitions: {
        jsonWebToken: {
                type: "apiKey", 
                in: "header", 
                name: "Authorization"
               } 
    },
    apis: ["./api/emp/seatAllocation/seatAllocation.router.js"],
  };
  //./api/emp/seatAllocation/swagger.js
  const specs = swaggerJsdoc(options);
  app.use(
    "/api-docs",
    swaggerUi.serve,
    swaggerUi.setup(specs, { explorer: true }) //, { explorer: true } //Add search bar
  );
 
// app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerDocument));

// app.UseSwaggerUI(c =>
//     {
//         c.SwaggerEndpoint("./v1/swagger.json", "My API V1"); //originally "./swagger/v1/swagger.json"
//     });



app.use('/seats', routes);

module.exports = app;