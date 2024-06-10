# Student Payment System Backend

This is a backend service for a student payment system. It's built using Java, Spring Boot, and Maven.

## Features

- Upload payment details for students
- Retrieve all payments
- Retrieve payments by student code
- Retrieve a specific payment by ID
- Update the payment status

## Setup and Installation

1. Clone the repository
2. Navigate to the project directory
3. Run `mvn clean install` to build the project
4. Run `mvn spring-boot:run` to start the application

## API Endpoints

- `GET /payment/all` - Retrieve all payments
- `GET /payment/{id}` - Retrieve a specific payment by ID
- `GET /payment/paymentsByStudent/{code}` - Retrieve payments by student code
- `POST /payment/savePayment` - Upload payment details for a student
- `GET /payment/paymentFile/{paymentId}` - Retrieve the PDF document for a payment
- `PUT /payment/updateStatus/{paymentId}` - Update the payment status

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License

[MIT](https://choosealicense.com/licenses/mit/)
