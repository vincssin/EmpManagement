# **Assignment 3 – NewsAPI Database**

The Database interaction of the News API application using MongoDB

## Objective:
The Objective of this application is to understand the MongoDB to fetch and store the data.

### The estimated effort to complete this assignment is 3-4 hours

## Things to do
- Fork the boilerplate
- Clone the repo in your local directory
- Start coding in your local copy
- Push the code in git
- Submit the code to your mentor


## Expected Outcome:
By the end of the assignment you should be able to understand

- Working with MongoDB
- Using mongoose


## Boilerplate
[Nodejs Assignment 3](/services/gitlab/stack_nodejs/assignment3_newsapidb)

## Assignment:
    NewsDB:-
        - The news should be added into the database.
        - the news should be retrieved from the data base.
        - the news should have the loggedin user information as well.
        - use "api/v1/news" route
        - The data should be
            news:{
                newsId,
                newsheading,
                newsImage,
                newsdescription,
                newsAuther,
                newsSource,
                newsUser,
            }
        - Test the application using mocha and chai
