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



router.get('/', (req, res) => {
    return res.send({ message: 'Testttttttttttttttttt' });
});

module.exports = router;