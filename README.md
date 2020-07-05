FORMAT: 1A
HOST: localhost:8080/

# supermarket

Polls is a simple API allowing consumers to view polls and vote in them.

## Product [/product/{id}]

### Get a Product [GET]

+ Response 200 (application/json)

        {
            "barcode": "12345w6459113",
            "id": 6,
            "name": "Even more bananas againa",
            "category": "FRUITS",
            "vatTarrif": "LOW",
            "unit": "KG",
            "price": null,
            "brand": {
                "id": 2
            },
            "suppliers": [
                {
                    "id": 3
                },
                {
                    "id": 2
                }
            ]
        }
        
## Product Creation/Update [/product]

### Create a New Product [POST]

You may create your own product using this action. It takes a JSON
object containing the following properties, another JSON object containing a brand id, and a collection of JSON objects with the supplier(s) id(s).

+ barcode (string) - The product barcode
+ name (string) - The product name
+ category (number) - The product category
+ vatTarrif (number) - The product VAT category
+ unit (number) - The product unit of measurement category
+ price (number) - The product price
+ brand (JSON object) - JSON object representing the product brand id
+ suppliers (number) - JSON object representing a collection of product supplier(s) id(s)

+ Request (application/json)

        {
            "barcode": "123456789123",
            "name": "Even more bananas againa",
            "category": 2,
            "vatTarrif": 2,
            "unit": 2,
            "price": null,
            "brand": {
                "id": 2
            },
            "suppliers": [
                {
                    "id": 3
                },
                {
                    "id": 2
                }
            ]
        }

+ Response 201 (application/json)


    + Body

            {
                "barcode": "1234567890123",
                "id": 1,
                "name": "Bananas",
                "category": "FRUITS",
                "vatTarrif": "LOW",
                "unit": "KG",
                "price": null,
                "brand": {
                "id": 2
                },
                "suppliers": [
                    {
                        "id": 3
                    },
                    {
                        "id": 2
                    }
                ]
            }
            

### Update an Existing Product [PUT]

You may update an existing product using this action. It takes a JSON
object containing any of the following properties that need to be updated, another JSON object containing a brand id, and a collection of JSON objects with the supplier(s) id(s).

+ barcode (string) - The product barcode
+ name (string) - The product name
+ category (number) - The product category
+ vatTarrif (number) - The product VAT category
+ unit (number) - The product unit of measurement category
+ price (number) - The product price
+ brand (JSON object) - JSON object representing the product brand 
+ suppliers (number) - JSON object representing a collection of product supplier(s) id(s)

+ Request (application/json)

        {
            "barcode": "123456789123",
            "name": "Even more bananas againa",
            "category": 2,
            "vatTarrif": 2,
            "unit": 2,
            "price": null,
            "brand": {
                "id": 2
            },
            "suppliers": [
                {
                    "id": 3
                },
                {
                    "id": 2
                }
            ]
        }

+ Response 201 (application/json)


    + Body

            {
                "barcode": "1234567890123",
                "id": 1,
                "name": "Bananas",
                "category": "FRUITS",
                "vatTarrif": "LOW",
                "unit": "KG",
                "price": null,
                "brand": {
                "id": 2
                },
                "suppliers": [
                    {
                        "id": 3
                    },
                    {
                        "id": 2
                    }
                ]
            }