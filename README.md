# leyton-boris-bikes

An app that provides a JSON api showing the nearest 5 Boris-bike points around Leyton, and how many bikes there are available at each. 

Source data here: https://api.tfl.gov.uk/#BikePoint

## Usage

lein uberjar

java -jar target/bikes-standalone.jar <port, defaults to 3000>

curl http://localhost:3000/bikes

curl -u user1:password http://localhost:3000/bikes-with-auth 

## License

Copyright © 2018 Widd Industries

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
