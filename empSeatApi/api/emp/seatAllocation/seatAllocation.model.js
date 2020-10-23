const mongoose = require('mongoose');
const Schema = mongoose.Schema;
// var empdetails = new Object();
const seatSchema = new Schema({
    seatid: String,
    seatNo: String,
    buildingName: String,
    floor: String,
    description: String,
    createdAt: String,
    assignedBy: String,
    assignedAt: String,
    empid: String,
    isAllocated: Boolean,
    empdetails: Map,
    updatedAt: String,
}
// },
// { timestamps: true }

);

module.exports = mongoose.model('seats', seatSchema);