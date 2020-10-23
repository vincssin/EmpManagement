const NewsController = require('./seatAllocation.controller');
// const logger = require('../../../logger');


//Seat Curd Operations

const addSeats = (req, res) => {
    // return NewsController.AddSeats(req, res);
    return secureRoute(req, res, 'AddSeats');
}

const addSeat = (req, res) => {
    // return NewsController.AddSeat(req, res);
    return secureRoute(req, res, 'AddSeat');
}

const removeSeat = (req, res) => {
    // return NewsController.RemoveSeat(req, res);
    return secureRoute(req, res, 'RemoveSeat');
}

const updateSeat = (req, res) => {
    // return NewsController.UpdateSeat(req, res);
    return secureRoute(req, res, 'UpdateSeat');
}

//Seat Allocation Operations

const seatAllocation = (req, res) => {
    // return NewsController.SeatAllocation(req, res);
    return secureRoute(req, res, 'SeatAllocation');
}

const editSeatAllocation = (req, res) => {
    // return NewsController.EditSeatAllocation(req, res);
    return secureRoute(req, res, 'EditSeatAllocation');
}

const removeSeatAllocation = (req, res) => {
    // return NewsController.RemoveSeatAllocation(req, res);
    return secureRoute(req, res, 'RemoveSeatAllocation');
}


//Seat all types of the Sets 

const getAvailableSeats = (req, res) => {
    // return NewsController.GetAvailableSeats(req, res);
    return secureRoute(req, res, 'GetAvailableSeats');
}

const getAllocatedSeats = (req, res) => {
    // return NewsController.GetAllocatedSeats(req, res);
    return secureRoute(req, res, 'GetAllocatedSeats');
}

const getAllSeats = (req, res) => {
  
     // return NewsController.GetAllSeats(req, res);

     return secureRoute(req, res, 'GetAllSeats');
}

const secureRoute =  (req, res, routFunc) => {
    
    console.log('AUTHORIZED HEADER: '+req.get('header'))
    if(req.get('Authorization')){
    getAuthUser(req.get('Authorization'),true).then((value) => {
        console.log('Response Message: '+value);
        if(value === 'success'){

            switch(routFunc) {
                case "AddSeats":
                    return NewsController.AddSeats(req, res);
                  break;
                case "AddSeat":
                    return NewsController.AddSeat(req, res);
                  break;
                case "RemoveSeat":
                    return NewsController.RemoveSeat(req, res);
                  break;
                case "UpdateSeat":
                    return NewsController.UpdateSeat(req, res);
                  break;
                case "SeatAllocation":
                    return NewsController.SeatAllocation(req, res);
                  break;
                case "EditSeatAllocation":
                    return NewsController.EditSeatAllocation(req, res);
                  break;
                case "RemoveSeatAllocation":
                    return NewsController.RemoveSeatAllocation(req, res);
                  break;
                case "GetAvailableSeats":
                    return NewsController.GetAvailableSeats(req, res);
                  break;
                case "GetAllocatedSeats":
                    return NewsController.GetAllocatedSeats(req, res);
                  break;
                case "GetAllSeats":
                    return NewsController.GetAllSeats(req, res);
                  break;
                default:
                    res.send({ message: 'Bad request'});
              }
        }else{
            // var isMsg = value.includes("message");
            if(typeof value === 'string'){
                res.status(401).send({ message: value});
            }else{
                res.status(400).send(value);
            }
        }
      })
    }else{
        res.status(401).send({ message: 'Authentication Failed: Authorization header not found Please check and try again'});
    }
    
}


const fetch = require("node-fetch");
const getAuthUser = async (token, checkUser) => {

    let isAuth=false;
    let msg='AAAA';
    
   await getAuth(token).then(res1 => res1.json()
            ).then(data1 => {
                console.log(data1+" authdata: " + data1.isAuthorized);
                if (data1.isAuthorized && data1.isAuthorized === 'true') {
                    console.log('Authenticated succesfully');
                    isAuth =true;
                    msg="success";
                } else {
                    console.log('unauthorized user '+data1);
                    msg=data1;
                }
            }).catch(error => {
                msg="Authentication Failed - Ivalid token or authentication server failed to start"
                console.log("ERROR111: " + error);
            });

            if(isAuth && checkUser){
                await  getUser(token).then(res2 => res2.json()
                ).then(data2 => {
                    console.log("role: " + data2.role);
                    console.log("userData: " + data2);
                    if (data2.role === 'ROLE_ADMIN') { //ROLE_USER
                        msg="success";
                    }else if (data2.role === 'ROLE_USER'){
                        msg="user does not have permission to access this resource";
                    }else{
                        msg="Invalid user";
                    }
                }).catch(error => {
                    console.log("ERROR222: " + error);
                });  
            }
            
           return msg; 
}

const getAuth = async (token) => {
    console.log('SERVICE getAuth........');
    const url = "http://localhost:8102/api/emp/user/isAuthorized";
    return await fetch('http://localhost:8102/api/emp/user/isAuthorized', {
        headers: {
            'Authorization': `${token}`,
            'Content-Type': 'application/json'
        },
    }).catch(error => {
        console.log("ERROR00: " + error)
    });
}


const getUser = async (token) => {
    console.log('SERVICE getUser........');
    const url = "http://localhost:8102/api/emp/user/get";
     return await fetch('http://localhost:8102/api/emp/user/get', {
        headers: {
            'Authorization': `${token}`,
            'Content-Type': 'application/json'
        },
    }).catch(error => {
        console.log("ERROR00: " + error)
    });
}




module.exports = {
    addSeats,
    addSeat,
    removeSeat,
    updateSeat,

    seatAllocation,
    editSeatAllocation,
    removeSeatAllocation,

    getAvailableSeats,
    getAllocatedSeats,
    getAllSeats,
    
};