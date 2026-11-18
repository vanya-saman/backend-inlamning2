
#!/usr/bin/env bash

BASE="http://localhost:8080"

set -e

request() {
    echo
    echo "========================================"
    echo "$1"
    echo "========================================"
    shift
    curl -sS "$@"
    echo
}

# 1. Create customer
request "Create customer" \
    -X POST "$BASE/customers?name=Anna&phone=123456789"

# 2. Get customer ID
request "Get customer ID" \
    "$BASE/customer-id?name=Anna"

# 3. Create vehicle
request "Create vehicle" \
    -X POST "$BASE/vehicles?registrationNumber=ABC123&brand=Volvo&model=V60&productionYear=2022"

# 4. Connect vehicle to customer
request "Connect vehicle to customer" \
    -X POST "$BASE/addvehicletocustomer?customerId=1&vehicleId=1"

# 5. List all customers and vehicles
request "List all customers and vehicles" \
    "$BASE/customers"

# 6. List all vehicles
request "List all vehicles" \
    "$BASE/vehicles"

# 7. List vehicles by brand
request "List Volvo vehicles" \
    "$BASE/vehicles-by-brand?brand=Volvo"

# 8. Update vehicle brand
request "Update vehicle brand to Saab" \
    -X PUT "$BASE/vehicles/1/brand?brand=Saab"

# 9. List registration numbers and brands
request "List registration numbers and brands" \
    "$BASE/vehicle-brand-info"

# 10. Verify updated vehicle
request "Verify updated vehicle" \
    "$BASE/vehicles"

echo
echo "========================================"
echo "All endpoint requests completed."
echo "========================================"
