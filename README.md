# _Virtual Pets_

### _Epicodus: Java Week 4, Advanced Java Topics: Virtual Pets_

#### By _[**Caleb Stevenson**](https://github.com/CGrahamS) &amp; **Chance Neff**  [**Elysia Nason**](https://github.com/elysiaavery)_

## Description


## Specs

## Setup/Installation Requirements

* In your first terminal window:
  * Start postgres: `$ postgres`
* In your second terminal window:
  * Start psql: `$ psql`
  * Create database: `# CREATE DATABASE virtual_pets;`
* In your third terminal window:
  * Clone this repository to your desktop: `$ cd Desktop; git clone https://github.com/CGrahamS/virtual-pets`
  * Navigate to the hair-salon directory: `$ cd virtual-pets`
  * Create database schema from virtual_pets.sql: `$ psql virtual_pets < virtual_pets.sql`
* Back in your second terminal window:
  * Confirm the database has been restored correctly:
    * Connect to virtual_pets database: `# \c virtual_pets;`
    * Print out database tables: `# \dt;`
    <br>
    NOTE: You should see `stylists` and `clients` tables in the `virtual_pets` database.
* Back in your third terminal window:
  * Run the server: `$ gradle run`
* In the browser of your choosing, navigate to "localhost:4567" (tested in Chrome).

## Known Bugs

None

## Support and contact details

Caleb Stevenson: _cgrahamstevenson@gmail.com_
Chance Neff: _crneff84@gmail.com_
Elysia Nason: _elysia.avery@gmail.com_

## Technologies Used

_Java,
Spark,
Velocity,
SQL_

### License

This webpage is licensed under the GPL license.

Copyright &copy; 2016 **_Caleb Stevenson &amp; Chance Neff &amp; Elysia Nason_**
