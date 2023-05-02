## Project 2 - Multi-threaded Key-Value Store using RPC
## There are three java classes and one interface in this project.

## To start the program first the server needs to be started
## The user needs to enter the RMI registry port number which by default 1099
## The code then asks the user to enter the server port number which can be number in the range of 0 to 65535 (Eg: 1234)
## Next the Client class needs to be started
## The Client side code asks the user to enter the IP address which is by default localhost
## It then asks the user to enter the port number which again by default is 100 because Java Rmi runs on that port.
## The code then asks the user to enter the operation like PUT, GET or DELETE request
## The program then asks the user to enter the request. The hashmap that stores the key value pairs has pre-populated data.
## The program supports three requests and the user can make each of 5 (5 GET request or 5 PUT requests or 5 DELETE requests) :
## PUT : The user has to enter the request in the given format (Example : PUT key value). The request is seperated by spaces.
## GET : Even without entering values the user can fetch some details as the hashmap is pre-populated. If the value does not exist it gives key not found output. (Example : GET key)
## DELETE : The user can delete values from the hashmap (Example : DELETE key)
## Whenever the 5 PUT or GET or DELETE requests are fulfilled it shows the message in the CLI and continues till all the three operations got five of each request.
## If any request other that these are made it gives an invalid request output and tells you to make an appropriate request.

## SCREENSHOTS
## The project folder has two screenshots of the all the PUT, GET and DELETE operations made.
