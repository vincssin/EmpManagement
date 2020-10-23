
// ===========================SEATS SCHEMA======================
/**
 * @swagger
 * components:
 *    schemas:
 *      Seats: 
 *        type: object
 *        required:
 *          - seatNo
 *          - buildingName
 *          - floor
 *        properties:
 *          id:
 *            type: integer
 *            description: The auto-generated id of the Seat.
 *          seatNo:
 *            type: string
 *            description: unique id of seat.
 *          buildingName:
 *            type: string
 *            description: Name of the building.
 *          floor:
 *            type: string
 *            description: Name of the floor.
 *          description:
 *            type: string
 *            description: Any description if you want.
 *          createdAt:
 *            type: string
 *            description: the date of the record creation(auto creation).
 *        example:
 *            buildingName: Prestige Shantiniketan Complex Rd
 *            floor: 14th Floor, Tower B
 *            description: added extra seats
 *    securitySchemes:
 *        ApiKeyAuth:
 *          type: apiKey
 *          in: header
 *          name: Authorization
*/


// *            - in: header
// *              name: Authorization
// *              type: string
// *              required: true

// ===========================SEATS CURD OPERATIONS======================
//****addseats****
/**
 * @swagger
 * tags:
 *    name: Seats
 *    description: API to manage employee seats.  
 * path:
 *    /addseats:    
 *        post:
 *          summary: Creates a new Seats
 *          tags: [Seats] 
 *          requestBody:
 *            required: true
 *            content:  
 *              application/json:
 *                schema:   
 *                  $ref: '#/components/schemas/Seats'
 *          security:
 *            - ApiKeyAuth: []
 *              
 *          responses:
 *            "200":
 *              description: seats addedd successfully.
 *            "404":
 *              description: Resource not found.
 *            
 */

//****addseat****
/**
 * @swagger 
 * path:
 *    /addseat{seatNo}:    
 *        post:
 *          summary: Creates a new Seats
 *          tags: [Seats] 
 *          parameters:
 *            - in: path
 *              name: seatNo
 *              type: string
 *              required: true
 *          requestBody:
 *            required: true
 *            content:  
 *              application/json:
 *                schema:   
 *                  $ref: '#/components/schemas/Seats'
 *          security:
 *            - ApiKeyAuth: []
 *          responses:
 *            "200":
 *              description: seats addedd successfully.
 *            "404":
 *              description: Resource not found.
 *            
 */

//****removeseat****
/**
 * @swagger 
 * path:
 *    /removeseat/{seatNo}:    
 *        delete:
 *          summary: Delete existing Seat
 *          tags: [Seats] 
 *          parameters:
 *            - in: path
 *              name: seatNo
 *              type: string
 *              required: true
 *          security:
 *            - ApiKeyAuth: []

 *          responses:
 *            "200":
 *              description: seats addedd successfully.
 *            "404":
 *              description: Resource not found.
 *            
 */

 //****updateseat****
/**
 * @swagger 
 * path:
 *    /updateseat/{seatNo}:    
 *        put:
 *          summary: Update existing Seat record
 *          tags: [Seats] 
 *          parameters:
 *            - in: path
 *              name: seatNo
 *              type: string
 *              required: true
 *          requestBody:
 *            required: true
 *            content:  
 *              application/json:
 *                schema:   
 *                  $ref: '#/components/schemas/Seats'
 *          security:
 *            - ApiKeyAuth: []
 *          responses:
 *            "200":
 *              description: seats addedd successfully.
 *            "404":
 *              description: Resource not found.
 *            
 */

// ===========================SeatAllocation======================
 //****seatallocation****
 /**
 * @swagger
 * tags:
 *    name: SeatAllocation
 *    description: API to manage employee seats allocation.  
 * path:
 *    /seatallocation/:seatNo/:empid':    
 *        put:
 *          summary: Allocate seat to employee
 *          tags: [SeatAllocation] 
 *          parameters:
 *            - in: path
 *              name: seatNo
 *              type: string
 *              required: true
 *            - in: path
 *              name: empid
 *              type: string
 *              required: true
 *          security:
 *            - ApiKeyAuth: []
 *              
 *          responses:
 *            "200":
 *              description: seats addedd successfully.
 *            "404":
 *              description: Resource not found.
 *            
 */

 //****editseatallocation****
 /**
 * @swagger
  
 * path:
 *    /editseatallocation/:seatNo/:empid':    
 *        put:
 *          summary: Edit alllocated seat to employee
 *          tags: [SeatAllocation] 
 *          parameters:
 *            - in: path
 *              name: seatNo
 *              type: string
 *              required: true
 *            - in: path
 *              name: empid
 *              type: string
 *              required: true
 *          security:
 *            - ApiKeyAuth: []
 *              
 *          responses:
 *            "200":
 *              description: seats addedd successfully.
 *            "404":
 *              description: Resource not found.
 *            
 */

 //****removeseatallocation****
 /**
 * @swagger
 
 * path:
 *    /removeseatallocation:    
 *        put:
 *          summary: Edit alllocated seat to employee
 *          tags: [SeatAllocation] 
 *          parameters:
 *            - in: query
 *              name: seatNo
 *              type: string
 *              required: true

 *          security:
 *            - ApiKeyAuth: []
 *              
 *          responses:
 *            "200":
 *              description: seats addedd successfully.
 *            "404":
 *              description: Resource not found.
 *            
 */

 // ===========================CHECK SEATS AVAILABILTY======================

//****getavailableseats****
/**
 * @swagger
 * tags:
 *    name: SeatsAvailability
 *    description: API to check available seats.  
 * path:
 *    /getavailableseats:    
 *        post:
 *          summary: Get all unallocated seats
 *          tags: [SeatsAvailability] 
 *          security:
 *            - ApiKeyAuth: []
 *              
 *          responses:
 *            "200":
 *              description: seats addedd successfully.
 *            "404":
 *              description: Resource not found.
 *            
 */

 //****getallocatedseats****
/**
 * @swagger
  
 * path:
 *    /getallocatedseats:    
 *        post:
 *          summary: Get all allocated seats
 *          tags: [SeatsAvailability] 
 *          security:
 *            - ApiKeyAuth: []
 *              
 *          responses:
 *            "200":
 *              description: seats addedd successfully.
 *            "404":
 *              description: Resource not found.
 *            
 */

  //****getallseats****
/**
 * @swagger
  
 * path:
 *    /getallseats:    
 *        post:
 *          summary: Get all seats
 *          tags: [SeatsAvailability] 
 *          security:
 *            - ApiKeyAuth: []
 *              
 *          responses:
 *            "200":
 *              description: seats addedd successfully.
 *            "404":
 *              description: Resource not found.
 *            
 */

const express = require('express');
const router = express.Router();
const service = require('./seatAllocation.service');

router.post('/addseats', service.addSeats);
router.post('/addseat', service.addSeat);
router.delete('/removeseat/:seatNo', service.removeSeat);
router.put('/updateseat/:seatNo', service.updateSeat);

router.put('/seatallocation/:seatNo/:empid', service.seatAllocation);
router.put('/editseatallocation/:seatNo/:empid', service.editSeatAllocation);
router.put('/removeseatallocation/', service.removeSeatAllocation);

router.get('/getavailableseats', service.getAvailableSeats);
router.get('/getallocatedseats', service.getAllocatedSeats);
router.get('/getallseats', service.getAllSeats);
router.get('/getallseats/:buildingName', service.getAllSeats);



router.get('/', (req, res) => {
    return res.send({ message: 'Testttttttttttttttttt' });
});

module.exports = router;