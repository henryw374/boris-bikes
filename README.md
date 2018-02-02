# leyton-boris-bikes

An app that provides a JSON api showing the nearest 5 Boris-bike points around Leyton, and how many bikes there are available. 

Source data here: https://api.tfl.gov.uk/#BikePoint

## Usage

lein uberjar
java -jar leyton-boris-bikes-app.jar <port>

curl http://localhost:3000/bikes

## License

Copyright © 2018 Widd Industries

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
