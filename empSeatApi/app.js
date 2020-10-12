const express = require('express');
const app = express();
const routes = require('./api/emp/seatAllocation/seatAllocation.router');
const config = require('./config');
const cors = require('cors');
const mongoose = require('mongoose');

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

app.use('/api/emp/seat', routes);

module.exports = app;