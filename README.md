# [Rento](http://www.rento.nu)
[![Build Status](https://travis-ci.org/hansson/rento.png?branch=master)](https://travis-ci.org/hansson/rento)

Licensed under GPL-3, see license.txt for full license. Also check out https://tldrlegal.com/license/gnu-general-public-license-v3-(gpl-3) for a summary

This project is made to try to make it easier to find apartments to rent in Karlskrona, and hopefully
more cities in the future!

## Installation
* Fix template file set_envs.sh.template


## Cities with all landlords done or in issues
* Karlshamn
* Karlskrona
* Växjö
* 

## API

###Objects###

####Apartment####
Name          | type
------------- | -------------
mId           | int
mArea         | String
mCity         | String
mAddress      | String
mUrl          | String
mRooms        | Double
mSize         | int
mRent         | int
mIdentifier   | String (optional)
mLandlord     | String
mAdded        | Date
mStudent      | boolean

###Requests###

####POST - /api/apartment####
Get a single apartment from apartment id.

Parameters    | type
------------- | -------------
apartment     | int (ex. 4578)

__Response:__ Apartment json object

---

####GET - /api/apartments####
Get default apartments (all apartments in Karlskrona)

__Response:__ Json array of apartment objects

---

####POST - /api/apartments####
Get all apartments from a specific city.

Parameters    | type
------------- | -------------
city          | String (ex. Karlskrona), the "all"-keyword can be used to get a list of all apartments from every city

__Response:__ Json array of apartment objects

---
