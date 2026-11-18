#!/usr/bin/env bash

make_request(){
	local url=$1
	curl -X POST "$url" >/dev/null 2>&1
}

echo -n "Testing connection..."
if ! curl http://localhost:8080/vehicles >/dev/null 2>&1; then
	echo " failed!"
	echo "Is the server running on localhost:8080?"
	exit 1
fi

echo " success!"

echo "Generating customers..."
make_request "http://localhost:8080/customers?name=Anna&phone=123456789"
make_request "http://localhost:8080/customers?name=Erik&phone=987654321"
make_request "http://localhost:8080/customers?name=Lisa&phone=555123456"
make_request "http://localhost:8080/customers?name=Oscar&phone=111222333"

echo "Generating vehicles..."
make_request "http://localhost:8080/vehicles?registrationNumber=ABC123&brand=Volvo&model=V60&productionYear=2022"
make_request "http://localhost:8080/vehicles?registrationNumber=DEF456&brand=Volvo&model=XC60&productionYear=2021"
make_request "http://localhost:8080/vehicles?registrationNumber=GHI789&brand=Saab&model=9-3&productionYear=2008"
make_request "http://localhost:8080/vehicles?registrationNumber=JKL012&brand=BMW&model=320d&productionYear=2020"
make_request "http://localhost:8080/vehicles?registrationNumber=MNO345&brand=Toyota&model=Corolla&productionYear=2019"
make_request "http://localhost:8080/vehicles?registrationNumber=PQR678&brand=Saab&model=9-5&productionYear=2005"
make_request "http://localhost:8080/vehicles?registrationNumber=STU901&brand=Audi&model=A4&productionYear=2023"
make_request "http://localhost:8080/vehicles?registrationNumber=VWX234&brand=Mercedes&model=C220&productionYear=2021"

echo "Binding vehicles to customers..."
make_request "http://localhost:8080/addvehicletocustomer?customerId=1&vehicleId=1"
make_request "http://localhost:8080/addvehicletocustomer?customerId=1&vehicleId=2"
make_request "http://localhost:8080/addvehicletocustomer?customerId=2&vehicleId=3"
make_request "http://localhost:8080/addvehicletocustomer?customerId=2&vehicleId=4"
make_request "http://localhost:8080/addvehicletocustomer?customerId=2&vehicleId=7"
make_request "http://localhost:8080/addvehicletocustomer?customerId=3&vehicleId=5"
make_request "http://localhost:8080/addvehicletocustomer?customerId=3&vehicleId=6"
