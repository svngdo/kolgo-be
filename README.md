# **Welcome to KOLgo!**

**Description**
**Tools**
- Spring Boot
- PostgreSQL 15
- pgAdmin4
## **Setup**
### Environment setup:
- Setup Java SDK: Download Java SDK [here](https://www.oracle.com/java/technologies/downloads/)
- Setup maven: follow step in the docs (Remember follow
  setup in README)
- Required setup: - Java JDK: 17 - Apache Maven: 3.8.6 -
  Postgresql: 15

### Environment variables setup:
- Create env.properties file in `src/main/resources`
- Set variables in env.properties, example:
```
### DB CONFIG ###
DB_HOST=localhost
DB_PORT=5432
DB_NAME=kolgo
DB_USERNAME=postgres
DB_PASSWORD=postgres

### SERVER CONFIG ###
SERVER_HOST=localhost
SERVER_PORT=8080
SERVER_CONTEXT_PATH=/api

### MAIL CONFIG ###
MAIL_HOST=your.smtp.host
MAIL_PORT=587
MAIL_USERNAME=yourstmpusername
MAIL_PASSWORD=yourstmppassword
MAIL_DISPLAY_NAME=KOLgo

### FILE CONFIG ###
FILE_RESOURCE_PATH=/your/picture/path
FILE_IMAGE_PATH=/your/image/path
FILE_ICON_PATH=/your/icon/path

### JWT CONFIG
JWT_SECRET=your-secret-key
JWT_ACCESS_TOKEN_EXP_MS=604800000
JWT_REFRESH_TOKEN_EXP_MS=604800000
JWT_VERIFY_ACCOUNT_EXPIRATION_MS=432000000
JWT_RESET_PASSWORD_EXPIRATION_MS=900000
JWT_EXPIRATION_DAY=7

### VNPAY ###
VNPAY_VERSION=2.1.0
VNPAY_PAYMENT_URL=https://sandbox.vnpayment.vn/paymentv2/vpcpay.html
VNPAY_MERCHANT_ID=YOURVNPAYMERCHANTID
VNPAY_HASH_SECRET=YOURVNPAYHASHSECRET
VNPAY_RETURN_URL=http://localhost:3000/vnpay/return
VNPAY_CANCEL_URL=http://localhost:8080/vnpay/cancel
```

### Run project

After setup everything for development you just need to run: <br>
$ `mvn spring-boot:run`

## **API Documentation**
http://localhost:8080/api/swagger-ui/index.html
