# Backend Inlämning 2

En samling av REST-endpoints för en bilverkstad med bilar och kunder.

# Krav:

- Java 21+
- Curl

# Användning:

1. Kör `run.sh` för att starta server på port 8080.

2. `test-endpoints.sh` testar samtliga endpoints och visar respons.

3. `generate-data.sh` fyller minnesdatabasen genom REST anrop.

# Endpoints

- POST /customers - Skapa kund med parametrar `name` och `phone`.
- POST /vehicles - Skapa fordon med parametrar `registrationNumber` `brand` `model` `productionYear`.
- POST /addvehicletocustomer - Koppla fordon med kund, med parametrar `customerId` och `vehicleId`
- GET /customers - Lista alla kunder och deras bilar.
- GET /vehicles - Lista alla bilar med regnummer och märke.
- GET /vehicles-by-brand - Lista alla bilar av ett visst märke med parameter `brand`.
- GET /customer-id - Sök kund med parameter `name` och få kund-id för första matchning.
- GET /vehicle-brand-info - Lista alla bilar med regnummer och märken
- PUT /vehicles/{vehicleId}/brand - Uppdatera märke med parameter `brand`
