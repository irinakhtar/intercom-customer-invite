# intercom-customer-invite
## Required software

## How to build and run
```$xslt
mvn clean install
java -cp target\intercom-customer-invite-1.0-SNAPSHOT-shaded.jar com.intercom.Application <Input File> <Latitude> <Longitude>
```

## Example

```$xslt
java -cp target\intercom-customer-invite-1.0-SNAPSHOT-shaded.jar com.intercom.Application src\main\resources\customers.txt -6.257664 53.339428 D:\JavaProject\ou
tput.txt

```