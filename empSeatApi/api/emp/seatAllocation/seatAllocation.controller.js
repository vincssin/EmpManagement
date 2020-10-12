// const logger = require('../../../logger');
const SeatModel = require('./seatAllocation.model');
const { v4: uuidv4 } = require('uuid');

////Seat Curd Operations
const AddSeats = (req, res) => {
    console.log('AddSeats New seats Loop.... ');
    var isAdd = true;
    var seatNo = '';
    for (var i = 1; i <= 3; i++) {
        let seat = new SeatModel();

        if (i < 9) {
            seatNo = "000" + i;
        } else if (i < 900) {
            seatNo = "0" + i;
        }

        seat.seatid = uuidv4();
        seat.seatNo = 'U' + seatNo,
            seat.buildingName = 'Prestige Shantiniketan Complex Rd',
            seat.floor = '14th Floor, Tower B',
            seat.description = '',
            seat.assignedBy = '',
            seat.assignedAt = '',
            seat.empid = '',
            seat.isAllocated = false,
            console.log('seat: ' + seat);
        seat.save((err, doc) => {
            if (err) {
                console.log('-----error');
                isAdd = false;
                // break;
                // res.send({ error: err });
            } else {
                console.log('-----added succesfully');
                // res.send({ message: 'seats addedd successfully' });
            }
        });
    }


    if (isAdd) {
        res.send({ message: 'seats addedd successfully' });
    } else {
        res.send({ error: 'Somthing went wrong' });
    }

}

const AddSeat = (req, res) => {
    console.log('Db service Add-New: ');
    console.log('req.body.url: ');
    var isAdd = true;
    var seatNo = '';
    if (req.body.seatNo) {
        SeatModel.findOne({ seatNo: req.body.seatNo }, (err, docs) => {
            if (err) {
                res.send({ error: err });
            } else if (docs) {
                res.send({ message: 'Seat no ' + req.body.seatNo + ' already exist' });
            } else {
                let seat = new SeatModel();
                seat.seatid = uuidv4();
                seat.seatNo = req.body.seatNo,
                    seat.buildingName = req.body.buildingName,
                    seat.floor = req.body.floor,
                    seat.description = req.body.description,
                    seat.createdAt = new Date().toLocaleString(),

                    seat.assignedBy = '',
                    seat.assignedAt = '',
                    seat.empid = '',
                    seat.isAllocated = 'false',
                    console.log('seat: ' + seat);
                seat.save((err, doc) => {
                    if (err) {
                        console.log('-----error');
                        res.send({ error: err });
                    } else {
                        console.log('-----added succesfully');
                        res.send({ message: 'seats addedd successfully' });
                    }
                });
            }
        });
    } else {
        console.log('-----data body empty please check and try again');
        res.send({ errmessage: 'data body empty please check and try again' });
    }

}

const RemoveSeat = (req, res) => {
    console.log('RemoveSeat....req.params.seatNo: '+req.params.seatNo);
    if (req.params.seatNo) {
        SeatModel.findOneAndDelete({ seatNo: req.params.seatNo }, (err, doc) => {
            if (err) {
                res.send({ error: err });
            } else if (doc) {
                res.send({ message: 'Seat '+req.params.seatNo+' deleted successfully' });
            } else {
                res.send({ message: 'Data not found' });
            }
        });
    } else {
        res.send({ errmessage: 'Please check reuest parameter and try again' });
    }
}

const UpdateSeat = (req, res) => {
    console.log('UpdateSeat....req.params.seatNo: '+req.params.seatNo);
    if (req.params.seatNo) {
        SeatModel.findOneAndUpdate({ seatNo: req.params.seatNo }, req.body, (err, doc)=>{
            if (err) {
                res.send({ error: err });
            } else if (doc) {
                res.send({ message: 'Seat '+req.params.seatNo+' updated successfully' });
            } else {
                res.send({ message: 'Data not found' });
            }
        });
    } else {
        res.send({ errmessage: 'Please check request parameter and try again' });
    }
}


//Seat Allocation Operations


const SeatAllocation = (req, res) => {
    console.log('AllocateSeat....req.params.seatNo: '+req.params.seatNo);
    console.log('AllocateSeat....req.params.empid: '+req.params.empid);
    if (req.params.seatNo) {
        var empdetailsObj = {
            id:req.params.empid,
            name:'vvvvv'
        };
        SeatModel.findOneAndUpdate({ seatNo: req.params.seatNo }, 
            {
                assignedAt: new Date().toLocaleString(),
                empid: req.params.empid,
                isAllocated: true,
                updatedAt: new Date().toLocaleString(),
                empdetails: empdetailsObj
            }, (err, doc)=>{
            if (err) {
                res.send({ error: err });
            } else if (doc) {
                res.send({ message: 'Seat '+req.params.seatNo+' allocated for emp '+ req.params.empid});
            } else {
                res.send({ message: 'Data not found' });
            }
        });
    } else {
        res.send({ errmessage: 'Please check request parameter and try again' });
    }
}

const EditSeatAllocation = (req, res) => {
    console.log('AllocateSeat....req.params.seatNo: '+req.params.seatNo);
    console.log('AllocateSeat....req.params.empid: '+req.params.empid);
    if (req.params.seatNo) {
        var empdetailsObj = {
            id:req.params.empid,
            name:''
        };
        SeatModel.findOneAndUpdate({ seatNo: req.params.seatNo }, 
            {
                assignedAt: new Date().toLocaleString(),
                empid: req.params.empid,
                isAllocated: true,
                updatedAt: new Date().toLocaleString(),
                empdetails: empdetailsObj
            }, (err, doc)=>{
            if (err) {
                res.send({ error: err });
            } else if (doc) {
                res.send({ message: 'Seat '+req.params.seatNo+' allocated for emp '+ req.params.empid});
            } else {
                res.send({ message: 'Data not found' });
            }
        });
    } else {
        res.send({ errmessage: 'Please check request parameter and try again' });
    }
}

const RemoveSeatAllocation = (req, res) => {
    
    var empdetailsObj = {
        id:'',
        name:''
    };
    if(req.query.seatNo){
        console.log('DeleteSeatAllocation: seatNo '+req.query.seatNo);
        SeatModel.findOneAndUpdate({ seatNo: req.query.seatNo }, 
            {
                assignedAt: '',
                empid: '',
                isAllocated: false,
                updatedAt: new Date().toLocaleString(),
                empdetails: empdetailsObj
            }, (err, doc)=>{
            if (err) {
                res.send({ error: err });
            } else if (doc) {
                res.send({ message: 'Seat '+req.query.seatNo+' allocation removed '});
            } else {
                res.send({ message: 'Data not found' });
            }
        });
    
    }else if(req.query.empid){
        console.log('DeleteSeatAllocation: empId '+req.query.empid);
        SeatModel.findOneAndUpdate({ seatNo: req.query.empid }, 
            {
                assignedAt: '',
                empid: '',
                isAllocated: false,
                updatedAt: new Date().toLocaleString(),
                empdetails: empdetailsObj
            }, (err, doc)=>{
            if (err) {
                res.send({ error: err });
            } else if (doc) {
                res.send({ message: 'Seat allocation removed for emp '+req.query.empid});
            } else {
                res.send({ message: 'Data not found' });
            }
        });
    }else {
        res.send({ errmessage: 'Please check request parameter and try again' });
    }
    
}

//get avalibale seats



const GetAvailableSeats = (req, res) => {
    console.log('GetAvailableSeats....');
    SeatModel.find({ isAllocated: false }, (err, docs) => {
        if (err) {
            res.send({ error: err });
        } else if (docs) {

            res.send({ Seats: [docs] });
        } else {
            res.send({ message: 'Data not found' });
        }
    });
}

const GetAllocatedSeats = (req, res) => {
    console.log('GetAvailableSeats....');
    SeatModel.find({ isAllocated: true }, (err, docs) => {
        if (err) {
            res.send({ error: err });
        } else if (docs) {

            res.send({ Seats: [docs] });
        } else {
            res.send({ message: 'Data not found' });
        }
    });
}

const GetAllSeats =  (req, res) => {

    if(true){
    SeatModel.find((err, docs) => {
        if (err) {
            res.send({ error: err });
        } else if (docs) {

            res.send({ Seats: [docs] });
        } else {
            res.send({ message: 'Data not found' });
        }
    });
}
}


module.exports = {
    AddSeats,
    AddSeat,
    RemoveSeat,
    UpdateSeat,

    SeatAllocation,
    EditSeatAllocation,
    RemoveSeatAllocation,

    GetAvailableSeats,
    GetAllocatedSeats,
    GetAllSeats,

}