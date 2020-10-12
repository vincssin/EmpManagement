const expect = require('chai').expect;
const request = require('supertest');
const app = require('../app');

data = {
    source: "sourcedata,",
    title: "ExpressJs",
    description: "Expressjs use for creating Api's",
    url: "htttp://Expressjs.com",
    author: "vinoth",
    urlToImage: "img:20.jpg",
    publishedAt: "28-04-2020",
    content: "technology"
}
describe('News Api testing', function () {

    it('Should get all news, returning array of data', function (done) {

        request(app)
            .get('/api/v1/news/')
            .set('Accept', 'application/json')
            .expect('Content-Type', /json/)
            .then((response) => {
                // console.log('000000000: ' + response.status);
                expect(response.body.articles.length).gt(0);
            });
        done();
    });

    it('Should return one data', function (done) {

        request(app)
            .get('/api/v1/news/9a9ef8c1-bc9c-48d4-ac06-881888ebcea6')
            .set('Accept', 'application/json')
            .expect('Content-Type', /json/)
            .then((response) => {
                // console.log('000000000: ' + response.status);
                expect(response.body.articles.length).equals(1);
            });
        done();
    });


    it('Should get data not found message', function (done) {

        request(app)
            .get('/api/v1/news/aaaaa')
            .set('Accept', 'application/json')
            .expect('Content-Type', /json/)
            .then((response) => {
                // console.log('000000000: ' + response.status);
                expect(response.body.message, 'Data not found')
            });
        done();
    });
    

    it('Should get data added successfully message', function (done) {

        request(app)
            .post('/api/v1/news/')
            .send(data)
            .set('Accept', 'application/json')
            .expect('Content-Type', /json/)
            .then((response) => {
                // console.log('000000000: ' + response.body.message);
                expect(response.body.message, 'News addedd successfully')
            });
        done();
    });

    it('Should get empty data message', function (done) {

        request(app)
            .post('/api/v1/news/')
            .send({})
            .set('Accept', 'application/json')
            .expect('Content-Type', /json/)
            .then((response) => {
                // console.log('000000000: ' + response.body.message);
                expect(response.body.message, 'data body empty please check and try again')
            });
        done();
    });



    it('Should get News deleted successfully message', function (done) {

        request(app)
            .delete('/api/v1/news/9a9ef8c1-bc9c-48d4-ac06-881888ebcea6')
            .set('Accept', 'application/json')
            .expect('Content-Type', /json/)
            .then((response) => {
                expect(response.body.message, 'News deleted successfully')
            });
        done();
    });


    it('Should get News deleted successfully message', function (done) {

        request(app)
            .delete('/api/v1/news/aaaaaa')
            .set('Accept', 'application/json')
            .expect('Content-Type', /json/)
            .then((response) => {
                expect(response.body.message, 'Data not found')
            });
        done();
    });


});