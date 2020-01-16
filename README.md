# intercom-customer-invite
This project develops a customer invite service for Intercom. The service finds the customers within 100kms from a given geo location. The followings are use while developing the project:
1) Gson: This maven dependency is used for parsing json strings
2) JUnit4: This maven dependency is used for writing unit test
3) Mockito: This maven dependency is used as a mocking framework while writing unit tests.

## Required software
1. JDK 1.8+
2. Maven 3.6+

## How to build and run
```$xslt
mvn clean install
java -cp target\intercom-customer-invite-1.0-SNAPSHOT-shaded.jar com.intercom.Application <input file> <Latitude> <Longitude> <output file>
```

## Example

```$xslt
java -cp target\intercom-customer-invite-1.0-SNAPSHOT-shaded.jar com.intercom.Application customers.txt 53.339428 -6.257664 output.txt
```

## How to use CustomerInviteService

```aidl
CustomerInviteService customerInviteService = new CustomerInviteServiceImpl(
                new CustomerFileParser(inputFile));
ArrayList<Customer> customersWithin100Km = customerInviteService
                .inviteCustomerWithin100Km(latitude, longitude);
```

## TODO list
1) Create GeoLocation class as a model to represent latitude and longitude for improving the readability to the users.
2) Write unit test for CustomerFileParser class and refractor if necessary.
3) Add builder pattern to build Customer object.